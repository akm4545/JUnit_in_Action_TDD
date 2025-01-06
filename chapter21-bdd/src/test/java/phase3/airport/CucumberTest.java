package phase3.airport;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

//JUnit으로 Cucumber 테스트를 실행하기 위한 진입점 역할을 하는 클래스
//동일한 패키지에 있는 기능 파일들이 전부 실행된다
//Cucumber에 관한 JUnit5 extension이 없으므로 JUnit4 runner를 사용
@RunWith(Cucumber.class)

//Cucumber 실행 결과 리포트를 보기 좋게 출력하기 위한 플러그인 옵션 값 지정
//"pretty"를 사용하여 Gherkin 소스를 컬러로 표시 - html, json등 다른 옵션이 많다
//features 속성에 Cucumber가 프로젝트 폴더에서 찾아야 할 기능 파일의 위치를 지정
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:features"
)
public class CucumberTest {
}
