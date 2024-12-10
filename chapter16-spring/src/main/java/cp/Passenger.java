package cp;

public class Passenger {
    private String name;
    private Country country;

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
}
