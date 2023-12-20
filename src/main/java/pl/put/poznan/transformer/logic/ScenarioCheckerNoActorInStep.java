package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ScenarioCheckerNoActorInStep implements ScenarioChecker{

    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario mainScenario) {
        ConcreteScenario scenario = mainScenario.getScenario();
        ArrayList<Actor> listOfActors = mainScenario.getActors();
        listOfActors.addAll(mainScenario.getSystemActors());
        return checkActors(scenario,listOfActors, 1);
    }
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) { return 69; }
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

    private boolean lackOfActor(Step step, ArrayList<Actor> listOfActors){
        for (Actor actor : listOfActors)
            if (step.getContent().startsWith(actor.getName()))
                return false;
        return true;
    }
}
