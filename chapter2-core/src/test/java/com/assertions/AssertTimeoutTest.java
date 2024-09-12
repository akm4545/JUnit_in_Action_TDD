package com.assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class AssertTimeoutTest {

//    assertTimeout, assertTimeoutPreemptively 메서드로 시스템의 성능이 충분한지 확인할 수 있다
//    제한 시간 내에 작업을 수행할 수 있는지 검증할 수 있다
    private SUT systemUnderTest = new SUT("테스트 대상 시스템");

    @Test
    @DisplayName("작업을 마칠 때까지 기다리는 assertTimeout 메서드")
    void testTimeout() throws InterruptedException {
        systemUnderTest.addJob(new Job("Job 1"));

//        assertTimeout 메서드는 executable 객체가 작업을 마칠 때까지 기다린다
//        만약 테스트가 주어진 시간을 초과하면 execution exceeded timeout of 500 ms by 193 ms 와 같은 메시지로 테스트가 얼마나 늦어졌는지 알려 준다
        assertTimeout(ofMillis(500), () -> systemUnderTest.run(200));
    }

    @Test
    @DisplayName("시간이 지나면 작업을 중지시키는 assertTimeoutPreemptively 메서드")
    void testTimeoutPreemptively() throws InterruptedException{
        systemUnderTest.addJob(new Job("Job 1"));

//        assertTimeoutPreemptively 메서드는 시간이 지마녀 executable 객체를 중지시킨다
//        만약 테스트가 실패한다면 execution timed out after 500 ms 와 같이 지정한 시간 안에 테스트가 완료되지 못했다고 알려 준다
        assertTimeoutPreemptively(ofMillis(500), () -> systemUnderTest.run(200));
    }
}
