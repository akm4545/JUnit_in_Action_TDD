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
  
# **Maven으로 JUnit 리포트 만들기**  
Maven은 XML 형식의 JUnit 리포트를 생성할 수 있다. 기본적으로 Maven은 일반적인 텍스트 형식 또는 XML 형식의 JUnit 리포트를 만들 수 있으며  
관습적으로 target/surefire-reports/ 폴더에 테스트 리포트를 생성한다. HTML 형식의 JUnit 리포트를 생성하기 위해 추가 설정이 필요하지는  
않다.  
  
HTML 형식의 리포트를 생성하는 작업은 Maven 플러그인이 수행한다. 구체적으로는 maven-surefire-report-plugin이며 이는 우리가 일반적으로  
알고 있는 Maven의 핵심 단계에 연결되어 있지 않다.(사람들이 소프트웨어를 빌드할 때마다 HTML 리포트가 필요한 것은 아니기 때문이다)  
컴파일러 플러그인과 Surefire 플러그인에서 했던 것처럼 특정 단계를 실행하여 플러그인을 호출할 수는 없다. 대신 명령 프롬프트에서 다음 명령을  
입력하여 직접 호출해야 한다.  
  
mvn surefire-report:report  
  
이 명령을 실행하면 Maven은 소스 파일을 컴파일하고 테스트한 다음 Surefire 플러그인을 호출하여 일반 텍스트 형식과 XML 형식의 리포트를 생성한다.  
그 후 surefire-report 플러그인은 target/surefire-reports/ 디렉토리의 모든 XML 리포트를 HTML 리포트로 변환하여 target/site/   
디렉토리에 생성한다(이것은 프로젝트에서 생성된 모든 문서를 보관하는 폴더의 관습이며 HTML 리포트는 문서로 간주한다). 