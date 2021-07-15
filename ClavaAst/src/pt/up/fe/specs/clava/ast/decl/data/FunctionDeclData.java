/**
 * Copyright 2017 SPeCS.
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

package pt.up.fe.specs.clava.ast.decl.data;

import java.util.Collections;
import java.util.List;

import pt.up.fe.specs.clava.ast.attr.OpenCLKernelAttr;
import pt.up.fe.specs.clava.ast.decl.enums.ExceptionType;
import pt.up.fe.specs.clava.ast.decl.enums.StorageClass;
import pt.up.fe.specs.clava.ast.decl.enums.TemplateKind;
import pt.up.fe.specs.clava.ast.extra.TemplateArgument;

/**
 * @deprecated
 * @author JoaoBispo
 *
 */
@Deprecated
public class FunctionDeclData {

    private static final long NULL_EXCEPT_ADDRESS = 0;

    public static long getNullExceptAddress() {
        return NULL_EXCEPT_ADDRESS;
    }

    private final StorageClass storageClass;
    private final boolean isInline;
    private final boolean isVirtual;
    private final boolean isModulePrivate;
    private final boolean isPure;
    private final boolean isDelete;
    private final ExceptionType exceptionSpecifier;
    private final long exceptionAddress;
    private final List<TemplateArgument> templateArguments;
    private final OpenCLKernelAttr openClKernelAttr;
    private final TemplateKind templateKind;

    public FunctionDeclData() {
        this(StorageClass.None, false, false, false, false, false, ExceptionType.NONE, -1, Collections.emptyList(),
                null, TemplateKind.NON_TEMPLATE);
    }

    public FunctionDeclData(StorageClass storageClass, boolean isInline, boolean isVirtual, boolean isModulePrivate,
            boolean isPure, boolean isDelete, ExceptionType exceptionSpecifier, long exceptionAddress,
            List<TemplateArgument> templateArguments, OpenCLKernelAttr openClKernelAttr, TemplateKind templateKind) {

        this.storageClass = storageClass;
        this.isInline = isInline;
        this.isVirtual = isVirtual;
        this.isModulePrivate = isModulePrivate;
        this.isPure = isPure;
        this.isDelete = isDelete;
        this.exceptionSpecifier = exceptionSpecifier;
        this.exceptionAddress = exceptionAddress;
        this.templateArguments = templateArguments;
        this.openClKernelAttr = openClKernelAttr;
        this.templateKind = templateKind;
    }

    public StorageClass getStorageClass() {
        return storageClass;
    }

    public boolean isInline() {
        return isInline;
    }

    public boolean isVirtual() {
        return isVirtual;
    }

    public boolean isModulePrivate() {
        return isModulePrivate;
    }

    public boolean isPure() {
        return isPure;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public ExceptionType getExceptionSpecifier() {
        return exceptionSpecifier;
    }

    public long getExceptionAddress() {
        return exceptionAddress;
    }

    public boolean isStatic() {
        return getStorageClass() == StorageClass.Static;
    }

    public boolean hasOpenCLKernelAttr() {
        return openClKernelAttr != null;
    }

    public TemplateKind getTemplateKind() {
        return templateKind;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("StorageClass: " + storageClass);
        string.append(", isInline: " + isInline);
        string.append(", isVirtual: " + isVirtual);
        string.append(", isModulePrivate: " + isModulePrivate);
        string.append(", isPure: " + isPure);
        string.append(", isDelete: " + isDelete);
        string.append(", ExceptionType: " + exceptionSpecifier);
        string.append(", exceptionAddress: " + exceptionAddress);
        string.append(", Template Arguments: " + templateArguments);
        string.append(", Template Kind: " + templateKind);

        return string.toString();
    }

}
