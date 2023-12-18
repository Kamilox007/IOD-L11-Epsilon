package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ScenarioCheckerNoActorInStep implements ScenarioChecker{

    @Override
    public ArrayList<String> visitScenarioArrayList(MainScenario mainScenario) {
        ConcreteScenario scenario = mainScenario.getScenario();
        ArrayList<Actor> listOfActors = mainScenario.getActors();
        ArrayList<String> actorlessSteps = checkActors(scenario,listOfActors);
        for (String step : actorlessSteps) {
            System.out.println(step);
        }
        for (Actor actor : listOfActors){
            System.out.println(actor.getName());
        }
        return checkActors(scenario,listOfActors);
    }
    @Override
    public Integer visitScenarioInt(ConcreteScenario scenario) { return 69; }
    private ArrayList<String> checkActors(ConcreteScenario scenario, ArrayList<Actor> listOfActors) {
        ArrayList<String> stepList = new ArrayList<>();
        int i = 1;
        for (Step step : scenario.getSteps()) {
            for (Actor actor : listOfActors) {
                if(!step.getContent().startsWith(actor.getName())) {
                    stepList.add(""+i+" "+step.getContent());
                    break;
                }
                i++;
            }
            if(step.getSubScenario()!=null) {
                stepList.addAll(checkActors(step.getSubScenario(),listOfActors));
            }
        }
        return stepList;
    }
}
