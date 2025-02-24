# **JUnit5 확장 모델 살펴보기**

JUnit4는 runner와 rule을 사용하여 테스트를 확장할 수 있었다. 반면 JUnit5 확장 모델은 Extension API라는 단일 개념으로 설명할 수  
있다. Extension 자체는 내부에 필드나 메서드가 없는 인터페이스인 마커 인터페이스(태그 인터페이스 또는 토큰 인터페이스라고도 한다.)일  
뿐이다. 마커 인터페이스는 구현 메서드가 따로 없는 인터페이스로 해당 인터페이스를 구현하는 클래스에 특별한 의미나 기능을 부여하기 위해  
사용한다. 대표적인 사례로는 Serializable 또는 Cloneable 인터페이스가 있다. Serializable 인터페이스에는 구현 메서드가 없지만  
해당 인터페이스를 구현하는 클래스는 직렬화 속성을 갖는다는 것을 나타낼 수 있다.

JUnit5 extension으로 테스트 클래스나 테스트 메서드의 동작을 확장할 수 있으며 이러한 extension을 다른 테스트에서도 재사용할 수 있다.  
JUnit5 extension은 특정 이벤트를 감시하다가 이벤트가 발생하면 테스트를 작동하게 할 수도 있다. 이러한 이벤트를 확장 지점(extension  
point)이라고 하는데 테스트가 생애 주기를 타는 중에 사전에 정의한 확장 지점에 걸리면 JUnit 엔진은 등록한 extension을 자동으로 호출한다.

확장 지점의 종류는 다음과 같다.

- 조건부 테스트 실행: 특정 조건을 충족했을 때 테스트를 실행하기 위해 사용한다.
- 생애 주기 콜백: 테스트가 생애 주기에 반응하도록 만들어야 할 때 사용한다.
- 파라미터 리졸브: 런타임에서 테스트에 주입할 파라미터를 리졸브하는 시점에서 사용한다.
- 예외 처리: 특정 유형의 예외가 발생할 때 수행할 테스트 동작을 정의한다.
- 테스트 인스턴스 후처리: 테스트 인스턴스가 생성된 다음에 실행할 때 사용한다.

이러한 JUnit5 extension은 주로 프레임워크나 빌드 도구에서 사용한다. 애플리케이션 개발에서도 쓸 수 있지만 프레임워크나 빌드 도구에서  
쓰는 정도로 많이 쓰진 않는다. JUnit5 extension을 생성하는 것과 사용하는 데에는 일반적인 원칙이 있다.

# **JUnit5 extension 생성하기**

예제로 사용할 항공편 Passenger 클래스, 테스트 클래스 예제  
Passenger 클래스, PassengerTest 클래스 참조

개발자는 콘텍스트에 맞게 테스트를 실행할지 말지 결정하는 로직을 개발한다고 가정하자. 가령 승객 수에 따라 원활("low"), 보통("regular"),  
혼잡("peak")으로 구분된 세 가지 콘텍스트가 있다. 그리고 원활과 보통 콘텍스트에서만 테스트가 실행되어야 한다고 하자. 혼잡 콘텍스트에서는  
테스트를 실행하면 안 된다. 시스템이 과부하되도록 만들 필요는 없기 때문이다.

개발자의 작업은 혼잡 콘텍스트에는 테스트를 실행하지 않고 원활과 보통 콘텍스트에서만 테스트를 실행하는 JUnit5 extension을 만드는 것이다.  
그런 다음 개발자는 JUnit5 extension을 사용해서 테스트를 확장한다. 그렇다면 ExecutionCondition 인터페이스를 구현하는 방식으로 테스트를  
확장할 수 있다.

extension 예제  
extensions 패키지 -> ExecutionContextExtension 클래스 참조

이제 PassengerTest에 @ExtendWith 어노테이션을 추가하면 테스트를 확장할 수 있다. 만약 테스트가 빌활성화된다면 콘솔에서 테스트가 실행되지  
못하는 이유를 확인할 수 있다.

참고로 JVM이 테스트 조건부 실행의 효과를 우회하게 만들 수 있다. 조건부 실행 자체를 비활성화하려면 junit.jupiter.conditions.deactivate  
설정을 변경하면 된다. [Run]-[Edit Configuration]을 클릭하여 junit.jupiter.conditions.deactivate=*을 설정한다. 그러면 테스트 실행과  
관련한 모든 조건을 비활성화할 수 있다. 이렇게 하면 테스트가 어떤 조건에도 영향을 받지 않으므로 결국 모든 테스트가 실행될 것이다.

# **확장 지점을 사용하여 JUnit5 테스트 구현하기**

개발자는 승객 관련 부분 비즈니스 로직을 구현하고 테스트한다고 가정하자. 물론 승객 정보가 데이터베이스에 영속되는 것까지 확인해야 한다.

