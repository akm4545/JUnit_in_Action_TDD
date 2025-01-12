package phase3.airport;

import org.junit.jupiter.api.Test;
import phase1.airport.Passenger;

import static org.junit.jupiter.api.Assertions.*;


public class PassengerTest {

    @Test
    public void testPassengerCreation() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");

        assertNotNull(passenger);
    }

    @Test
    public void testNonUsPassengerCreation() {
        Passenger passenger = new Passenger("900-45-6789", "John Smith", "GB");

        assertNotNull(passenger);
    }

    @Test
    public void testCreatePassengerWithInvalidSsn() {
        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("123-456-789", "John Smith", "US");
                });

        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("900-45-6789", "John Smith", "US");
                });
    }

    @Test
    public void testCreatePassengerWithInvalidNonUsIdentifier() {
        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("900-456-789", "John Smith", "GB");
                });
        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("123-45-6789", "John Smith", "GB");
                });
    }

    @Test
    public void testCreatePassengerWithInvalidCountryCode() {
        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("900-45-6789", "John Smith", "GJ");
                });
    }

    @Test
    public void testSetInvalidSsn() {
        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
                    passenger.setIdentifier("123-456-789");
                });
    }

    @Test
    public void testSetValidSsn() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        passenger.setIdentifier("123-98-7654");
        assertEquals("123-98-7654", passenger.getIdentifier());
    }

    @Test
    public void testSetValidNonUsIdentifier() {
        Passenger passenger = new Passenger("900-45-6789", "John Smith", "GB");
        passenger.setIdentifier("900-98-7654");
        assertEquals("900-98-7654", passenger.getIdentifier());
    }

    @Test
    public void testSetInvalidCountryCode() {
        assertThrows(RuntimeException.class,
                () -> {
                    Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
                    passenger.setCountryCode("GJ");
                });
    }

    @Test
    public void testSetValidCountryCode() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        passenger.setCountryCode("GB");
        assertEquals("GB", passenger.getCountryCode());
    }

    @Test
    public void testPassengerToString() {
        Passenger passenger = new Passenger("123-45-6789", "John Smith", "US");
        passenger.setName("John Brown");
        assertEquals("Passenger John Brown with identifier: 123-45-6789 from US", passenger.toString());
    }



}
