package SpringJdbc;

import jdbc.CountriesLoader;
import jdbc.TablesManager;
import jdbc.dao.CountryDao;
import model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static jdbc.CountriesLoader.COUNTRY_INIT_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountriesDatabaseTest {

    @Autowired
    private CountryDao countryDao = new CountryDao();

    @Autowired
    private CountriesLoader countriesLoader = new CountriesLoader();

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartWithA = new ArrayList<>();

    @BeforeEach
    public void setUp() {
//        데이터베이스는 스프링이 초기화하므로 수동으로 초기화할 필요가 없다
//        TablesManager.createTable();
        initExpectedCountryLists();

        countriesLoader.loadCountries();
    }

    @Test
//    테스트가 콘텍스트(여기서는 내장 데이터베이스의 상태)를 변경했을 때 사용한다
//    이렇게 하면 테스트마다 새로운 콘텍스트가 제공되므로 데이터베이스 부담이 줄어든다
    @DirtiesContext
    public void testCountryList() {
        List<Country> countryList = countryDao.getCountryList();

        assertNotNull(countryList);
        assertEquals(expectedCountryList.size(), countryList.size());

        for(int i=0; i<expectedCountryList.size(); i++){
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    @DirtiesContext
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
