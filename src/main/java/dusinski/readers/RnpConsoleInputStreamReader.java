package dusinski.readers;

import dusinski.exceptions.RnpReadException;
import dusinski.models.RnpInputExpression;
import dusinski.services.RnpParserService;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RnpConsoleInputStreamReader implements RnpExpressionReader {

    private final Scanner scanner = new Scanner(System.in);

    public RnpConsoleInputStreamReader() {
        System.out.println("Arguments not provided, expression will be read from console input stream");
    }

    @Override
    public RnpInputExpression read() throws RnpReadException {
        return new RnpInputExpression(getArguments());
    }

    private List<String> getArguments() {
        System.out.println("Please write rnp input expression");
        String nextLine = scanner.nextLine();
        while (StringUtils.isBlank(nextLine)) {
            System.out.println("empty expression, please write expression again");
            nextLine = scanner.nextLine();
        }
        return Arrays.asList(nextLine.trim().split(RnpParserService.RNP_EXPRESSION_DELIMITER));
    }
}
