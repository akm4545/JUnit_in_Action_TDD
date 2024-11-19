package flights;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void testFlightCreation(){
        Flight flight = new Flight("AA123", 100);
        assertNotNull(flight);
    }

    @Test
    public void testInvalidFlightNumber(){
        assertThrows(RuntimeException.class,
                () -> {
                    Flight flight = new Flight("AA12", 100);
                });
    }
}
