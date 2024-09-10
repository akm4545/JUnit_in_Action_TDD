package com.displayname;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//디스플레이 네임은 테스트 목적을 알려 줄 수 있는 완전한 문장 수준으로 적는 것이 일반적이다

//전체 테스트 클래스에 적용할 디스플레이 네임
//인텔리제이에서 테스트를 마우스 오른쪽 버튼으로 클릭한 다음 run 명령으로 테스트를 싱행하면 디스플레이 네임이 표시된다
@DisplayName("@DisplayName을 사용한 테스트 클래스")
public class DisplayNameTest {
    private SUT systemUnderTest = new SUT();

    @Test
    @DisplayName("hello 테스트 대상 시스템!")
    void testHello(){
        assertEquals("Hello", systemUnderTest.hello());
    }

    @Test
    @DisplayName("😱")
    void testTalking(){
        assertEquals("How are you?", systemUnderTest.talk());
    }

//    디스플레이 네임을 따로 명시하지 않은 테스트는 메서드 이름을 표시한다
    @Test
    void testBye(){
        assertEquals("Bye", systemUnderTest.bye());
    }
}
