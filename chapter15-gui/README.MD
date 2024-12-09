# **프레젠테이션 계층 테스트**  
프레젠테이션 계층 테스트는 간단히 말해서 애플리케이션의 GUI에서 버그를 찾는 작업이다. GUI에서 오류를 찾는 작업은 다른 애플리케이션 계층에서  
오류를 찾는 일만큼이나 중요한 작업이다. 나쁜 사용자 경험은 사용자로 하여금 해당 사이트 이용을 꺼리게 만들어 결국 사용자를 잃게 만든다. 또한 유저  
인터페이스의 버그 때문에 애플리케이션의 다른 부분이 오작동할 수도 있다.  
  
사용자와 상요작용한다는 GUI의 속성 떄문에 GUI 테스트에는 독특한 과제가 있으며 이를 위한 고유한 도구와 기술이 필요하다.  
  
웹 사이트와의 상호작용을 테스트한다는 것은 지속성 관점에서 어려운 일일 수 있다. 시간이 지나면서 웹 사이트 콘텐츠가 변경될 수 있고 인터넷 연결에  
문제가 발생할 수도 있다. 이런 일들이 일어나면 테스트는 일시적으로 혹은 영구적으로 실패할 수도 있다. 따라서 이 장에 제시하는 테스트는 웬만하면  
UI가 변경되지 않을 사이트를 대상으로 하여 테스트가 되도록 오래 유효하게끔 설계되었다.  
  
이 장에서는 다음 내용을 테스트한다.  
- (맞춤법을 포함하여) 웹 페이지의 콘텐츠를 세부적인 수준까지 검증한다.  
- 애플리케이션 구조 또는 내비게이션(예를 들어 링크를 클릭해서 예상되는 페이지로 이동하는지 확인)을 검증한다.  
- 유저 스토리를 인수 테스트로 검증한다.  
  
유저 스토리(user story)는 소프트웨어의 기능을 자연어로 설명한 것을 말한다. 인수 테스트(acceptance test)는 기능 명세의 요구 사항을  
충족했는지를 확인하기 위해 수행하는 테스트다. 웹 애플리케이션이 특정 브라우저나 OS에서 제대로 동작하는지도 검증한다.  
  
# **테스트 프레임워크 선택하기**  
JUnit5 에서 프레젠테이션 계층 테스트를 수행하기 위한 두 가지 오픈 소스 도구인 HtmlUnit과 Selenium이 있다. HtmlUnit은 헤드리스 브라우저  
(headless browser)를 사용하는 테스트 프레임워크로 자바로 만들어졌으므로 JUnit5 테스트와 동일한 가상 머신에서 실행할 수 있다. 헤드리스  
브라우저란 GUI를 제공하지 않는 브라우저다. 즉 브라우저 창이나 유저 인터페이스 없이 프로그램으로 브라우저를 조작하는 방식을 말한다. 애플리케이션이  
OS나 자바스크립트, DOM, CSS 같은 브라우저별 구현과 독립적일 때 HtmlUnit을 사용한다.  
  
반면 Selenium은 다양한 웹 브라우저를 프로그래밍 방식으로 실제로 구동하며 JUnit5 테스트 실행 결과를 보여 준다. 특정 브라우저 버전이나 OS를  
검증하고자 할 때 Selenium을 사용한다. 애플리케이션이 자바스크립트, DOM, CSS 등 브라우저에 특화된 구현을 사용하거나 이에 의존하는 경우에도  
Selenium을 사용할 수 있다.  
  
# **HtmlUnit 살펴보기**  
HtmlUnit은 헤드리스 브라우저를 사용하는 테스트 프레임워크이며 자바로 만들어진 오픈 소스다. HtmlUnit은 사용자가 브라우저 기반 웹 애플리케이션을  
프로그래밍 방식으로 테스트할 수 있게 해 준다. 그러나 HtmlUnit과 JUnit5를 사용한 테스트는 유저 인터페이스를 시각적으로 보여 주지는 않는다.  
  
