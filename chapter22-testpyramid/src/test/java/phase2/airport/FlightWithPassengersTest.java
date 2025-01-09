package phase2.airport;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
                .addClasses(Passenger.class, Flight.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

//    주입
    @Inject
    Flight flight;

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
}
