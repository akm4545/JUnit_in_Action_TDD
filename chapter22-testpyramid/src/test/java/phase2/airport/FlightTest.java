package phase2.airport;

import org.junit.jupiter.api.Test;
import phase1.airport.Flight;

import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void testFlightCreation() {
        phase1.airport.Flight flight = new phase1.airport.Flight("AA123", 300);

        assertNotNull(flight);
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class,
                () -> {
                    phase1.airport.Flight flight = new phase1.airport.Flight("AA12", 100);
                });

        assertThrows(RuntimeException.class,
                () -> {
                    phase1.airport.Flight flight = new phase1.airport.Flight("AA12345", 100);
                });
    }

    @Test
    public void testValidFlightNumber() {
        phase1.airport.Flight flight = new phase1.airport.Flight("AA345", 100);
        assertNotNull(flight);

        flight = new phase1.airport.Flight("AA3456", 100);
        assertNotNull(flight);
    }

    @Test
    public void testAddPassengers() {
        phase1.airport.Flight flight = new phase1.airport.Flight("AA1234", 50);
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
        phase1.airport.Flight flight = new phase1.airport.Flight("AA1234", 50);
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
        phase1.airport.Flight flight = new phase1.airport.Flight("AA1234", 50);
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
        phase1.airport.Flight flight = new phase1.airport.Flight("AA1234", 50);
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
        phase1.airport.Flight flight = new phase1.airport.Flight("AA1234", 50);
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
        phase1.airport.Flight flight = new Flight("AA1234", 50);
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
