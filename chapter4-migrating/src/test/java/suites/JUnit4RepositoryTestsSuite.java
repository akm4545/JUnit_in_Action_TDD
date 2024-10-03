package suites;

import categories.JUnit4CustomerTest;
import categories.JUnit4CustomersRepository;
import categories.RepositoryTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(RepositoryTests.class)
@Suite.SuiteClasses({JUnit4CustomerTest.class, JUnit4CustomersRepository.class})
public class JUnit4RepositoryTestsSuite {
}
