package phase4.airport;

import org.junit.jupiter.api.Test;
import phase1.airport.Flight;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void testFlightCreation() {
        Flight flight = new Flight("AA123", 300);

        assertNotNull(flight);
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class,
                () -> {
                    Flight flight = new Flight("AA12", 100);
                });

        assertThrows(RuntimeException.class,
                () -> {
                    Flight flight = new Flight("AA12345", 100);
                });
    }

    @Test
    public void testValidFlightNumber() {
        Flight flight = new Flight("AA345", 100);
        assertNotNull(flight);

        flight = new Flight("AA3456", 100);
        assertNotNull(flight);
    }

    @Test
    public void testAddPassengers() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");

        for(int i=0; i<flight.getSeats(); i++){
            flight.addPassenger();
        }

        assertEquals(50, flight.getPassengers());
        assertThrows(RuntimeException.class,
                () -> {
                    flight.addPassenger();
                });
    }

    @Test
    public void testSetInvalidSeats() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");

        for (int i = 0; i < flight.getSeats(); i++) {
            flight.addPassenger();
        }

        assertEquals(50, flight.getPassengers());
        assertThrows(RuntimeException.class,
                () -> {
                    flight.setSeats(49);
                });
    }

    @Test
    public void testSetValidSeats() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");

        for (int i = 0; i < flight.getSeats(); i++) {
            flight.addPassenger();
        }

        assertEquals(50, flight.getPassengers());

        flight.setSeats(52);
        assertEquals(52, flight.getSeats());
    }

    @Test
    public void testChangeOrigin() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();

        assertEquals(true, flight.isFlying());
        assertEquals(true, flight.isTakenOff());
        assertEquals(false, flight.isLanded());

        assertThrows(RuntimeException.class,
                () -> {
                    flight.setOrigin("Manchester");
                });
    }

    @Test
    public void testLand() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();

        assertEquals(true, flight.isTakenOff());
        assertEquals(false, flight.isLanded());

        flight.land();

        assertEquals(true, flight.isTakenOff());
        assertEquals(true, flight.isLanded());
        assertEquals(false, flight.isFlying());
    }

    @Test
    public void testChangeDestination() {
        Flight flight = new Flight("AA1234", 50);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.takeOff();
        flight.land();

        assertThrows(RuntimeException.class,
                () -> {
                    flight.setDestination("Sibiu");
                });
    }
}
