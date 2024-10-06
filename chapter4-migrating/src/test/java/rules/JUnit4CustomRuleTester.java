package rules;

import org.junit.Rule;
import org.junit.Test;

public class JUnit4CustomRuleTester {

    @Rule
    public CustomRule myRule = new CustomRule();

    @Test
    public void myCustomRuleTest() {
        System.out.println("Call of a test method");
    }
}
