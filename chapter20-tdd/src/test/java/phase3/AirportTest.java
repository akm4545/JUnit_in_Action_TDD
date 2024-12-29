package phase3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import phase2.BusinessFlight;
import phase2.EconomyFlight;
import phase2.Flight;
import phase2.Passenger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {

    @DisplayName("Given 이코노미 항공편에서")
    @Nested
    class EconomyFlightTest {
        private phase2.Flight economyFlight;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
        }

        @Test
        @DisplayName("이코노미 항공편과 일반 승객에 관한 테스트")
        public void testEconomyFlightRegularPassenger() {
            phase2.Passenger mike = new phase2.Passenger("Mike", false);

            assertEquals("1", economyFlight.getId());
            assertEquals(true, economyFlight.addPassenger(mike));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("Mike", economyFlight.getPassengers().get(0).getName());

            assertEquals(true, economyFlight.removePassenger(mike));
            assertEquals(0, economyFlight.getPassengers().size());
        }

        @Test
        @DisplayName("이코노미 항공편과 VIP 승객에 관한 테스트")
        public void testEconomyFlightVipPassenger() {
            phase2.Passenger james = new phase2.Passenger("James", true);

            assertEquals("1", economyFlight.getId());
            assertEquals(true, economyFlight.addPassenger(james));
            assertEquals(1, economyFlight.getPassengers().size());
            assertEquals("James", economyFlight.getPassengers().get(0).getName());

            assertEquals(false, economyFlight.removePassenger(james));
            assertEquals(1, economyFlight.getPassengers().size());
        }
    }

    @DisplayName("Given 비즈니스 항공편에서")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
        }

        @Test
        @DisplayName("비즈니스 항공편과 일반 승객에 관한 테스트")
        public void testBusinessFlightRegularPassenger() {
            phase2.Passenger mike = new phase2.Passenger("Mike", false);

            assertEquals(false, businessFlight.addPassenger(mike));
            assertEquals(0, businessFlight.getPassengers().size());
            assertEquals(false, businessFlight.removePassenger(mike));
            assertEquals(0, businessFlight.getPassengers().size());
        }

        @Test
        @DisplayName("비즈니스 항공편과 VIP 승객에 관한 테스트")
        public void testBusinessFlightVipPassenger() {
            phase2.Passenger james = new Passenger("James", true);

            assertEquals(true, businessFlight.addPassenger(james));
            assertEquals(1, businessFlight.getPassengers().size());
            assertEquals(false, businessFlight.removePassenger(james));
            assertEquals(1, businessFlight.getPassengers().size());
        }
    }
}
