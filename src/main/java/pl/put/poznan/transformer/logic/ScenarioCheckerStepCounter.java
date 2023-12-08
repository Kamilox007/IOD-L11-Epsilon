package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ScenarioCheckerStepCounter implements ScenarioChecker {
    @Override
    public void visitScenario(ConcreteScenario scenario) {
        System.out.println("Liczba kroków w tym scenariuszu: "+countSteps(scenario));
    }

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
