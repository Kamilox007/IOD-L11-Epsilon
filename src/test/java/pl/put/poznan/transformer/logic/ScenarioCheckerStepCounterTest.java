package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScenarioCheckerStepCounterTest {



    @Test
    public void testCountSteps1(){
        ScenarioCheckerStepCounter scenariochecker = new ScenarioCheckerStepCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("1 FOR EACH 1", null);
        Step step2 = new Step("2 ELSE 2", null);
        Step step3 = new Step("3 IF 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);

        assertEquals(scenariochecker.countSteps(mockConcreteScenario),3);

    }

    @Test
    public void testCountSteps2(){
        ScenarioCheckerStepCounter scenariochecker = new ScenarioCheckerStepCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("1 FOR EACH 1", null);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);

        assertEquals(scenariochecker.countSteps(mockConcreteScenario),1);

    }

    @Test
    public void testCountSteps3(){
        ScenarioCheckerStepCounter scenariochecker = new ScenarioCheckerStepCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("1 FOR EACH 1", null);
        Step step2 = new Step("2 ELSE 2", null);
        Step step3 = new Step("3 IF 3", null);
        Step step4 = new Step("2 ELSE 2", null);
        Step step5 = new Step("3 IF 3", null);
        Step step6 = new Step("2 ELSE 2", null);
        Step step7 = new Step("3 IF 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        steps.add(step4);
        steps.add(step5);
        steps.add(step6);
        steps.add(step7);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);

        assertEquals(scenariochecker.countSteps(mockConcreteScenario),7);

    }
    @Test
    public void testNullSteps(){
        ScenarioCheckerStepCounter scenariochecker = new ScenarioCheckerStepCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step(null, null);
        Step step2 = new Step(null, null);
        Step step3 = new Step(null, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);

        assertEquals(scenariochecker.countSteps(mockConcreteScenario),3);

    }

    @Test
    public void testNestedSteps(){
        ScenarioCheckerStepCounter scenariochecker = new ScenarioCheckerStepCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);
        ConcreteScenario mockConcreteScenario2 = mock(ConcreteScenario.class);


        Step step4 = new Step("IF",null);
        Step step5 = new Step("ELSE",null);
        Step step6 = new Step("FOR EACH",null);

        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step4);
        steps2.add(step5);
        steps2.add(step6);

        when(mockConcreteScenario2.getSteps()).thenReturn(steps2);

        Step step1 = new Step("FOR EACH 1", null);
        Step step2 = new Step("ELSE 2", null);
        Step step3 = new Step("IF 3", mockConcreteScenario2);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);

        assertEquals(scenariochecker.countSteps(mockConcreteScenario),6);

    }
}