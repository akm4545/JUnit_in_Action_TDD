package phase3.airport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlightBuilderUtil {

//    public static phase2.airport.Flight buildFlightFromCsv() throws IOException {
//        phase2.airport.Flight flight = new Flight("AA1234", 50);
//        flight.setOrigin("London");
//        flight.setDestination("Bucharest");
//
//        try (BufferedReader reader = new BufferedReader(
//                new FileReader("src/test/resources/flights_information.csv")
//        )) {
//            String line = null;
//
//            do{
//                line = reader.readLine();
//
//                if(line != null){
//                    String[] passengerString = line.toString().split(";");
//                    phase2.airport.Passenger passenger = new Passenger(
//                            passengerString[0].trim(),
//                            passengerString[1].trim(),
//                            passengerString[2].trim());
//
//                    flight.addPassenger(passenger);
//                }
//            }while (line != null);
//        }
//
//        return flight;
//    }

    public static Flight buildFlightFromCsv(String flightNumber, int seats, String fileName) throws IOException {
        Flight flight = new Flight(flightNumber, seats);
        flight.setOrigin("London");
        flight.setDestination("Bucharest");
        flight.setDistance(2100);

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = null;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] passengerString = line.toString().split(";");
                    Passenger passenger = new Passenger(passengerString[0].trim(), passengerString[1].trim(), passengerString[2].trim());
                    flight.addPassenger(passenger);
                }
            } while (line != null);

        }

        return flight;
    }
}
