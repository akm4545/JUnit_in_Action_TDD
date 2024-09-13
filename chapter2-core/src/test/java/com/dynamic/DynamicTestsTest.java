package com.dynamic;

import com.predicate.PositiveNumberPredicate;
import org.junit.jupiter.api.*;

import java.util.Iterator;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class DynamicTestsTest {
//    팩토리 메서드 하나에서 음수, 0, 양수를 체크하는 세 가지 메서드 생성
//    여기서는 하나의 테스트 메서드만 구현했지만 런타임에 동적으로 세 개의 테스트를 만들어낼 수 있다
    private PositiveNumberPredicate predicate = new PositiveNumberPredicate();

//    테스트 시작 전 한 번 실행
    @BeforeAll
    static void setUpClass() {
        System.out.println("@BeforeAll method");
    }

//    테스트 종료 후 한 번 실행
    @AfterAll
    static void tearDownClass(){
        System.out.println("@AfterAll method");
    }

//    @TestFactory 어노테이션이 달린 메서드가 실행되기 전에 실행
    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach method");
    }

//    @TestFactory 어노테이션 달린 메서드가 실행된 후 실행
    @AfterEach
    void tearDown(){
        System.out.println("@AfterEach method");
    }

//    팩토리 메서드는 negative number, zero, positive number 레이블을 달고 있는 세 가지 테스트 메서드를 생성
    @TestFactory
    Iterator<DynamicTest> positiveNumberPredicateTestCases(){
//        각 테스트는 dynamicTest 메서드의 두 번째 파라미터로 주어지는 Executable 객체가 실행
        return asList(
                dynamicTest("negative number", () -> assertFalse(predicate.check(-1))),
                dynamicTest("zero", () -> assertFalse(predicate.check(0))),
                dynamicTest("positive number", () -> assertTrue(predicate.check(1)))
        ).iterator();
    }
}
