# **컨테이너를 활용한 테스트**  
  
# **일반적인 단위 테스트의 한계**  
HttpServlet의 isAuthenticated 메서드를 구현하는 예제  
servlet 패키지 -> SampleServlet 클래스 참조  
  
서블릿이란 웹 애플리케이션 서버에서 동작하는 자바 클래스를 말한다. 사용자 정보가 올바른지를 확인할 수 있는 인증 메커니즘이 필요한데 사용자  
인증이 되었는지를 검증하는 메서드로 isAutehnticated 메서드를 사용하며 테스트하는 상황이라고 가정한다.  
  
해당 서블릿 예제는 일반적인 단위 테스트의 문제가 무엇인지 보여 준다. 만약 isAuthenticated 메서드를 테스트하려면 사전에 유효한  
HttpServletRequest 객체를 가지고 있어야 한다. 그런데 HttpServletRequest는 인터페이스이므로 new 예약어를 사용해서 HttpServletRequest를  
생성할 수가 없다. 게다가 HttpServletRequest 객체의 생애 주기나 구현은 컨테이너(이 경우 서블릿 컨테이너)에서 제공하는 것이지 개발자가 하는  
것이 아니다. HttpSession도 역시 마찬가지다. 즉 JUnit만으로는 isAuthenticated 메서드와 일반적인 서블릿에 관한 테스트를 작성하기가  
어렵다.  
  
서블릿이나 JSP에서 컨테니어는 Tomcat이나 Jetty 같은 서블릿 컨테이너를 말한다. 그 외에는 EJB(Enterprise Java Beans) 컨테이너인 JBoss등이  
있다.(JBoss는 WildFly로 이름이 변경되었다.) 이러한 컨테이너에서 자바 코드는 기본적으로 잘 동작한다. 그러나 컨테이너가 런타임에  
HttpServletRequest 같은 객체를 생성하고 관리하는 한 일반적인 JUnit만으로는(JUnit5 기능을 쓴다든지, 스텁이나 모의 객체를 활용한다든지) 해당  
객체를 테스트하기 어렵다.  
  
