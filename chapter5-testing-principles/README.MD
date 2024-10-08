# **단위 테스트를 하는 이유**  
단위 테스트의 핵심 목표는 애플리케이션이 예상대로 작동하는지 확인하고 사전에 버그를 찾아내는 것이다.  
기능 테스트로도 작동을 확인하고 버그를 찾을 수 있다. 하지만 단위 테스트는 기능 테스트보다 훨씬  
강력하고 다양한 기능을 제공한다.  
  
단위 테스트를 수행했을 때의 장점  
- 기능 테스트만 수행했을 때보다 테스트 커버리지를 높일 수 있다.  
- 팀 생산성이 향상된다.  
- 회귀(regression)를 사전에 발견하여 디버깅 작업을 줄일 수 있다.  
- 소스를 리팩토링(refactoring)하거나 변경할 때 개발자에게 확신을 준다.  
- 애플리케이션 기능 구현에 도움을 준다.  
- 코드의 예상 동작을 문서화할 수 있다.  
- 코드 커버리지를 비롯해 다양한 지표를 측정하게 해 준다.  
  
단위 테스트 = 소스코드의 특정 모듈(프로그램 내 하나의 기능)이 의도된 대로 정확히 작동하는지 검증  
외부 리소스를 직접 사용해야 하는 테스트라면 단위 테스트가 아니다.  
  
기능 테스트 = 사용자와 어플리케이션의 상호작용이 원활하게 이루어지는지 테스트  
따라서 테스트 수행시 시스템을 구동하고 사용하는데 필요한 모든 구성요소가 필요하다.  
E2E(End to End) 테스트로도 볼 수 있다.
  
# **테스트 커버리지를 높일 수 있다.**  
단위 테스트는 애플리케이션이 가져야 하는 첫 번째 유형의 테스트다. 물론 단위 테스트 작성과  
기능 테스트 작성 중 하나만 해야 한다면 기능 테스트를 작성해야 한다. 대략 기능 테스트로는  
애플리케이션 코드의 70%를 커버할 수 있다. 기능 테스트로 커버하는 수준보다 더 높은 테스트  
커버리지를 얻어야 한다면 단위 테스트를 작성하는 것이 좋다.  
단위 테스트는 기능 테스트로는 수행하기 어렵거나 불가능한 오류 조건에 대해서도 쉽게 테스트할 수 있다.  
  
# **팀 생산성이 향상된다**  
단위 테스트를 활용하면 다른 컴포넌트가 준비될 때까지 기다리지 않고도 질적으로 우수한 코드  
(즉 테스트된 코드)를 전달할 수 있다. 반면 기능 테스트는 조금 더 단위가 크며 테스트를 실행하기  
전에 전체 애플리케이션 혹은 상당한 부분이 준비되어 있어야 한다.  
  
# **회귀를 사전에 발견하여 디버깅 작업을 줄일 수 있다.**  
통과하는 단위 테스트 묶음은 코드가 제대로 동작하는지 확인해 주고 기존 소스를 리팩토링 하거나 새로운  
기능을 추가 또는 수정할 때 개발자에게 확신을 준다.  
단위 테스트 묶음을 사용하면 어디에 문제가 있는지 알 수 있고 애플리케이션을 일일이 디버깅할 필요도  
줄어든다. 기능 테스트는 유스 케이스 어디엔가 버그가 숨어 있다는 사실을 알려주지만 단위 테스트는  
특정 메서드가 어떤 이유로 실패했는지를 구체적으로 알려준다. 즉 무엇이 문제인지 찾아내기 위해 몇 시간씩  
들여 고생할 필요가 없다.  
  
# **자신 있게 리팩토링할 수 있다**  
단위 테스트가 없으면 리팩토링을 정당화하기 어렵다. 소스를 고치는 일에는 항상 오류를 만들 위험이 내포되어 있기  
때문이다. 구현을 조금 개선하거나 메서드 이름을 바꾸기 위해 굳이 몇 시간이나 디버딩할 위험을 감수할 필요는 없다.  
이럴 때 단위 테스트가 있다면 리팩토링에 대한 확신을 주고 안전망을 제공해 줄 것이다.  
  
