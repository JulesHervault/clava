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

package pt.up.fe.specs.clava;

import pt.up.fe.specs.clava.ast.stmt.WrapperStmt;
import pt.up.fe.specs.util.treenode.transform.TransformQueue;
import pt.up.fe.specs.util.treenode.transform.TransformResult;
import pt.up.fe.specs.util.treenode.transform.TransformRule;

public interface ClavaRule extends TransformRule<ClavaNode, TransformResult> {

    default TransformResult empty() {
        return TransformResult.empty();
    }

    default void delete(ClavaNode node, TransformQueue<ClavaNode> queue) {
        if (!node.hasParent()) {
            queue.delete(node);
            return;
        }

        var nodeToDelete = node.getParent() instanceof WrapperStmt ? node.getParent() : node;
        queue.delete(nodeToDelete);
    }

}
