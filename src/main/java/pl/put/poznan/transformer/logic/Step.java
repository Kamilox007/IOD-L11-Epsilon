package pl.put.poznan.transformer.logic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder({"Content", "Steps"})
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

    public String getContent() {
        return content;
    }
}
