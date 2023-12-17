package pl.put.poznan.transformer.logic;

public class ScenarioCheckerKeywordCounter implements ScenarioChecker{
    private String[] keywords = {"IF","ELSE","FOR EACH"};
    @Override
    public void visitScenario(ConcreteScenario scenario) {
        System.out.println("Liczba kroków ze słowami kluczowymi: "+checkKeywords(scenario));
    }

    private int checkKeywords(ConcreteScenario scenario) {
        int sum = 0;
        for (Step step : scenario.getSteps()) {
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
