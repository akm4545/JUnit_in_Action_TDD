package rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import runners.Calculator;

public class RuleExceptionTester {

//    @Rule 어노테이션이 달린 ExpectedException 타입의 객체를 선언
//    @Rule 어노테이션은 public 인스턴스 필드나 public 인스턴스 메서드에만 붙일 수 있다
//    팩토리 메서드인 ExpectedException.none()으로 객체를 쉽게 생성할 수 있다
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Calculator calculator = new Calculator();

    @Test
    public void expectIllegalArgumentException() {
//        2. ExpectedException 인스턴스에 예외가 만들어짐
        expectedException.expect(IllegalArgumentException.class);
//        3. 오류 메시지 기록
        expectedException.expectMessage("음수의 제곱근을 구할 수 없다");

//        1. 메서드 호출
        calculator.sqrt(-1);
    }

    @Test
    public void expectArithmeticException() {
        expectedException.expect(ArithmeticException.class);
        expectedException.expectMessage("0으로 나눌 수 없다");

        calculator.divide(1, 0);
    }
}