# **기능 구현에 도움이 된다**  
단위 테스트는 테스트 중인 API를 유연하게 만들고 격리된 상태에서 테스트가 가능하게끔 만들어 준다.  
떄로는 단위 테스트를 하기 위해 소스를 리팩토링해야 하는 경우도 있다. 즉 단위 테스트를 잘할 수 있게  
소스 코드를 짜야 한다는 TDD를 실천하게 될 것이다.  
단위 테스트를 만들고 수정할 때는 단위 테스트를 잘 관찰하는 것이 중요하다.  
단위 테스트가 너무 길고 다루기 힘들다면 일반적으로는 테스트 대상 코드에 설계 문제가 있는 것이며  
리팩토링이 필요하다. 테스트 메서드 하나가 너무 많은 기능을 테스트하고 있을 수도 있다.  
격리된 상태에서 테스트가 기능을 검증하지 못한다는 건 테스트 대상 코드가 충분히 유연하지 못하며 리팩토링이  
필요하다는 의미다.  
  
# **코드의 예상 동작을 문서화할 수 있다**  
새로운 API를 배워야 한다고 가정했을 때 API를 상세하게 설명하는 300쪽 분량의 문서와 API 사용 방법에 대한  
몇 가지 예제가 있다면 예제를 선택할 것이다.  
단위 테스트는 그 자체로 API 사용 예제가 된다. 따라서 단위 테스트는 그 자체로 개발자들에게 훌륭한 설계 문서가 될 수  
있다. 단위 테스트는 현재 운영 중인 코드와 함께 업데이트되므로 일반적인 설계 문서와 달리 최신 상태일 수밖에 없다.  
테스트를 수행하기만 해도 유스 케이스를 명확하게 알 수 있다. 유스 케이스를 알 수 있고 각 테스트를 실행할  
때마다 구체적으로 어떤 일이 일어날지를 알 수 있으므로 테스트는 프로젝트 문서의 일부라고 할 수 있다.  
  
# **코드 커버리지 및 다양한 지표를 측정할 수 있게 해 준다**  
단위 테스트는 버튼만 누르면 모든 프로그램이 여전히 잘 동작하는지를 알려 준다. 또한 단위 테스트는  
라인별로 테스트가 실행 되었는지 되지 않았는지 보여주는 코드 커버리지 지표도 제공한다. 도구를 사용하여  
진행한 빌드에서 다음 빌드까지 테스트 통과/실패의 진행 상황도 추적할 수 있다. 테스트 성능을 모니터링하여  
성능이 이전 빌드보다 떨어졌다면 테스트가 실패하도록 유도할 수도 있다.  
  
# **테스트 유형**  
소프트웨어 테스트를 분류하는 방법은 다양하지만(물론 용어의 차이도 있다) 크게 네 가지로 분류한다.  
- 단위 테스트  
- 통합 테스트  
- 시스템 테스트  
- 인수 테스트  
  
# **단위 테스트**  
단위 테스트는 소스 코드의 개별 단위(메서드나 클래스)를 테스트하여 해당 코드를 사용할 수 있는지를 결정하는  
소프트웨어 테스트 기법이다. 단위 테스트는 개발 과정에서 발생할 수 있는 잠재적 오류로부터 개발자를 보호하는  
안정망 역할을 하므로 코드 변경을 하고자 하는 개발자에게 확신을 줄 수 있다. 좋은 단위 테스트가 있고 코드를  
수정할 때마다 테스트를 실행한다면 변경점이 기존 기능에 영향을 미치지 않는다 확신할 수 있다.  
단위 테스트에서는 개별 단위를 격리하여 테스트하는 것이 중요하다.  
  
# **통합 테스트**  
단위 테스트는 품질 관리가 필수적이다. 단위 테스트로 개별 클래스나 메서드 테스트가 잘되었다면 다음은 클래스를  
다른 메서드나 서비스와 연결하는 과정이 필요하다. 통합 테스트는 대상 환경에서 실행 가능한 컴포넌트 간의 상호작용을  
테스트하는 것이다.  
  
컴포넌트 간에 상호작용하는 경우  
상호작용 = 테스트 설명  
객체 = 테스트는 객체를 인스턴스화하고 다른 객체의 메서드를 호출한다. 통합 테스트를 통해 다른 클래스에  
속한 객체 간에 어떻게 협력해서 문제를 해결할 수 있는지 확인할 수 있다.  
서비스 = 통합 테스트는 컨테이너가 애플리케이션을 관리하는 동안 실행된다. 그동안 애플리케이션은 데이터베이스와  
연동되어 있거나 다른 외부 리소스, 장치와 통신할 수 있다. 컨테이너에 배포되는 애플리케이션을 개발할 때  
통합 테스트를 활용할 수 있다.  
서브 시스템 = 계층적 애플리케이션에서는 화면을 관리하는 프론트엔드와 비즈니스 로직을 수행하는 백엔드로  
구분된다. 통합 테스트를 가지고 프론트엔드를 통과한 요청이 벡엔드에서 적절한 응답을 반환하는지 확인할 수 있다.  
웹 인터페이스와 같은 프론트엔드와 비즈니스 로직을 수행하는 벡엔드가 구분된 아키텍처를 가진 애플리케이션은  
통합 테스트를 활용할 수 있다.  
  
