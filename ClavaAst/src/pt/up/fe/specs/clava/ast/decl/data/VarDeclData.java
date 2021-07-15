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

package pt.up.fe.specs.clava.ast.decl.data;

import java.util.Optional;

import org.suikasoft.jOptions.Interfaces.DataStore;

import com.google.common.base.Preconditions;

import pt.up.fe.specs.clava.ast.decl.VarDecl;
import pt.up.fe.specs.clava.ast.decl.enums.InitializationStyle;
import pt.up.fe.specs.clava.ast.decl.enums.StorageClass;
import pt.up.fe.specs.clava.language.TLSKind;

/**
 * @deprecated
 * @author JoaoBispo
 *
 */
@Deprecated
public class VarDeclData {

    public StorageClass getStorageClass() {
        return storageClass;
    }

    public TLSKind getTlsKind() {
        return tlsKind;
    }

    public boolean isModulePrivate() {
        return isModulePrivate;
    }

    private final StorageClass storageClass;
    private final TLSKind tlsKind;

    private final boolean isModulePrivate;
    private final boolean isNrvo;

    private InitializationStyle initKind;

    private final DataStore varDeclData2;

    public VarDeclData() {
        this(StorageClass.None, TLSKind.NONE, false, false, InitializationStyle.NO_INIT, false);
    }

    public VarDeclData(StorageClass storageClass, TLSKind tlsKind, boolean isModulePrivate, boolean isNrvo,
            InitializationStyle initKind, DataStore varDeclData2) {

        this.storageClass = storageClass;
        this.tlsKind = tlsKind;
        this.isModulePrivate = isModulePrivate;
        this.isNrvo = isNrvo;
        this.initKind = initKind;
        this.varDeclData2 = varDeclData2;
    }

    /**
     * @deprecated Use construtor that accepts VarDeclDumperInfo, or a future constructor that is simpler
     * @param storageClass
     * @param tlsKind
     * @param isModulePrivate
     * @param isNrvo
     * @param initKind
     * @param isConstexpr
     */
    @Deprecated
    public VarDeclData(StorageClass storageClass, TLSKind tlsKind, boolean isModulePrivate, boolean isNrvo,
            InitializationStyle initKind, boolean isConstexpr) {

        Preconditions.checkArgument(isConstexpr == false, "This constructor only works if 'isConstexpr' is false");

        this.storageClass = storageClass;
        this.tlsKind = tlsKind;
        this.isModulePrivate = isModulePrivate;
        this.isNrvo = isNrvo;
        this.initKind = initKind;
        this.varDeclData2 = DataStore.newInstance("VarDeclData");
    }

    public VarDeclData copy() {
        return new VarDeclData(storageClass, tlsKind, isModulePrivate, isNrvo, initKind, varDeclData2);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        string.append("StorageClass:").append(storageClass);
        string.append(", TLSKind:").append(tlsKind);
        string.append(", isModulePrivate:").append(isModulePrivate);
        string.append(", isNrvo:").append(isNrvo);
        string.append(", init:").append(initKind);
        string.append(", vardecl data v2:").append(varDeclData2);

        return string.toString();
    }

    public InitializationStyle getInitKind() {
        return initKind;
    }

    // public boolean isConstexpr() {
    // return isConstexpr;
    // }

    public void setInitKind(InitializationStyle initKind) {
        this.initKind = initKind;
    }

    public boolean isNrvo() {
        return isNrvo;
    }

    /**
     * @deprecated use getVarDeclDataV2() instead. Will throw an exception if used.
     * @return
     */
    @Deprecated
    public Optional<VarDeclDumperInfo> getVarDeclDumperInfo() {
        throw new RuntimeException(
                "VarDeclData.getVarDeclDumperInfo() is deprecated, please use VarDeclData.getVarDeclDataV2()");
        // return Optional.ofNullable(varDeclDumperInfo);
    }

    // public VarDeclDataV2 getVarDeclDataV2() {
    // return varDeclData2;
    // }

    /**
     * @deprecated use hasVarDeclV2() instead. Will throw an exception if used.
     * @return
     */
    @Deprecated
    public boolean hasVarDeclDumperInfo() {
        throw new RuntimeException(
                "VarDeclData.hasVarDeclDumperInfo() is deprecated, please use VarDeclData.hasVarDeclV2()");
        // return varDeclDumperInfo != null;
    }

    public boolean hasVarDeclV2() {
        return varDeclData2 != null;
    }

    public boolean isConstexpr() {
        return varDeclData2.get(VarDecl.IS_CONSTEXPR);
        // return getVarDeclDumperInfo().map(data -> data.isConstexpr()).orElse(false);
    }

    public boolean hasGlobalStorage() {
        return varDeclData2.get(VarDecl.HAS_GLOBAL_STORAGE);
        // return getVarDeclDumperInfo().map(data -> data.hasGlobalStorage()).orElse(false);
    }

    public boolean isStaticDataMember() {
        return varDeclData2.get(VarDecl.IS_STATIC_DATA_MEMBER);
        // return getVarDeclDumperInfo().map(data -> data.isStaticDataMember()).orElse(false);
    }

    public boolean isOutOfLine() {
        return varDeclData2.get(VarDecl.IS_OUT_OF_LINE);
        // return getVarDeclDumperInfo().map(data -> data.isOutOfLine()).orElse(false);
    }

    public Optional<String> getQualifiedName() {
        String qualifiedPrefix = varDeclData2.get(VarDecl.QUALIFIED_PREFIX);
        String qualifiedName = qualifiedPrefix.isEmpty() ? varDeclData2.get(VarDecl.DECL_NAME)
                : qualifiedPrefix + "::" + varDeclData2.get(VarDecl.DECL_NAME);
        return Optional.of(qualifiedName);
        // return Optional.of(varDeclData2.get(VarDecl.QUALIFIED_NAME));
        // return getVarDeclDumperInfo().map(data -> data.getQualifiedName());
    }
}
