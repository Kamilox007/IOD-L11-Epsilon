package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.put.poznan.transformer.logic.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

public class JsonController {

    private String path;

    /*TODO: Na razie kod obsługuje błąd gdy w scenariuszu nie ma jakiegoś słowa kluczowego, np. Title
       oraz gdy nie ma scenariusza o podanej nazwie.
       Można jeszcze dorobić, by sprawdzał, czy nie ma jakiś nadmiernych pól (z naciskiem na 'można').
     */
    public JsonController(String fileName){
        this.path = "./database/" + fileName + "/";
    }
    private static ConcreteScenario unpackScenario(JsonNode rootNode, ObjectMapper objectMapper){
        if (rootNode.isNull())
            return null;
        ConcreteScenario scenario = new ConcreteScenario();

        for (JsonNode steps : rootNode){
            JsonNode content = steps.get("Content");
            JsonNode step = steps.get("Steps");
            ConcreteScenario subScenario = unpackScenario(step, objectMapper);
            Step scenarioStep = new Step(null, subScenario);
            scenario.addStep(scenarioStep);
        }
        return scenario;
    }

    public MainScenario unpackJson() throws IOException, NullPointerException {

        MainScenario mainScenario = new MainScenario();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(new File(path));
        mainScenario.addTitle(rootNode.get("Title").toString());

        JsonNode actors = rootNode.get("Actors");
        for (JsonNode objNode : actors){
            Actor actor = new Actor();
            actor.addName(objNode.toString());
            mainScenario.addActor(actor);
        }

        actors = rootNode.get("System Actor");
        for (JsonNode objNode : actors){
            Actor actor = new Actor();
            actor.addName(objNode.toString());
            mainScenario.addSystemActor(actor);
        }

        mainScenario.addScenario(unpackScenario(rootNode.path("Scenario"), objectMapper));
        ScenarioCheckerStepCounter counter = new ScenarioCheckerStepCounter();

        return mainScenario;
    }

}
