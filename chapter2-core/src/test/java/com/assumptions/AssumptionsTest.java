package com.assumptions;

import com.assumptions.environment.JavaSpecification;
import com.assumptions.environment.OperationSystem;
import com.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {
//    윈도우 OS와 자바 8에서만 실행해야 하는 테스트
//    가정문이 참인 경우에만 테스트가 실행하게 만들 수 있다


    private static String EXPECTED_JAVA_VERSION = "1.8";
    private TestsEnvironment environment = new TestsEnvironment(
            new JavaSpecification(System.getProperty("java.vm.specification.version")),
            new OperationSystem(System.getProperty("os.name"), System.getProperty("os.arch"))
    );
    private SUT systemUnderTest = new SUT();
    
//    assumeTrue
//    특정 조건이 참일때만 테스트가 실행됨

//    각 테스트가 실행되기 전에 실행
//    현재 OS 환경이 윈도우라는 가정을 만족하지 않으면 테스트가 실행되지 않는다
    @BeforeEach
    void setUp() {
        assumeTrue(environment.isWindows());
    }
    
//    assumingThat 
//    테스트 전제 조건을 설정 
//    이 메서드는 테스트를 실행하기 전에 특정 조건이 충족되는지를 검사하고 조건이 충족되지 않으면 해당 테스트를 건너뛴다
    
//    자바 버전이 1.8인지 검증한다 
//    자바 버전이 1.8일 때만 시슽메에서 현재 실행 중인 작업이 없음을 검증한다
    @Test
    void testNoJobToRun() {
        assumingThat(
                () -> environment.getJavaVersion().equals(EXPECTED_JAVA_VERSION),
                () -> assertFalse(systemUnderTest.hasJobToRun())
        );
    }

    @Test
    void testJobToRun() {
        assumeTrue(environment.isAmd64Architecture());

        systemUnderTest.run(new Job());

        assertTrue(systemUnderTest.hasJobToRun());
    }

}
