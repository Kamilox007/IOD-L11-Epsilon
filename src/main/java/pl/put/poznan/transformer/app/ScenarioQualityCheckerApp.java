package pl.put.poznan.transformer.app;

import pl.put.poznan.transformer.logic.ConcreteScenario;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.ScenarioCheckerStepCounter;
import pl.put.poznan.transformer.logic.Step;

public class ScenarioQualityCheckerApp {

    public static void main(String[] args) {
        ConcreteScenario scenario = new ConcreteScenario();
        ConcreteScenario subscenario = new ConcreteScenario();
        subscenario.addStep(new Step("PodKrok 1",null));
        subscenario.addStep(new Step("PodKrok 2",null));
        subscenario.addStep(new Step("PodKrok 3",null));
        subscenario.addStep(new Step("PodKrok 4",null));
        subscenario.addStep(new Step("PodKrok 5",null));
        scenario.addStep(new Step("Krok 1",null));
        scenario.addStep(new Step("Krok 2",null));
        scenario.addStep(new Step("Krok 3",null));
        scenario.addStep(new Step("Krok 4",null));
        scenario.addStep(new Step("Krok 5",null));
        scenario.addStep(new Step("Krok 6",subscenario));
        ScenarioCheckerStepCounter counter = new ScenarioCheckerStepCounter();
        scenario.acceptChecker(counter);


    }

}
