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

package pt.up.fe.specs.clava.parsing.pragma;

import java.util.HashMap;
import java.util.Map;

import pt.up.fe.specs.clava.ast.pragma.Pragma;
import pt.up.fe.specs.clava.context.ClavaContext;
import pt.up.fe.specs.util.stringparser.StringParser;
import pt.up.fe.specs.util.stringparser.StringParsers;

public class LaraPragmaParser implements PragmaParser {

    private static final Map<String, PragmaParser> LARA_PARSERS;
    static {
        LARA_PARSERS = new HashMap<>();
        LARA_PARSERS.put("marker", LaraPragmaParser::marker);
        LARA_PARSERS.put("tag", LaraPragmaParser::tag);
    }

    @Override
    public Pragma parse(StringParser parser, ClavaContext context) {

        String contents = parser.toString();

        // Check LARA pragma type
        String laraType = parser.apply(StringParsers::parseWord);
        PragmaParser laraParser = LARA_PARSERS.get(laraType.toLowerCase());

        if (laraParser != null) {
            Pragma pragma = laraParser.parse(parser, context);

            return pragma;
        }

        throw new RuntimeException("LARA pragma not supported: #pragma lara" + contents);
    }

    private static Pragma marker(StringParser contents, ClavaContext context) {
        // Get marker id
        String markerId = contents.apply(StringParsers::parseWord);
        return context.getFactory().laraMarkerPragma(markerId);
        // return new LaraMarkerPragma(markerId, context);
    }

    private static Pragma tag(StringParser contents, ClavaContext context) {

        String refId = contents.apply(StringParsers::parseWord);
        return context.getFactory().laraTagPragma(refId);
        // return new LaraTagPragma(refId, context);
    }

}
