package com.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssertThrowsTest {
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

//    assertThrows 메서드는 예외가 발생했을 때 Throwable 객체를 반환한다
//    개발자는 반환한 Throwable 객체를 단언문으로 검증할 수 있다
//    이렇게 하면 시스템에 할당된 Job 객체가 없을 때 에외를 던지는지 확인할 수 있으므로 테스트 가독성이 좋아진다

    @Test
    @DisplayName("예외가 발생하는지 검증한다")
    void testExpectedException() {
//        systemUnderTest 에서 run 메서드를 호출했을 때 NoJobException이 발생하는지 검증
        assertThrows(NoJobException.class, systemUnderTest::run);
    }

    @Test
    @DisplayName("예외가 발생하고 예외에 대한 참조가 유지되는지 검증한다")
    void testCatchException() {
//        systemUnderTest.run(1000) 문장이 NoJobException을 던지는지 검증
//        throwable에 예외에 대한 참조가 유지되었는지도 검증
        Throwable throwable = assertThrows(NoJobException.class, () -> systemUnderTest.run(1000));

//        throwable이 가지고 있는 에러 메시지가 실행할 작업이 없습니다! 인지 검증 
        assertEquals("실행할 작업이 없습니다!", throwable.getMessage());
    }
}
