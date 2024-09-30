# **소프르웨어 아키텍처(software architecture)**  
소프트웨어 아키텍처란 소프트웨어 시스템의 기본 구조를 말한다  
소프트웨어 시스템은 체계적인 방식으로 만들어야 한다  
소프트웨어 시스템 구조는 소프트웨어 요소, 소프트웨어 요소 간의 관계, 요소와 관계를 이루는 속성들로 구성된다  
  
소프트웨어 아키텍처는 건축에서의 아키텍처와 비슷하다  
소프트웨어 시스템의 아키텍처는 피라미트 구조의 제일 아랫단으로 표현할 수 있는다 다른 상자들은 이를 기초로 삼아  
쌓이기 때문이다 소프트웨어 아키텍처의 기초를 이루는 요소는 물리적인 아키텍처의 기초만큼이나 이동이나 교체가 어렵다  
바닥을 수정하려면 그 위에 올려진 것을 전부 옮겨야 하기 때문이다  
  
아키텍처에 대한 마틴 파울러의 정의(아키텍처는 나중에 변경하기 어렵다 그래서 가능한 한 작아야 한다)를 따르다 보면  
아키텍처 요소를 더 쉽게 교체할 수 있도록 구성해야 한다는 결론에 도달하게 된다  
참고로 JUnit5의 아키텍처 또한 JUnit4의 단점을 극복하기 위해 만들어졌다  
  
# **JUnit4 아키텍처**  
JUnit4를 다뤄야 하는 첫 번째 이유는 아직도 수많은 레거시 코드가 JUni4를 사용하기 때문이다  
JUnit4에서 JUnit5로의 전환은 즉각적으로 진행하기 어려우며 어떤 시스템은 JUnit4와 JUnit5를 함께 써야 할 수도 있다  
JUni5는 JUnit Vintage를 통해 기존 레거시 프로젝트에서 이전 JUnit4 코드와 함께 작동하도록 설계되었다  
  
# **JUnit4 모놀리식 아키텍처**  
2006년에 출시된 JUnit4는 단순한 모놀리식 아키텍처를 가지고 있다.  
JUnit4의 모든 기능은 jar 파일 한 개 안에 들어있다 이는 개발자가 JUnit4를 프로젝트에서 사용하고자 한다면  
클래스패스에 단일 jar 파일만 추가하면 된다는 뜻이다  

# **JUnit4 runner**  
JUnit4 runner는 JUnit4 추상 클래스 Runner를 상속한 클래스다 JUnit4 runner는 JUnit 테스트의 실행을 담당한다  
JUnit4는 단일 jar 파일로 동작이 가능하지만 JUnit4의 기능을 조금 더 확장해서 사용하는 것이 일반적이다
개발자는 테스트 실행 전후에 추가 작업을 수행하는 등 기본 기능에 사용자 정의 기능을 추가할 수 있다  
  
JUnit4 runner는 리플렉션을 사용하여 테스트를 확장할 수 있다  
물론 리플렉션은 캡슐화를 저해한다 그러나 이 기법은 JUnit4와 그 이전 버전에서 확장성을 제공하는 유일한 방법이었고  
이는 JUnit5가 만들어진 이유 중 하나가 되었다  
JUnit5 extension이 JUnit4 runner를 대체할 수 있다  
  
스프링 프레임워크를 위한 runner를 사용할 수도 있고 Mockito로 객체를 모의할 때 필요한 runner를 사용하는 등  
기존에 이미 만들어져 있는 runner를 사용할 수도 있다  
JUnit4의 추상 클래스인 Runner를 상속받아서 메서드를 재정의하고 리플렉션을 활용하여 사용자 정의 runner를 만들 수 있다  
이런 방식은 캡슐화를 저해하지만 JUnit4에 사용자 정의 기능을 추가하는 거의 유일한 방법이었다  
  
테스트 묶음 실행 전에 사용자 정의 기능을 추가하여 Calculator 클래스를 사용하는 테스트듸 동작을 보강  
사용자 정의 runner를 만들어 @RunWith 어노테이션의 파리미터로 사용  
이렇게 하면 원래의 JUnit 기능에 사용자 정의 기능을 추가할 수 있다   
  
runners 패키지 -> CustomTestRunner 클래스 참조  
  
@RunWith 어노테이션  
runners 패키지 -> CalculatorTets 클래스 참조  
  
# **JUnit4 rule**  
JUnit4 rule은 테스트 메서드 호출을 가로채는(intercept) 컴포넌트다 JUnit4 rule을 사용해서 테스트 메서드가  
실행되기 전후에 다른 작업을 수행할 수 있다 rule은 JUnit4 에만 적용된다는 점을 주의해야 한다  
  
