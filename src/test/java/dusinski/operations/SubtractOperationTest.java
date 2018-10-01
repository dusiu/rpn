package dusinski.operations;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubtractOperationTest {

    private Operation subtract = new SubtractOperation();

    @Test
    public void shouldCalculateProperly() {
        double number1 = 10;
        double number2 = 5;

        double result = subtract.calculate(number1, number2);

        assertThat(result).isEqualTo(5, Offset.offset(0.1));
    }
}
