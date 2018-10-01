package dusinski.writers;

import dusinski.exceptions.RnpWriteException;
import dusinski.models.RnpResult;

public interface RnpExpressionWriter {

    void write(RnpResult rnpResult) throws RnpWriteException;
}
