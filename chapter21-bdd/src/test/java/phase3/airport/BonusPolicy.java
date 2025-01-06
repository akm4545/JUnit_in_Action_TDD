package phase3.airport;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import phase3.mileage.Mileage;

import static org.junit.jupiter.api.Assertions.*;

public class BonusPolicy {
    private Passenger mike;
    private Passenger john;
    private Mileage mileage;

    @Given("^마일리지와 일반 승객이 있는 상황에서$")
    public void 마일리지와_일반_승객이_있는_상황에서() throws Throwable {
        mike = new Passenger("Mike", false);
        mileage = new Mileage();
    }

    @When("^일반 승객이 가지고 있는 마일리지가 (\\d+)과 (\\d+)와 (\\d+)일 때$")
//    파라미터가 있는 시나리오를 작성하여 세 가지 마일리지에 대응하는 세 가지 파라미터가 있다
    public void 일반_승객이_가지고_있는_마일리지가_과_와_일_때(int mileage1, int mileage2, int mileage3) throws Throwable {
        mileage.addMileage(mike, mileage1);
        mileage.addMileage(mike, mileage2);
        mileage.addMileage(mike, mileage3);
    }

    @Then("^일반 승객의 보너스 포인트는 (\\d+)가 된다$")
    public void 일반_승객의_보너스_포인트는_가_된다(int points) throws Throwable {
        mileage.calculateGivenPoints();

        assertEquals(points, mileage.getPassengersPointsMap().get(mike).intValue());
    }

    @Given("^마일리지와 VIP 승객이 있는 상황에서$")
    public void 마일리지와_VIP_승객이_있는_상황에서() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^VIP 승객이 가지고 있는 마일리지가 (\\d+)과 (\\d+)와 (\\d+)일 때$")
    public void vip_승객이_가지고_있는_마일리지가_과_와_일_때(int arg1, int arg2, int arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^VIP 승객의 보너스 포인트는 (\\d+)가 된다$")
    public void vip_승객의_보너스_포인트는_가_된다(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
