package pl.put.poznan.transformer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import pl.put.poznan.transformer.logic.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            String convertedContent = content.toString().substring(1, content.toString().length() - 1);
            ConcreteScenario subScenario = unpackScenario(step, objectMapper);
            Step scenarioStep = new Step(convertedContent, subScenario);
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
            Actor actor = new Actor(objNode.toString().substring(1, objNode.toString().length() - 1));
            mainScenario.addActor(actor);
        }

        actors = rootNode.get("System Actor");
        for (JsonNode objNode : actors){
            Actor actor = new Actor(objNode.toString().substring(1, objNode.toString().length() - 1));
            mainScenario.addSystemActor(actor);
        }

        mainScenario.addScenario(unpackScenario(rootNode.path("Scenario"), objectMapper));
        ScenarioCheckerStepCounter counter = new ScenarioCheckerStepCounter();

        return mainScenario;
    }

    public String packJson(ArrayList<String> file) throws IOException, NullPointerException {
        /*
        Map<String, List<Map<String, String>>> packedJson = new HashMap<>();
        List<Map<String, String>> wrongSteps = new ArrayList<>();
        for (String step : file){
            Map<String, String> map = new HashMap<>();
            String[] splitStep = step.split(" ", 2);
            map.put("Number", splitStep[0]);
            map.put("Step content", splitStep[1]);
            packedJson += ",\n";
            wrongSteps.add(map);
        }
        packedJson.put("Wrong steps: ,\n", wrongSteps);
        return packedJson;
        */
         String output = "{\n   \"Wrong steps\":[";
         file.remove(file.size() - 1);
         for (String step : file){
             String[] splitStep = step.split(" ", 2);
             output += "\n       {\n        " + "\"Step number\": \"" + splitStep[0] + "\",\n";
             output += "        \"Step content\": \"" + splitStep[1] + "\"\n       },";
         }
         output = output.substring(0, output.length() - 1);
         output += "\n   ]\n}";
         return output;
    }

}