교차로에서 더 많은 교통사고가 발생하는 것처럼 객체 간 상호작용이 발생하는 지점에서 버그가 생기기 쉽다.  
이상적으로는 애플리케이션 코드를 짜기 전에 통합 테스트부터 정의해야 한다. 통합 테스트 코드 작성은  
개발자가 올바르게 동작하는 객체를 만드는 능력을 향상시키는 데에 큰 도움을 준다.  
  
# **시스템 테스트**  
시스템 테스트는 시스템이 구체화된 요구 사항을 만족하는지 평가하기 위해 완전한 통합 환경에서 수행하는 테스트를  
말한다. 시트템 테스트의 목표는 통합되어 있는 단위 간의 불일치를 찾아내는 것이다.  
테스트 더블(test doulbe)이나 모의 객체(mock object)는 복잡한 실제 객체의 동작을 모사할 수 있으므로  
의존하는 컴포넌트를 테스트에 통합하는 것이 불가능하거나 지금 당장은 사용할 수 없을 때 유용하게 사용할 수  
있다. 모의 객체는 단위 테스트 수준에서도 자주 사용한다. 모의 객체는 일부 객체를 테스트에 통합할 수 없거나  
통합할 필요가 없을 때 그 객체를 대체하는 역할을 한다. 테스트 더블은 실제 객체를 대체하기 위해 만든 것으로  
실제 객체의 동작을 비슷하게 흉내 내는 데 사용한다. 모의 객체나 테스트 더블은 이를 사용하는 객체의 동작을  
테스트하기 위해 만든다.  
  
# **인수 테스트**  
애플리케이션이 오류 없이 수행되는 것도 중요하다. 그러나 애플리케이션은 반드시 고객의 필요를 만족시켜야 한다.  
인수 테스트는 최종 수준의 테스트 단계라고 할 수 있다. 인수 테스트는 애플리케이션이 고객이나 이해관계자가  
정의한 목표를 달성하는지 확인하기 위해 사용한다.  
인수 테스트는 최상위 수준의 테스트로 인수 테스트를 활용하면 애플리케이션이 비즈니스 목표를 달성하고 기획한  
작업을 제대로 하고 있는지와 같은 필수적인 질문데 답할 수 있다.  
인수 테스트는 Given, When, Then 같은 키워드를 사용하여 표현할 수 있다. Given, When, Then 키워드를  
사용한다는 것은 결국 사용자가 시스템과 상호 작용한다는 시나리오를 따르게 되는 것이다.  
  
Given 이코노미 항공편에서  
When 일반 승객은  
Then 추가할 수 있고 삭제할 수도 있다.  
  
인수 테스트는 가장 넓은 범위를 다루는 테스트이므로 애플리케이션의 기능을 테스트하는 것이며 애플리케이션의  
상당 부분이 현재 사용 가능하다는 것을 전제로 한다.  

# **블랙박스 테스트**  
블랙박스 테스트(black-box testing)는 시스템의 내부 상태나 동작에 대한 지식이 없을 때 수행하는 테스트다.  
블랙박스 테스트는 정확성을 검증하기 위해 외부적인 시스템 인터페이스를 사용한다.  
이름에서부터 알 수 있듯 블랙박스 테스트는 시스템을 블랙박스로 취급한다는 특징이 있다. 예를 들어  
버튼과 LED 전구가 붙어 있는 상자가 있다고 가정했을 때 내부에 무엇이 있는지 또는 상자가 어떻게 작동하는지는  
모른다. 다만 올바른 입력이 제공될때 시스템이 제대로 된 출력을 한다는 것만은 알고 있다. 시스템을 제대로  
테스트 하기 위해 알아야 하는 것은 시스템의 기능적 명세다. 일반적으로는 프로젝트 초기에 명세를 작성하므로  
블랙박스 테스트는 비교적 일찍 시작할 수 있다. QA 엔지니어, 개발자, 고객 등 누구나 시스템을 테스트할 수 있다는 것도  
블랙박스 테스트의 특징이다.  
블랙박스 테스트의 가장 간단한 형태는 사용자 인터페이스에서 수동으로 직접 작업을 해 보는 것이다.  
조금 더 정교한 방법으로는 HttpUnit, HtmlUnit, 셀레니움(Selenium)과 같은 도구를 사용할 수 있다.  
  
