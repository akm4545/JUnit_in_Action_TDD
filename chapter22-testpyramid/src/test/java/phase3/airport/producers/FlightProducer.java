package phase3.airport.producers;

import phase3.airport.Flight;
import phase3.airport.FlightBuilderUtil;
import phase3.airport.annotations.FlightNumber;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {

//    컨테이너가 자동으로 이 메서드를 호출하여 완성한 항공편 객체를 생성하도록 함
//    그 결과 @Inject 어노테이션이 붙은 Flight 필드에 주입할 수 있게 된다
    @Produces
//    생산자 메서드를 사용하여 사용자 정의한 객체를 주입할 수 있다
    @FlightNumber(number = "AA1234")
    public Flight createFlight() throws IOException {
//        항공편 객체 반환
        return FlightBuilderUtil.buildFlightFromCsv("AA1234", 50, "src/teset/resources/flights_information.csv");
    }
}
