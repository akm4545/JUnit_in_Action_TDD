package car;

//이렇게 수정하면 이후에 모의 Driver 객체를 생성하고 Vehicle 객체 생성 시
//모의 객체를 Vehicle에 전달할 수 있다. 이를 의존성 주입(dependency injection)이라고 한다
//즉 의존성이 필요한 시점에 그에 맞는 의존성을 전달하는 것이다.
//물론 다른 유형의 Driver를 구현하여 모의 객체로 만들 수도 있다.
public class Vehicle1 {
    Driver d;
    boolean hasDriver = true;

    Vehicle1(Driver d){
        this.d = d;
    }

    private void setHasDriver(boolean hasDriver){
        this.hasDriver = hasDriver;
    }
}
