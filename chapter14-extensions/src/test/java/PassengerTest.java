import extensions.DatabaseOperationsExtension;
import extensions.ExecutionContextExtension;
import jdbc.Passenger;
import jdbc.PassengerDao;
import jdbc.PassengerExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

//테스트 확장 어노테이션
@ExtendWith({ExecutionContextExtension.class,
            DatabaseOperationsExtension.class})
public class PassengerTest {

    private PassengerDao passengerDao;

    public PassengerTest(PassengerDao passengerDao){
        this.passengerDao = passengerDao;
    }

    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("123-456-789", "John Smith");

        assertEquals("Passenger John Smith with identifier: 123-456-789", passenger.toString());
    }

    @Test
    void testInsertPassenger() throws PassengerExistsException {
        Passenger passenger = new Passenger("123-456-789", "John Smith");
        passengerDao.insert(passenger);

        assertEquals("John Smith", passengerDao.getById("123-456-789").getName());
    }
}
