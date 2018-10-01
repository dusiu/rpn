package dusinski.readers;

import dusinski.models.RnpInputExpression;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RnpMainMethodArgumentReaderTest {


    @Test
    public void shouldLoadArgsProperly() {
        String arg1 = "one";
        String arg2 = "two";
        String arg3 = "three";
        String[] arguments = {arg1, arg2, arg3};

        RnpExpressionReader rnpExpressionReader = new RnpMainMethodArgumentReader(arguments);
        RnpInputExpression result = rnpExpressionReader.read();

        assertThat(result.getRnpExpressionElement()).hasSize(3);
        assertThat(result.getRnpExpressionElement().get(0)).isEqualTo(arg1);
        assertThat(result.getRnpExpressionElement().get(1)).isEqualTo(arg2);
        assertThat(result.getRnpExpressionElement().get(2)).isEqualTo(arg3);
    }
}
