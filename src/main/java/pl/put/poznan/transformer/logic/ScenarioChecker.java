package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public interface ScenarioChecker {
    public String visitScenarioString(MainScenario scenario);
    public Integer visitScenarioInt(ConcreteScenario scenario);
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario);
    public String visitScenarioStringDepthVariant(MainScenario scenario, int maxDepth);
}
