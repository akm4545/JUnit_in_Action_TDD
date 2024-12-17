package sb;

import beans.FlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import sb.model.Flight;
import sb.model.Passenger;
import sb.registration.PassengerRegistrationEvent;
import sb.registration.RegistrationManager;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
//현재 패키지와 그 이하 모든 패키지에서 스프링 빈을 찾는다
@Import(FlightBuilder.class)
public class FlightTest {

    @Autowired
    private Flight flight;

    @Autowired
    private RegistrationManager registrationManager;

    @Test
    void testFlightPassengersRegistration() {
//        등록된 fligth 빈에서 모든 승객 조회
        for(Passenger passenger : flight.getPassengers()){
            assertFalse(passenger.isRegistered());

            registrationManager.getApplicationContext().publishEvent(new PassengerRegistrationEvent(passenger));
        }

        System.out.println("모든 승객이 등록되었는지 확인");

        for(Passenger passenger : flight.getPassengers()){
            assertTrue(passenger.isRegistered());
        }
    }
}
