package phase1;

public class Airport {

    public static void main(String[] args){
        Flight economyFlight = new Flight("1", "Economy");
        Flight businessFlight = new Flight("2", "Business");

        Passenger james = new Passenger("James", true);
        Passenger mike = new Passenger("Mike", false);

        businessFlight.addPassenger(james);
        businessFlight.removePassenger(james);
        businessFlight.addPassenger(mike);

        economyFlight.addPassenger(mike);

        System.out.println("비즈니스 항공편 승객 리스트:");
        for (Passenger passenger : businessFlight.getPassengersList()){
            System.out.println(passenger.getName());
        }

        System.out.println("이코노미 항공편 승객 리스트:");
        for(Passenger passenger : economyFlight.getPassengersList()){
            System.out.println(passenger.getName());
        }
    }
}
