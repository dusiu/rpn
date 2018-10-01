package dusinski.services;

import dusinski.models.RnpInputExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class RnpWhitespacesRemover {

    public RnpInputExpression removeWhitespaces(RnpInputExpression expression) {
        List<String> rnpExpressionElements = expression.getRnpExpressionElement();
        if (containWhiteSpaces(expression)) {
            System.out.println("There was whitespaces in expression, removing them");
            rnpExpressionElements = rnpExpressionElements.stream()
                    .filter(element -> !StringUtils.isWhitespace(element))
                    .collect(Collectors.toList());
            return new RnpInputExpression(rnpExpressionElements);
        }
        return expression;
    }

    private boolean containWhiteSpaces(RnpInputExpression expression) {
        return expression.getRnpExpressionElement()
                .stream()
                .anyMatch(StringUtils::isWhitespace);
    }

}
