package dusinski;

import dusinski.operations.Operation;
import dusinski.operations.SubtractOperation;
import dusinski.operations.SumOperation;
import dusinski.readers.RnpConsoleInputStreamReader;
import dusinski.readers.RnpExpressionReader;
import dusinski.readers.RnpMainMethodArgumentReader;
import dusinski.services.RnpParserService;
import dusinski.writers.RnpConsoleWriter;

import java.util.Set;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

public class RnpParserApplication {

    private static final Set<Operation> OPERATIONS = Set.of(new SubtractOperation(), new SumOperation());

    public static void main(String[] args) {
        RnpExpressionReader rnpExpressionReader;
        if (isEmpty(args)) {
            rnpExpressionReader = new RnpConsoleInputStreamReader();
        } else {
            rnpExpressionReader = new RnpMainMethodArgumentReader(args);
        }
        new RnpParserService(rnpExpressionReader, new RnpConsoleWriter(), OPERATIONS).parseAndSaveExpression();
    }
}
