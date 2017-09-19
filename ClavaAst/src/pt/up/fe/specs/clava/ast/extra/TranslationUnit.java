/**
 * Copyright 2016 SPeCS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License. under the License.
 */

package pt.up.fe.specs.clava.ast.extra;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import pt.up.fe.specs.clava.ClavaNode;
import pt.up.fe.specs.clava.ClavaNodeInfo;
import pt.up.fe.specs.clava.SourceRange;
import pt.up.fe.specs.clava.ast.ClavaNodeFactory;
import pt.up.fe.specs.clava.ast.decl.Decl;
import pt.up.fe.specs.clava.ast.decl.FunctionDecl;
import pt.up.fe.specs.clava.ast.decl.IncludeDecl;
import pt.up.fe.specs.clava.ast.decl.VarDecl;
import pt.up.fe.specs.clava.ast.stmt.DeclStmt;
import pt.up.fe.specs.clava.utils.IncludeManager;
import pt.up.fe.specs.util.SpecsIo;
import pt.up.fe.specs.util.lazy.Lazy;
import pt.up.fe.specs.util.treenode.NodeInsertUtils;
import pt.up.fe.specs.util.utilities.StringLines;

/**
 * Represents a source file.
 *
 * @author JoaoBispo
 *
 */
public class TranslationUnit extends ClavaNode {

    private static final Set<String> HEADER_EXTENSIONS = new HashSet<>(Arrays.asList("h", "hpp"));
    private static final Set<String> CXX_EXTENSIONS = new HashSet<>(Arrays.asList("cpp", "hpp"));

    private final String filename;
    private final String path;

    private final IncludeManager includes;

    private final Lazy<Boolean> isCxxUnit;

    public TranslationUnit(String filename, String relativePath, Collection<Decl> declarations) {
        super(createInfo(new ArrayList<>(declarations), relativePath + filename), declarations);

        this.filename = filename;
        path = relativePath;

        List<IncludeDecl> includesList = declarations.stream()
                .filter(decl -> decl instanceof IncludeDecl)
                .map(include -> (IncludeDecl) include)
                .collect(Collectors.toList());

        includes = new IncludeManager(includesList, this);

        this.isCxxUnit = Lazy.newInstance(this::testIsCXXUnit);
    }

    public static ClavaNodeInfo createInfo(List<Decl> declarations, String filepath) {
        if (declarations.isEmpty()) {
            return ClavaNodeInfo.undefinedInfo(new SourceRange(filepath, 1, 1, 1, 1));
        }

        Decl firstDecl = declarations.get(0);
        int startLine = firstDecl.getLocation().getStartLine();
        int startCol = firstDecl.getLocation().getStartCol();
        int endLine = firstDecl.getLocation().getEndLine();
        int endCol = firstDecl.getLocation().getEndCol();

        for (int i = 1; i < declarations.size(); i++) {
            Decl decl = declarations.get(i);

            // If line came first, replace
            if (decl.getLocation().getStartLine() < startLine) {
                startLine = decl.getLocation().getStartLine();
                startCol = decl.getLocation().getStartCol();
            }
            // If the same line, use smaller start column
            else if (decl.getLocation().getStartLine() == startLine) {
                startCol = Math.min(decl.getLocation().getStartCol(), startCol);
            }

            // If last line came last, use it
            if (decl.getLocation().getEndLine() > endLine) {
                endLine = decl.getLocation().getEndLine();
                endCol = decl.getLocation().getEndCol();
            }

            else if (decl.getLocation().getEndLine() == endLine) {
                endCol = Math.max(decl.getLocation().getEndCol(), endCol);
            }

        }

        return ClavaNodeInfo.undefinedInfo(new SourceRange(filepath, startLine, startCol, endLine, endCol));
    }

    @Override
    protected ClavaNode copyPrivate() {
        return new TranslationUnit(filename, path, Collections.emptyList());
    }

    @Override
    public String getCode() {

        String body = getChildrenStream()
                // String body = getDecls().stream()
                // .map(child -> child.getCode())
                .map(this::getChildCode)
                .collect(Collectors.joining(ln()));

        // If header file, add include guards
        if (isHeaderFile()) {
            body = addIncludeGuards(body);
        }

        return body;
    }

