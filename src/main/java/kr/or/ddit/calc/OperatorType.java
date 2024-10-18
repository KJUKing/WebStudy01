package kr.or.ddit.calc;

import java.util.function.DoubleBinaryOperator;

public enum OperatorType{
    PLUS('+', (l, r) -> l + r),
    MINUS('-', (l, r) -> l - r),
    MULTIPLY('*', (l, r) -> l * r),
    DIVIDE('/', (l, r) -> l / r);

    private OperatorType(char sign, DoubleBinaryOperator operator) {
        this.sign = sign;
        this.operator = operator;
    }
    private DoubleBinaryOperator operator;

    private char sign;

    public char getSign() {
        return sign;
    }

    public double operate(double left, double right) {
        return operator.applyAsDouble(left, right);
    }

    //HCLC(High Cohesion Loose Coupling)
    public String getExpression(double left, double right) {
        return String.format("%f %c %f = %f", left, sign, right, operate(left, right));
    }

}
