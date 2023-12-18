package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public interface Scenario {
    public Integer acceptCheckerInt(ScenarioChecker scenarioChecker);
    public ArrayList<String> acceptCheckerArrayList(ScenarioChecker scenarioChecker);

}
