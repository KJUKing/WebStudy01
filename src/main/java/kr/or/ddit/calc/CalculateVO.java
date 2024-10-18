package kr.or.ddit.calc;

import java.io.Serializable;
import java.util.Objects;

public class CalculateVO implements Serializable {
    private double left;
    private double right;
    private OperatorType operator;

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public OperatorType getOperator() {
        return operator;
    }

    public void setOperator(OperatorType operator) {
        this.operator = operator;
    }

    public double getResult() {
        return operator.operate(left, right);
    }

    public String getExpression() {
        return operator.getExpression(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculateVO that = (CalculateVO) o;
        return Double.compare(left, that.left) == 0 && Double.compare(right, that.right) == 0 && operator == that.operator;
    }

    @Override
    public String toString() {
        return "CalculateVO{" +
                "left=" + left +
                ", right=" + right +
                ", operator=" + operator +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, operator);
    }
}
