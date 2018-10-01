package dusinski.readers;

import dusinski.exceptions.RnpReadException;
import dusinski.models.RnpInputExpression;

import java.util.Arrays;
import java.util.List;

public class RnpMainMethodArgumentReader implements RnpExpressionReader {

    private final List<String> arguments;

    public RnpMainMethodArgumentReader(String[] arguments) {
        this.arguments = Arrays.asList(arguments);
    }

    @Override
    public RnpInputExpression read() throws RnpReadException {
        return new RnpInputExpression(arguments);
    }
}