# **화이트박스 테스트**  
유리 상자 테스트(glass-box testing)라고도 한다. 화이트박스 테스트는 구현에 관한 해박한 지식을  
바탕으로 테스트를 만들고 테스트 프로세스를 진행한다. 화이트박스 테스트를 만들기 위해서는 컴포넌트  
구현을 이해하는 것 외에도 테스트 프로세스가 다른 구성 요소와 상호작용하는 방식을 알아야 한다.  
그러므로 화이트박스 테스트를 실행하는 데에는 개발자가 적격이다.  
GUI를 사용할 수 있을 때까지 기다릴 필요가 없으므로 화이트박스 테스트는 프로젝트 초기 단계에서  
구현할 수 있다. 게다가 다양한 실행 경로를 커버할 수 있다.  
화이트박스 테스트를 만들기 위해서는 애플리케이션 내부(적어도 API)에 대한 지식이 필요하다는  
장벽이 있지만 외부 사용자가 미처 알 수 없는 다양한 실행 경로를 다룰 수 있다.  
  
# **장단점 비교**  
절대적인 답은 없으며 두 가지 방식을 함께 고려하는 것이 가장 합리적이다.  
  
블랙박스 테스트 장점  
- 사용자 중심적이며 설계 명세와 다른 부분이 무엇인지 바로 알 수 있다.  
- 테스터가 비개발자여도 상관없다.  
- 개발자와 독립적으로 수행할 수 있다.  
  
블랙박스 테스트 단점  
- 입력할 수 있는 경우의 수가 제한적이다.  
- 커버되지 않은 실행 경로가 많을 수 있다.  
- 테스트가 중복될 수 있다. 세부 정보가 없다는 것은 동일한 실행 경로를 여러 번 커버할 수 있다는  
것을 의미한다.  
  
화이트박스 테스트 장점  
- 프로젝트 초기부터 테스트를 구현할 수 있다.  
- GUI가 필요하지 않다.  
- 개발자가 제어하므로 다양한 실행 경로를 커버할 수 있다.  
  
화이트박스 테스트 단점  
- 프로그래밍 지식이 있는 사람만 테스트를 구현할 수 있다.  
- 구현을 변경하려면 테스트를 다시 작성해야 한다.  
- 테스트와 구현이 밀접하게 결합되어 있다.  
  
# **사용자 중심 접근**  
블랙박스 테스트는 먼저 사용자 요구 사항을 해결한다. 고객의 피드백에는 엄청난 가치가 있다.  
익스트림 프로그래밍의 목표 중 하나는 제품을 빨리 그리고 자주 릴리스하는 것이다. 그러나 고객에게  
여기에 당신의 의견을 남겨주세요 라는 식으로 요청한다면 제대로된 피드백을 받기는 어려울 것이다.  
그 대신 수동으로 실행해 볼 수 있는 테스트 스크립트를 고객에게 제공하여 고객을 테스트에 참여시키면  
훨씬 좋은 결과를 가져올 수 있다. 고객이 애플리케이션에 대해 생각하게 함으로써 시스템이 수행해  
줘야 하는 작업을 명확하게 이야기할 것이디 때문이다. 미리 만들어진 GUI와 상호작용한 결과를  
예상 결과와 비교하는 것도 고객이 직접 테스트를 수행하는 사례라고 볼 수 있다.  
  
# **테스트의 어려움**  
블랙박스 테스트는 웹 브라우저든 테스크톱 애플리케이션이든 프론트엔드를 구현해야 하므로 테스트  
작성과 실행이 더 어렵다. 또 다른 문제는 화면에 나타난 결과가 예상한 바와 같다고 해서 그것이  
애플리케이션이 항상 정확하게 동작했다는 것을 보장하지 않는다는 점이다. 화이트박스 테스트는  
일반적으로 블랙박스 테스트보다 테스트 작성과 실행이 더 쉽지만 개발자가 이를 직접 구현해야  
한다는 불편함이 있다.  
  
# **테스트 커버리지**  
화이트박스 테스트를 이용하면 블랙박스 테스트보다 더 나은 테스트 커버리지를 얻을 수 있다. 반면 블랙박스  
테스트는 화이트박스 테스트보다 더 많은 비즈니스적 가치가 있을 수 있다.  
이러한 테스트 구분은 현학적으로 보일 수 있다. 그러나 분할 정복(divide and conquer)이 소프트웨어  
개발에만 적용되어야 하는 것은 아니다. 테스트에도 적용할 수 있다. 개발자가 여러 유형의 테스트를 활용하면  
코드 커버리지를 높일 수 있을뿐더러 애플리케이션을 리팩토링하거나 개선하는 데에도 자신감이 생길 것이다.  

  
