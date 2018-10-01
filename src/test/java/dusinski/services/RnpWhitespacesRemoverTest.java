package dusinski.services;

import dusinski.models.RnpInputExpression;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class RnpWhitespacesRemoverTest {

    private RnpWhitespacesRemover rnpWhitespacesRemover = new RnpWhitespacesRemover();


    @Test
    public void shouldDoNothingWhenEmptyExpression() {
        RnpInputExpression rnpInputExpression = new RnpInputExpression(Collections.emptyList());

        RnpInputExpression result = rnpWhitespacesRemover.removeWhitespaces(rnpInputExpression);

        assertThat(result.getRnpExpressionElement()).isEmpty();
    }

    @Test
    public void shouldRemoveWhitespaces() {
        String arg1 = "5";
        String arg2 = "4";
        String arg3 = "\t";
        String arg4 = "+";
        RnpInputExpression rnpInputExpression = new RnpInputExpression(Arrays.asList(arg1, arg2, arg3, arg4));

        RnpInputExpression result = rnpWhitespacesRemover.removeWhitespaces(rnpInputExpression);

        assertThat(result.getRnpExpressionElement()).isNotEmpty();
        assertThat(result.getRnpExpressionElement()).hasSize(3);
        assertThat(result.getRnpExpressionElement()).contains(arg1);
        assertThat(result.getRnpExpressionElement()).contains(arg2);
        assertThat(result.getRnpExpressionElement()).contains(arg4);
        assertThat(result.getRnpExpressionElement()).doesNotContain(arg3);
    }

}
