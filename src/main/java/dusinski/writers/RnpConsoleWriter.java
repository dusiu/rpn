package dusinski.writers;

import dusinski.exceptions.RnpWriteException;
import dusinski.models.RnpResult;

public class RnpConsoleWriter implements RnpExpressionWriter {

    @Override
    public void write(RnpResult rnpResult) throws RnpWriteException {
        System.out.println("input expression: " + rnpResult.getInputExpression() + ", result:" + rnpResult.getResult());
    }
}
