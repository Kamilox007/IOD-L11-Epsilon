package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for handling spring REST Api
 *
 * @author Epsilon
 * @version 2.0
 */

@RestController
@RequestMapping("/scenario")
public class ScenarioQualityCheckerController {
    /**
     * Logger for debugging the flow of requests in our app
     */
    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    /**
     *
     * @param name name of a .json file that is situated in database folder.
     * @return a single value that shows how many steps there are in the scenario
     */
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

    /**
     *
     * @param name name of a .json file that is situated in database folder.
     * @return a string containing numbers and description of all the steps that does not begin with the actor name.
     */
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
        return answer;
    }

    /**
     *
     * @param name name of a .json file that is situated in database folder.
     * @return a single value that shows how many steps are beginning with the keyword in a scenario
     */
    @RequestMapping(value="/KeywordCounter/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getKeyword(@PathVariable String name) {
        logger.info("Received a 'KeywordCounter' request, file name: " + name);
        String answer = "Nah";
        try{
            MainScenario mainScenario = new MainScenario();
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerStringReturner counter = new ScenarioCheckerStringReturner();
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

    /**
     *
     * @param name name of a .json file that is situated in database folder.
     * @return whole scenario converted to string
     */
    @RequestMapping(value="/ToString/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getScenarioString(@PathVariable String name) {
        logger.info("Received a 'ToString' request, file name: " + name);
        String answer = "Nah";
        try{
            MainScenario mainScenario = new MainScenario();
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerStringReturner stringer = new ScenarioCheckerStringReturner();
            answer = mainScenario.acceptCheckerString(stringer);
        } catch (IOException e){
            logger.error("Requested scenario does not exist.");
            return "There's no scenario with such name.";
        } catch (NullPointerException e) {
            logger.error("Requested scenario that is invalid.");
        }
        System.out.println();
        return answer;
    }

    /**
     *
     * @param name name of a .json file that is situated in database folder.
     * @param depth value determinating how deep in subscenarios we want to descend
     * @return a scenario as a string with discarded subscenarios after certain depth
     */
    @RequestMapping(value="/ToStringDepth/{name}/{depth}", method = RequestMethod.GET, produces = "application/json")
    public String getScenarioString(@PathVariable String name, @PathVariable int depth) {
        logger.info("Received a 'ToStringDepth' request, file name: " + name);
        String answer = "Nah";
        try{
            MainScenario mainScenario;
            JsonController unpack = new JsonController(name);
            mainScenario = unpack.unpackJson();
            logger.info("Successfully converted json to object");
            ScenarioCheckerSpecificDepth depther = new ScenarioCheckerSpecificDepth();
            answer = mainScenario.acceptCheckerStringDepth(depther, depth);
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


