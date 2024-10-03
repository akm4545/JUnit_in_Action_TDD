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
  