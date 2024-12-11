package cp;

import org.springframework.context.ApplicationEvent;

// ApplicationEvent는 모든 애플리케이션 이벤트가 상속해야 하는 스프링 추상 클래스
public class PassengerRegistrationEvent extends ApplicationEvent {

    private Passenger passenger;

    public PassengerRegistrationEvent(Passenger passenger){
        super(passenger);
        this.passenger = passenger;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger){
        this.passenger = passenger;
    }
}
