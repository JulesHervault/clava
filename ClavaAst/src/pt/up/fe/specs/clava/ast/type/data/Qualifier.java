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

package pt.up.fe.specs.clava.ast.type.data;

import java.util.Locale;

import pt.up.fe.specs.clava.ast.extra.App;

public enum Qualifier {

    CONST,
    RESTRICT,
    VOLATILE,
    GLOBAL;

    public String getCode() {
        if (this == RESTRICT) {
            if (App.getCurrentStandard() != null && App.getCurrentStandard().isCxx()) {
                return "__restrict__";
            }
        }

        if (this == GLOBAL) {
            return "__global";
        }

        return name().toLowerCase(Locale.UK);
    }
}
