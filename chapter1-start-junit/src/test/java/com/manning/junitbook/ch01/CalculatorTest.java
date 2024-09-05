package com.manning.junitbook.ch01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//테스트 클래스 정의
//클래스명에 Test라는 접미사를 붙이는 것이 테스트를 작성하는 일반적인 관행
public class CalculatorTest {

//    Test 어노테이션을 달아 해당 메서드가 단위 테스트 메서드임을 나타낸다
//    Test 어노테이션만 달면 JUnit은 해당 메서드를 테스트로 인식한다
    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        
//        음이 아닌 오차 안에서 예상 값과 실제 값이 같다고 단언한다
//        assertEquals에서 판정한 동등성은 Double.equals(Object)나
//        Double.compare(double, double)의 결과와 일관된다

//        Calculator 클래스에 10과 50을 전달하므로 assertEquals에 합게가 60이 될것이다
//        (10과 50은 정수로 소수점 이하기 0이니 두 수를 더할 때 부동 소수점 오류가 없을 것이다
//        그리하여 delta에는 0을 전달)

//        예상 값 60과 result를 비교하여 같지 않으면 JUnit은 확인되지 않은 예외를 던지고 검사는 실패한다

//        delta가 0인 경우는 대부분 무시해도 상관없다
//        delta는 부동 소수점 계산을 포함하여 오차가 생길 수 있는 계산에 자주 쓰인다
//        delta를 이용해 범위 비교를 할 수 있는데 actual이 expected-delta와 expected+delta 범위
//        내에 있으면 테스트가 통과된다

//        delta를 사용하는 assertEquals 메서드는 반올림이나 절사 오류가 있을 수 잇는 수학적 계산을 하거나
//        파일 수정 날짜를 비교할 때 유용하다
        assertEquals(60, result, 0);
    }
}
