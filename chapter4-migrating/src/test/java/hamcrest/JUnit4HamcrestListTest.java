package hamcrest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertThrows;

public class JUnit4HamcrestListTest {
    private List<String> values;

    @Before
    public void setUp() {
        values = new ArrayList<>();

        values.add("Oliver");
        values.add("Jack");
        values.add("Harry");
    }

    @Test
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values,
            hasItem(
                anyOf(
                        equalTo("Oliver"),
                        equalTo("Jack"),
                        equalTo("Harry")
                )
            )
        );
        assertThat("리스트의 순서에 맞게 객체를 포함하고 있는지 검증", values, contains("Oliver", "Jack", "Harry"));
        assertThat("리스트의 순서에 상관없이 객체를 포함하고 있는지 검증", values, containsInAnyOrder("Jack", "Harry", "Oliver"));
    }
}
