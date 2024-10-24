# **모의 객체로 테스트하기**  
개별 메서드 단위 테스트 시 다른 환경이나 다른 메서드와 격리하는 것은 좋은 목표다. 이를 위해 스텁을 활용하여 소스  
코드를 웹 서버, 파일 시스템, 데이터베이스 등의 환경에서 격리하여 단위 테스트를 할 수 있었다. 메서드 호출을 다른  
클래스로부터 격리하는 것과 같이 조금 더 세밀한 격리에서는 모의 객체를 사용한다.  
  
모의 객체의 개념은 팀 매키넌, 스티브 프리먼, 필립 크레이그가 XP2000에서 처음 제시했다. 모의 객체를 사용하면  
가능한 한 가장 세밀한 수준에서 테스트를 실행할 수 있다. 각 메서드별로 개별적인 단위 테스트를 만들어 개발하는  
것이 가능하다.  
  
# **모의 객체란 무엇인가**  
다른 클래스나 메서드와 격리된 상태에서 테스트를 하면 큰 이점이 있다. 작업할 인터페이스가 구성되어 있기만 하다면  
아직 완성되지 않은 코드도 테스트할 수 있다는 것이다. 격리된 상태에서의 테스트는 다른 부분의 코드가 완성되는  
것을 기다리지 않고 한 부분의 코드를 단위 테스트하는 데 큰 도움이 된다.  
  
모의 객체를 사용할 때의 가장 큰 장점은 메서드에 집중하는 테스트를 만들 수 있다는 것이다. 모의 객체를 사용하면  
테스트 대상 메서드가 메서드가 다른 객체를 호출해서 발생하는 부수 효과가 생길 일이 없다. 테스트를 작게, 목표한  
부분만 집중하여 작성하는 것은 이해하기 쉬운 테스트를 만들고 코드의 다른 부분이 변경되어도 테스트가 깨지지 않게  
한다는 점에서 효과가 크다. 단위 테스트의 장점은 리팩토링할 수 있도록 확신을 준다는 것이다. 단위 테스트는  
회귀를 막기 위한 안전장치다. 모의 객체를 활용해 세밀한 테스트트 하면 더 적은 테스트가 영향을 받게 되고 실패의  
원인을 정확히 찾아 주는 간명한 에러 메시지를 받을 수 있다.  
  
모의 객체는 비즈니스 로직의 일부만을 다른 부분과 격리해 테스트하는 데 적합하다. 모의 객체는 테스트 대상인  
메서드가 사용하는 객체를 대체하므로 테스트 대상 메서드는 다른 객체와 격리되는 효과가 생긴다. 그런 의미에서  
모의 객체는 스텁과 비슷하기도 하다. 그러나 모의 객체는 비즈니스 로직을 새로 구현할 필요가 없다. 모의 객체는  
테스트가 가짜로 만든 클래스의 모든 비즈니스 로직을 제어하도록 하는 껍데기일 뿐이다.  
  
스텁은 사전에 정의된 동작만 수행한다. 간단한 동작이라도 스텁의 행동은 런타임에 따라 달라지지 않는다. 반면  
모의 객체에서 사전에 정의된 동작은 없으며 대신 테스트를 실행하는 중에 모의 객체가 수행할 행동을 기대할 수  
있다. 다른 테스트를 수행할 때는 모의 객체를 새로 초기화한 다음 새로운 행동을 기대하면 된다. 모의 객체를  
활용한 테스트는 모의 객체 초기화 -> 기대 설정 -> 테스트 실행 -> 단언문 검증 순서로 진행된다.  
  
# **모의 객체를 활용해 단위 테스트하기*  
한 계좌에서 다른 계좌로 이체를 하는 간단한 유스 케이스를 생각해 보자.  
  
AccountService는 Account 객체를 처리하는 서비스 클래스다. 그리고 (예를 들어 JDBC 같은 걸 이용해서)  
AccountManager 클래스를 가지고 데이터베이스에 데이터를 영속시킨다. 우리의 관심사인 계좌 이체 서비스는  
AccountService.transfer 메서드로 구현할 수 있다. 모의 객체가 없다면 AccountService.transfer 메서드를  
테스트한다는 것은 데이터베이스를 준비하고 테스트 데이터를 사전에 밀어 넣고 (자바 EE 애플리케이션 서버 같은)  
컨테이너에 코드를 배포해야 한다는 말과 같다. 이런 프로세스는 애플리케이션이 엔드 투 엔드로 동작하는지 확인하는  
데 필요하다. 그러나 비즈니스 로직만 단위 테스트하려는 데 이 정도는 너무 과하다.  
  
계좌이체 솔루션  
account 패키지 하위  
Account, AccountManager, AccountService 참조  
  
