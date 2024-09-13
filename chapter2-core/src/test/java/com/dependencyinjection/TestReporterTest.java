package com.dependencyinjection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import java.util.HashMap;
import java.util.Map;

public class TestReporterTest {
    
//    publishEntry
//    테스트 수행 중에 사용자 정의 메시지나 정보를 콘솔에 출력하거나 로그로 남길 수 있게 해준다
//    키 = 벨류 형식으로 출력한다

    @Test
    void testReportSingleValue(TestReporter testReporter){
//        timestamp = 2024-09-13T11:33:29.256922800, value = Single value
        testReporter.publishEntry("Single value");
    }

    @Test
    void testReportKeyValuePair(TestReporter testReporter){
//        timestamp = 2024-09-13T11:33:29.242921800, Key = Value
        testReporter.publishEntry("Key", "Value");
    }

    @Test
    void testReportMultipleKeyValuePairs(TestReporter testReporter){
        Map<String, String> values = new HashMap<>();
        values.put("user", "John");
        values.put("password", "secret");

//        timestamp = 2024-09-13T11:33:29.254922300, password = secret, user = John
        testReporter.publishEntry(values);
    }
}
