package pl.put.poznan.transformer.gui;

import pl.put.poznan.transformer.logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ScenarioGUIFrame extends JFrame implements ActionListener {
    FileFilter jsonFileFilter = new FileFilter() {
        @Override
        public boolean accept(File f) {
            String name = f.getName();
            int index = name.lastIndexOf('.');
            if(index > 0) {
                String extension = name.substring(index + 1);
                return extension.equals("json");
            }
            return f.isDirectory();
        }

        @Override
        public String getDescription() {
            return "JSON Files";
        }
    };
    private Font titleFont = new Font("Century",Font.PLAIN,50);
    private JLabel title = new JLabel("SCENARIO QUALITY CHECKER");
    private JPanel titlePanel = new JPanel();
    private JTextField fileChooserText = new JTextField();
    private JButton fileChooserButton = new JButton("C");
    private JPanel fileChooserPanel = new JPanel();
    private JFileChooser fileChooser = new JFileChooser();
    private JPanel functionPanel = new JPanel();
    private JButton functionButtonCountSteps = new JButton("Count steps");
    private JButton functionButtonCountKeywords = new JButton("Count keywords");
    private JButton functionButtonNoActorInStep = new JButton("Show steps without actor");
    private JButton functionButtonGetScenarioString = new JButton("Get scenario as text");
    private JButton functionButtonGetScenarioStringDepth = new JButton("Scenario as text (choose depth)");
    private JPanel spinnerPanel = new JPanel(new GridLayout(2,1));
    private JLabel spinnerLabel = new JLabel("Depth");
    private JSpinner depthSpinner = new JSpinner(new SpinnerNumberModel(1,1,99,1));
    private JTextArea textArea = new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(textArea);
    private JPanel textAreaPanel = new JPanel();
    private JPanel mainPanel = new JPanel();
    private  BoxLayout mainPanelLayout = new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
    private void addComponents(){
        title.setFont(titleFont);
        titlePanel.add(title);

        mainPanel.add(titlePanel);

        fileChooserText.setPreferredSize(new Dimension(1000,50));
        fileChooserText.setEditable(false);
        fileChooserText.setBackground(Color.WHITE);
        fileChooserPanel.add(fileChooserText);

        fileChooserButton.setPreferredSize(new Dimension(50,50));
        fileChooserButton.addActionListener(this);
        fileChooserButton.setBackground(Color.WHITE);
        fileChooserPanel.add(fileChooserButton);

        mainPanel.add(fileChooserPanel);
        fileChooser.setAcceptAllFileFilterUsed(false);

        functionButtonCountSteps.setPreferredSize(new Dimension(200,50));
        functionButtonCountSteps.addActionListener(this);
        functionPanel.add(functionButtonCountSteps);

        functionButtonCountKeywords.setPreferredSize(new Dimension(200,50));
        functionButtonCountKeywords.addActionListener(this);
        functionPanel.add(functionButtonCountKeywords);

        functionButtonNoActorInStep.setPreferredSize(new Dimension(200,50));
        functionButtonNoActorInStep.addActionListener(this);
        functionPanel.add(functionButtonNoActorInStep);

        functionButtonGetScenarioString.setPreferredSize(new Dimension(200,50));
        functionButtonGetScenarioString.addActionListener(this);
        functionPanel.add(functionButtonGetScenarioString);

        functionButtonGetScenarioStringDepth.setPreferredSize(new Dimension(250,50));
        functionButtonGetScenarioStringDepth.addActionListener(this);
        functionPanel.add(functionButtonGetScenarioStringDepth);

        spinnerLabel.setPreferredSize(new Dimension(50,25));
        spinnerPanel.add(spinnerLabel);

        ((JSpinner.DefaultEditor) depthSpinner.getEditor()).getTextField().setEditable(false);
        spinnerPanel.add(depthSpinner);

        functionPanel.add(spinnerPanel);

        mainPanel.add(functionPanel);

        textArea.setPreferredSize(new Dimension(1200,500));
        textArea.setEditable(false);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        textAreaPanel.add(scrollPane);

        mainPanel.add(textAreaPanel);

        mainPanel.setLayout(mainPanelLayout);
        this.add(mainPanel);
    }

    private String chooseFile(JTextField textField) {
        fileChooser.setDialogTitle("Choose a .json file");
        fileChooser.addChoosableFileFilter(jsonFileFilter);
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        else {
            return textField.getText();
        }
    }

    public ScenarioGUIFrame() throws IOException {
        super("Scenario Quality Checker");
        System.out.println(new ImageIcon("icons\\filechoosericon.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addComponents();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Clicked the file choose button
        if(e.getSource().equals(fileChooserButton)) {
            fileChooserText.setText(chooseFile(fileChooserText));
        }

        // Clicked the count steps button
        else if(e.getSource().equals(functionButtonCountSteps)) {
            String jsonPath = fileChooserText.getText();
            if(!jsonPath.equals("")) {
                JsonGuiHandler jsonGuiHandler = new JsonGuiHandler(jsonPath);
                try {
                    MainScenario mainScenario = jsonGuiHandler.unpackJson();
                    ScenarioCheckerStepCounter counter = new ScenarioCheckerStepCounter();
                    textArea.setText("Step count: "+mainScenario.acceptCheckerInt(counter).toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // Clicked the count keywords button
        else if(e.getSource().equals(functionButtonCountKeywords)) {
            String jsonPath = fileChooserText.getText();
            if(!jsonPath.equals("")) {
                JsonGuiHandler jsonGuiHandler = new JsonGuiHandler(jsonPath);
                try {
                    MainScenario mainScenario = jsonGuiHandler.unpackJson();
                    ScenarioCheckerKeywordCounter counter = new ScenarioCheckerKeywordCounter();
                    textArea.setText("Keyword count: "+mainScenario.acceptCheckerInt(counter).toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // Clicked the file choose button
        else if(e.getSource().equals(functionButtonNoActorInStep)) {
            String jsonPath = fileChooserText.getText();
            if(!jsonPath.equals("")) {
                JsonGuiHandler jsonGuiHandler = new JsonGuiHandler(jsonPath);
                try {
                    MainScenario mainScenario = jsonGuiHandler.unpackJson();
                    ScenarioCheckerNoActorInStep noActorInStep = new ScenarioCheckerNoActorInStep();
                    ArrayList<String> incorrectSteps = mainScenario.acceptCheckerArrayList(noActorInStep);
                    incorrectSteps.remove(incorrectSteps.size() - 1);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Found ");
                    stringBuilder.append(incorrectSteps.size());
                    stringBuilder.append(" steps without an actor. Listing them below:");
                    for (String step : incorrectSteps) {
                        stringBuilder.append("\n");
                        stringBuilder.append(step);
                    }
                    textArea.setText(stringBuilder.toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // Clicked the get scenario string button
        else if(e.getSource().equals(functionButtonGetScenarioString)) {
            String jsonPath = fileChooserText.getText();
            if(!jsonPath.equals("")) {
                JsonGuiHandler jsonGuiHandler = new JsonGuiHandler(jsonPath);
                try {
                    MainScenario mainScenario = jsonGuiHandler.unpackJson();
                    ScenarioCheckerStringReturner scenarioCheckerStringReturner = new ScenarioCheckerStringReturner();
                    textArea.setText(mainScenario.acceptCheckerString(scenarioCheckerStringReturner));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        // Clicked the file choose button
        else if(e.getSource().equals(functionButtonGetScenarioStringDepth)) {
            String jsonPath = fileChooserText.getText();
            if(!jsonPath.equals("")) {
                JsonGuiHandler jsonGuiHandler = new JsonGuiHandler(jsonPath);
                try {
                    MainScenario mainScenario = jsonGuiHandler.unpackJson();
                    ScenarioCheckerSpecificDepth scenarioCheckerSpecificDepth = new ScenarioCheckerSpecificDepth();
                    textArea.setText(mainScenario.acceptCheckerStringDepth(scenarioCheckerSpecificDepth, Integer.parseInt(depthSpinner.getValue().toString())));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
