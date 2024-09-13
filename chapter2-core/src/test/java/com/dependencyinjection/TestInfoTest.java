package com.dependencyinjection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestInfoTest {
//    기본으로 내장되어 있는 TestInfoParameterResolver는 TestInfo 객체를 파라미터로 리졸브 하는데
//    TestInfo 객체는 현재의 컨테이너 또는 테스트에 대응한다
//    생성자나 메서드에서 테스트에 관한 정보를 제공하는 데 사용한다
    
//    테스트 클래스 생성자는 디스플레이 네임이 TestInfoTest인지 검증
//    디스플레이 네임의 기본 값은 메서드명이다
    TestInfoTest(TestInfo testInfo){
        assertEquals("TestInfoTest", testInfo.getDisplayName());
    }

//    각 테스트가 실행되기 전에 실행
//    TestInfo를 가지고 디스플레이 네임이 예상한 이름인지 검증한다
    @BeforeEach
    void setUp(TestInfo testInfo){
        String displayName = testInfo.getDisplayName();
        assertTrue(displayName.equals("사용자 정의한 디스플레이 네임") || displayName.equals("testGetNameOfTheMethod(TestInfo)"));
    }

//   디스플레이 네임 검증
    @Test
    void testGetNameOfTheMethod(TestInfo testInfo){
        assertEquals("testGetNameOfTheMethod(TestInfo)", testInfo.getDisplayName());
    }

    @Test
    @DisplayName("사용자 정의한 디스플레이 네임")
    void testGetNameOfTheMethodWithDisplayNameAnnotation(TestInfo testInfo){
        assertEquals("사용자 정의한 디스플레이 네임", testInfo.getDisplayName());
    }
}
