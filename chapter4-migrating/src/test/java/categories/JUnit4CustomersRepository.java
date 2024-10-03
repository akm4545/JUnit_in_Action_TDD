package categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import tags.Customer;
import tags.CustomersRepository;

import static org.junit.Assert.*;

//클래스 수준에서 어노테이션을 추가하면 클래스 안에 있는 테스트 메서드 모두를 특정 카테고리에 포함되는 것으로 만들 수 있다
@Category({IndividualTests.class, RepositoryTests.class})
public class JUnit4CustomersRepository {
    private String CUSTOMER_NAME = "John Smith";
    private CustomersRepository repository = new CustomersRepository();

    @Test
    public void testNonExistence() {
        boolean exists = repository.contains(CUSTOMER_NAME);

        assertFalse(exists);
    }

    @Test
    public void testCustomerPersistence() {
        repository.persist(new Customer(CUSTOMER_NAME));

        assertTrue(repository.contains("John Smith"));
    }
}
