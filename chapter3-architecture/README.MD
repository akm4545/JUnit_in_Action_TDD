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
  

