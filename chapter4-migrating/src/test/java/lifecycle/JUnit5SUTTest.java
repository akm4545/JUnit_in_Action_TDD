package lifecycle;

import org.junit.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JUnit5SUTTest {
    private static ResourceForAllTests resourceForAllTests;
    private SUT systemUnderTest;

//    @BeforeClass와 @BeforeAll 어노테이션이 달린 메서드는 모든 테스트 메서드가 실행되기 전에 한 번 실행
//    이떄 메서드는 정적이어야 한다
//    JUnit4 에서는 @BeforeClass가 달린 메서드는 반드시 public 접근 제어자를 사용해야 했다
//    JUnit5 에서는 @BeforeAll이 달린 메서드라도 테스트 클래스에 @TestInstance(Lifecycle.PER_CLASS) 어노테이션을 추가하면
//    비정적으로 선언할 수 있다
    @BeforeAll
    static void setUpClass() {
        resourceForAllTests = new ResourceForAllTests("테스트를 위한 리소스");
    }

//    @AfterClass와 @AfterAll 어노테이션이 달린 메서드는 모든 테스트 메서드가 실행된 후에 한 번 실행
//    나머지는 @BeforeAll과 가틍ㅁ
    @AfterAll
    static void tearDownClass() {
        resourceForAllTests.close();
    }

//    @Before와 @BeforeEach 어노테이션이 달린 메서드는 각각의 테스트 전에 실행
//    JUnit4 에서는 @Before가 달린 메서드는 반드시 public 접근 제어자를 사용해야 했다
    @BeforeEach
    void setUp() {
        systemUnderTest = new SUT("테스트 대상 시스템");
    }

//    @After와 @AfterEach 어노테이션이 달린 메서드는 각각의 테스트 후에 실행
//    나머지는 @BeforeEach와 같음
    @AfterEach
    void tearDown() {
        systemUnderTest.close();
    }

//    JUnit4에서 @Test가 달린 메서드는 반드시 public 접근 제어자를 사용해야 했다
    @Test
    void testRegularWork() {
        boolean canReceiveRegularWork = systemUnderTest.canReceiveRegularWork();

        assertTrue(canReceiveRegularWork);
    }
    
    @Test
    void testAdditionalWork() {
        boolean canReceiveAdditionalWork = systemUnderTest.canReceiveAdditionalWork();
        
        assertFalse(canReceiveAdditionalWork);
    }
    
//    JUnit4에서는 @Ignore를 사용해 테스트 실행을 비활성화했지만
//    JUnit5 에서는 @Disabled 어노테이션을 사용한다
    @Test
    @Disabled
    void myThirdTest() {
        assertEquals(2, 1, "2와 1이 다른지 검증");
    }
}
