package dusinski.services;

import dusinski.exceptions.IncorrectRpnExpression;
import dusinski.models.RnpInputExpression;
import dusinski.operations.Operation;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RnpExpressionValidator {

    private final Set<String> operators;

    RnpExpressionValidator(Set<Operation> operations) {
        this.operators = operations.stream().map(Operation::getOperator).collect(Collectors.toSet());
    }

    public void validateExpressionRemoveWhitespaces(RnpInputExpression expression) throws IncorrectRpnExpression {
        List<String> notParsableElements = expression.getRnpExpressionElement().stream()
                .filter(element -> !operators.contains(element))
                .filter(element -> !NumberUtils.isCreatable(element))
                .collect(Collectors.toList());
        if (!notParsableElements.isEmpty()) {
            throw new IncorrectRpnExpression("There is element which is not operator or number: " + notParsableElements);
        }
        long operatorsNumber = expression.getRnpExpressionElement().stream().filter(operators::contains).count();
        long valuesNumber = expression.getRnpExpressionElement().stream().filter(element -> !operators.contains(element)).count();
        if (isNotProperExpression(operatorsNumber, valuesNumber)) {
            throw new IncorrectRpnExpression("Expression not proper, there should be always 1 number of values more than operators, actually: "
                    + operatorsNumber + " operators, and: " + valuesNumber + " values");
        }
    }

    private boolean isNotProperExpression(long operatorsNumber, long valuesNumber) {
        return valuesNumber != (operatorsNumber + 1);
    }
}
