package hamcrest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JUnit5HamcrestListTest {
//    JUnit4 / 5 는 @Before / @BeforeEach와 @DisplayName 어노테이션 정도를 제외하고는 매우 유사하다
//    @Test의 의존성을 JUni4에서 JUnit5로 대체할 수 있다
//    JUnit4는 assertThat이 JUnit4에 내장되어 있지만 JUnit5는 hamcrest에 있다
    private List<String> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();

        values.add("Oliver");
        values.add("Jack");
        values.add("Harry");
    }

    @Test
    @DisplayName("Hamcrest를 사용한 테스트")
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("Oliver"), equalTo("Jack"),
                equalTo("Harry"))));
        assertThat("리스트의 순서에 맞게 객체를 포함하고 있는지 검증", values, contains("Oliver", "Jack", "Harry"));
        assertThat("리스트의 순서에 상관없이 객체를 포함하고 있는지 검증", values, containsInAnyOrder("Jack", "Harry", "Oliver"));
    }
}
