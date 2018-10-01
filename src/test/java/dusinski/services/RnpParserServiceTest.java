package dusinski.services;

import dusinski.exceptions.IncorrectRpnExpression;
import dusinski.exceptions.IncorrectRpnOperation;
import dusinski.exceptions.RnpReadException;
import dusinski.exceptions.RnpWriteException;
import dusinski.models.RnpResult;
import dusinski.operations.SubtractOperation;
import dusinski.operations.SumOperation;
import dusinski.readers.RnpMainMethodArgumentReader;
import dusinski.writers.RnpConsoleWriter;
import dusinski.writers.RnpExpressionWriter;
import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class RnpParserServiceTest {

    @Test(expected = RnpReadException.class)
    public void shouldThrowExceptionWhenEmptyReader() {
        new RnpParserService(null, new RnpConsoleWriter(), Collections.emptySet());
    }

    @Test(expected = RnpWriteException.class)
    public void shouldThrowExceptionWhenEmptyWriter() {
        new RnpParserService(new RnpMainMethodArgumentReader(new String[]{}), null, Collections.emptySet());
    }

    @Test(expected = IncorrectRpnOperation.class)
    public void shouldThrowExceptionWhenEmptyOperationSet() {
        new RnpParserService(new RnpMainMethodArgumentReader(new String[]{}), new RnpConsoleWriter(), Collections.emptySet());
    }

    @Test(expected = IncorrectRpnExpression.class)
    public void shouldThrowExceptionWhenMissingOperator() {
        String[] inputString = {"2", "3", "+"};
        AtomicReference<RnpResult> result = new AtomicReference<>();
        RnpExpressionWriter outputWriter = result::set;

        RnpParserService parserService = new RnpParserService(new RnpMainMethodArgumentReader(inputString), outputWriter, Collections.singleton(new SubtractOperation()));
        parserService.parseAndSaveExpression();
    }

    @Test
    public void shouldCalculateOperationProperly() {
        String[] inputString = {"2", "3", "+"};
        AtomicReference<RnpResult> result = new AtomicReference<>();
        RnpExpressionWriter outputWriter = result::set;

        RnpParserService parserService = new RnpParserService(new RnpMainMethodArgumentReader(inputString), outputWriter, Collections.singleton(new SumOperation()));
        parserService.parseAndSaveExpression();

        assertThat(result.get()).isNotNull();
        assertThat(result.get().getInputExpression()).isEqualTo(Arrays.stream(inputString).collect(Collectors.joining(RnpParserService.RNP_EXPRESSION_DELIMITER)));
        assertThat(result.get().getResult()).isEqualTo(5, Offset.offset(0.1));
    }


}
