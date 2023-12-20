package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

/**
 * Class responsible for counting steps in a scenario that does not begin with an author name.
 *
 * @author Epsilon
 * @version 1.1
 */
public class ScenarioCheckerNoActorInStep implements ScenarioChecker{

    /**
     * This method is a concrete visitor that executes private function to count steps without actors.
     *
     * @param mainScenario An object containing data loaded from one of the Json-scenario files.
     * @return ArrayList of incorrect steps.
     */
    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario mainScenario) {
        ConcreteScenario scenario = mainScenario.getScenario();
        ArrayList<Actor> listOfActors = mainScenario.getActors();
        listOfActors.addAll(mainScenario.getSystemActors());
        return checkActors(scenario,listOfActors, 1);
    }
    /**
     * Implementing method from the interface
     */
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) { return 0; }

    /**
     * This method iterates through all steps to check whether they begin with one of the actors.
     *
     * @param scenario An object that contains a list of all steps.
     * @return ArrayList of all incorrect steps with extra index containing number of steps at the end.
     */
    private ArrayList<String> checkActors(ConcreteScenario scenario, ArrayList<Actor> listOfActors, Integer i) {
        ArrayList<String> stepList = new ArrayList<>();
        for (Step step : scenario.getSteps()) {
            if (lackOfActor(step, listOfActors))
                stepList.add("" + i + " " + step.getContent());
            i++;

            if (step.getSubScenario() != null) {
                stepList.addAll(checkActors(step.getSubScenario(), listOfActors, i));
                i = Integer.parseInt(stepList.get(stepList.size() - 1)); // rozpakowujemy ile krokow przeszedl podscenariusz
                stepList.remove(stepList.size() - 1); // usuwamy z listy pomocniczą liczbę
            }
        }
        stepList.add(i.toString()); //Dodajemy liczbę kroków na koniec
        return stepList;
    }

    /**
     * This method is being used for checking if the step begins with the name of one of the actors.
     *
     * @param step An object containing a concrete step within the scenario.
     * @param listOfActors An object containing a list of all actors.
     * @return false if the step begins with the name of an actor, true otherwise.
     */
    private boolean lackOfActor(Step step, ArrayList<Actor> listOfActors){
        for (Actor actor : listOfActors)
            if (step.getContent().startsWith(actor.getName()))
                return false;
        return true;
    }
}
