package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.gui.GUIThread;
import pl.put.poznan.transformer.gui.ScenarioGUIFrame;

import java.io.IOException;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class ScenarioQualityCheckerApp {

    public static void main(String[] args) throws IOException {
        GUIThread guiThread = new GUIThread();
        guiThread.start();
        SpringApplication.run(ScenarioQualityCheckerApp.class, args);
    }
}
