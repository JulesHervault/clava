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

package pt.up.fe.specs.clava.ast.omp;

import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.ALIGNED;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.COLLAPSE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.COPYIN;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.COPYPRIVATE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.DEFAULT;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.DEFAULTMAP;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.DEPEND;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.DEVICE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.DIST_SCHEDULE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.FINAL;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.FIRSTPRIVATE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.FROM;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.GRAINSIZE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.IF;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.INBRANCH;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.IS_DEVICE_PTR;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.LASTPRIVATE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.LINEAR;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.LINK;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.MAP;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.MERGEABLE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.NOGROUP;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.NOTINBRANCH;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.NOWAIT;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.NUM_TASKS;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.NUM_TEAMS;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.NUM_THREADS;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.PRIORITY;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.PRIVATE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.PROC_BIND;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.REDUCTION;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.SAFELEN;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.SCHEDULE;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.SHARED;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.SIMDLEN;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.THREADS;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.THREAD_LIMIT;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.TO;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.UNIFORM;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.UNTIED;
import static pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind.USE_DEVICE_PTR;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import pt.up.fe.specs.clava.ast.omp.clauses.OmpClauseKind;
import pt.up.fe.specs.util.enums.EnumHelperWithValue;
import pt.up.fe.specs.util.lazy.Lazy;
import pt.up.fe.specs.util.lazy.ThreadSafeLazy;
import pt.up.fe.specs.util.providers.StringProvider;
import pt.up.fe.specs.util.stringparser.ParserResult;
import pt.up.fe.specs.util.utilities.StringSlice;

public enum OmpDirectiveKind implements StringProvider {