실행할 테스트에 동작을 추가하려면 TestRule 인터페이스를 구현한 필드에 @Rule 어노테이션을 사용해야 한다  
JUnit4 rule은 테스트 메서드에서 사용하거나 구성할 수 있는 객체를 만든다는 점에서 테스트를 유연하게 만들 수 있다  
  
rules 패키지 -> RuleExceptionTester 참조  
  
때로는 테스트 관련 정보를 저장하기 위해 파일이나 폴더를 만드는 등 임시 자원을 가지고 작업해야 한다  
이때는 TemporaryFolder rule을 사용하면 테스트가 끝난 후 (테스트 통과 여부에 상관없이) 삭제되는 임시 폴더를 만들 수 있다  
  
rules 패키지 아래 -> RuleTester 참조  
  
사용자 정의 rule을 만들어 테스트를 실행하기 전에 특정 프로세스를 시작하고 테스트가 끝난 다음 중지하거나  
테스트를 실행하기 전에 데이터베이스에 커넥션을 얻고 테스트가 끝난 다음 커넥션을 반납할 수 있다  
JUnit4 rule을 작성하려면 TestRule 인터페이스를 구현하는 클래스를 만들어야 한다  
  
rules 패키지 아래 -> CustomRule 참조  
  
TestRule 인터페이스를 구현하려면 Statement 타입 객체를 반환하는 apply(Statement, Description) 메서드를 재정의 해야 한다  
Statement 객체는 JUnit 런타임 내의 테스트를 나타내며 evaluate 메서드로 테스트를 실행할 수 있다  
  
rules 패키지 아래 -> CustomStatement 참조  
  
Description 객체는 현재 테스트를 설명하는데 사용한다 또한 리플렉션으로 테스트에 관한 정보를 읽어낼 수 있다  
  
rules 패키지 아래 -> CustomRuleTester 참조  
  
또 다른 방법으로 CustomRule 타입 필드를 private으로 선언한 다음 @Rule 어노테이션이 붙은 게터 메서드를  
public하게 선언하여 외부로 노출시킬 수도 있다  
이는 @Rule 어노테이션이 public 인스턴스 필드와 public 인스턴스 메서드에서만 적용 가능하다는 점에도 부합한다  
  
rules 패키지 아래 -> CustomRuleTester2 참조  
  
JUnit4 runner와 rule을 활용해서 JUnit4의 모놀리식 아키텍처를 확장할 수 있다  
  
JUnit5에서 JUnit4를 사용하는 데 필요한 의존성은 junit-vintage-engine이다  
JUnit vintage는 JUnit5와 이전 버전 간의 호환성을 보장한다 JUnit Vintage를 활용하여 Maven은 JUnit4 의존성에  
추이적으로 접근할 수 있다  
  
# **JUnit4 아키텍처의 단점**  
JUnit은 개발자뿐만 아니라 IDE나 빌드 도구 같은 많은 소프트웨어에서 사용되었다  
JUnit4의 모놀리식 아키텍처는 이러한 도구들과의 상호작용을 고려하여 설계된 것은 아니었다  
이런 상황에서 JUnit4가 제공하는 API는 충분히 유연하지 못했다 그 결과 JUnit4를 사용하는 IDE나 빌드 도구와  
JUnit4간에 지나치게 결합도가 높아지게 되었다 JUnit4의 API가 IDE나 빌드 도구와 적절히 상호작용할 만큼 클래스와  
메서드를 제공할 수 있도록 설계된 것이 아니기 떄문이었다 이런 상황에서 IDE나 빌드 도구는 때로 JUnit 클래스 내부로  
들어가서 리플렉션으로 필요한 정보를 꺼내 오는 방식으로 JUnit4와 상호작용할 수밖에 없었다  
JUnit의 개발자가 private 변수의 이름을 변경하기로 하면 이런 변경이 리플렉션으로 JUnit에 접근하는 도구에  
역으로 영향을 줄 수 있었다  
결과적으로 모든 개발자가 똑같은 jar파일을 사용하고 빌드 도구와 IDE가 JUnit4와 밀접하게 결합되었기 떄문에 JUnit4  
의 인기와 단순성이 되려 JUnit4의 발전 가능성을 저해하게 되었다  
이러한 문제를 해결하기 위해 새로운 아키텍처와 그에 발맞춘 API가 생겨났고 더 작고 모듈화된 JUnit에 대한 요구가 생겨났다  
  
# **JUnit5 아키텍처**  
  
# **JUnit5 모듈성**  
JUnit5는 JUnit이 더욱 발전할 수 있도록 모듈식으로 개발할 필요가 있었다 JUnit5는 다양한 빌드 도구와 IDE를  
사용하는 프로그램적 클라이언트와 상호작용할 수 있도록 설계되어야 했다  
요구되는 사항들은 아래와 같이 논리적으로 분리되었다  
  
