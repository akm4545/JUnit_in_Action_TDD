package cp;

import java.util.Objects;

public class Passenger {
    private String name;
    private Country country;

//    패턴 적용
    private boolean isRegistered;

    public Passenger(String name){
        this.name = name;
//        의존성 제거 전
//        this.country = new Country("USA", "US");
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

//    Passenger 객체는 의존하고 있는 Country 객체를 더는 직접 생성하지 않는다
//    두 객체 간의 직접적인 의존성이 제거되었다
    public void setCountry(Country conCountry){
        this.country = country;
    }
    
//    패턴 적용
    public boolean isRegistered() {
        return isRegistered;
    }
    
//    패턴 적용
    public void setIsRegistered(boolean isRegistered){
        this.isRegistered = isRegistered;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return name.equals(passenger.name) &&
                Objects.equals(country, passenger.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country);
    }
}
