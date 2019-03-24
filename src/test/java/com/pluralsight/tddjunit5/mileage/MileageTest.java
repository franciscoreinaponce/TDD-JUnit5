package com.pluralsight.tddjunit5.mileage;

import com.pluralsight.tddjunit5.airport.Flight;
import com.pluralsight.tddjunit5.airport.Passenger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MileageTest {

    private Mileage mileage;

    @BeforeAll
    void beforeAll() {
        mileage = new Mileage();
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {"1; e; Mike; false; 349", "2; b; John; true; 278", "3; e; Mike; false; 319", "4; p; John; true; 817", "4; e; Mike; false; 623", "5; e; John; true; 978"})
    void checkGivenPoints(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
        for (Passenger passenger : flight.getPassengersSet()) {
            mileage.addMileage(passenger, flight.getDistance());
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/flights_information.csv")
    void checkGivenPointsWithCsvInput(@ConvertWith(FlightArgumentConverter.class) Flight flight) {
        for (Passenger passenger : flight.getPassengersSet()) {
            mileage.addMileage(passenger, flight.getDistance());
        }
    }

    @AfterAll
    void afterAll() {
        mileage.calculateGivenPoints();
        assertEquals(64, mileage.getPassengerPointsMap().get(new Passenger("Mike", false)).intValue());
        assertEquals(207, mileage.getPassengerPointsMap().get(new Passenger("John", true)).intValue());
        System.out.println(mileage.getPassengerPointsMap());
    }

}
