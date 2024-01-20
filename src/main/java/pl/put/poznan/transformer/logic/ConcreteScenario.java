package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class ConcreteScenario {
    private ArrayList<Step> steps;

    public ConcreteScenario(){
        steps = new ArrayList<>();
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    public void addStep(Step step) { steps.add(step); }
}
