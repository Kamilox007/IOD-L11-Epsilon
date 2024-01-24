package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class ScenarioCheckerKeywordCounterTest {


    @Test
    public void testMultipleIf(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("IF 1", null);
        Step step2 = new Step("IF 2", null);
        Step step3 = new Step("IF 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),3);

    }

    @Test
    public void testMultipleElse(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("ELSE 1", null);
        Step step2 = new Step("ELSE 2", null);
        Step step3 = new Step("ELSE 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),3);

    }

    @Test
    public void testMultipleForEach(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("FOR EACH 1", null);
        Step step2 = new Step("FOR EACH 2", null);
        Step step3 = new Step("FOR EACH 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),3);

    }

    @Test
    public void testNoKeywords(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("1", null);
        Step step2 = new Step("2", null);
        Step step3 = new Step("3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),0);

    }

    @Test
    public void testMultipleDifferent(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("FOR EACH 1", null);
        Step step2 = new Step("ELSE 2", null);
        Step step3 = new Step("IF 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),3);

    }



    @Test
    public void testKeywordsInside(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);


        Step step1 = new Step("1 FOR EACH 1", null);
        Step step2 = new Step("2 ELSE 2", null);
        Step step3 = new Step("3 IF 3", null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),0);

    }
    @Test
    public void testNull(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
        ConcreteScenario mockConcreteScenario = mock(ConcreteScenario.class);

        Step step1 = new Step(null, null);
        Step step2 = new Step(null, null);
        Step step3 = new Step(null, null);
        ArrayList<Step> steps = new ArrayList<>();
        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        when(mockConcreteScenario.getSteps()).thenReturn(steps);


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),0);

    }


    @Test
    public void testNestedKeywords(){
        ScenarioCheckerKeywordCounter scenariochecker = new ScenarioCheckerKeywordCounter();
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


        assertEquals(scenariochecker.checkKeywords(mockConcreteScenario),6);

    }



}