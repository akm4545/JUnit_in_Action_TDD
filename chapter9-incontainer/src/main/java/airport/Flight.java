package airport;

import java.util.HashSet;
import java.util.Set;

public class Flight {
    private String flightNumber;
    private int seats;
    Set<Passenger> passengers = new HashSet<>();

    public Flight(String flightNumber, int seats){
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats){
        if(passengers.size() > seats){
            throw new RuntimeException("현재 승객 수보다 적은 좌석을 설정할 수 없습니다!");
        }
        this.seats = seats;
    }

    public int getNumberOfPassengers() {
        return passengers.size();
    }

    public boolean addPassenger(Passenger passenger){
        if(passengers.size() >= seats){
            throw new RuntimeException("좌석 수보다 더 많은 승객을 추가할 수 없습니다!");
        }

        return passengers.add(passenger);
    }

    public boolean removePassenger(Passenger passenger){
        return passengers.remove(passenger);
    }

    @Override
    public String toString() {
        return "Flight " + getFlightNumber();
    }
}
