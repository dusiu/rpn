package dusinski.models;

import java.util.List;

public class RnpInputExpression {

    private final List<String> rnpExpressionElements;

    public RnpInputExpression(List<String> rnpExpressionElements) {
        this.rnpExpressionElements = rnpExpressionElements;
    }

    public List<String> getRnpExpressionElement() {
        return rnpExpressionElements;
    }

    @Override
    public String toString() {
        return "RnpInputExpression{" +
                "rnpExpressionElements=" + rnpExpressionElements +
                '}';
    }
}
