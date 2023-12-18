package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ScenarioCheckerNoActorInStep implements ScenarioChecker{


    @Override
    public void visitScenario(ConcreteScenario scenario) {
        ArrayList<Actor> listOfActors = scenario.getActors();
        ArrayList<String> actorlessSteps = checkActors(scenario,listOfActors);
        for (String step : actorlessSteps) {
            System.out.println(step);
        }

    }
    private ArrayList<String> checkActors(ConcreteScenario scenario, ArrayList<Actor> listOfActors) {
        ArrayList<String> stepList = new ArrayList<>();
        int i = 1;
        for (Step step : scenario.getSteps()) {
            for (Actor actor : listOfActors) {
                if(!step.getContent().startsWith(actor.getName())) {
                    stepList.add(""+i+" "+step.getContent());
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
