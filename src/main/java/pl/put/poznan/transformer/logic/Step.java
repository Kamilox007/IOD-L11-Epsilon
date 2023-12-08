package pl.put.poznan.transformer.logic;

public class Step {
    private String content;
    private ConcreteScenario subScenario;

    public Step(String text, ConcreteScenario subScenario) {
        content = text;
        this.subScenario = subScenario;
    }
    public ConcreteScenario getSubScenario() {
        return subScenario;
    }
}
