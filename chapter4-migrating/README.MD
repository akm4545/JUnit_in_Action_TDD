# **JUnit4에서 JUnit5로의 전환 과정**  
JUnit5에는 새호운 패키지, 어노테이션, 메서드, 클래스가 추가되었다 일부 JUnit5 기능은 JUnit4 기능과 유사하지만  
JUnit5에 추가된 기능들은 보통 기존에 없던 신규 기능이다 JUnit Jupiter의 프로그래밍 모델과 확장 모델은  
기본적으로 JUnit4의 rule이나 runner 같은 기능을 지속해서 지원하지 않는다  
  
JUnit은 JUnit Vintage 테스트 엔진을 활용하여 테스트를 JUnit4에서 JUnit5로 전환하는 로드맵을 제시한다  
JUnit Vintage 테스트 엔진은 JUnit Platform에서 과거 JUnit 버전 테스트를 실행할 수 있게 해 준다  
클래스패스에 JUnit5 Jupiter와 JUnit4가 모두 존재하더라도 충돌이 발생하지는 않는다 JUnit5와 JUnit4는 전환을  
완료할 때까지 공존할 수 있으므로 테스트를 전환하는 작업은 작업의 우선순위와 작업 단계에서 마주하게 될 다양한 어려움을  
고려하여 신중하게 진행할 수 있다  
  
전환 주요 단계  
의존성을 교체한다  
JUnit4에서는 하나의 의존성만 있으면 된다. 하지만 JUnit5에는 목적에 따라 다양한 의존성이 필요할 수 있다.  
예를 들어 JUnit5에서 JUnit4 테스트를 실행하기 위해서는 Vintage가 필요하다.  
  
JUnit4 어노테이션을 JUnit5 어노테이션으로 교체한다.    
JUnit5는 JUnit4의 어노테이션 중의 일부를 가지고 있다. 그리고 JUnit5에서 새로 추가된 어노테이션은 신규 기능을 적용하고  
더 나은 테스트를 작성하는 데 도움이 된다.    
  
테스트 클래스와 메서드를 교체한다.  
JUnit5에서 사용하는 단언문이나 가정문은 다른 패키지의 다른 클래스로 옮겨졌다.  
  
JUnit4 rule과 runner를 JUnit5의 확장 모델로 교체한다.  
JUnit4 rule과 runner를 JUnit5 확장 모델로 바꾸는 작업에는 일반적으로 다른 단계보다 많은 노력이 필요하다.  
하지만 JUnit4와 JUnit5는 장기간 공존할 수 있으므로 JUnit4 rule과 runner는 코드에 계속 남아 있어도 상관없고 나중에  
바꾸어도 괜찮다.  
  
# **JUnit4에서 JUnit5로 전환하는 데 필요한 의존성**  
JUnit5를 사용한다는 것은 곧 디스플레이 네임을 사용한 중첩 테스트나 동적 테스트를 수행할 수 있다는  
말과 같다. JUnit5의 기능을 활용해서 테스트를 수행하기 위해서는 먼저 의존성을 교체해야 한다.  
JUnit4는 모놀리식 아키텍처로 되어 있으므로 Maven 설정 파일을 확인해 보면 한 개의 의존성만을 가지고 있다.  
  
JUnit5의 JUnit Vintage는 JUnit4의 의존성을 대체할 수 있다. JUnit5 Vintage를 활용한다면  
Maven은 JUnit4의 의존성에 추이적으로 접근할 수 있다. JUnit5 Vintage를 적용하는 것이 JUnit4를  
JUnit5로 전환하는 첫 번째 단계다. JUnit4와 JUnit5 테스트는 전환 과제가 끝날 떄까지 프로젝트 내에서  
계속 공존할 수 있다.  
  
pom.xml 참조  
  
다음으로 JUnit4 어노테이션을 JUnit5 Jupiter 어노테이션으로 교체해야 한다.  
JUnit5를 사용하여 테스트를 작성하기 위해서는 junit-jupiter-api와 junit-jupiter-engine  
의존성을 추가해야 한다. junit-jupiter-api는 (어노테이션, 클래스, 메서드를 포함하여) JUnit Jupiter로  
테스트를 작성하기 위한 API를 제공하는 아티팩트다. junit-jupiter-engine은 테스트 엔진을 실행하기 위한  
JUnit Jupiter의 핵심적인 아티팩트다.
  
pom.xml 참조  
  
이 외에도 파라미터를 사용한 테스트를 작성하려면 junit-jupiter-params 의존성이 필요할 수 있다.  
만약 JUnit4에서 JUnit5로 전환하는 작업이 끝났다면 junit-vintage-engine 의존성을 삭제해도 무방하다.  
  
# **JUnit4와 JUnit5에서 비슷하게 사용하는 어노테이션, 클래스, 메서드**  
어노테이션  
JUnit4 -> JUnit5  
@BeforeClass, @AfterClass -> @BeforeAll, @AfterAll  
@Before, @After -> @BeforeEach, @AfterEach  
@Ignore -> @Disabled  
@Category -> @Tag  
  
단언문  
JUnit4 -> JUnit5  
Assert 클래스를 사용한다. -> Assertions 클래스를 사용한다.  
단언문 메세지는 첫 번째 파라미터에 적는다. -> 단언문 메세지는 마지막 파라미터에 적는다.  
assertThat 메서드를 사용할 수 있다. -> assertThat 메서드를 지원하지 않는다. assertAll과 assertThrows 메서드가 추가되었다.  
  
가정문  
JUnit4 -> JUnit5  
Assume 클래스를 사용한다. -> Assumptions 클래스를 사용한다.  
assumeNotNull, assumeNoException 메서드를 사용할 수 있다. -> assumeNotNull, assumeNoException 메서드를 사용할 수 없다.  
  
JUnit4와 JUnit5의 생애주기 메서드 전환  
  
lifecycle 패키지 아래 -> 클래스들 참조  
  
JUnit5로 넘어오면서 테스트 메서드에 대한 기본 접근 제어 수준이 public에서 디폴트로 완화되었다.  
테스트 메서드는 테스트 클래스가 속한 패키지 내에서만 접근하면 되므로 굳이 public으로 선언할 필요가 없다.  
  
# **JUnit4의 @Category와 JUnit5의 @Tag**  
그룹화 작업을 위해 JUnit4에서 @Category를 사용했다면 JUnit5에서는 @Tag를 사용해야 한다.  
  
categories 패키지 하위 클래스 참조  
suites 패키지 하위 클래스 참조  
  
JUnit4 카테고리는 여전히 많이 사용되지만 단점도 있다. 테스트 묶음을 위한 특별한 인터페이스를 만들어야 하며  
@Category의 파라미터에 들어갈 마커 인터페이스를 전부 따로 정의해야 한다. 결국 작성해야 할 코드가 너무 많아진다.  
  
JUnit5의 태그 예제  
Tag 패키지 하위 클래스 참조  
  
JUnit4 카테고리를 대체하는 JUnit5 태그를 사용하기 위해서는 pom.xml 파일을 수정하거나 IDE를 이용해 실행할 태그를 선택한다.  
  
pom.xml 참조  
  



