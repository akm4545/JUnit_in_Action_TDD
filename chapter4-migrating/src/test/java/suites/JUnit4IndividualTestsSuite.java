package suites;

import categories.IndividualTests;
import categories.JUnit4CustomerTest;
import categories.JUnit4CustomersRepository;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//@RunWith(Categories.class) 어노테이션을 달아 특정한 runner로 테스트를 실행하도록 지정
@RunWith(Categories.class)
//@Category(IndividualTests.class) 어노테이션으로 실행할 테스트의 카테고리를 지정
@Categories.IncludeCategory(IndividualTests.class)
//JUnit4CustomerTest, JUnit4CustomerRepositoryTest 클래스에서 해당 어노테이션이 달린 테스트를 찾아 실행
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepository.class})
public class JUnit4IndividualTestsSuite {
}
