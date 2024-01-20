package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.Collections;

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

    @Override
    public String visitScenarioStringDepthVariant(MainScenario scenario, int maxDepth) {
        return null;
    }

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
