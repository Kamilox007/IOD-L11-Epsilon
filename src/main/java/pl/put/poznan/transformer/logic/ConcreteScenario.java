package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class ConcreteScenario implements Scenario {
    private ArrayList<Step> steps;
    private ArrayList<Actor> actors;

    public ConcreteScenario(){
        steps = new ArrayList<>();
        actors = new ArrayList<>();
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public void addStep(Step step) {
        steps.add(step);
    }
    @Override
    public void acceptChecker(ScenarioChecker scenarioChecker) {
        scenarioChecker.visitScenario(this);
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }
}
