package phase4.airport;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class PassengersPolicy {

    private Flight economyFlight;
    private Flight businessFlight;
    private Flight premiumFlight;
    private Passenger mike;
    private Passenger john;
    @Given("이코노미 항공편에서")
    public void given이코노미항공편에서() {
        economyFlight = new EconomyFlight("1");
    }

    @When("일반 승객은")
    public void when일반승객은() {
        mike = new Passenger("Mike", false);
    }

    @Then("이코노미 항공편에서 추가가 가능하고 삭제도 가능하다")
    public void then이코노미항공편에서추가가가능하고삭제도가능하다() {
        assertAll("일반 승객은 이코노미 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(mike)),
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertEquals("Mike", new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName()),
                () -> assertEquals(true, economyFlight.removePassenger(mike)),
                () -> assertEquals(0, economyFlight.getPassengerSet().size())
        );
    }

    @When("VIP 승객은")
    public void whenVIP승객은() {
        john = new Passenger("John", true);
    }

    @Then("이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다")
    public void then이코노미항공편에서추가가가능하지만삭제는불가능하다() {
        assertAll("VIP 승객은 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals("1", economyFlight.getId()),
                () -> assertEquals(true, economyFlight.addPassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertEquals("John", new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName()),
                () -> assertEquals(false, economyFlight.removePassenger(john)),
                () -> assertEquals(1, economyFlight.getPassengerSet().size())
        );
    }

    @Given("비즈니스 항공편에서")
    public void given비즈니스항공편에서() {
        businessFlight = new BusinessFlight("2");
    }

    @Then("비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능하다")
    public void then비즈니스항공편에서추가가불가능하고삭제도불가능하다() {
        assertAll("일반 승객은 비즈니스 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertEquals(false, businessFlight.addPassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengerSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(mike)),
                () -> assertEquals(0, businessFlight.getPassengerSet().size())
        );
    }

    @Then("비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능하다")
    public void then비즈니스항공편에서추가가가능하지만삭제는불가능하다() {
        assertAll("VIP 승객은 비즈니스 항공편에서 추가가 가능하지만 삭제는 불가능한지 검증",
                () -> assertEquals(true, businessFlight.addPassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengerSet().size()),
                () -> assertEquals(false, businessFlight.removePassenger(john)),
                () -> assertEquals(1, businessFlight.getPassengerSet().size())
        );
    }

    @Given("프리미엄 항공편에서")
    public void given프리미엄항공편에서() {
        premiumFlight = new PremiumFlight("3");
    }

    @Then("프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능하다")
    public void then프리미엄항공편에서추가가불가능하고삭제도불가능하다() {
        assertAll("일반 승객은 프리미엄 항공편에서 추가가 불가능하고 삭제도 불가능한지 검증",
                () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengerSet().size()),
                () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                () -> assertEquals(0, premiumFlight.getPassengerSet().size())
        );
    }

    @Then("프리미엄 항공편에서 추가가 가능하고 삭제도 가능하다")
    public void then프리미엄항공편에서추가가가능하고삭제도가능하다() {
        assertAll("VIP 승객은 프리미엄 항공편에서 추가가 가능하고 삭제도 가능한지 검증",
                () -> assertEquals(true, premiumFlight.addPassenger(john)),
                () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                () -> assertEquals(true, premiumFlight.removePassenger(john)),
                () -> assertEquals(0, premiumFlight.getPassengerSet().size())
        );
    }

    @Then("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다")
    public void then이코노미항공편에일반승객을중복해서추가할수없다() {
        for (int i = 0; i < 10; i++) {
            economyFlight.addPassenger(mike);
        }
        assertAll("이코노미 항공편에 일반 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertTrue(economyFlight.getPassengerSet().contains(mike)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName().equals("Mike"))
        );
    }

    @Then("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없다")
    public void then이코노미항공편에VIP승객을중복해서추가할수없다() {
        for (int i = 0; i < 10; i++) {
            economyFlight.addPassenger(john);
        }

        assertAll("이코노미 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, economyFlight.getPassengerSet().size()),
                () -> assertTrue(economyFlight.getPassengerSet().contains(john)),
                () -> assertTrue(new ArrayList<>(economyFlight.getPassengerSet()).get(0).getName().equals("John"))
        );
    }

    @Then("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없다")
    public void then비즈니스항공편에VIP승객을중복해서추가할수없다() {
        for (int i = 0; i < 10; i++) {
            businessFlight.addPassenger(john);
        }

        assertAll("비즈니스 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, businessFlight.getPassengerSet().size()),
                () -> assertTrue(businessFlight.getPassengerSet().contains(john)),
                () -> assertTrue(new ArrayList<>(businessFlight.getPassengerSet()).get(0).getName().equals("John"))
        );
    }

    @Then("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없다")
    public void then프리미엄항공편에VIP승객을중복해서추가할수없다() {
        for (int i = 0; i < 10; i++) {
            premiumFlight.addPassenger(john);
        }

        assertAll("프리미엄 항공편에 VIP 승객을 중복해서 추가할 수 없는지 검증",
                () -> assertEquals(1, premiumFlight.getPassengerSet().size()),
                () -> assertTrue(premiumFlight.getPassengerSet().contains(john)),
                () -> assertTrue(new ArrayList<>(premiumFlight.getPassengerSet()).get(0).getName().equals("John"))
        );
    }
}