    FOR(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, LINEAR, REDUCTION, SCHEDULE, COLLAPSE, OmpClauseKind.ORDERED, NOWAIT),
    SECTIONS(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, REDUCTION, NOWAIT),
    SECTION,
    SINGLE(PRIVATE, FIRSTPRIVATE, COPYPRIVATE, NOWAIT),
    SIMD(SAFELEN, SIMDLEN, LINEAR, ALIGNED, PRIVATE, LASTPRIVATE, REDUCTION, COLLAPSE),
    DECLARE_SIMD(SIMDLEN, LINEAR, ALIGNED, UNIFORM, INBRANCH, NOTINBRANCH),
    FOR_SIMD(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, LINEAR, REDUCTION, SCHEDULE, COLLAPSE, OmpClauseKind.ORDERED, NOWAIT,
            SAFELEN, SIMDLEN, ALIGNED),
    TARGET(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND),
    TARGET_DATA(IF, DEVICE, MAP, USE_DEVICE_PTR),
    TARGET_UPDATE(IF, DEVICE, NOWAIT, DEPEND, TO, FROM),
    DECLARE_TARGET(TO, LINK),
    END_DECLARE_TARGET(TO, LINK),
    TEAMS(NUM_TEAMS, THREAD_LIMIT, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, REDUCTION),
    DISTRIBUTE(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, COLLAPSE, DIST_SCHEDULE),
    DISTRIBUTE_SIMD(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, SAFELEN, SIMDLEN, LINEAR, ALIGNED,
            REDUCTION),
    DISTRIBUTE_PARALLEL_FOR(PRIVATE, LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, IF, NUM_THREADS, DEFAULT,
            FIRSTPRIVATE, SHARED, COPYIN, REDUCTION, PROC_BIND, LINEAR, SCHEDULE, OmpClauseKind.ORDERED),
    DISTRIBUTE_PARALLEL_FOR_SIMD(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, IF, NUM_THREADS, DEFAULT,
            SHARED, COPYIN, REDUCTION, PROC_BIND, LINEAR, SCHEDULE, OmpClauseKind.ORDERED),
    PARALLEL_FOR(IF, NUM_THREADS, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, COPYIN, REDUCTION, PROC_BIND,
            LASTPRIVATE, LINEAR, REDUCTION, SCHEDULE, COLLAPSE, OmpClauseKind.ORDERED),
    PARALLEL_SECTIONS(IF, NUM_THREADS, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, COPYIN, REDUCTION, PROC_BIND,
            LASTPRIVATE),
    PARALLEL(IF, NUM_THREADS, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, COPYIN, REDUCTION, PROC_BIND),
    PARALLEL_FOR_SIMD(IF, NUM_THREADS, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, COPYIN, REDUCTION, PROC_BIND,
            LASTPRIVATE, LINEAR, SCHEDULE, COLLAPSE, OmpClauseKind.ORDERED,
            SAFELEN, SIMDLEN, ALIGNED),
    TARGET_TEAMS(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND, NUM_TEAMS,
            THREAD_LIMIT, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, REDUCTION),
    TEAMS_DISTRIBUTE(NUM_TEAMS, THREAD_LIMIT, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, REDUCTION, LASTPRIVATE, COLLAPSE,
            DIST_SCHEDULE),
    TEAMS_DISTRIBUTE_SIMD(NUM_TEAMS, THREAD_LIMIT, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, REDUCTION,
            LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, SAFELEN, SIMDLEN, LINEAR, ALIGNED),
    TARGET_TEAMS_DISTRIBUTE(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND,
            NUM_TEAMS, THREAD_LIMIT, DEFAULT, SHARED, REDUCTION, LASTPRIVATE, COLLAPSE,
            DIST_SCHEDULE),
    TARGET_TEAMS_DISTRIBUTE_SIMD(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND,
            NUM_TEAMS, THREAD_LIMIT, DEFAULT, SHARED, REDUCTION,
            LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, SAFELEN, SIMDLEN, LINEAR, ALIGNED),
    TEAMS_DISTRIBUTE_PARALLEL_FOR(NUM_TEAMS, THREAD_LIMIT, DEFAULT, PRIVATE, FIRSTPRIVATE, SHARED, REDUCTION,
            LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, IF, NUM_THREADS,
            COPYIN, PROC_BIND, LINEAR, SCHEDULE, OmpClauseKind.ORDERED),
    TEAMS_DISTRIBUTE_PARALLEL_FOR_SIMD(PRIVATE, FIRSTPRIVATE, LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, IF, NUM_THREADS,
            DEFAULT, SHARED, COPYIN, REDUCTION, PROC_BIND, LINEAR, SCHEDULE, OmpClauseKind.ORDERED, NUM_TEAMS,
            THREAD_LIMIT),
    TARGET_TEAMS_DISTRIBUTE_PARALLEL_FOR(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT,
            DEPEND, NUM_TEAMS, THREAD_LIMIT, DEFAULT, SHARED, REDUCTION,
            LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, NUM_THREADS,
            COPYIN, PROC_BIND, LINEAR, SCHEDULE, OmpClauseKind.ORDERED),
    TARGET_TEAMS_DISTRIBUTE_PARALLEL_FOR_SIMD(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT,
            DEPEND, LASTPRIVATE, COLLAPSE, DIST_SCHEDULE, NUM_THREADS, DEFAULT, SHARED, COPYIN, REDUCTION, PROC_BIND,
            LINEAR, SCHEDULE, OmpClauseKind.ORDERED, NUM_TEAMS, THREAD_LIMIT),
    TASK(IF, FINAL, UNTIED, DEFAULT, MERGEABLE, PRIVATE, FIRSTPRIVATE, SHARED, DEPEND, PRIORITY),
    TASKYIELD,
    MASTER,
    CRITICAL, // [(name)[hint(expression)]]
    BARRIER,
    TASKWAIT,
    TASKGROUP,
    ATOMIC, // [seq_cst[,]] read|write|update|capture [[,] seq_cst] // [seq_cst]
    FLUSH, // [(list)]
    ORDERED(THREADS, OmpClauseKind.SIMD, DEPEND),
    CANCEL, // construct-type-clause [ [,] if-clause]
    CANCELATION_POINT, // construct-type-clause
    THREADPRIVATE, // (list)
    DECLARE_REDUCTION, // (reduction-identifier : typenamelist : combiner) [initializer-clause])
    TASKLOOP(IF, SHARED, PRIVATE, FIRSTPRIVATE, LASTPRIVATE, DEFAULT, GRAINSIZE, NUM_TASKS, COLLAPSE, FINAL, PRIORITY,
            UNTIED, MERGEABLE, NOGROUP),
    TASKLOOP_SIMD(IF, SHARED, PRIVATE, FIRSTPRIVATE, LASTPRIVATE, DEFAULT, GRAINSIZE, NUM_TASKS, COLLAPSE, FINAL,
            PRIORITY, UNTIED, MERGEABLE, NOGROUP, SAFELEN, SIMDLEN, LINEAR, ALIGNED, REDUCTION),
    TARGET_ENTER_DATA(IF, DEVICE, MAP, DEPEND, NOWAIT),
    TARGET_EXIT_DATA(IF, DEVICE, MAP, DEPEND, NOWAIT),
    TARGET_PARALLEL(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND, IF, NUM_THREADS,
            DEFAULT, SHARED, REDUCTION, PROC_BIND),
    TARGET_PARALLEL_FOR(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND, NUM_THREADS,
            DEFAULT, SHARED, REDUCTION, PROC_BIND,
            LASTPRIVATE, LINEAR, SCHEDULE, COLLAPSE, OmpClauseKind.ORDERED),
    TARGET_PARALLEL_FOR_SIMD(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND,
            NUM_THREADS, DEFAULT, SHARED, REDUCTION, PROC_BIND,
            LASTPRIVATE, LINEAR, SCHEDULE, COLLAPSE, OmpClauseKind.ORDERED,
            SAFELEN, SIMDLEN, ALIGNED),
    TARGET_SIMD(IF, DEVICE, PRIVATE, FIRSTPRIVATE, MAP, IS_DEVICE_PTR, DEFAULTMAP, NOWAIT, DEPEND, SAFELEN, SIMDLEN,
            LINEAR, ALIGNED, LASTPRIVATE, REDUCTION, COLLAPSE);

