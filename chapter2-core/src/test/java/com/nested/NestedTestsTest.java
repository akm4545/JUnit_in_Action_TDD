package com.nested;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//메인 테스트 -> 중첩 테스트 BuilderTest와 결합되어 있다
//즉 @Nested는 테스트 클래스 안에 클래스를 정의하여 테스트를 계층적으로 구성할 수 있게 해준다
//하단 예제에서와 같이 상위 클래스의 상태 공유도 가능하다
public class NestedTestsTest {
//    고객에 대한 클래스
//    일부 정보가 누락될 수 있으므로 빌더 패턴을 사용하여 고객 정보를 생성하고 테스트

//    모든 중첩 테스트에 사용할 고객의 이름과 성을 선언
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Smith";

//    중첩 테스트인 BuilderTest는 빌더 패턴을 활용하여 Customer 객체를 제대로 생성했는지 검증한다
//    각 필드의 값이 동등한지 아닌지는 customerBuilder 테스트가 끝날 때 확인할 수 있다
    @Nested
    class BuilderTest {
        private String MIDDLE_NAME = "Michael";

        @Test
        void customerBuilder() throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy");
            Date customerDate = simpleDateFormat.parse("04-21-2019");

            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .withMiddleName(MIDDLE_NAME)
                    .withBecomeCustomer(customerDate)
                    .build();

            assertAll(() -> {
                assertEquals(Gender.MALE, customer.getGender());
                assertEquals(FIRST_NAME, customer.getFirstName());
                assertEquals(LAST_NAME, customer.getLastName());
                assertEquals(MIDDLE_NAME, customer.getMiddleName());
                assertEquals(customerDate, customer.getBecomeCustomer());
            });
        }
    }

    @Nested
    class CustomerEqualsTest {
        private String OTHER_FIRST_NAME = "John";
        private String OTHER_LAST_NAME = "Doe";

        @Test
        void testDifferentCustomers() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .build();

            Customer otherCustomer = new Customer.Builder(Gender.MALE, OTHER_FIRST_NAME, OTHER_LAST_NAME)
                    .build();

            assertEquals(customer, otherCustomer);
        }

        @Test
        void testSameCustomer() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .build();

            Customer otherCustomer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .build();

            assertAll(() -> {
                assertEquals(customer, otherCustomer);
                assertNotSame(customer, otherCustomer);
            });
        }
    }

    @Nested
    class CustomerHashCodeTest {
        private String OTHER_FIRST_NAME = "John";
        private String OTHER_LAST_NAME = "Doe";

        @Test
        void testDifferentCustomers() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .build();

            Customer otherCustomer = new Customer.Builder(Gender.MALE, OTHER_FIRST_NAME, OTHER_LAST_NAME)
                    .build();

            assertNotEquals(customer.hashCode(), otherCustomer.hashCode());
        }

        @Test
        void testSameCustomer() {
            Customer customer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .build();

            Customer otherCustomer = new Customer.Builder(Gender.MALE, FIRST_NAME, LAST_NAME)
                    .build();

            assertEquals(customer.hashCode(), otherCustomer.hashCode());
        }
    }
}
