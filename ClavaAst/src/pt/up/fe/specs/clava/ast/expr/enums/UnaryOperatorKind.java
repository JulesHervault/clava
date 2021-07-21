/**
 * Copyright 2018 SPeCS.
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

package pt.up.fe.specs.clava.ast.expr.enums;

import java.util.EnumSet;
import java.util.Set;

import pt.up.fe.specs.clava.ClavaLog;
import pt.up.fe.specs.util.enums.EnumHelperWithValue;
import pt.up.fe.specs.util.lazy.Lazy;
import pt.up.fe.specs.util.providers.StringProvider;

public enum UnaryOperatorKind implements StringProvider {
    PostInc,
    PostDec,
    PreInc,
    PreDec,
    AddrOf,
    Deref,
    Plus,
    Minus,
    Not,
    LNot,
    Real,
    Imag,
    Extension,
    Coawait;

    private static final Lazy<EnumHelperWithValue<UnaryOperatorKind>> ENUM_HELPER = EnumHelperWithValue
            .newLazyHelperWithValue(UnaryOperatorKind.class);

    private static final Set<UnaryOperatorKind> OPS_WITH_SPACE = EnumSet.of(Real, Imag, Extension, Coawait);

    public static EnumHelperWithValue<UnaryOperatorKind> getEnumHelper() {
        return ENUM_HELPER.get();
    }

    public boolean isBitwise() {
        return this == Not;
    }
    // final String op;
    //
    // private UnaryOperatorKind(String op) {
    // this.op = op;
    // }

    public String getCode() {
        switch (this) {
        case PostInc:
            return "++";
        case PostDec:
            return "--";
        case PreInc:
            return "++";
        case PreDec:
            return "--";
        case AddrOf:
            return "&";
        case Deref:
            return "*";
        case Plus:
            return "+";
        case Minus:
            return "-";
        case Not:
            return "~";
        case LNot:
            return "!";
        case Real:
            return "__real__";
        case Imag:
            return "__imag__ ";
        case Extension:
            return "__extension__";
        case Coawait:
            return "co_await";
        default:
            // Using debug level to not show messages every time the op map is built
            ClavaLog.debug("Code not implemented for unary operator '" + this + "'");
            return "<UNDEFINED_UNARY_OP>";
        // throw new NotImplementedException(this);
        }
        // return op;
    }

    public boolean requiresSpace() {
        return OPS_WITH_SPACE.contains(this);
    }

    public String getName() {
        return name().toLowerCase();
    }

    @Override
    public String getString() {
        return getCode();
        // return op;
    }

}