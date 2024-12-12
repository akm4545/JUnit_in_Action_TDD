package beans;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import sb.model.Country;
import sb.model.Passenger;

//@TestConfiguration 어노테이션을 사용하여 빈을 TestBeans 클래스에 등록
@TestConfiguration
public class TestBeans {

//  @Bean 어노테이션을 사용하여 빈 생성 메서드로 사용  
    @Bean
    Passenger createPassenger() {
        Passenger passenger = new Passenger("John Smith");

        passenger.setCountry(createCountry());
        passenger.setIsRegistered(false);

        return passenger;
    }

    @Bean
    Country createCountry() {
        Country country = new Country("USA", "US");

        return country;
    }
}
