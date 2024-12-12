package sb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import sb.model.Passenger;
import sb.registration.PassengerRegistrationEvent;
import sb.registration.RegistrationManager;

import static org.junit.jupiter.api.Assertions.assertTrue;

// 스프링 부트가 생성한 테스트에 @SpringBootTest 어노테이션을 추가한다
//@SpringBootTest 어노테이션은 @EnableAutoConfiguration과 함께 현재 테스트 클래스 패키지와 그 서브 페키지에서
//스프링 빈을 검색한다
@SpringBootTest
@EnableAutoConfiguration
//XML 설정의 빈을 불러올때 사용
@ImportResource("classpath:application-context.xml")
public class RegistrationTest {

    @Autowired
    private Passenger passenger;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testPersonRegistration() {
        registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));

        System.out.println("After registering:");
        System.out.println(passenger);

        assertTrue(passenger.isRegistered());
    }
}
