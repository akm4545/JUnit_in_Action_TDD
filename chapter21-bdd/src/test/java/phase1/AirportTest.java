package phase1;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
//                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals(1, economyFlight.getPassengerSet().size()),
//                        () -> assertEquals("Mike", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
//                        () -> assertEquals(0, economyFlight.getPassengers().size())
                        () -> assertEquals(0, economyFlight.getPassengerSet().size())
                );
            }

            @DisplayName("Then 이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다")
//            5번 반복 테스트 진행
//            반복 테스트는 RepetitionInfo 객체를 사용할 수 있다
            @RepeatedTest(5)
            public void testEconomyFlightRegularPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
//                테스트가 진행될 때마다 RepetitionInfo객체에 지정한 횟수만큼 승객을 추가한다
                for(int i=0; i<repetitionInfo.getCurrentRepetition(); i++){
                    economyFlight.addPassenger(mike);
                }

                assertAll("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없는지 검증",
//                        () -> assertEquals(1, economyFlight.getPassengers().size()),
//                        () -> assertTrue(economyFlight.getPassengers().contains(mike)),
//                        () -> assertTrue(economyFlight.getPassengers().get(0).getName().equals("Mike"))
                        () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                        () -> assertTrue(economyFlight.getPassengerSet().contains(mike)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName().equals("Mike"))
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
//                        () -> assertEquals(1, economyFlight.getPassengers().size()),
                        () -> assertEquals(1, economyFlight.getPassengerSet().size()),
//                        () -> assertEquals("James", economyFlight.getPassengers().get(0).getName()),
                        () -> assertEquals("James", new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(james)),
//                        () -> assertEquals(1, economyFlight.getPassengers().size())
                        () -> assertEquals(1, economyFlight.getPassengerSet().size())
                );
            }

            @DisplayName("Then 이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없다")
            @RepeatedTest(5)
            public void testEconomyFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(james);
                }
                assertAll("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                        () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                        () -> assertTrue(economyFlight.getPassengerSet().contains(james)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName().equals("James"))
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
//                        () -> assertEquals(0, businessFlight.getPassengers().size()),
                        () -> assertEquals(0, businessFlight.getPassengerSet().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(mike)),
//                        () -> assertEquals(0, businessFlight.getPassengers().size())
                        () -> assertEquals(0, businessFlight.getPassengerSet().size())
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
//                            () -> assertEquals(1, businessFlight.getPassengers().size()),
                            () -> assertEquals(1, businessFlight.getPassengerSet().size()),
                            () -> assertEquals(false, businessFlight.removePassenger(james)),
//                            () -> assertEquals(1, businessFlight.getPassengers().size())
                            () -> assertEquals(1, businessFlight.getPassengerSet().size())
                    );
                }

                @DisplayName("Then 비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없다")
                @RepeatedTest(5)
                public void testBusinessFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                    for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                        businessFlight.addPassenger(james);
                    }
                    assertAll("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                            () -> assertEquals(1, businessFlight.getPassengerSet().size()),
                            () -> assertTrue(businessFlight.getPassengerSet().contains(james)),
                            () -> assertTrue(new ArrayList<>(businessFlight.getPassengerSet()).get(0).getName().equals("James"))
                    );
                }
            }
        }
    }

    @DisplayName("Given 프리미엄 항공편에서")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
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
//                        () -> assertEquals(0, premiumFlight.getPassengers().size()),
                        () -> assertEquals(0, premiumFlight.getPassengerSet().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
//                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                        () -> assertEquals(0, premiumFlight.getPassengerSet().size())
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
//                        () -> assertEquals(1, premiumFlight.getPassengers().size()),
                        () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(james)),
//                        () -> assertEquals(0, premiumFlight.getPassengers().size())
                        () -> assertEquals(0, premiumFlight.getPassengerSet().size())
                );
            }

            @DisplayName("Then 프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없다")
            @RepeatedTest(5)
            public void testPremiumFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    premiumFlight.addPassenger(james);
                }
                assertAll("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                        () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                        () -> assertTrue(premiumFlight.getPassengerSet().contains(james)),
                        () -> assertTrue(new ArrayList<>(premiumFlight.getPassengerSet()).get(0).getName().equals("James"))
                );
            }
        }
    }
}