# **HtmlUnit 실전 예제**  
htmlunit 패키지 -> ManageWebClient 클래스 참조  
  
첫 번째 HtmlUnit 예제  
htmlunit 패키지 -> HtmlUnitPageTest 클래스 참조  
  
# **HtmlUnit 테스트 작성하기**  
HtmlUnit 테스트 작성은 웹 브라우저를 사용하는 사용자의 행동을 시뮬레이션하는 것과 같다. 우리는 브라우저를 사람 손으로 일일이 조작하는 대신  
에뮬레이션된 브라우저를 프로그래밍 방식으로 제어하는 것이다. 이렇게 하면 각 단계마다 HTML 모델 객체를 쿼리하고 그 값이 예상한 바와 일치하는지  
확인할 수 있다. 프레임워크가 문제를 감지하면 예외를 던지므로 오류를 확인할 필요가 없어져 테스트가 간결해진다.  
  
# **HTML 단언문**  
단언문은 단위 테스트의 핵심적인 요소다. HtmlUnit은 JUnit5와 연계가 가능하지만 WebAssert라는 비슷한 개념의 클래스를 제공한다. WebAssert  
클래스에서는 assertTitleEquals, assertTextPresent, notNull 같은 HTML에 관한 표준적인 단언문을 제공한다.  
  
# **특정한 웹 브라우저 테스트하기**  
HtmlUnit 2.50 버전 이후부터는 아래의 브라우저를 지원한다.  
웹 브라우저와 버전 / HtmlUnit BrowserVersion 상수  
- 엣지(최신 버전) / BrowserVersion.EDGE  
- 파이어폭스(최신 버전) / BrowserVersion.FIREFOX  
- 파이어폭스 7.8 / BrowserVersion.FIREFOX_78  
- 크롬(최신 버전) / BrowserVersion.CHROME  
- 현 시점 가장 잘 지원되는 브라우저 / BrowserVersion.BEST_SUPPORTED  
  
브라우저 버전을 특정하지 않았을 때 WebClient는 디폴트로 BrowserVersion.BEST_SUPPORTED를 에뮬레이션한다. 특정한 버전의 브라우저를  
에뮬레이션하려면 아래와 같이 사용한다.  
  
WebClient webClient = new WebClient(BrowserVersion.FIREFOX_78);  
  
# **여러 브라우저 테스트하기**  
htmlunit 패키지 -> JavadocPageAllBrowserTest 클래스 참조  
  
# **독립 실행형 GUI 테스트 생성하기**  
외부 페이지는 사전 고지 없이 변경될 수 있으므로 실제로 URL을 입력해서 테스트 하는 방법이 그리 좋지만은 않을 수 있다. 테스트가 웹 사이트에 심히  
의존적이 되어 웹 사이트를 약간만 바꾸더라도 테스트 결과가 달라질 수 있기 때문이다. 이번에는 테스트 코드에 HTML을 내장해 실행할건데 이 방법을  
진행하려면 웹 클라이언트에서 모의 HTTP 연결을 사용해야 한다. (모의 HTTP 연결은 htmlunit의 MockWebConnection의 인스턴스를 사용한다) 이렇게  
하면 JUnit5 테스트에서 어떤 URL 값을 사용하든지 디폴트로 설정한 페이지를 가져올 수 있다. 이번에는 사전 고지 없이 변경될 수 있는 실제 화면의  
URL을 쓰지 말고 모의 HTTP 연결을 사용하는 웹 클라이언트에서 맺은 HTML 응답의 제목을 테스트 한다.  
  
htmlunit 패키지 -> InLineHtmlFixtureTest 클래스 참조  
  
만약 여러 페이지로 구성된 애플리케이션을 테스트하고 싶다면 각 페이지에 MockWebConnection.setResponse 메서드를 호출하면 된다.  
반드시 URL의 맨 끝을 /로 끝내야 한다. 맨 끝에 /를 쓰지 않으면 모의 HTTP 연결은 해당 URL을 찾을 수가 없다. 만약 /를 작성하지 않았다면 예외가  
발생한다.  
  
