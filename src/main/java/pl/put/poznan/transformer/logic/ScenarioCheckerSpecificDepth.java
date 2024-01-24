package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
/**
 * Class responsible for filtering scenario to a certain depth.
 *
 * @author Epsilon
 * @version 2.0
 */
public class ScenarioCheckerSpecificDepth implements ScenarioChecker {
    /**
     * A method that returns a scenario as a text, with numbered steps and indents. Uses recursion
     *
     * @param scenario Main scenario object containing a scenario loaded from a JSON File
     * @param maxDepth Integer that specifies, how many levels of the scenario should be returned
     * @return Scenario in a form of String with numbered steps and indents
     */
    @Override
    public String visitScenarioStringDepthVariant(MainScenario scenario, int maxDepth) {
        return scenarioToStringDepthVariant(scenario.getScenario(),"",1,0, maxDepth);
    }

    @Override
    public String visitScenarioString(MainScenario scenario) {
        return null;
    }

    /**
     * Implementing method from the interface
     */
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) {
        return null;
    }

    /**
     * Implementing method from the interface
     */
    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario) {
        return new ArrayList<>();
    }


    /**
     * A method that is responsible for iterating through steps and discarding steps after specific depth is reached.
     *
     * @param scenario An object that contains a list of all steps.
     * @param superStepNumber number of a parent step (set it to "" when executing)
     * @param currentNumber number of a step in the current subscenario (set it to 1 when executing)
     * @param depth current search depth (set it to 0 when executing)
     * @param maxDepth depth to which function is supposed to search
     * @return scenario without steps after maxDepth in string format
     */
    public String scenarioToStringDepthVariant(ConcreteScenario scenario, String superStepNumber, int currentNumber, int depth, int maxDepth) {
        if (depth >= maxDepth) {
            return "";
        }
        else {
            StringBuilder scenarioText = new StringBuilder();
            for (Step step : scenario.getSteps()) {
                String stepText = "  ".repeat(depth) + superStepNumber + Integer.toString(currentNumber) + ". " + step.getContent()+"\n";
                scenarioText.append(stepText);
                ConcreteScenario subScenario = step.getSubScenario();
                if (subScenario != null) {
                    scenarioText.append(scenarioToStringDepthVariant(subScenario, superStepNumber + Integer.toString(currentNumber) + ".", 1, depth + 1, maxDepth));
                }
                currentNumber++;
            }
            return scenarioText.toString();
        }
    }
}
