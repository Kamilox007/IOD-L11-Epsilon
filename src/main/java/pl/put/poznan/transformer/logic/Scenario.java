package pl.put.poznan.transformer.logic;

import java.util.List;

public interface Scenario {
    public Integer acceptCheckerInt(ScenarioChecker scenarioChecker);
    public List<String> acceptCheckerArrayList(ScenarioChecker scenarioChecker);
    public String acceptCheckerString(ScenarioChecker scenarioChecker);
    public String acceptCheckerStringDepth(ScenarioChecker scenarioChecker, int maxDepth);

}
