package phase3.airport;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Flight {

    private String flightNumber;
    private int seats;
    private String origin;
    private String destination;
    private boolean flying;
    private boolean takenOff;
    private boolean landed;
    Set<Passenger> passengers = new HashSet<>();

    private int distance;

    private String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$";
    private Pattern pattern = Pattern.compile(flightNumberRegex);

    public Flight(String flightNumber, int seats){
        Matcher matcher = pattern.matcher(flightNumber);

        if(!matcher.matches()){
            throw new RuntimeException("항공편명이 적절하지 않습니다");
        }

        this.flightNumber = flightNumber;
        this.seats = seats;
        this.flying = false;
        this.takenOff = false;
        this.landed = false;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats){
        if(passengers.size() > seats){
            throw new RuntimeException("현재 승객 수보다 적은 좌석을 설정할 수 없습니다");
        }

        this.seats = seats;
    }

    public int getPassengersNumber() {
        return passengers.size();
    }

    public boolean addPassenger(Passenger passenger){
        if(passengers.size() >= seats){
            throw new RuntimeException("항공편의 좌석 수보다 더 많은 승객을 추가할 수 없습니다");
        }

        return passengers.add(passenger);
    }

    public boolean removePassenger(Passenger passenger){
        return passengers.remove(passenger);
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin){
        if(takenOff){
            throw new RuntimeException("이륙한 후에는 출발지를 바꿀 수 없습니다");
        }

        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination){
        if(landed){
            throw new RuntimeException("착륙한 후에는 목적지를 바꿀 수 없습니다");
        }

        this.destination = destination;
    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isTakenOff() {
        return takenOff;
    }

    public boolean isLanded() {
        return landed;
    }

    @Override
    public String toString() {
        return "Flight " + getFlightNumber() + " from " + getOrigin() + " to " + getDestination();
    }

    public void takeOff() {
        System.out.println(this + " is taking off");

        flying = true;
        takenOff = true;
    }

    public void land() {
        System.out.println(this + " is landing");

        flying = false;
        landed = true;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance){
        this.distance = distance;
    }
}
