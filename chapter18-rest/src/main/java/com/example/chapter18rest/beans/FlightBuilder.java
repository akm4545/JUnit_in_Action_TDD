package com.example.chapter18rest.beans;

import com.example.chapter18rest.model.Country;
import com.example.chapter18rest.model.Flight;
import com.example.chapter18rest.model.Passenger;
import org.springframework.context.annotation.Bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FlightBuilder {

    private Map<String, Country> countryMap = new HashMap<>();

    public FlightBuilder() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("/src/main/resources/countries_information.csv"))){
            String line = null;

            do{
                line = reader.readLine();

                if(line != null){
                    String[] countriesString = line.toString().split(";");

                    Country country = new Country(countriesString[0].trim(), countriesString[1].trim());
                    countryMap.put(countriesString[1].trim(), country);
                }
            }while (line != null);
        }
    }

    @Bean
    Map<String, Country> getCountriesMap() {
        return Collections.unmodifiableMap(countryMap);
    }

    @Bean
    public Flight buildFlightFromCsv() throws IOException{
        Flight flight = new Flight("AA1234", 20);

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/flight_information.csv"))){
            String line = null;

            do{
                line = reader.readLine();

                if(line != null){
                    String[] passengerString = line.toString().split(";");

                    Passenger passenger = new Passenger(passengerString[0].trim());
                    passenger.setCountry(countryMap.get(passengerString[1].trim()));
                    passenger.setIsRegistered(false);

                    flight.addPassenger(passenger);
                }
            }while (line != null);
        }

        return flight;
    }
}
