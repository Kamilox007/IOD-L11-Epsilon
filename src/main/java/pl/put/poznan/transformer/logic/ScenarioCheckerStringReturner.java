package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Class responsible for converting a scenario to a string format.
 *
 * @author Epsilon
 * @version 2.0
 */
public class ScenarioCheckerStringReturner implements ScenarioChecker {
    /**
     * A method that returns a scenario as a text, with numbered steps and indents. Uses recursion
     *
     * @param scenario Main scenario object containing a scenario loaded from a JSON File
     * @return Scenario in a form of String with numbered steps and indents
     */
    @Override
    public String visitScenarioString(MainScenario scenario) {
        return scenarioToString(scenario.getScenario(),"",1,0);
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
     * Implementing method from the interface
     */
    @Override
    public String visitScenarioStringDepthVariant(MainScenario scenario, int maxDepth) {
        return null;
    }

    /**
     * A method that converts a scenario from ConcreteScenario format to string.
     *
     * @param scenario An object that contains a list of all steps.
     * @param superStepNumber number of a parent step (set it to "" when executing)
     * @param currentNumber number of a step in the current subscenario (set it to 1 when executing)
     * @param depth current search depth (set it to 0 when executing)
     * @return converted scenario to a string format
     */
    public String scenarioToString(ConcreteScenario scenario, String superStepNumber, int currentNumber, int depth) {
        StringBuilder scenarioText = new StringBuilder();
        for (Step step : scenario.getSteps()) {
            String stepText = "  ".repeat(depth)+superStepNumber+Integer.toString(currentNumber)+". "+step.getContent()+"\n";
            scenarioText.append(stepText);
            ConcreteScenario subScenario = step.getSubScenario();
            if (subScenario != null) {
                scenarioText.append(scenarioToString(subScenario, superStepNumber+Integer.toString(currentNumber)+".",1,depth+1));
            }
            currentNumber++;
        }
        return scenarioText.toString();
    }
}