    private String getChildCode(ClavaNode decl) {
        String code = decl.getCode();
        String commentCode = decl.getInlineCommentsCode();

        // If not empty, add comment to first line
        if (!commentCode.isEmpty()) {
            List<String> lines = StringLines.getLines(code);

            // Find first non-empty line
            int index = IntStream.range(0, lines.size())
                    .filter(i -> !lines.get(i).trim().isEmpty())
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Expected to find non-empty code: " + decl.getCode()));
            lines.set(index, lines.get(index) + commentCode);
            code = lines.stream().collect(Collectors.joining(ln()));
        }

        return code;
    }

    private String addIncludeGuards(String body) {
        StringBuilder builder = new StringBuilder();

        // Replace '.' with '_' and surround id with "_", to avoid problems such as the id starting with a number
        String filenameId = "_" + filename.replace(".", "_") + "_";

        // Other sanitizations
        filenameId = filenameId.replace("-", "_");

        // Prepend a few characters to guarantee uniqueness
        // filenameId = filenameId + UUID.randomUUID().toString().substring(0, 3);
        filenameId = filenameId.toUpperCase();

        builder.append("#ifndef ").append(filenameId).append(ln());
        builder.append("#define ").append(filenameId).append(ln() + ln());
        builder.append(body);
        builder.append(ln() + "#endif" + ln());

        return builder.toString();
    }

    public String getFilename() {
        return filename;
    }

    public String getFolderpath() {
        return path;
    }

    public String getFilepath() {
        return getLocation().getFilepath();
    }

    public File getFile() {
        return new File(path, filename);
    }

    public boolean isHeaderFile() {
        String extension = SpecsIo.getExtension(filename);

        return HEADER_EXTENSIONS.contains(extension.toLowerCase());
    }

    public boolean isCXXUnit() {
        return isCxxUnit.get();
    }

    private boolean testIsCXXUnit() {
        // 1) Check if file has CXX extension.
        // Cannot test for C extensions because you can have C++ code inside .c files, for instance.
        if (CXX_EXTENSIONS.contains(SpecsIo.getExtension(filename))) {
            return true;
        }

        // 2) Check if file has CXX-exclusive nodes
        return getDescendantsStream()
                // .filter(node -> node.getNodeName().startsWith("CXX"))
                .filter(ClavaNode::isCxxNode)
                .findFirst()
                .isPresent();
    }

    @Override
    public String toContentString() {
        return super.toContentString() + filename + " ";
    }

    public IncludeManager getIncludes() {
        return includes;
    }

    /**
     * Helper method which sets 'isAngled' to false.
     * 
     * @param translationUnit
     */
    public void addInclude(TranslationUnit translationUnit) {
        addInclude(translationUnit, false);
    }

    /**
     * Adds the given translation unit as an include of this translation unit.
     * 
     * @param translationUnit
     */
    private void addInclude(TranslationUnit translationUnit, boolean isAngled) {

        // Get relative path to include the file in this file
        String relativePath = SpecsIo.getRelativePath(translationUnit.getFile(), getFile());
        addInclude(relativePath, isAngled);
    }

    public void addInclude(String includeName, boolean isAngled) {
        getIncludes().addInclude(ClavaNodeFactory.include(includeName, isAngled));
    }

    public void addInclude(IncludeDecl include) {
        addInclude(include.getInclude().getInclude(), include.getInclude().isAngled());
    }

    public void addDeclaration(VarDecl varDecl) {
        // Find insertion point. Insert before first function declaration
        Optional<ClavaNode> firstFunction = getChildrenStream()
                .filter(child -> child instanceof FunctionDecl)
                .findFirst();

        DeclStmt stmt = ClavaNodeFactory.declStmt(ClavaNodeInfo.undefinedInfo(), Arrays.asList(varDecl));

        if (firstFunction.isPresent()) {
            NodeInsertUtils.insertBefore(firstFunction.get(), stmt);
            return;
        }

        // Add at the end of the translation unit
        addChild(getNumChildren(), stmt);

    }
}
