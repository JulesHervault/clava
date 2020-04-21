package pt.up.fe.specs.clava.weaver.memoi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.up.fe.specs.clava.weaver.memoi.stats.Stats;
import pt.up.fe.specs.util.SpecsCheck;

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

public class MergedMemoiReport {

    private String id;
    private String funcSig;
    private int inputCount;
    private int outputCount;
    private List<String> inputTypes;
    private List<String> outputTypes;
    private List<String> callSites;

    private List<Integer> elements;
    private List<Integer> calls;
    private List<Integer> hits;
    private List<Integer> misses;

    private Map<String, MergedMemoiEntry> counts;

    private int reportCount = 1;

    private Stats stats;

    public MergedMemoiReport(MemoiReport report) {

        this.id = report.getId();
        this.funcSig = report.getFuncSig();
        this.outputTypes = report.getOutputTypes();
        this.inputCount = report.getInputCount();
        this.outputCount = report.getOutputCount();
        this.inputTypes = new ArrayList<>(report.getInputTypes());
        this.callSites = new ArrayList<>(report.getCall_sites());

        this.elements = new ArrayList<Integer>();
        this.elements.add(report.getElements());

        this.calls = new ArrayList<Integer>(report.getCalls());
        this.calls.add(report.getCalls());

        this.hits = new ArrayList<Integer>(report.getHits());
        this.hits.add(report.getHits());

        this.misses = new ArrayList<Integer>(report.getMisses());
        this.misses.add(report.getMisses());

        this.counts = new HashMap<String, MergedMemoiEntry>();
        for (MemoiEntry oldEntry : report.getCounts()) {

            String key = oldEntry.getKey();
            MergedMemoiEntry newEntry = new MergedMemoiEntry(oldEntry);
            counts.put(key, newEntry);
        }
    }

    public List<MergedMemoiEntry> getMeanSorted() {

        var list = new ArrayList<MergedMemoiEntry>(counts.values());
        list.sort(MemoiComparator.mean(this));

        return list;
    }

    public List<MergedMemoiEntry> getSortedCounts(Comparator<MergedMemoiEntry> countComparator) {

        var list = new ArrayList<MergedMemoiEntry>(counts.values());

        list.sort(countComparator);

        return list;
    }

    void mergeReport(MemoiReport tempReport) {

        mergeReport(tempReport, false);
    }

    /**
     * Merges a MemoiReport into this MergedMemoiReport.
     * 
     * @param tempReport
     * @param check
     */
    void mergeReport(MemoiReport tempReport, boolean check) {

        if (check) {
            testReport(tempReport);
        }

        elements.add(tempReport.getElements());
        calls.add(tempReport.getCalls());
        hits.add(tempReport.getHits());
        misses.add(tempReport.getMisses());

        for (MemoiEntry oldEntry : tempReport.getCounts()) {

            String key = oldEntry.getKey();

            if (!counts.containsKey(key)) {

                MergedMemoiEntry newEntry = new MergedMemoiEntry(oldEntry);

                counts.put(key, newEntry);
            } else {

                counts.get(key).addCounter(oldEntry.getCounter());
            }

        }
    }

    private void testReport(MemoiReport tempReport) {

        SpecsCheck.checkArgument(this.funcSig.equals(tempReport.getFuncSig()),
                () -> "The function signatures of the reports are not equal");
        SpecsCheck.checkArgument(this.callSites.equals(tempReport.getCall_sites()),
                () -> "The call sites of the reports are not equal");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncSig() {
        return funcSig;
    }

    public void setFuncSig(String funcSig) {
        this.funcSig = funcSig;
    }

    public List<String> getOutputTypes() {
        return outputTypes;
    }

    public void setOutputType(List<String> outputTypes) {
        this.outputTypes = outputTypes;
    }

    public int getInputCount() {
        return inputCount;
    }

    public void setInputCount(int inputCount) {
        this.inputCount = inputCount;
    }

    public int getOutputCount() {
        return outputCount;
    }

    public void setOutputCount(int outputCount) {
        this.outputCount = outputCount;
    }

    public List<String> getInputTypes() {
        return inputTypes;
    }

    public void setInputTypes(List<String> inputTypes) {
        this.inputTypes = inputTypes;
    }

    public List<String> getCallSites() {
        return callSites;
    }

    public void setCallSites(List<String> call_sites) {
        this.callSites = call_sites;
    }

    public List<Integer> getElements() {
        return elements;
    }

    public void setElements(List<Integer> elements) {
        this.elements = elements;
    }

    public List<Integer> getCalls() {
        return calls;
    }

    public void setCalls(List<Integer> calls) {
        this.calls = calls;
    }

    public List<Integer> getHits() {
        return hits;
    }

    public void setHits(List<Integer> hits) {
        this.hits = hits;
    }

    public List<Integer> getMisses() {
        return misses;
    }

    public void setMisses(List<Integer> misses) {
        this.misses = misses;
    }

    public Map<String, MergedMemoiEntry> getCounts() {
        return counts;
    }

    public void setCounts(Map<String, MergedMemoiEntry> counts) {
        this.counts = counts;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public void printStats() {

        System.out.println("\n\n=== profile stats ===");
        System.out.println("target function: " + funcSig);
        System.out.println("call sites: " + callSites);
        System.out.println("report count: " + reportCount);

        stats.print();
    }

    public void makeStats() {
        this.stats = new Stats(this);
    }
}