# **HTML 폼 테스트하기**  
HtmlPage API는 HTML 폼을 가져오는 데에도 사용할 수 있다. 가령 List<HtmlForm>을 반환하는 getForms 메서드를 사용하여 HTML 폼 리스트를  
가져올 수 있다. 그리고 getFormByName 메서드를 사용하여 특정 이름을 가진 첫 번째 HtmlForm을 가져올 수도 있다. 오버로딩된 HtmlForm.getInput  
메서드 중 하나를 호출하여 HTML 입력을 가져올 수 있고 사용자 입력을 setValueAttribute 메서드를 이용해 변경할 수도 있다.  
  
예제 html 
webapp 패키지 -> formtest.html 참조  
  
html 폼 테스트  
htmlunit 패키지 -> FormTest 클래스 참조  
  
프레임워크가 어떤 단계에서든 객체를 찾지 못하면 API에서 예외가 발생하고 테스트는 자동으로 실패한다. 그러면 개발자는 테스트에 집중할 수 있으며  
페이지나 폼이 예상과 다를 경우 프레임워크가 알아서 테스트를 실패시키므로 테스트 결과를 일일이 검증할 필요가 없다.  
  
# **자바스크립트 테스트하기**  
HtmlUnit은 자바스크립트 함수도 테스트할 수 있다. 예를 들어 Document.write()를 통해 HTML을 생성하더라도 getPage 메서드를 호출하고 요소를  
찾아 클릭하고 결과를 확인하는 일반적인 패턴을 따르기 떄문이다.  
  
다음 문장으로 웹 클라이언트에서 자바스크립트를 지원하게 하거나 지원하지 않도록 만들 수 있다.  
  
webClient.getOptions().setJavaScriptEnabled(true or false);  
  
HtmlUnit은 기본적으로 자바스크립트 지원을 활성화해 놓았다. 다음 문장으로 스크립트가 종료되기 전에 얼마나 오래 실행될 수 있는지 그 시간을 설정할  
수 있다.  
  
webClient.setJavaScriptTimeout(timeout);  
  
자바스크립트 경고나 확인을 처리하기 위해 콜백 루틴을 추가할 수 있다.  
  
# **자바스크립트로 경고 테스트하기**  
테스트로 어떤 자바스크립트 경고(alert)가 발생했는지 알 수 있다. 하단 테스트에서는 페이지가 로드된 다음 발생한 경고를 수집하고 결과를 확인할 수  
있는 경고 핸들러를 사용한다. CollectingAlertHandler 객체에 나중에 검사를 할 수 있도록 경고 메시지를 저장해 둔다.  

htmlunit 패키지 -> FormTest 클래스 참조  
  
경고 동작을 특정하려면 사용자 정의 경고 핸들러를 구현해야 한다. 가령 경고 핸들러를 다음과 같이 설정하면 자바스크립트 경고가 발생했을 때 테스트가  
실패한다.  
  
webClient.setAlertHandler((page, message) -> fail("JavaScript alert: " + message));  
  
이렇게 람다식을 사용하여 자체적으로 경고 핸들러를 구현할 수 있다. 이런 식으로 setConfirmHandler 메서드를 이용하여 경고 외에도 컨펌 핸들러를  
사용자 정의할 수 있다.  
  
htmlunit 패키지 -> WindowConfirmTest 클래스 참조  
  
# **Selenium 살펴보기**  
Selenium은 웹 애플리케이션을 테스트하기 위한 오픈 소스 프레임워크다. Selenium의 최대 강점은 특정 OS 위에서 실제 브라우저를 자동화하여 테스트를  
수행할 수 있다는 것이다. 테스트와 동일한 VM에서 브라우저를 에뮬레이션하는 HtmlUnit과는 다른 점이다. 또한 Selenium은 자바와 Junit5는 물론이고  
여러 프로그래밍 언어를 지원한다.  
  
