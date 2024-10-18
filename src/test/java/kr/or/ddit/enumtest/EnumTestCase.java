package kr.or.ddit.enumtest;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.DoubleBinaryOperator;

class EnumTestCase {

    enum OperatorType{
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
    }

    @Test
    void test() {

    }
}
