package com.lifecycle;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SUTTest {
//    SUT(system under test, 테스트 대상 시스템)를 초기화하고 기본 작업을 수행할 수는 있지만
//    추가 작업을 수행할 수는 없는지 검증한다고 하자

//    이때 JUnit5의 생애 주기 메서드는 각 테스트 전후에 테스트 대상 시스템이 초기화되고 종료되게 한다
//    테스트 메서드는 시스템이 기본 작업을 수행할 수 있고 추가 작업을 수행할 수는 없는지 검증한다

    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

//    @BeforeAll 어노테이션이 붙은 메서드는 전체 테스트가 실행되기 전에 한 번 실행된다
//    @BeforeAll 어노테이션이 붙은 메서드는 테스트 클래스에 @TestInstance(Lifecycle.PER_CLASS) 어노테이션 잆다면 정적(static)으로 선언해야 한다
    @BeforeAll
    static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

//    @BeforeEach 어노테이션이 붙은 메서드는 각 테스트가 실행되기 전에 실행된다
//    하단에 테스트 메서드가 두 개 있으므로 총 두 번 실행된다
    @BeforeEach
    void setUp() {
        systemUnderTest = new SUT("테스트 대상 시스템");
    }

//    @AfterAll 어노테이션이 붙은 메서드는 전체 테스트가 실행된 후 한 번 실행된다
//    @AfterAll 어노테이션이 붙은 메서드는 테스트 클래스에 @TestInstance(Lifecycle.PRE_CLASS) 어노테이션이 없다면 정적으로 선언해야 한다
    @AfterAll
    static void tearDownClass(){
        resourceForAllTests.close();
    }

//    @AfterEach 어노테이션이 붙은 메서드는 각 테스트가 실행된 이후에 실행된다
    @AfterEach
    void tearDown() {
        systemUnderTest.close();
    }

    @Test
    void testRegularWork(){
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();

        assertTrue(canReceiveRegularWork);
    }

//    @Test 어노테이션이 붙은 테스트 메서드 두 개는 서로 간에 독립적으로 실행된다
    @Test
    void testAdditionalWork(){
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();

        assertFalse(canReceiveAdditionalWork);
    }
}