    private static final Lazy<EnumHelperWithValue<OmpDirectiveKind>> HELPER = new ThreadSafeLazy<>(
            () -> new EnumHelperWithValue<>(OmpDirectiveKind.class));

    private static final Lazy<Set<OmpDirectiveKind>> DIRECTIVES_WITH_IF = Lazy
            .newInstance(() -> EnumSet.of(PARALLEL, TASK, TASKLOOP, TARGET_DATA, TARGET_ENTER_DATA, TARGET_EXIT_DATA,
                    TARGET, TARGET_UPDATE));

    // the set of clause kinds that each directive can have
    private final Set<OmpClauseKind> legalClauses;

    private OmpDirectiveKind(OmpClauseKind... legalClauses) {

        this.legalClauses = new HashSet<>(Arrays.asList(legalClauses));
    }

    public boolean isClauseLegal(OmpClauseKind clause) {

        return legalClauses.contains(clause);
    }

    public static EnumHelperWithValue<OmpDirectiveKind> getHelper() {
        return HELPER.get();
    }

    private static final Lazy<Collection<String>> OMP_DIRECTIVE_STRINGS = new ThreadSafeLazy<>(
            OmpDirectiveKind::buildDirectiveStrings);

    private static Collection<String> buildDirectiveStrings() {
        List<String> directiveStrings = Arrays.stream(OmpDirectiveKind.values())
                .map(OmpDirectiveKind::getString)
                .collect(Collectors.toList());

        Collections.sort(directiveStrings);
        Collections.reverse(directiveStrings);

        return directiveStrings;
    }

    public static ParserResult<OmpDirectiveKind> parseOmpDirective(StringSlice string) {

        for (String ompDirectiveString : OMP_DIRECTIVE_STRINGS.get()) {
            if (!string.startsWith(ompDirectiveString)) {
                continue;
            }

            // OmpDirectiveKind ompDirective = getHelper().valueOf(ompDirectiveString.replace(' ', '_').toUpperCase());
            OmpDirectiveKind ompDirective = getHelper().fromValue(ompDirectiveString);

            StringSlice parsedString = string.substring(ompDirectiveString.length()).trim();

            return new ParserResult<OmpDirectiveKind>(parsedString, ompDirective);
        }

        throw new RuntimeException("Could not determine OpenMP directive: " + string);

    }

    @Override
    public String getString() {
        return name().replace('_', ' ').toLowerCase();
    }

    /**
     * Some directives support the 'if' clause, which can optionally have the directive name inside the clause
     * parameters.
     * 
     * @return
     */
    public Optional<String> getIfName() {
        // Check if directive support its name in the if
        if (DIRECTIVES_WITH_IF.get().contains(this)) {
            // Parse name
            return Optional.of(this.name().toLowerCase().replace('_', ' '));
        }

        // Check if it is a parallel directive
        if (this.name().startsWith("PARALLEL_")) {
            return Optional.of("parallel");
        }

        return Optional.empty();
    }
}
