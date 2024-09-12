package com.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertAllTest {
//  오버로딩 한  assertAll 메서드 중의 일부
//  assertAll 메서드의 첫 번째 파라미터인 heading은 assertAll 메서드 내에서 단언문이 어떤 일을 하는지를 알려 준다
//  assertAll 메서드의 실패 메시지는 그룹 내의 모든 단언문에 대한 자세한 정보를 제공할 수 있다
//  또한 테스트가 무엇을 하는지 분명하게 알려줄 수 있도록 @DisplayName 어노테이션을 사용한다  
    
//  executable 객체의 컬렉션을 assertAll 메서드의 파라미터로 사용한다
//  이렇게 하면 executable 객체가 예외를 던지지 않는다는 것을 더 간결하고 편리하게 단언할 수 있다

//  assertAll 메서드의 좋은 점은 일부 단언문이 실패하더라도 모든 단언문을 항상 검증한다는 것이다
//  executable 객체가 실패하더라도 뒤의 객체가 실행된다
//  JUnit4에서는 여러 개의 assert 메서드 중에 하나가 실패한다면 그 실패로 인해 전체 메서드가 중단되었다

    @Test
    @DisplayName("테스트 대상 시스템을 검증하지 않았다")
    void testSystemNotVerified() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

//        단언문의 조건이 읽기 쉽게 작성되어 있다
//        executable 객체 중 하나가 예외를 던지는 경우 표시할 메세지를 파라미터로 작성
        assertAll("테스트 대상 시스템을 검증하지 않았는지 확인",
//                assertEquals 메서드로 검증할 executable 객체
                () -> assertEquals("테스트 대상 시스템", systemUnderTest.getSystemName()),
//                assertFalse 메서드로 검증할 executable 객체 전달
                () -> assertFalse(systemUnderTest.isVerified()));
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증한다")
    void testSystemUnderVerification() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        systemUnderTest.verify();

        assertAll("테스트 대상 시스템을 검증했는지 확인",
                () -> assertEquals("테스트 대상 시스템", systemUnderTest.getSystemName()),
                () -> assertTrue(systemUnderTest.isVerified()));
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증한다")
    void testSystemUnderVerification2() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

        systemUnderTest.verify();

//        Supplier<String> 인터페이를 사용하면 테스트가 성공했을 때 오류 메시지를 만들지 않는다
//        이런 식으로 람다식이나 메서드 참조를 사용하여 시스템을 검증하면 성능을 조금 높일 수 있다
        
//        단언문에서 람다식을 파라미터로 사용할 때의 이점은 지연 전달 덕분에 성능이 향상된다는 데 있다
//        즉 테스트가 조건을 충족하면 람다식이 호출되지 않는다
//        예전 스타일로 테스트를 작성했을 때에는 불가능했던 방법이다
        
//        assertTrue 메서드를 사용하여 조건이 참인지 검증
//        실패하면 테스트 대상 시스템을 검증했는지 확인 메세지가 지언 전달
        assertTrue(systemUnderTest.isVerified(), () -> "테스트 대상 시스템을 검증했는지 확인");
    }

    @Test
    @DisplayName("테스트 대상 시스템을 검증하지 않았다")
    void testSystemNotUnderVerification3() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

//        거짓인지 검증
        assertFalse(systemUnderTest.isVerified(), () -> "테스트 대상 시스템을 검증하지 않았는지 확인");
    }

    @Test
    @DisplayName("테스트 대상 시스템은 현재 작업이 없다")
    void testNoJob() {
        SUT systemUnderTest = new SUT("테스트 대상 시스템");

//        객체가 존재하는지 (null 체크) 검증
        assertNull(systemUnderTest.getCurrentJob(), () -> "테스트 대상 시스템은 현재 작업이 없는지 확인");
    }
}
