package phase5.airport;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jbehave.core.annotations.Named;
import phase5.mileage.Mileage;

import static org.junit.Assert.assertEquals;

public class BonusPolicy {

    private Passenger mike;
    private Passenger john;
    private Mileage mileage;


    @Given("마일리지와 일반 승객이 있는 상황에서")
    public void given마일리지와일반승객이있는상황에서() {
        mike = new Passenger("Mike", false);
        mileage = new Mileage();
    }

    @When("일반 승객이 가지고 있는 마일리지가 <mileage1>과 <mileage2>와 <mileage3>일 때")
    public void when일반승객이가지고있는마일리지가mileage1과mileage2와mileage3일때(@Named("mileage1") int mileage1, @Named("mileage2") int mileage2, @Named("mileage3") int mileage3) {
        mileage.addMileage(mike, mileage1);
        mileage.addMileage(mike, mileage2);
        mileage.addMileage(mike, mileage3);
    }

    @Then("일반 승객의 보너스 포인트는 <points>가 된다")
    public void then일반승객의보너스포인트는points가된다(@Named("points") int points) {
        mileage.calculateGivenPoints();
        assertEquals(points, mileage.getPassengersPointsMap().get(mike).intValue());
    }

    @Given("마일리지와 VIP 승객이 있는 상황에서")
    public void given마일리지와VIP승객이있는상황에서() {
        john = new Passenger("John", true);
        mileage = new Mileage();
    }

    @When("VIP 승객이 가지고 있는 마일리지가 <mileage1>과 <mileage2>와 <mileage3>일 때")
    public void whenVIP승객이가지고있는마일리지가mileage1과mileage2와mileage3일때(@Named("mileage1") int mileage1, @Named("mileage2") int mileage2, @Named("mileage3") int mileage3) {
        mileage.addMileage(john, mileage1);
        mileage.addMileage(john, mileage2);
        mileage.addMileage(john, mileage3);
    }

    @Then("VIP 승객의 보너스 포인트는 <points>가 된다")
    public void thenVIP승객의보너스포인트는points가된다(@Named("points") int points) {
        mileage.calculateGivenPoints();
        assertEquals(points, mileage.getPassengersPointsMap().get(john).intValue());
    }
}
