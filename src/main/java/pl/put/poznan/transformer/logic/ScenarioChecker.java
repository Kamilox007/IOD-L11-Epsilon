package pl.put.poznan.transformer.logic;

import com.sun.tools.javac.Main;

import java.util.ArrayList;

public interface ScenarioChecker {
    public Integer visitScenarioInt(ConcreteScenario scenario);
    public ArrayList<String> visitScenarioArrayList(MainScenario scenario);
}
