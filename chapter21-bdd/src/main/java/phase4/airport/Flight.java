package phase4.airport;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Flight {

    private String id;

//    List<Passenger> passengers = new ArrayList<Passenger>();
//    리팩토링
    Set<Passenger> passengers = new HashSet<>();

    public Flight(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

//    public List<Passenger> getPassengers() {
//        return Collections.unmodifiableList(passengers);
//    }
//    리팩토링
    public Set<Passenger> getPassengerSet() {
        return Collections.unmodifiableSet(passengers);
    }

    public abstract boolean addPassenger(Passenger passenger);

    public abstract boolean removePassenger(Passenger passenger);
}
