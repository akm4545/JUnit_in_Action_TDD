package cp;

public class Country {
    private String name;
    private String codeName;

    public Country(String name, String codeName){
        this.name = name;
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public String getCodeName() {
        return codeName;
    }
}
