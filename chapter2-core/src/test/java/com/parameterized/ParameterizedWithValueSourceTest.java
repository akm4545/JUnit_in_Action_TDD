package com.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithValueSourceTest {
    private WordCounter wordCounter = new WordCounter();

//    @ParameterizedTest 어노테이션을 사용하여 해당 테스트가 파라미터를 사용한 테스트임을 명시
    @ParameterizedTest
//    테스트 메서드의 파라미터로 전달할 값을 특정 
//    테스트 메서느는 @ValueSource에 적혀 있는 문자열의 수만큼 총 두 번 실행된다
    @ValueSource(strings = { "Check three parameters", "JUnit in Action" })
    void testWordsInSentence(String sentence){
        assertEquals(3, wordCounter.countWords(sentence));
    }
}
