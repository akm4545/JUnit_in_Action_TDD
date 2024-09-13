package com.parameterized;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedWithCsvSourceTest {
    private WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
//    @CsvSource 어노테이션에 나열된 CSV 형식의 문자열에서 구문을 분석하여 가져온다
//    따라서 총 세 번 실행된다
    @CsvSource({"2, Unit testing", "3, JUnit in Action", "4, Write solid Java code"})
    void testWordsInSentence(int expected, String sentence){
//        각 행의 첫 번째 값은 expected에 할당
//        두 번째 값은 sentence에 할당
        assertEquals(expected, wordCounter.countWords(sentence));
    }
}
