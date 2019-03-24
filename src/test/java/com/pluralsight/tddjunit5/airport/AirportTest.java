package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AirportTest {

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @DisplayName("When we have an usual passenger")
        @Nested
        class UsualPassenger {

            @DisplayName("Then you can add and remove him from an economy flight")
            @Test
            public void testEconomyFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersSet().size())
                );
            }

            @DisplayName("Then you cannot add him to an economy flight more than once")
            @RepeatedTest(5)
            public void testEconomyFlightUsualPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }
                assertAll("Verify a usual passenger can be added to economy flight only once",
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(mike)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Mike"))
                );
            }

        }

        @DisplayName("When we have an VIP passenger")
        @Nested
        class VipPassenger {

            @DisplayName("Then you can add and remove him from a VIP flight")
            @Test
            public void testEconomyFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and a economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(john)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                        () -> assertEquals(false, economyFlight.removePassenger(john)),
                        () -> assertEquals(1, economyFlight.getPassengersSet().size())
                );
            }

            @DisplayName("Then you cannot add him to an economy flight more than once")
            @RepeatedTest(5)
            public void testEconomyFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(john);
                }
                assertAll("Verify a vip passenger can be added to economy flight only once",
                        () -> assertEquals(1, economyFlight.getPassengersSet().size()),
                        () -> assertTrue(economyFlight.getPassengersSet().contains(john)),
                        () -> assertTrue(new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("John"))
                );
            }

        }

    }

    @DisplayName("Given there is an business flight")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;
        private Passenger mike;
        private Passenger john;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            john = new Passenger("John", true);
        }

        @DisplayName("When we have an usual passenger")
        @Nested
        class UsualPassenger {

            @DisplayName("Then you can add or remove him for a business flight")
            @Test
            public void testBusinessFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and an business flight",
                        () -> assertEquals(false, businessFlight.addPassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(mike)),
                        () -> assertEquals(0, businessFlight.getPassengersSet().size())
                );
            }

        }

        @DisplayName("When we have VIP passenger")
        @Nested
        class VipPassenger {

            @DisplayName("Then you can add and remove him from business flight")
            @Test
            public void testBusinessFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and an business flight",
                        () -> assertEquals(true, businessFlight.addPassenger(john)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                        () -> assertEquals(false, businessFlight.removePassenger(john)),
                        () -> assertEquals(1, businessFlight.getPassengersSet().size())
                );
            }

            @DisplayName("Then you cannot add him to an business flight more than once")
            @RepeatedTest(5)
            public void testBusinessFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    businessFlight.addPassenger(john);
                }
                assertAll("Verify a VIP passenger can be added to economy flight only once",
                        () -> assertEquals(1, businessFlight.getPassengersSet().size()),
                        () -> assertTrue(businessFlight.getPassengersSet().contains(john)),
                        () -> assertTrue(new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName().equals("John"))
                );
            }

        }

    }

    @DisplayName("Given there is a premium flight")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger john;
        private Passenger mike;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            john = new Passenger("John", true);
            mike = new Passenger("Mike", false);
        }

        @DisplayName("When we have an usual passenger")
        @Nested
        class UsualPassenger {

            @DisplayName("Then you cannot add or remove him from premium flight")
            @Test
            public void testPremiumFlightUsualPassenger() {
                assertAll("Verify all conditions for a usual passenger and an premium flight",
                        () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                );
            }

            @DisplayName("When we have a VIP passenger")
            @Nested
            class VipPassenger {

                @DisplayName("Then you can add or remove him from premium flight")
                @Test
                public void testPremiumFlightVipPassenger() {
                    assertAll("Verify all conditions for a VIP passenger and an premium flight",
                            () -> assertEquals(true, premiumFlight.addPassenger(john)),
                            () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                            () -> assertEquals(true, premiumFlight.removePassenger(john)),
                            () -> assertEquals(0, premiumFlight.getPassengersSet().size())
                    );
                }

                @DisplayName("Then you cannot add him to an premium flight more than once")
                @RepeatedTest(5)
                public void testPremiumFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
                    for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                        premiumFlight.addPassenger(john);
                    }
                    assertAll("Verify a VIP passenger can be added to premium flight only once",
                            () -> assertEquals(1, premiumFlight.getPassengersSet().size()),
                            () -> assertTrue(premiumFlight.getPassengersSet().contains(john)),
                            () -> assertTrue(new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("John"))
                    );
                }

            }

        }

    }

}

