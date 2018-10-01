package dusinski.operations;

public class SubtractOperation implements Operation {

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }
}
