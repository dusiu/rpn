package dusinski.operations;

import org.assertj.core.data.Offset;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SumOperationTest {

    private Operation sum = new SumOperation();

    @Test
    public void shouldCalculateProperly() {
        double number1 = 10;
        double number2 = 5;

        double result = sum.calculate(number1, number2);

        assertThat(result).isEqualTo(15, Offset.offset(0.1));
    }

}
