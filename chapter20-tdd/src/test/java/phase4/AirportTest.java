package phase4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportTest {
    
    @DisplayName("Given 이코노미 항공편에서")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;

//        기존에 있던 중첩 테스트 외에 테스트를 하나 더 추가할 것이므로 프리미엄 항공편과
//        관련된 모든 테스트에서 필드를 재사용하기 위해 flight 필드와 passenger 필드를 각 중첩
//        테스트 안에서 선언하는 것으로 수정
        private Passenger mike;
        private Passenger james;

//        테스트 실행 전 두 필드를 초기화
        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

//        여러 승객 유형을 테스트하기 위해 중첩 테스트 안에 다시 중첩 테스트를 만든다
        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {
            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다")
            public void testEconomyFlightRegularPassenger() {
//                assertAll 메서드를 사용해 결괏값 검증 로직을 리팩토링
//                이전의 단언문을 하나로 묶을 수 있어 유리하게 읽힌다
                assertAll("일반 승객은 이코노미 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("Mike", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {
            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다")
            public void testEconomyFlightVipPassenger() {
                assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals("James", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengers().size())
                );
            }
        }
    }

    @DisplayName("Given 비즈니스 항공편에서")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {
            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다")
            public void testBusinessFlightRegularPassenger() {
                assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                        () -> assertEquals(false, businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengers().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengers().size())
                );
            }

            @Nested
            @DisplayName("When VIP 승객은")
            class VipPassenger {
                @Test
                @DisplayName("Then 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다")
                public void testBusinessFlightVipPassenger() {
                    assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                            () -> assertEquals(true, businessFlight.addPassenger(james)),
                            () -> assertEquals(1, businessFlight.getPassengers().size()),
                            () -> assertEquals(false, businessFlight.removePassenger(james)),
                            () -> assertEquals(1, businessFlight.getPassengers().size())
                    );
                }
            }
        }
    }

    @DisplayName("Given 프리미엄 항공편에서")
    @Nested
    class PremiumFlightTest {
        private phase4.Flight premiumFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {
            @Test
            @DisplayName("Then 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다")
            public void testPremiumFlightRegularPassenger() {
                assertAll("프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                        () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {
            @Test
            @DisplayName("Then 프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다")
            public void testPremiumFlightVipPassenger() {
                assertAll("VIP 승객은 프리미엄 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                        () -> assertEquals(true, premiumFlight.addPassenger(james)),
                        () -> assertEquals(1, premiumFlight.getPassengers().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(james)),
                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                );
            }

        }
    }
}
