package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
/**
 * Class responsible for counting steps in a scenario that begin with one of the keywords.
 *
 * @author Epsilon
 * @version 2.0
 */
public class ScenarioCheckerKeywordCounter implements ScenarioChecker{
    private String[] keywords = {"IF","ELSE","FOR EACH"}; //Leave it this way, believe me

    @Override
    public String visitScenarioString(MainScenario scenario) {
        return null;
    }

    /**
     * This method is a concrete visitor that executes private function to count steps that begin with one of the keywords.
     *
     * @param scenario An object that contains a list of all steps.
     * @return number of steps in a scenario that begin with one of the keywords.
     */
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) {
        return checkKeywords(scenario);
    }

    /**
     * Implementing method from the interface
     */
    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario) { return new ArrayList<>(); }

    @Override
    public String visitScenarioStringDepthVariant(MainScenario scenario, int maxDepth) {
        return null;
    }

    /**
     * This method iterates through all steps within and counts only those that start with one of the keyword.
     *
     * @param scenario An object that contains a list of all steps.
     * @return number of steps in a scenario that begin with one of the keywords.
     */
    public int checkKeywords(ConcreteScenario scenario) {
        int sum = 0;
        for (Step step : scenario.getSteps()) {
            //
            for (String keyword : keywords) {
                String tmp = step.getContent();
                if(tmp != null) {
                    if(tmp.startsWith(keyword)) {
                        sum++;
                        break;
                    }
                }
            }
            if(step.getSubScenario()!=null) {
                sum+=checkKeywords(step.getSubScenario());
            }
        }
        return sum;
    }
}
