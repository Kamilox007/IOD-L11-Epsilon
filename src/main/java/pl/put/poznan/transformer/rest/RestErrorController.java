package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RestErrorController implements ErrorController {

    private static final Logger logger = LoggerFactory.getLogger(ScenarioQualityCheckerController.class);

    @RequestMapping("/error")
    @ResponseBody
    String error(HttpServletRequest request) {
        logger.error("Received an invalid request");
        System.out.println();
        return "Aplikacja nie obsługuje takiego zapytania";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
