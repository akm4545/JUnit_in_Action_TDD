package phase4.airport;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DistancesManager {

    private static final int DISTANCE_FACTOR = 10;

    private Map<Passenger, Integer> passengersDistancesMap = new HashMap<>();
    private Map<Passenger, Integer> passengersPointsMap = new HashMap<>();


    public Map<Passenger, Integer> getPassengersDistancesMap() {
        return Collections.unmodifiableMap(passengersDistancesMap);
    }

    public Map<Passenger, Integer> getPassengersPointsMap() {
        return Collections.unmodifiableMap(passengersPointsMap);

    }

    public void addDistance(Passenger passenger, int distance){
        if(passengersDistancesMap.containsKey(passenger)){
            passengersDistancesMap.put(passenger, passengersDistancesMap.get(passenger) + distance);
        }else{
            passengersDistancesMap.put(passenger, distance);
        }
    }

    public void calculateGivenPoints() {
        for(Passenger passenger : getPassengersDistancesMap().keySet()){
            passengersPointsMap.put(passenger, getPassengersDistancesMap().get(passenger) / DISTANCE_FACTOR);
        }
    }
}
