package dusinski.operations;

public class SumOperation implements Operation {

    @Override
    public String getOperator() {
        return "+";
    }

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber;
    }
}
