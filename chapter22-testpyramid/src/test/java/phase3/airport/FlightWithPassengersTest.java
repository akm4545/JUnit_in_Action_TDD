package phase3.airport;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import phase3.airport.annotations.FlightNumber;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

//Arquillian을 테스트 runner로 사용
@RunWith(Arquillian.class)
public class FlightWithPassengersTest {

//   정적 메서드(배포 메서드)
    @Deployment
//    ShrinkWrap 아카이브를 반환
//    테스트 아카이브는 테스트가 필요한 리소스와 클래스를 격리하기 위해 사용한다
//    테스트 아카이브는 ShrinkWrap을 사용하여 정의한다
//    마이크로배포 전략을 사용하면 테스트하려는 클래스에 정확히 집중활 수 있다
//    결과적으로는 테스트는 매우 간단하고 관리하기 쉬워지는데 현재는 컨테이너에 Passenger와 Flight 클래스가 포함되어 있다
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClasses(phase2.airport.Passenger.class, phase2.airport.Flight.class, FlightBuilderUtil.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

//    주입
    @Inject
//    어노테이션을 달아 의존성이 주입된 항공편에 식별자를 추가
    @FlightNumber(number = "AA1234")
    Flight flight;

//    객체를 모의하기 위해 어노테이션 추가
    /*@Mock
    DistancesManager distancesManager;*/

//    이전에는 @Mock 어노테이션을 사용하여 distancesManager를 모의하고 있었다
//    @Spy를 쓰면 @Mock과 달리 모의할 메서드만 모의하고 나머지 기능은 그대로 유지할 수 있다
    @Spy
    DistancesManager distancesManager;

//    @Mock 어노테이션이 달린 모의 객체를 초기화하는 데 필요한 MockitoRule 객체에 @Rule 어노테이션 추가
//    Arquillian은 JUnit5와 호환되지 않으므로 JUnit4 rule 사용
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

//    private static Map<Passenger, Integer> passengersPointsMap = new HashMap<>();
    private static Map<Passenger, Integer> passengersDistancesMap = new HashMap<>();

    @BeforeClass
    public static void setUp () {
//        passengersPointsMap.put(new Passenger("900-45-6809", "Susan Todd", "GB"), 210);
//        passengersPointsMap.put(new Passenger("900-45-6797", "Harry Christensen", "GB"), 420);
//        passengersPointsMap.put(new Passenger("123-45-6799", "Bethany King", "US"), 630);
        passengersDistancesMap.put(new Passenger("900-45-6809", "Susan Todd", "GB"), 2100);
        passengersDistancesMap.put(new Passenger("900-45-6797", "Harry Christensen", "GB"), 4200);
        passengersDistancesMap.put(new Passenger("123-45-6799", "Bethany King", "US"), 6300);
    }

//    Arquillian은 @Deployment 어노테이션이 붙은 public 정적 메서드에서 테스트 아카이브를 찾는다
//    그 다음 @Test 어노테이션이 붙은 메서드가 컨테이너 환경 내에서 수행된다
    @Test(expected = RuntimeException.class)
    public void testNumberOfSeatsCannotBeExceeded() throws IOException {
        assertEquals(50, flight.getPassengersNumber());

        flight.addPassenger(new Passenger("124-56-7890", "Michael Johnson", "US"));
    }

    @Test
    public void testAddRemovePassengers() throws IOException {
        flight.setSeats(51);
        Passenger additionalPassenger = new Passenger("124-56-7890", "Michael Johnson", "US");
        flight.addPassenger(additionalPassenger);

        assertEquals(51, flight.getPassengersNumber());

        flight.removePassenger(additionalPassenger);

        assertEquals(50, flight.getPassengersNumber());
        assertEquals(51, flight.getSeats());
    }

    @Test
    public void testFlightsDistances() {
//      getPassengersPointsMap을 실행할 떄 passengersPointsMap을 반환하도록 모의
//        when(distancesManager.getPassengersDistancesMap()).thenReturn(passengersPointsMap);
        when(distancesManager.getPassengersDistancesMap()).thenReturn(passengersDistancesMap);

        distancesManager.calculateGivenPoints();

        assertEquals(210, distancesManager.getPassengersPointsMap().get(new Passenger("900-45-6809", "Susan Todd", "GB")).longValue());
        assertEquals(420, distancesManager.getPassengersPointsMap().get(new Passenger("900-45-6797", "Harry Christensen", "GB")).longValue());
        assertEquals(630, distancesManager.getPassengersPointsMap().get(new Passenger("123-45-6799", "Bethany King", "US")).longValue());
    }
}
