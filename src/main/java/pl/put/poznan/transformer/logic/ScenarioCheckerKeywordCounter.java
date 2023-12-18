package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ScenarioCheckerKeywordCounter implements ScenarioChecker{
    private String[] keywords = {"\"IF","\"ELSE","\"FOR EACH"}; //Leave it this way, believe me
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) {
        return checkKeywords(scenario);
    }
    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario) { return new ArrayList<>(); }


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