# **승객 정보를 데이터베이스에 영속시키기**

개발자의 작업은 승객 정보를 테스트 데이터베이스에 영속시키는 것이다. 물론 테스트 묶음이 실행되기 전에는 데이터베이스가 전부 초기화되어  
있어야 하며 데이터베이스 커넥션을 항상 갖고 있어야 한다. 그리고 테스트 묶음을 전부 실행한 다음에는 데이터베이스 커넥션을 반납해야  
한다. 테스트를 실행하기 전에 데이터베이스를 알려진 상태(일정한 상태)로 만들어 적절히 테스트할 수 있는 상황이 되었는지도 확인해야 한다.

사용할 데이터베이스는 H2로 자바로 개발된 관계형 데이터베이스이며 인메모리(in-memory) 데이터베이스로 많이 사용된다. H2는 테스트  
목적으로 자바 애플리케이션에 내장되는 경우가 많다.

데이터베이스 커넥션을 관리하기 위한 클래스 예제  
jdbc 패키지 -> ConnectionManager 클래스 참조

데이터베이스 테이블을 관리하기 위한 클래스 예제  
jdbc 패키지 -> TablesManager 클래스 참조

데이터베이스에 대한 쿼리 실행을 구현하기 위한 DAO 예제  
jdbc 패키지 -> PassengerDao, PassengerDaoImpl 참조

이제 개발자는 아래 내용을 실행하기 위한 JUnit5 extension을 구현한다.

1. 전체 테스트 묶음을 실행하기 전에 데이터베이스를 초기화하고 데이터베이스 커넥션을 얻는다.
2. 테스트 묶음이 종료되었을 때 데이터베이스 커넥션을 반납한다．
3. 테스트를 실행하기 전에 데이터베이스가 알려진 상태인지 확인해서 개발자가 테스트를 정확하게 실행할 수 있는지 확인 가능하게 한다．　　
  
요구 사항을 보면 테스트의 생애 주기에 따라 개발자가 생애 주기 콜백을 구현하는 것은 당연하다. 테스트 생애 주기와 관련한 extension을 구현하기  
위해 개발자는 다음 인터페이스를 구현한다.  
- BeforeEachCallback과 AfterEachCallback은 각 테스트 메서드가 실행되기 전후에 각각 실행된다.  
- beforeAllCallback과 AfterAllCallback은 모든 테스트 메서드가 실행되기 전후에 한 번 실행된다.  
  
extensions -> DatabaseOperationsExtension 클래스 참조  
  
PassengerTest 클래스에 확장을 @ExtendWith 어노테이션에 추가하고 데이터베이스의 기능을 검증하기 위해 새로운 테스트 추가  
PassengerTest 클래스 참조  
  
테스트를 실행하면 에러가 발생한다.  
PassengerTest 클래스의 생성자가 PassengerDao 타입의 파라미터를 받지만 해당 파라미터를 리졸브하는 ParameterResolver가 없기 떄문에  
발생한다. 이 문제를 해결하기 위해서는 ParameterResolver 인터페이스를 구현해야 한다.  
  
extensions -> DataAccessObjectParameterResolver 클래스 참조  
  
위와 같이 @ExtendWith 어노테이션으로 테스트를 확장하여 사용하면 된다.  
  
# **승객의 고유성 검증하기**  
개발자는 동일한 승객이 두 번 이상 중복되어 저장되지 않도록 하는 기능을 추가한다. 이런 요구 사항을 구현하기 위해 개발자는 먼저 사용자 정의 예외를  
만들고 예외 처리 extension을 구현하고자 한다. 개발자는 일반적인 SQLException을 사용하는 것보다 사용자 정의 예외를 사용하는 것이 오류를  
명확하게 표현할 수 있다고 생각했기 때문에 예외를 새로 정의한다.  
  
jdbc -> PassengerExistsException 클래스 참조  
  
다음으로 개발자는 PassengerDao 인터페이스와 PassengerDaoImpl 클래스가 insert 메서드에서 예외를 던질 수 있게 수정한다.  
  
개발자는 동일한 승객을 두 번 반복해서 삽입하는 시나리오에 대한 테스트를 만들 것이다. 이 테스트에서는 예외가 발생할 것이 예상되므로 먼저 예외 처리  
extension을 구현하여 예외를 로그로 남길 것이다.  
  
extensions -> LogPassengerExistsExceptionExtension 클래스 참조  
  
PassengerTest 클래스에 확장을 추가 등록한다.

# **확장지점**  
확장 지점 / 구현해야 하는 인터페이스  
- 조건부 테스트 실행: ExecutionCondition  
- 생애 주기 콜백: BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback  
- 파리미터 리졸브: ParameterResolver  
- 예외 처리: TestExecutionExceptionHandler  
- 테스트 인스턴스 후처리: TestInstancePostProcessor
　　