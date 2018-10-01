package dusinski.readers;

import dusinski.exceptions.RnpReadException;
import dusinski.models.RnpInputExpression;

public interface RnpExpressionReader {

    RnpInputExpression read() throws RnpReadException;
}
