package airport.producers;

import airport.Flight;
import airport.FlightBuilderUtil;

import javax.enterprise.inject.Produces;
import java.io.IOException;

public class FlightProducer {

    @Produces
    public Flight createFlight() throws IOException{
        return FlightBuilderUtil.buildFlightFromCsv();
    }
}
