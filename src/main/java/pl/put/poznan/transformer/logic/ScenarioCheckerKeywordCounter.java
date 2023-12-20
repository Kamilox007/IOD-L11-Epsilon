package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
/**
 * Class responsible for counting steps in a scenario that begin with one of the keywords.
 *
 * @author Epsilon
 * @version 1.0
 */
public class ScenarioCheckerKeywordCounter implements ScenarioChecker{
    private String[] keywords = {"IF","ELSE","FOR EACH"}; //Leave it this way, believe me

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

    /**
     * This method iterates through all steps within and counts only those that start with one of the keyword.
     *
     * @param scenario An object that contains a list of all steps.
     * @return number of steps in a scenario that begin with one of the keywords.
     */
    private int checkKeywords(ConcreteScenario scenario) {
        int sum = 0;
        for (Step step : scenario.getSteps()) {
            //
            for (String keyword : keywords) {
                if(step.getContent().startsWith(keyword)) {
                    sum++;
                    break;
                }
            }
            if(step.getSubScenario()!=null) {
                sum+=checkKeywords(step.getSubScenario());
            }
        }
        return sum;
    }
}
