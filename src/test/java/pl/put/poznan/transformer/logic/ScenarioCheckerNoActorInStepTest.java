package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ScenarioCheckerNoActorInStepTest {



    @Test
    public void testActorInStep(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("Andrzej");
        Actor actor2 = new Actor("Boingo");
        actors.add(actor1);
        actors.add(actor2);

        Step step1 = new Step("Boingo poszedl na spacer", null);
        Step step2 = new Step("Andrzej poszedl razem z nim", null);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("3");

        assertEquals(incorrectSteps,shouldBe);

    }

    @Test
    public void testWrongActors(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("Boingo");
        Actor actor2 = new Actor("Marta");

        actors.add(actor1);
        actors.add(actor2);

        Step step1 = new Step("Boingo poszedl na spacer", null);
        Step step2 = new Step("Andrzej poszedl razem z nim", null);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("2 Andrzej poszedl razem z nim");
        shouldBe.add("3");

        assertEquals(incorrectSteps,shouldBe);

    }


    @Test
    public void testNoActors(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();


        Step step1 = new Step("Boingo poszedl na spacer", null);
        Step step2 = new Step("Andrzej poszedl razem z nim", null);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("1 Boingo poszedl na spacer");
        shouldBe.add("2 Andrzej poszedl razem z nim");
        shouldBe.add("3");

        assertEquals(incorrectSteps,shouldBe);

    }


    @Test
    public void testNestedScenario(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);
        ConcreteScenario mockConcreteScenario2 = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("Boingo");
        Actor actor2 = new Actor("Andrzej");
        Actor actor3 = new Actor("System");

        actors.add(actor1);
        actors.add(actor2);
        actors.add(actor3);

        Step step4 = new Step("Boingo zapala petardę",null);
        Step step5 = new Step("System popiera takie zachowanie",null);
        Step step6 = new Step("Andrzej przyłącza się do festiwalu",null);

        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step4);
        steps2.add(step5);
        steps2.add(step6);

        when(mockConcreteScenario2.getSteps()).thenReturn(steps2);

        Step step1 = new Step("Boingo zapala petardę",null);
        Step step2 = new Step("System popiera takie zachowanie",null);
        Step step3 = new Step("Andrzej przyłącza się do festiwalu",mockConcreteScenario2);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("7");

        assertEquals(incorrectSteps,shouldBe);

    }

    @Test
    public void testNestedScenarioNoActors(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);
        ConcreteScenario mockConcreteScenario2 = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("Boingo");
        Actor actor2 = new Actor("System");

        actors.add(actor1);
        actors.add(actor2);

        Step step4 = new Step("Boingo zapala petardę",null);
        Step step5 = new Step("System popiera takie zachowanie",null);
        Step step6 = new Step("Andrzej przyłącza się do festiwalu",null);

        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step4);
        steps2.add(step5);
        steps2.add(step6);

        when(mockConcreteScenario2.getSteps()).thenReturn(steps2);

        Step step1 = new Step("Boingo zapala petardę",null);
        Step step2 = new Step("System popiera takie zachowanie",null);
        Step step3 = new Step("Andrzej przyłącza się do festiwalu",mockConcreteScenario2);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("3 Andrzej przyłącza się do festiwalu");
        shouldBe.add("6 Andrzej przyłącza się do festiwalu");
        shouldBe.add("7");

        assertEquals(incorrectSteps,shouldBe);

    }

    @Test
    public void testActorInside(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("Andrzej");
        Actor actor2 = new Actor("Boingo");
        actors.add(actor1);
        actors.add(actor2);

        Step step1 = new Step("Boingo poszedl na spacer", null);
        Step step2 = new Step("Poszedl Andrzej razem z nim", null);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("2 Poszedl Andrzej razem z nim");
        shouldBe.add("3");

        assertEquals(incorrectSteps,shouldBe);

    }

    @Test
    public void testActorInTheEnd(){
        ScenarioCheckerNoActorInStep scenariochecker = new ScenarioCheckerNoActorInStep();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);

        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor("Andrzej");
        Actor actor2 = new Actor("Boingo");
        actors.add(actor1);
        actors.add(actor2);

        Step step1 = new Step("Boingo poszedl na spacer", null);
        Step step2 = new Step("Poszedl razem z nim Andrzej", null);

        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);

        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        ArrayList<String> incorrectSteps = scenariochecker.checkActors(mockConcreteScenario,actors,1);

        ArrayList<String>shouldBe = new ArrayList<String>();
        shouldBe.add("2 Poszedl razem z nim Andrzej");
        shouldBe.add("3");

        assertEquals(incorrectSteps,shouldBe);

    }

}