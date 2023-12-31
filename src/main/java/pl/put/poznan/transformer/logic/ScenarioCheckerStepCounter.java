package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

/**
 * Class responsible for counting steps in a scenario.
 *
 * @author Epsilon
 * @version 1.0
 */
public class ScenarioCheckerStepCounter implements ScenarioChecker {

    /**
     * This method is a concrete visitor that executes private function to count steps.
     *
     * @param scenario An object containing data loaded from one of the Json-scenario files.
     * @return number of steps in a scenario.
     */
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) {
        return countSteps(scenario);
    }

    /**
     * Implementing method from the interface
     */
    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario) { return new ArrayList<>(); }

    /**
     * This method iterates through all steps within a scenario to count them.
     *
     * @param scenario An object that contains a list of all steps.
     * @return number of steps in a scenario.
     */
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
