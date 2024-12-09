package cp;

public class Passenger {
    private String name;
    private Country country;

    public Passenger(String name){
        this.name = name;
        this.country = new Country("USA", "US");
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}
