package dusinski.services;

import dusinski.exceptions.IncorrectRpnExpression;
import dusinski.exceptions.IncorrectRpnOperation;
import dusinski.exceptions.RnpReadException;
import dusinski.exceptions.RnpWriteException;
import dusinski.models.RnpInputExpression;
import dusinski.models.RnpResult;
import dusinski.operations.Operation;
import dusinski.readers.RnpExpressionReader;
import dusinski.writers.RnpExpressionWriter;

import java.util.Optional;
import java.util.Set;
import java.util.Stack;

public class RnpParserService {
    private static final int MIN_STACK_SIZE_FOR_CALCULATION = 2;
    public static final String RNP_EXPRESSION_DELIMITER = " ";
    private final RnpWhitespacesRemover rnpWhitespacesRemover = new RnpWhitespacesRemover();
    private final RnpExpressionValidator rnpExpressionValidator;
    private final Set<Operation> operations;
    private final RnpExpressionReader rnpExpressionReader;
    private final RnpExpressionWriter rnpExpressionWriter;
    private final Stack<Double> valuesStack = new Stack<>();

    public RnpParserService(RnpExpressionReader rnpExpressionReader, RnpExpressionWriter rnpExpressionWriter, Set<Operation> operations) {
        if (rnpExpressionReader == null) {
            throw new RnpReadException("RnpExpressionReader is null");
        }
        if (rnpExpressionWriter == null) {
            throw new RnpWriteException("RnpExpressionWriter is null");
        }
        if (operations == null || operations.isEmpty()) {
            throw new IncorrectRpnOperation("There should be at least 1 defined operation");
        }
        this.rnpExpressionReader = rnpExpressionReader;
        this.rnpExpressionWriter = rnpExpressionWriter;
        this.operations = operations;
        this.rnpExpressionValidator = new RnpExpressionValidator(operations);
    }

    public void parseAndSaveExpression() {
        saveRnpResult(parseRnpExpression());
    }

    private RnpResult parseRnpExpression() {
        RnpInputExpression inputExpression = rnpWhitespacesRemover.removeWhitespaces(rnpExpressionReader.read());
        rnpExpressionValidator.validateExpressionRemoveWhitespaces(inputExpression);
        return new RnpResult(inputExpression.getRnpExpressionElement(), calculateRnpExpression(inputExpression));
    }

    private void saveRnpResult(RnpResult rnpResult) {
        rnpExpressionWriter.write(rnpResult);
    }

    private double calculateRnpExpression(RnpInputExpression read) {
        for (String value : read.getRnpExpressionElement()) {
            Optional<Operation> operation = getOperation(value);
            if (operation.isPresent()) {
                if (valuesStack.size() < MIN_STACK_SIZE_FOR_CALCULATION) {
                    throw new IncorrectRpnExpression("Wrong expression, can't proceed calculation on empty valuesStack");
                }
                valuesStack.push(performOperation(operation.get()));
            } else {
                valuesStack.add(Double.parseDouble(value));
            }
        }
        if (valuesStack.size() > 1) {
            throw new IncorrectRpnExpression("There are multiple values, but there isn't any operation to perform!");
        }
        return valuesStack.pop();
    }

    private double performOperation(Operation operation) {
        double youngerElement = valuesStack.pop();
        double olderElement = valuesStack.pop();
        return operation.calculate(olderElement, youngerElement);
    }

    private Optional<Operation> getOperation(String value) {
        return operations.stream().filter(operation -> operation.getOperator().equals(value)).findFirst();
    }

}
