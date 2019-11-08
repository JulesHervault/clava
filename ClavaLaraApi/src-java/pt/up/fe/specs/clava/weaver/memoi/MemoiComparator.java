package pt.up.fe.specs.clava.weaver.memoi;

import java.util.Comparator;
import java.util.List;

/**
 * Copyright 2019 SPeCS.
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

public class MemoiComparator {

    public static Comparator<MergedMemoiEntry> getMeanComparator(int total) {

        return (MergedMemoiEntry e1, MergedMemoiEntry e2) -> {

            double mean1 = mean(e1.getCounter(), total);
            double mean2 = mean(e2.getCounter(), total);

            if (mean1 > mean2) {
                return 1;
            } else if (mean1 < mean2) {
                return -1;
            } else {
                return 0;
            }
        };
    }

    private static double mean(List<Integer> c1, int t) {

        return c1.stream().reduce(0, Integer::sum) / t;
    }
}
