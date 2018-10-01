package dusinski.operations;

public interface Operation {

    String getOperator();

    double calculate(double firstNumber, double secondNumber);
}
