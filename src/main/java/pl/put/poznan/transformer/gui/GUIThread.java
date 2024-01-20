package pl.put.poznan.transformer.gui;

import java.io.IOException;

public class GUIThread extends Thread {
    public void run() {
        try {
            new ScenarioGUIFrame();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
