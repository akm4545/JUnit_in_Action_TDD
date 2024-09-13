package com.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

public class ParameterizedWithEnumSourceTest {
    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
//    @EnumSOurce의 대상을 Sentences.class 열거형 전체로 잡는다
//    따라서 총 세 번 실행된다
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentences){
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    @ParameterizedTest
//    Enum을 사용하지만 대상 인스턴스를 JUNIT_IN_ACTION, THREE_PARAMETERS만으로 한정
//    따라서 총 두 번 실행
    @EnumSource(value = Sentences.class, names = {"JUNIT_IN_ACTION", "THREE_PARAMETERS"})
    void testSelectedWordsInSentence(Sentences sentences){
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    @ParameterizedTest
//    mode = EXCLUDE 제외
//    제외 대상 = THREE_PARAMETERS
    @EnumSource(value = Sentences.class, mode = EXCLUDE, names = {"THREE_PARAMETERS"})
    void testExcludedWordsInSentence(Sentences sentences){
        assertEquals(3, wordCounter.countWords(sentences.value()));
    }

    enum Sentences {
        JUNIT_IN_ACTION("JUnit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three paramters");

        private final String sentence;

        Sentences(String sentence){
            this.sentence = sentence;
        }

        public String value(){
            return sentence;
        }
    }
}
