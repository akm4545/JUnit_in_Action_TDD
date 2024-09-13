package com.repeated;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestReporter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RepeatedTestsTest {
//    반복 테스트에서 각 반복은 생애 주기 콜백과 extension을 완벽하게 지원하는 일반적인 @Test 어노테이션이 붙은 테스트 
//    메서드와 비슷한 메커니즘으로 동작한다 
//    이는 set과 list가 정적으로 선언된 이유이기도 하다
    private static Set<Integer> integerSet = new HashSet<>();
    private static List<Integer> integerList = new ArrayList<>();

//    add 메서드가 항상 안정적이고 동일한 결과를 가져다 주는지 반복 검증
//    value가 반복 횟수
    @RepeatedTest(value = 5, name = "{displayName} - repetition {currentRepetition}/{totalRepetitions}")
    @DisplayName("Test add operation")
    void addNumber() {
        Calculator calculator = new Calculator();

        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
    }

//    컬렉션이 적절한 동작을 수행하는지 반복 검증
    @RepeatedTest(value = 5, name = "the list contains {currentRepetition} elements(s), the set contains 1 element")
    void testAddingToCollections(TestReporter testReporter, RepetitionInfo repetitionInfo){
        integerSet.add(1);
        integerList.add(repetitionInfo.getCurrentRepetition());

        testReporter.publishEntry("Repetition number", String.valueOf(repetitionInfo.getCurrentRepetition()));
        assertEquals(1, integerSet.size());
        assertEquals(repetitionInfo.getCurrentRepetition(), integerList.size());
    }
}
