package cp;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static cp.PassengerUtil.getExpectedPassenger;
import static org.junit.Assert.assertEquals;

public class SimpleAppTest {

    private static final String APPLICATION_CONTEXT_XML_FILE_NAME = "classpath:application-context.xml";
//    스프링 프레임워크에서 제공하는 애플리케이션 콘텍스트 구현체 중 하나
    private ClassPathXmlApplicationContext context;
    private Passenger expectedPassenger;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML_FILE_NAME);
//        스프링 콘텍스트에서 가져온 빈과 비교하기 위해 사용
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    public void testInitPassenger() {
        Passenger passenger = (Passenger) context.getBean("passenger");
//        객체 비교를 위해 equals, hashCode 메서드가 재정의되어 있음
        assertEquals(expectedPassenger, passenger);

        System.out.println(passenger);
    }
}
