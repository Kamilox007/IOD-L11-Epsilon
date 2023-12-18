package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.MainScenario;
import pl.put.poznan.transformer.logic.ScenarioCheckerStepCounter;

import java.io.IOException;

@RestController
@RequestMapping("/scenario")
public class ScenarioQualityCheckerController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    //TODO: Jak już będą napisane metody to dopisać je do każdej funckji obsługi + dorobić jakieś logi z nimi
    @RequestMapping(value="/StepCounter/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getSteps(@PathVariable String name) {
        // log the parameters
        logger.info("Received a 'StepCounter' request, file name: " + name);
        MainScenario mainScenario = new MainScenario();
        try{
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerStepCounter counter = new ScenarioCheckerStepCounter();
            mainScenario.getScenario().acceptChecker(counter);
        } catch (IOException e){
            logger.info("Requested scenario does not exist.");
            return "There's no scenario with such name.";
        } catch (NullPointerException e) {
            logger.info("Requested scenario that is invalid.");
            return "This scenario isn't constructed properly.";
        }
        return mainScenario.getScenario().toString(); // tutaj wstawic odpowiedz metody
    }

    @RequestMapping(value="/ActorInStep/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getActors(@PathVariable String name) {
        // log the parameters
        logger.info("Received a 'ActorInStep' request, file name: " + name);
        try{
            MainScenario mainScenario = new MainScenario();
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
        } catch (IOException e){
            logger.info("Requested scenario does not exist.");
            return "There's no scenario with such name.";
        } catch (NullPointerException e) {
            logger.info("Requested scenario that is invalid.");
            return "This scenario isn't constructed properly.";
        }
        return "Wszystko git"; // tutaj wstawic odpowiedz metody
    }

    @RequestMapping(value="/KeywordCounter/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getKeyword(@PathVariable String name) {
        // log the parameters
        logger.info("Received a 'KeywordCounter' request, file name: " + name);

        try{
            MainScenario mainScenario = new MainScenario();
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
        } catch (IOException e){
            logger.info("Requested scenario does not exist.");
            return "There's no scenario with such name.";
        } catch (NullPointerException e) {
            logger.info("Requested scenario that is invalid.");
            return "This scenario isn't constructed properly.";
        }
        return "Wszystko git"; // tutaj wstawic odpowiedz metody
    }
}


