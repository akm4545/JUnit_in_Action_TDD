package car;

//Vehicle 객체가 생성될 때 Driver 객체도 같이 만들어진다.
//두 가지 객체가 강하게 결합되어 있고 Vehicle 클래스가 Driver 클래스에 의존하는 문제가 있는 것이다
//이럴 때는 Driver 객체를 Vehicle 클래스에 전달하는 방식으로 해결할 수 있다.
public class Vehicle {
    Driver d = new Driver();
    boolean hasDriver = true;

    private void setHasDriver(boolean hasDriver){
        this.hasDriver = hasDriver;
    }
}
