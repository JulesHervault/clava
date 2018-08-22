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

package pt.up.fe.specs.cxxweaver.tests;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import pt.up.fe.specs.clava.language.Standard;
import pt.up.fe.specs.cxxweaver.ClavaWeaverTester;
import pt.up.fe.specs.util.SpecsSystem;

public class CTest {

    @BeforeClass
    public static void setupOnce() {
        SpecsSystem.programStandardInit();
        ClavaWeaverTester.clean();
    }

    @After
    public void tearDown() {
        ClavaWeaverTester.clean();
    }

    private static ClavaWeaverTester newTester() {
        return new ClavaWeaverTester("clava/test/weaver/", Standard.C99)
                .setResultPackage("c/results")
                .setSrcPackage("c/src");
    }

    @Test
    public void testLoop() {
        newTester().test("Loop.lara", "loop.c");
    }

    @Test
    public void testReplaceCallWithStmt() {
        newTester().test("ReplaceCallWithStmt.lara", "ReplaceCallWithStmt.c");
    }

    @Test
    public void testInsertsLiteral() {
        newTester().test("InsertsLiteral.lara", "inserts.c");
    }

    @Test
    public void testInsertsJp() {
        newTester().test("InsertsJp.lara", "inserts.c");
    }

    @Test
    public void testClone() {
        newTester().test("Clone.lara", "clone.c");
    }

    @Test
    public void testAddGlobal() {
        newTester().test("AddGlobal.lara", "add_global_1.c", "add_global_2.c");
    }

    @Test
    public void testExpressions() {
        newTester().test("Expressions.lara", "expressions.c");
    }

    @Test
    public void testDijkstra() {
        newTester().setCheckWovenCodeSyntax(false).test("Dijkstra.lara", "dijkstra.c");
        newTester().setCheckWovenCodeSyntax(false).test("Dijkstra.lara", "dijkstra.c");
    }

    @Test
    public void testWrap() {
        newTester().test("Wrap.lara", "wrap.c", "wrap.h");
    }

    @Test
    public void testVarrefInWhile() {
        newTester().test("VarrefInWhile.lara", "varref_in_while.c");
    }

    @Test
    public void testInline() {
        newTester().test("Inline.lara", "inline.c", "inline_utils.h", "inline_utils.c");
    }

    @Test
    public void testSetType() {
        newTester().test("SetType.lara", "set_type.c");
    }

    @Test
    public void testDetach() {
        newTester().test("Detach.lara", "detach.c");
    }

    @Test
    public void testInlineNasLu() {
        newTester().test("InlineNasLu.lara", "inline_nas_lu.c");
    }

    @Test
    public void testInlineNasFt() {
        newTester().test("InlineNasFt.lara", "inline_nas_ft.c");
    }

}
