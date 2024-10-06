package rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.naming.PartialResultException;

public class JUnit4RuleExceptionTester {

//    ExpectedException 객체 생성
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
//        예상 예외 타입
        expectedException.expect(IllegalArgumentException.class);
//        오류 메세지
        expectedException.expectMessage("음수의 제곱근을 구할 수 없다");

//        예외 발생
        calculator.sqrt(-1);
    }

    @Test
    public void expectArithmeticException() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("0으로 나눌 수 없다");

        calculator.divide(1, 0);
    }
}