Selenium 웹 드라이버는 테스트를 작성할 때 사용하는 핵심적인 인터페이스다. Selenium 웹 드라이버는 W3C의 권장 사항이며 크롬, 파이어폭스, 엣지를  
비롯한 여러 최신 브라우저에서 구현한 스펙이다. Selenium 웹 드라이버는 각 브라우저의 기본 자동화 지원을 사용하여 브라우저를 직접 호출한다.  
브라우저마다 직접 호출하는 방식 및 지원 기능에는 약간씩 차이가 있을 수 있다. 브라우저마다 작업을 수행하는 로직이 다르기 떄문이다.  
  
Selenium 웹 드라이버는 다음 네 가지 요소로 구성되어 있다.  
- Selenium 클라이언트 라이브러리: Selenium은 자바 외에도 PHP, 파이썬, 루비 같은 프로그래밍 언어를 위한 여러 가지 라이브러리를 지원한다.  
- HTTP에서 사용하는 JSON 와이어 프로토콜: JSON(JavaScript object notation)은 웹 서버와 클라이언트 간 데이터 전송에 사용한다. 웹 드라이버  
(FirefoxDriver, ChromeDriver, EdgeDriver 등)는 자체적인 HTTP 서버를 가지고 있는데, JSON은 REST API 데이터를 HTTP 서버로 전송하는 데  
사용하는 와이어 프로토콜을 말한다.  
- 브라우저 드라이버: 브라우저마다 별도의 브라우저 드라이버가 있다. 브라우저 드라이버는 브라우저 기능의 내부 로직을 드러내지 않고 해당 브라우저와  
통신한다. 브라우저 드라이버가 요청을 받으면 해당 요청이 브라우저에서 실행되고 응답은 HTTP 형태로 돌려 보낸다.  
- 브라우저: Selenium은 파이어폭스, 크롬, 엣지, 사파리 같은 여러 브라우저를 지원한다.  
  
# **Selenium으로 테스트 작성하기**  
Selenium은 버전 3부터 아래의 부라우저를 지원한다.  
브라우저 / 브라우저 드라이버 클래스 이름  
- 구글 크롬: ChromeDriver  
- 사파리: SafariDriver  
- 오페라: OperaDriver  
- 파이어폭스: FirefoxDriver  
- 엣지: EdgeDriver  
  
특정 브라우저를 테스트하기 위해서는 해당 브라우저에 맞는 Selenium 드라이버를 다운로드한 다음 OS 경로에 Selenium 드라이버가 위치한 경로를  
추가해야 한다. 각 드라이버는 Selenium 다운로드 페이지에서 다운로드 받는 링크 경로를 알 수 있다.  
  
다운로드 후 환경 변수에 Selenium 드라이버가 있는 폴드를 추가해야 한다.  
  
# **특정한 웹 브라우저 테스트하기**  
크롬 테스트  
selenium 패키지 -> ChromeSeleniumTest 클래스 참조  
  
파이어폭스 테스트  
selenium 패키지 -> FirefoxSeleniumTest 클래스 참조  
  
# **웹 브라우저를 사용한 내비게이션 테스트**  
웹 페이지에서 특정 요소를 찾아 클릭 (파이어폭스 사용)  
selenium 패키지 -> WikipediaAccessTest 클래스 참조  
  
# **여러 브라우저로 테스트하기**  
selenium 패키지 -> MultiBrowserSeleniumTest 클래스 참조  
  
# **여러 브라우저로 구글 검색과 내비게이션 테스트하기**  
selenium 패키지 -> GoogleSearchTest 클래스 참조  
  
# **웹 사이트 인증 테스트하기**  
테스트에는 Heroku라는 웹 사이트를 사용하는데 이곳에서는 HTML 폼 인증을 비롯해 자동으로 테스트할 수 있는 많은 기능을 제공한다.  
  
테스트할 웹 사이트의 홈페이즈를 설명하는 클래스  
selenium 패키지 -> pages 패키지 -> 하위 클래스 참조  
  
로그인 테스트  
selenium 패키지 -> suite 패키지 -> LoginTest 클래스 참조  
  



