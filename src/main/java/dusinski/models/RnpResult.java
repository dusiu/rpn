package dusinski.models;

import dusinski.services.RnpParserService;

import java.util.List;
import java.util.stream.Collectors;

public class RnpResult {

    private final String inputExpression;
    private final double result;

    public RnpResult(List<String> inputExpression, double result) {
        this.inputExpression = inputExpression.stream().collect(Collectors.joining(RnpParserService.RNP_EXPRESSION_DELIMITER));
        this.result = result;
    }

    public double getResult() {
        return result;
    }

    public String getInputExpression() {
        return inputExpression;
    }

    @Override
    public String toString() {
        return "RnpResult{" +
                "inputExpression='" + inputExpression + '\'' +
                ", result=" + result +
                '}';
    }
}
