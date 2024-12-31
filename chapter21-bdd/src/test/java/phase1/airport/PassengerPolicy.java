package phase1.airport;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerPolicy {
    private Flight economyFlight;
    private Flight businessFlight;
    private Flight premiumFlight;
    private Passenger mike;
    private Passenger john;

//    Cucumber 플러그인은 @Given("^이코노미 항공편에서$") 어노테이션이 달린 메서드를 생성한다
//    즉 이 메서드는 시나리오에서 Given 이코노미 항공편에서 스텝이 실행될 때 실행된다
    @Given("^이코노미 항공편에서$")
//    Cucumber 플러그인 시나리오에서 Given 이코노미 항공편에서 스텝에 해당하는 메서드 스텁을 만든다
//    개발자는 여기에 테스트를 구현하면 된다
    public void 이코노미_항공편에서() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        economyFlight = new EconomyFlight("1");

//        throw new PendingException();
    }

//    Cucumber 플러그인은 @When("^일반 승객은$") 어노테이션이 달린 메서드를 생성한다
//    즉 이 메서드는 시나리오에서 When 일반 승객은 스텝이 실행될 때 실행된다
    @When("^일반 승객은$")
//    Cucumber 플러그인 시나리오에서 When 일반 승객은 스텝에 해당하는 메서드 스텁을 만든다
    public void 일반_승객은() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        mike = new Passenger("Mike", false);

//        throw new PendingException();
    }

//    Cucumber 플러그인은 @Then("^이코노미 항공편에서 추가가 가능하고 삭제도 가능하다$") 어노테이션이 달린 메서드를 생성한다
//    즉 이 메서드는 시나리오에서 Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다 스텝이 실행될 때 실행된다
    @Then("^이코노미 항공편에서 추가가 가능하고 삭제도 가능하다$")
//    Cucumber 플러그인 시나리오에서 Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다 스텝에 해당하는 메서드 스텁을 만든다
    public void 이코노미_항공편에서_추가가_가능하고_삭제도_가능하다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertAll("일반 승객은 이코노미 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertTrue(economyFlight.getPassengerSet().contains(mike)),
                () -> assertEquals(true, economyFlight.removePassenger(mike)),
                () -> assertEquals(0, economyFlight.getPassengerSet().size())
        );

//        throw new PendingException();
    }

    @Then("^이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다$")
    public void 이코노미_항공편에_일반_승객을_중복해서_추가할_수_없다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        for(int i=0; i<10; i++){
            economyFlight.addPassenger(mike);
        }

        assertAll("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertTrue(economyFlight.getPassengerSet().contains(mike)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName().equals("Mike"))
        );

//        throw new PendingException();
    }

    @When("^VIP 승객은$")
    public void vip_승객은() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        john = new Passenger("John", true);

//        throw new PendingException();
    }

    @Then("^이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다$")
    public void 이코노미_항공편에서_추가가_가능하지만_삭제는_불가능하다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertTrue(economyFlight.getPassengerSet().contains(john)),
                () -> assertEquals(false, economyFlight.removePassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengerSet().size())
        );


//        throw new PendingException();
    }

    @Then("^이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없다$")
    public void 이코노미_항공편에_VIP_승객을_중복해서_추가할_수_없다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        for(int i=0; i<10; i++){
            economyFlight.addPassenger(john);
        }

        assertAll("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertTrue(economyFlight.getPassengerSet().contains(john)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName().equals("John"))
        );

//        throw new PendingException();
    }

    @Given("^비즈니스 항공편에서$")
    public void 비즈니스_항공편에서() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        businessFlight = new BusinessFlight("2");
//        throw new PendingException();
    }

    @Then("^비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다$")
    public void 비즈니스_항공편에서_추가가_불가능하고_삭제도_불가능하다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertEquals(false, businessFlight.addPassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengerSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengerSet().size())
        );

//        throw new PendingException();
    }

    @Then("^비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다$")
    public void 비즈니스_항공편에서_추가가_가능하지만_삭제는_불가능하다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals(true, businessFlight.addPassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengerSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengerSet().size())
        );

//        throw new PendingException();
    }

    @Then("^비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없다$")
    public void 비즈니스_항공편에_VIP_승객을_중복해서_추가할_수_없다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        for(int i=0; i<10; i++){
            businessFlight.addPassenger(john);
        }

        assertAll("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, businessFlight.getPassengerSet().size()),
                () -> assertTrue(businessFlight.getPassengerSet().contains(john)),
                () -> assertTrue(new ArrayList<>(businessFlight.getPassengerSet()).get(0).getName().equals("John"))
        );
//        throw new PendingException();
    }

    @Given("^프리미엄 항공편에서$")
    public void 프리미엄_항공편에서() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        premiumFlight = new PremiumFlight("3");
//        throw new PendingException();
    }

    @Then("^프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다$")
    public void 프리미엄_항공편에서_추가가_불가능하고_삭제도_불가능하다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertAll("일반 승객은 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengerSet().size()),
                () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengerSet().size())
        );
//        throw new PendingException();
    }

    @Then("^프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다$")
    public void 프리미엄_항공편에서_추가가_가능하고_삭제도_가능하다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertAll("VIP 승객은 프리미엄 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertEquals(true, premiumFlight.addPassenger(john)),
                () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                () -> assertEquals(true, premiumFlight.removePassenger(john)),
                () -> assertEquals(0, premiumFlight.getPassengerSet().size())
        );

//        throw new PendingException();
    }

    @Then("^프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없다$")
    public void 프리미엄_항공편에_VIP_승객을_중복해서_추가할_수_없다() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        for (int i=0; i<10; i++){
            premiumFlight.addPassenger(john);
        }

        assertAll("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                () -> assertTrue(premiumFlight.getPassengerSet().contains(john)),
                () -> assertTrue(new ArrayList<>(premiumFlight.getPassengerSet()).get(0).getName().equals("John"))
        );
//        throw new PendingException();
    }
}
