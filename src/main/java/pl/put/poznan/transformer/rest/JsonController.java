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
/**
 * Class responsible for loading and packing scenarios from .json format
 *
 * @author Epsilon
 * @version 2.0
 */
public class JsonController {
    /**
     * Global variable holding path to a folder containing all scenarios in .json files
     */
    private String path;

    /*TODO: Na razie kod obsługuje błąd gdy w scenariuszu nie ma jakiegoś słowa kluczowego, np. Title
       oraz gdy nie ma scenariusza o podanej nazwie.
       Można jeszcze dorobić, by sprawdzał, czy nie ma jakiś nadmiernych pól (z naciskiem na 'można').
     */

    /**
     * Class constructor
     * @param fileName name of a .json file containing a scenario we want to pack/unpack
     */
    public JsonController(String fileName){
        this.path = "./database/" + fileName + "/";
    }

    /**
     * Unpacks a JSON representation of a scenario into a ConcreteScenario object.
     *
     * @param rootNode The root node of the JSON structure representing the scenario.
     * @param objectMapper The ObjectMapper used for JSON processing and mapping.
     * @return A ConcreteScenario object representing the unpacked scenario
     */
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

    /**
     * Unpacks a JSON file into a MainScenario object.
     *
     * @return A MainScenario object containing information parsed from the JSON file.
     * @throws IOException If an error occurs while reading the JSON file.
     * @throws NullPointerException If the required elements are not found in the JSON file.
     */
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
    /**
     * Packs a list of steps into a JSON-formatted string representing wrong steps.
     *
     *
     * @param file The list of steps to be packed into the JSON string.
     * @return A JSON-formatted string representing wrong steps based on the provided list.
     * @throws IOException If an error occurs during string construction.
     * @throws NullPointerException If the provided list is null or empty.
     */
    public String packJson(ArrayList<String> file) throws IOException, NullPointerException {
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
