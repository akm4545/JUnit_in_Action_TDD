package jdbc;

import jdbc.dao.CountryDao;
import model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static jdbc.CountriesLoader.COUNTRY_INIT_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountriesDatabaseTest {
    private CountryDao countryDao = new CountryDao();
    private CountriesLoader countriesLoader = new CountriesLoader();

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        TablesManager.createTable();
        initExpectedCountryLists();

        countriesLoader.loadCountries();
    }

    @Test
    public void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();

        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());

        for(int i=0; i<expectedCountryList.size(); i++){
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartWithA() {
        List<Country> countryList = countryDao.getCountryListStartWith("A");

        assertNotNull(countryList);
        assertEquals(expectedCountryListStartWithA.size(), countryList.size());

        for(int i=0; i<expectedCountryListStartWithA.size(); i++){
            assertEquals(expectedCountryListStartWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    public void dropDown() {
        TablesManager.dropTable();
    }

    private void initExpectedCountryLists() {
        for(int i=0; i<COUNTRY_INIT_DATA.length; i++){
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);

            if(country.getName().startsWith("A")){
                expectedCountryListStartWithA.add(country);
            }
        }
    }

}
