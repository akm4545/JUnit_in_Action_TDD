package com.hamcrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//테스트를 실행하면 Hamcrest 매처를 사용한 테스트가 훨씬 더 자세한 정보를 제공한다
public class HamcrestListTest {
    private List<String> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();

        values.add("John");
        values.add("Michael");
        values.add("Edwin");
    }

//    일부로 실패하도록 의도되었으며 단언문이 실패했을 때 어떤 내용을 보여 주는지 알아보기 위한 메서드
    @Test
    @DisplayName("Hamcrest를 사용하지 않아 실패 정보를 자세히 표현하지 못하는 테스트")
    public void testListWithoutHamcrest(){
        assertEquals(3, values.size());
//        테스트 메서드에서는 상대적으로 길고 읽기 부담스러운 단언문 사용
//        문법적으로 별로 어렵지 않다고 느낄 수 있으나 결론은 한 눈에 파악하기 쉽지 않음
        assertTrue(values.contains("Oliver") || values.contains("Jack") || values.contains("Harry"));
    }

    @Test
    @DisplayName("Hamcrest를 사용해서 자세한 실패 정보를 나타내는 테스트")
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
//        매처 중첩 사용
//        매처의 가장 강력한 기능 중 하나
        assertThat(values, hasItem(anyOf(equalTo("Oliver"), equalTo("Jack"), equalTo("Harry"))));
        assertThat("리스트의 순서에 맞게 객체를 포함하고 있는지 검증", values, contains("Oliver", "Jack", "Harry"));
        assertThat("리스트의 순서에 상관없이 객체를 포함하고 있는지 검증", values, containsInAnyOrder("Jack", "Harry", "Oliver"));
    }
}
