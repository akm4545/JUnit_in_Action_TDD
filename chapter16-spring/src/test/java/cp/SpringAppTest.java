package cp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static cp.PassengerUtil.getExpectedPassenger;
import static org.junit.Assert.assertEquals;

//SpringJUnit4ClassRunner를 활용하여 테스트를 실행
@RunWith(SpringJUnit4ClassRunner.class)
// 클래스 패스의 application-context 파일에서 콘텍스트를 구성하도록 만듦
//@ContextConfiguration은 스프링 프레임워크에서 제공하는 spring-test 의존성에 속하며
//콘텍스트를 프로그래밍 방식으로 구성했던 것을 대체할 수 있다
//application-context 파일에서 콘텍스트를 생성하고 사전에 정의한 빈을 테스트에 주입할 수 있다
@ContextConfiguration("classpath:application-context.xml")
public class SpringAppTest {

//    스프링이 컨테이너에서 미리 선언한 passenger 필드에 동일한 타입의 빈을 찾아 주입
//    이를 위해서는 컨테이너에 Passenger 타입의 단일한 빈이 있어야 한다
    @Autowired
    private Passenger passenger;
    private Passenger expectedPassenger;

    @Before
    public void setUp() {
        expectedPassenger = getExpectedPassenger();
    }

    @Test
    public void testInitPassenger() {
        assertEquals(expectedPassenger, passenger);
        System.out.println(passenger);
    }
}
