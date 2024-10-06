package rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

public class JUnit5ExceptionTester {
    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
//        assertThrow 메서드를 사용해 IllegalArgumentException을 던진다고 단언
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> calculator.sqrt(-1));

//        오류 메세지 검증
        assertEquals("음수의 제곱근을 구할 수 없다", throwable.getMessage());
    }

    @Test
    public void expectArithmeticException() {
        Throwable throwable = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));

        assertEquals("0으로 나눌 수 없다", throwable.getMessage());
    }
}
