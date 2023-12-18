package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ScenarioCheckerStepCounter implements ScenarioChecker {
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) {
        return countSteps(scenario);
    }
    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario) { return new ArrayList<>(); }

    private int countSteps(ConcreteScenario scenario) {
        ArrayList<Step> stepList = scenario.getSteps();
        int sum = 0;
        for (Step step : stepList) {
            sum++;
            if(step.getSubScenario() != null) {
                sum += countSteps(step.getSubScenario());
            }
        }
        return sum;
    }

}
