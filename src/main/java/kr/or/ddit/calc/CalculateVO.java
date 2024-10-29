package kr.or.ddit.calc;

import java.io.Serializable;
import java.util.Objects;

/**
 * VO(Value Object), DTO(Data Transfer Object), JavaBean
 *
 * 1. 값을 저장할 수 있는 property 정의.
 *  property : java bean 규약에 따라 정의 된 field
 * 2. property에 대해 캡슐화
 * 3. 캡슐화된 property에 접근할 수 있는 메소드 제공.(getter setter)
 *  get[set] + propertyname ==> camel case
 * 4. java bean 객체에 대한 상태 비교 방법 제공. : equals 재정의
 * 5. 상태를 확인할 수 있는 방법 제공 : toString 재정의
 * 6. 직렬화 가능 객체 표현
 *
 */
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
