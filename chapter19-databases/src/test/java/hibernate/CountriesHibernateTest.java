package hibernate;

import hibernate.model.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:application-context.xml")
public class CountriesHibernateTest {

    @Autowired
    private CountryService countryService;

//    엔티티 매니저를 생성하기 위한 팩토리
//    private EntityManagerFactory emf;
//    엔티티를 통해 DB에 접근할 수 있도록 하는 엔티티 매니저
//    private EntityManager em;

    private List<Country> expectedCountryList = new ArrayList<>();
    private List<Country> expectedCountryListStartsWithA = new ArrayList<>();

    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"}, {"Canada", "CA"}, {"France", "FR"},
            {"Germany", "DE"}, {"Italy", "IT"}, {"Japan", "JP"}, {"Romania", "RO"},
            {"Russian Federation", "RU"}, {"Spain", "ES"}, {"Switzerland", "CH"},
            {"United Kingdom", "UK"}, {"United States", "US"}};

    @BeforeEach
    public void setUp() {
        countryService.init();
        initExpectedCountryLists();
//
//        emf = Persistence.createEntityManagerFactory("manning.hibernate");
//        em = emf.createEntityManager();

//        트랜잭션 내에서 국가 객체를 데이터베이스에 영속
//        em.getTransaction().begin();

//        for(int i=0; i<COUNTRY_INIT_DATA.length; i++){
//            String[] countryInitData = COUNTRY_INIT_DATA[i];
//            Country country = new Country(countryInitData[0], countryInitData[1]);
//            em.persist(country);
//        }
//
//        em.getTransaction().commit();
    }

    @Test
    public void testCountryList() {
//        List<Country> countryList = em.createQuery("select c from Country c").getResultList();

        List<Country> countryList = countryService.getAllCountries();

        assertNotNull(countryList);
        assertEquals(COUNTRY_INIT_DATA.length, countryList.size());

        for(int i=0; i<expectedCountryList.size(); i++){
            assertEquals(expectedCountryList.get(i), countryList.get(i));
        }
    }

    @Test
    public void testCountryListStartsWithA() {
//        List<Country> countryList = em.createQuery("select c from Country c where c.name like 'A%'").getResultList();

        List<Country> countryList = countryService.getCountriesStartingWithA();

        assertNotNull(countryList);
        assertEquals(expectedCountryListStartsWithA.size(), countryList.size());

        for(int i=0; i<expectedCountryListStartsWithA.size(); i++){
            assertEquals(expectedCountryListStartsWithA.get(i), countryList.get(i));
        }
    }

    @AfterEach
    public void dropDown() {
//        em.close();
//        emf.close();

        countryService.clear();
    }

    private void initExpectedCountryLists() {
        for(int i=0; i<COUNTRY_INIT_DATA.length; i++){
            String[] countryInitData = COUNTRY_INIT_DATA[i];
            Country country = new Country(countryInitData[0], countryInitData[1]);
            expectedCountryList.add(country);

            if(country.getName().startsWith("A")){
                expectedCountryListStartsWithA.add(country);
            }
        }
    }
}
