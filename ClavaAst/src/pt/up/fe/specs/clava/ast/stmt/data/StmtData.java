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

package pt.up.fe.specs.clava.ast.stmt.data;

import pt.up.fe.specs.clava.ast.ClavaData;

/**
 * @deprecated
 * @author JoaoBispo
 *
 */
@Deprecated
public class StmtData extends ClavaData {

    // public static StmtData newInstance() {
    // return new StmtData(ClavaData.newInstance(id));
    // }

    public StmtData(ClavaData clavaData) {
        super(clavaData);
    }

    public StmtData(StmtData data) {
        super(data);

        setData(data);
    }

    public StmtData() {
        super(new ClavaData());
    }

    public StmtData setData(StmtData data) {
        return this;
    }

    // public StmtData(ExprDataV2 data) {
    // this(data);
    // }

    @Override
    public String toString() {

        return toString(super.toString(), "");
    }
}
