package cp;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PassengerRegistrationListener {
//    @EventListener 어노테이션을 달아 이 메서드를 PassengerRegistrationEvent 타입 이벤트의 리스너
//    로 등록. 해당 타입의 이벤트가 발행될 때마다 해당 리스너 메서드가 실행될 것이다
    @EventListener
    public void confirmRegistration(PassengerRegistrationEvent passengerRegistrationEvent){
        passengerRegistrationEvent.getPassenger().setIsRegistered(true);

        System.out.println("Confirming the registration for the passenger: "
            + passengerRegistrationEvent.getPassenger());
    }
}