개발자가 주로 사용하는 테스트를 작성하기 위한 API  
테스트를 발견하고 실행하는 데 사용하는 메커니즘  
IDE나 빌드 도구와 쉽게 상호작용하고 테스트를 구동할 수 있는 API  
  
그 결과 JUnit5 아키텍처는 다음 세 가지 모듈로 나뉘었다  
  
JUnit Platform: JVM위에서 테스트 프레임워크를 구동하기 위한 기반이 되는 플랫폼  
또한 콘솔, IDE, 빌드 도구에서 테스트를 구동할 수 있는 API도 제공  
  
JUnit Jupiter: JUnit5에서 테스트와 extension을 만들 수 있도록 프로그래밍 모델과 확장 모델을 결합한 것  
  
JUnit Vintage: JUnit Platform에서 JUnit3이나 JUnit4 기반의 테스트를 실행하기 위한 테스트 엔진으로  
하위 호환성을 보장한다  
  
# **JUnit Platform**  
JUnit Platform의 아티팩트  
junit-platform-commons: JUnit 안에서 사용하기 위한 JUnit의 내부 공통 라이브러리. 외부 사용을 권장하지 않음  
junit-platform-console: 콘솔에서 JUnit Platform의 테스트를 발견하고 실행할 수 있도록 지원  
junit-platform-console-standalone: 모든 의존성이 포함되어 있는 실행 가능한 jar 파일. 이 아티팩트는  
콘솔에서 Junit Platform을 구동할 수 있는 커맨드 라인 자바 애플리케이션인 콘솔 런처에서 사용한다  
예를 들어 JUnit Vintage나 JUnit Jupiter 테스트를 실행하고 테스트 실행 결과를 콘솔에 출력하는 데 사용할 수 있다  
junit-platform-engine: 테스트 엔진용 public API  
junit-platform-launcher: 테스트 계획을 구성하고 실행하기 위한 public API로 일반적으로 IDE나 빌드 도구에서 사용  
junit-platform-runner: JUnit4 환경의 JUnit Platform에서 테스트와 테스트 묶음을 실행하기 위한 runner  
junit-platform-suite-api: JUnit Platform에서 테스트 묶음을 구성하기 위한 어노테이션을 가지고 있다  
junit-platform-surefire-provider: Maven Surefire를 사용하여 JUnit Platform에서 테스트를 발견하고 실행할 수 있게 한다  
junit-platform-gradle-plugin: Gradle을 사용하여 JUnit Platform에서 테스트를 발견하고 실행할 수 있게 한다  
  
# **JUnit Jupiter**  
JUnit jupiter는 어노테이션, 클래스, 메서드를 비롯하여 JUnit5 테스트를 작성하기 위한 프로그래밍 모델과  
Extension API를 통해 JUnit5 extension을 작성하기 위한 확장 모델로 이루어져 있다  
Jupiter의 하위 프로젝트에서는 TestEngine을 통해 JUnit Platform에서 Jupiter 기반 테스트를 실행한다  
JUnit4 에서는 runner나 rule등 여러 개의 확장 지점이 있었던 반면 JUnit Jupiter의 확장 모델은 하나의 일관된 개념인  
Extension API로만 구성되었다 Extension 자체는 단순한 마커 인터페이스이다  
  
JUnit Jupiter 아티팩트  
junit-jupiter-api: JUnit5 테스트나 extension 작성을 위한 JUnit Jupiter API
junit-jupiter-engine: 런타임에서만 사용하는 JUnit Jupiter 테스트 엔진 구현체  
junit-jupiter-params: JUnit Jupiter에서 파라미터를 사용한 테스트를 지원  
junit-jupiter-migrationsupport: JUnit4에서 JUnit Jupiter로의 전환을 지원하며 JUnit4 rule을 실행하는데 사용  
  
# **JUnit Vintage**  
JUnit Vintage는 JUnit Platform에서 JUnit3와 JUnit4 기반 테스트를 실행하기 위한 TestEngine을 제공  
JUnit Vintage에 포함된 아티팩트는 junit-vintage-engine이 유일하다 junit-vintage-engine은 JUnit3나 JUnit4로  
작성된 테스트를 실행하기 위한 엔진 구현체를 말한다 물론 JUnit Vintage를 사용하기 위해서는 JUnit3나 JUnit4 jar 파일이 필요하다  
junit-vintage-engine은 JUnit5를 활용한 테스트와 이전 버전의 테스트를 같이 사용하고자 할 때 매우 유용하다  
JUnit5를 사용하여 프로젝트를 진행해야 하지만 예전 버전의 테스트를 여전히 유지해야 할 수도 있다  
이런 상황에서는 JUnit Vintage가 대안이 될 수 있다  
  



