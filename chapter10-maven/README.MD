# **Maven Surefire 플러그인**  
Maven 프로젝트에서 단위 테스트 관련 작업을 수행하기 위해서는 플러그인을 추가해야 한다. 단위 테스트를 실행하는 데 사용하는 Maven 플러그인 중  
하나가 maven-surefire-plugin이다. Maven Surefire 플러그인은 소스 코드에 대한 단위 테스트를 실행할 수 있게 도와준다. 이러한 단위 테스트가  
반드시 JUnit 테스트일 필요는 없다.  
  
JUnit 말고도 다양한 단위 테스트 프레임워크가 있으며 Maven Surefire 플러그인은 다른 프레임워크로 만든 테스트도 실행할 수 있다.  
pom.xml 참조  
  
maven-surefire-plugin을 실행하는 관습적인 방법은 Maven 테스트 단계를 호출하는 것이다. Maven 테스트 단계를 호출하면 Maven은 테스트 단계  
이전의 모든 단계(유효성 검사, 컴파일 단계)를 실행한 다음 테스트 단계와 관련한 모든 플러그인(구체적으로는 maven-surefire-plugin)을 호출한다.  
mvn clean test 명령으로 Maven 테스트 단계를 실행할 수 있다.  
  
Mavne은 먼저 target 디렉토리를 정리한 다음 소스 코드와 테스트를 컴파일한다. 그다음 JUnit5가 src/test/java 디렉토리에 있는 모든 테스트를  
실행한다.  
  
그러나 모든 테스트를 실행하지 않고 일부만 실행하려면 관습으로만 해결할 수가 없다. 이럴 떄는 maven-surefire-plugin에 추가적인 설정이 필요하다.  
구체적으로는 플러그인의 파라미터를 사용하여 실행하려는 테스트 실행 패턴을 특정할 수 있다.  
pom.xml 주석 참조  
  
