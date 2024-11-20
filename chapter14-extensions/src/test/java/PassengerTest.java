import extensions.ExecutionContextExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

//테스트 확장 어노테이션
@ExtendWith({ExecutionContextExtension.class})
public class PassengerTest {

    @Test
    void testPassenger() {
        Passenger passenger = new Passenger("123-456-789", "John Smith");

        assertEquals("Passenger John Smith with identifier: 123-456-789", passenger.toString());
    }
}
