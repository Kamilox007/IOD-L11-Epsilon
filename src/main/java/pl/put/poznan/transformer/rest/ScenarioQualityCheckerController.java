package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.MainScenario;
import pl.put.poznan.transformer.logic.ScenarioCheckerKeywordCounter;
import pl.put.poznan.transformer.logic.ScenarioCheckerNoActorInStep;
import pl.put.poznan.transformer.logic.ScenarioCheckerStepCounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scenario")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    //TODO: Jak już będą napisane metody to dopisać je do każdej funckji obsługi + dorobić jakieś logi z nimi
    @RequestMapping(value="/StepCounter/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getSteps(@PathVariable String name) {
        logger.info("Received a 'StepCounter' request, file name: " + name);
        MainScenario mainScenario = new MainScenario();
        String answer = "Nie";
        try{
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerStepCounter counter = new ScenarioCheckerStepCounter();
            answer = mainScenario.acceptCheckerInt(counter).toString();
            logger.debug("This scenario has " + answer + " steps.");
        } catch (IOException e){
            logger.error("Requested scenario does not exist.");
            return "There's no scenario with such name.";
        } catch (NullPointerException e) {
            logger.error("Requested scenario that is invalid.");
            return "This scenario isn't constructed properly.";
        }
        System.out.println();
        return answer;
    }

    @RequestMapping(value="/ActorInStep/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getActors(@PathVariable String name) {
        logger.info("Received a 'ActorInStep' request, file name: " + name);
        String answer = "";
        try{
            MainScenario mainScenario = new MainScenario();
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerNoActorInStep counter = new ScenarioCheckerNoActorInStep();
            ArrayList<String> incorrectSteps = mainScenario.acceptCheckerArrayList(counter);
            Integer foundSteps = incorrectSteps.size() - 1;
            logger.debug("Found " + foundSteps + " incorrect steps in scenario");
            answer = unpack.packJson(incorrectSteps);
            logger.info("Successfully converted object to Json file");
        } catch (IOException e){
            logger.error("Requested scenario does not exist.");
        } catch (NullPointerException e) {
            logger.error("Requested scenario that is invalid.");
        }
        System.out.println();
        return answer; // tutaj wstawic odpowiedz metody
    }

    @RequestMapping(value="/KeywordCounter/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getKeyword(@PathVariable String name) {
        logger.info("Received a 'KeywordCounter' request, file name: " + name);
        String answer = "Nah";
        try{
            MainScenario mainScenario = new MainScenario();
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerKeywordCounter counter = new ScenarioCheckerKeywordCounter();
            answer = mainScenario.acceptCheckerInt(counter).toString();
            logger.debug("This scenario has " + answer + " steps that begin with a keyword");
        } catch (IOException e){
            logger.error("Requested scenario does not exist.");
            return "There's no scenario with such name.";
        } catch (NullPointerException e) {
            logger.error("Requested scenario that is invalid.");
        }
        System.out.println();
        return answer;
    }
}