AccountService의 transfer 메서드를 단위 테스트 한다고 생각해 보자. 그렇다면 AccountManager의 구현체가 준비될 때까지  
AccountManager에 대한 모의 객체를 구현하여 테스트를 별도로 해야 할 것이다. transfer 메서드가 해당 인터페이스를 사용하며  
격리된 상태로 테스트를 해야 하기 떄문이다.  
  
account 패키지 하위 MockAccountManager 클래스 참조  
  
# **모의 객체에 비즈니스 로직을 작성하지 않는다**  
모의 객체를 작성할 때 가장 중요한 규칙은 모의 객체가 비즈니스 로직을 가져서는 안 된다는 것이다. 모의 객체는 테스트가 시키는  
대로만 해야 한다. 다시 말해 순전히 테스트에 의해서만 구동되는 객체가 모의 객체다. 이러한 특성은 모든 로직을 가지고 있는  
스텁과 반대된다. 모의 객체에 비즈니스 로직을 넣지 않으면 좋은 점이 두 가지 있다.  
1. 모의 객체를 만들기가 쉬워진다.  
2. 모의 객체는 빈 껍데기이므로 모의 객체를 테스트할 필요가 없다.  
  
모의 객체를 사용하는 일반적인 테스트 예제  
account 패키지 -> TestAccountService 클래스 참조  
  
모의 객체가 없이 실제로 테스트를 하려면 JDBC 등을 활용해야만 했다.  
  
# **문제될 만한 것만 테스트한다**  
Account 클래스까지 모의 객체로 만들지는 않았다. 굳이 데이터 접근을 위한 객체까지 모의 객체로 만들 필요가 없기 때문이다.  
이런 객체는 환경에 크게 영향을 받지도 않고 기본적으로 매우 단순한다. 그리고 Account 객체를 사용하는 다른 클래스에 대한  
테스트가 있다면 Account 객체를 간접적으로 테스트한 것으로 간주할 수 있다. Account 클래스가 올바르게 동작하지 않는다면  
Account 객체를 사용하는 다른 테스트가 실패하고 거기서 문제가 무엇인지 알려 줄 것이다.  
  
# **모의 객체를 활용해 리팩토링하기**  
일반적으로 단위 테스트는 테스트 대상 코드에 대해 완전히 투명해야 하며 테스트를 단순하게 만들 목적으로 런타임  
코드를 변경해서는 안 된다는 통념이 있다. 그러나 이는 옳지 않다. 단위 테스트는 런타임 코드의 가장 중요한 클라이언트이며  
다른 클라이언트와 거의 비슷한 수준의 취급을 받아야 한다. 코드가 테스트하기에 충분히 유연하지 않다면 코드를  
수정하는 것은 당연하다.  
  
AccountManager 클래스 구현  
account 패키지 -> DefaultAccountManager1 클래스 참조  
  
상위 예제에는 두 가지 문제가 있다. 둘 다 코드가 충분히 유연하지 못하며 변화에 적응하기 어렵게 설계된 것과 관련이 있다.  
첫 번째 문제는 Log 객체를 바꿔서 쓸 수 없다는 것이다. Log 객체를 클래스 내부에서 생성했기 때문이다. 예를 들어 테스트를  
위해 아무 일도 하지 않는 Log 클래스를 모의하고 싶지만 그럴 수는 없다. 일반적으로 이런 클래스는 어떤 Log 객체가 주어지더라도  
그걸 사용할 수 있어야 한다. 이 클래스의 목표는 로거를 만드는 것이 아니라 JDBC 로직을 수행하는 것이다.  
  
두 번째 문제인 PropertyResourceBundle 클래스도 비슷하다. 지금 당장은 괜찮아 보일 수 있다. 그러나 만약 XML을 사용하기로  
한다면 해당하는 부분도 바뀌어야 한다.  
  
어떤 구현체를 사용할지를 결정하는 것이 이 클래스 설계의 목표가 되어서는 안 된다.  
  
훌륭한 설계 전략은 클래스 안에서 객체를 직접 생성하는 것이 아니라 비즈니스 로직과 직접 관계없는 객체를 파라미터로 전달하는  
것이다. 궁극적으로 로거나 구성 관련 컴포넌트는 여러 곳에서 사용할 수 있도록 최상위 수준으로 올라가야 한다. 이런 전략은 코드를  
유연하게 만들고 변화에 잘 적응할 수 있게 한다.  
  
# **리팩토링 예제**  
호출자가 도메인 객체를 전달할 수 있게 모든 코드를 리팩토링 하는 것은 시간이 오래 걸릴 수 있다. 그리고 단위 테스트를  
작성하기 위해 아직 애플리케이션 전체를 리팩토링할 준비가 되지 않았을 수도 있다. 다행히도 간단한 리팩토링 몇 가지만  
적용하면 인터페이스를 동일하게 유지하면서도 내부에서 생성하면 안 되는 도메인 객체를 외부에서 전달하게 수정할 수 있다.  
  
리팩토링 예제  
account 패키지 -> DefaultAccountManager2 클래스 참조  
  

