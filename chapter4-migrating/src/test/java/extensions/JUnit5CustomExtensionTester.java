package extensions;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

//테스트 확장
@ExtendWith(CustomExtension.class)
public class JUnit5CustomExtensionTester {

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
