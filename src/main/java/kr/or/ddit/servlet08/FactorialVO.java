package kr.or.ddit.servlet08;

import java.io.Serializable;
import java.util.Objects;

public class FactorialVO implements Serializable {
    private int operand;
    private long result;
    private StringBuffer expression;

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
        this.expression = new StringBuffer();
        this.result = factorial(operand, expression);
    }

    public long getResult() {
        return result;
    }

    public StringBuffer getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "FactorialVO{" +
                "operand=" + operand +
                ", result=" + result +
                ", expression=" + expression +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactorialVO that = (FactorialVO) o;
        return operand == that.operand && result == that.result && Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, result, expression);
    }

    long factorial(int num, StringBuffer expr) {
        if (num <= 0) {
            throw new IllegalArgumentException("num must be greater than 0");
        }
        if (num == 1) {
            expr.append(num);
            return 1;
        } else {
            expr.append(num + " * ");
            return num * factorial(num - 1, expr);
        }
    }
}
