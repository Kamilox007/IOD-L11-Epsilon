package pl.put.poznan.transformer.logic;

import java.util.ArrayList;

public class MainScenario implements Scenario {

    private String title;
    private ArrayList<Actor> actors;
    private ArrayList<Actor> systemActors;
    private ConcreteScenario scenario;

    public MainScenario(){

        actors = new ArrayList<>();
        systemActors = new ArrayList<>();
        scenario = new ConcreteScenario();
    }

    public String getTitle() { return title;}
    public void addTitle(String title) { this.title = title;}

    public  ArrayList<Actor> getActors() {
        return actors;
    }
    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public ArrayList<Actor> getSystemActors() { return systemActors; }
    public void addSystemActor(Actor actor) { systemActors.add(actor); }

    public ConcreteScenario getScenario() { return scenario; }
    public void addScenario(ConcreteScenario Scenario) { scenario = Scenario; }
    @Override
    public Integer acceptCheckerInt(ScenarioChecker scenarioChecker) {
        return scenarioChecker.visitScenarioInt(this.scenario);
    }
    @Override
    public ArrayList<String> acceptCheckerArrayList(ScenarioChecker scenarioChecker) {
        return scenarioChecker.visitScenarioArrayList(this);
    }

    @Override
    public String acceptCheckerString(ScenarioChecker scenarioChecker) {
        return  scenarioChecker.visitScenarioString(this);
    }

    @Override
    public String acceptCheckerStringDepth(ScenarioChecker scenarioChecker, int maxDepth) {
        return scenarioChecker.visitScenarioStringDepthVariant(this, maxDepth);
    }
}