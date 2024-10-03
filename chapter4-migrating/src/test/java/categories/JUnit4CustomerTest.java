package categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import tags.Customer;

import static org.junit.Assert.assertEquals;

public class JUnit4CustomerTest {
    private String CUSTOMER_NAME = "John Smith";

//    @Category(IndividualTests.class) 어노테이션을 사용하면 테스트 메서드를 특정 카테고리에 포함시킬 수 있다
    @Category(IndividualTests.class)
    @Test
    public void TestCustomer() {
        Customer customer = new Customer(CUSTOMER_NAME);

        assertEquals("John Smith", customer.getName());
    }
}
