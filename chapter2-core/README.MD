# **테스트 클래스(test class)**  

클래스, 정적 멤버 클래스, 하나 이상의 테스트 메서드를 포함하는 @Nested 어노테이션이 붙은 내부 클래스를 말한다  
테스트 클래스는 추상 클래스일 수 없으며 단일한 생성자를 가지고 있어야 한다  
테스트 클래스의 생성자는 파라미터가 아예 없거나 런타임에 의존성 주입으로 동적으로 리졸브할 수 있는 파라미터만 사용할 수 있다  
테스트 클래스는 가시성을 보장하기 위한 최소 요구 사항으로 디폴트 접근 제어자를 사용할 수 있다  
JUnit4 까지 그랬던 것처럼 더는 테스트 클래스를 public으로만 선언할 필요가 없다  
  
# **테스트 메서드**  

@Test, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate 어노테이션이 붙은 메서드를 말한다  
  
# **생애 주기 메서드**  

@BeforeAll, @AfterAll, @BeforeEach, @AfterEach 어노테이션이 붙은 메서드를 말한다  
  
테스트 메서드는 추상 메서드일 수 없으며 반환 값을 가질 수 없다  
테스트 메서드의 반환 타입은 반드시 void 여야 한다  
  
JUnit은 테스트 메서드의 격리성을 보장하고 테스트 코드에서 의도치 않은 부수 효과를 방지하기 위해  
@Test 메서드를 호출하기 전에 테스트 클래스 인스턴스를 매번 새로 만든다  
테스트는 실행 순서에 관계없이 동일한 결과를 얻을 수 있어야 하는 것이 당연하기 때문이다  
각 테스트 메서드는 매번 새로 만들어진 테스트 클래스 인스턴스에서 실행되므로 테스트 메서드 간에 인스턴스 변수를   
재사용할 수는 없다  
  
대신 테스트 클래스에 @TestInstance(Lifecycle.PER_CLASS) 어노테이션을 추가하면 JUnit5는 동일한 테스트 인스턴스를 가지고  
클래스에 있는 모든 테스트 메서드를 실행한다  
테스트 클래스가 인스턴스 메서드 단위가 아니라 클래스 단위로 생성되는 것이다  
  
-> lifecycle 패키지 참조  
  
@DisplayName 어노테이션은 테스트 클래스, 테스트 메서드에서 사용할 수 있다  
@DisplayName은 개발자가 테스트 클래스나 테스트 메서드에 자신만의 디스플레이 네임을 작성하는 데 사용한다  
@DisplayName은 IDE나 빌드 도구의 테스트 리포트에서 보통 적용된다  
공백, 특수 문자, 이모지도 사용할 수 있다  
  
-> displayname 패키지 참조  
  
@Disabled 어노테이션은 테스트 클래스나 테스트 메서드에서 사용할 수 있다  
@Disabled를 붙인 테스트 클래스나 테스트 메서드는 비활성화되므로 테스트가 실행되지 않는다  
@Disabled를 클래스 단위에 적용하면 해당 테스트 클래스의 모든 메서드가 비활성화된다  
또한 개발자가 IDE에서 테스트를 실행하면 비활성화된 테스트와 각각의 테스트가 비활성화된 이유를 콘솔에서 확인할 수 있다  
  
-> disabled 패키지 참조  
  
# **중첩 테스트**  

내부 클래스(inner class)는 클래스의 멤버로 선언된 클래스를 말한다  
내부 클래스는 사실상 외부 클래스(outer class)의 일부이므로 외부 클래스의 프라이빗 인스턴스 변수에 접근할 수 있다  
두 개의 클래스가 지나치케 결합도가 높다면 내부 클래스와 외부 클래스로 만들어 내부 클래스에서 외부 클래스의 모든 인스턴스 변수에  
접근할 수 있도록 해 주는 것이 합리적이다  
  
중첩 테스트는 개발자가 비즈니스 로직을 잘 따르게 하는 한편, 분명한 테스트 코드를 작성하도록 유도하여 개발자가 테스트 프로세스에  
더욱 자연스럽게 적응하도록 만든다  
  
결합도 관점에서 중첩 테스트는 개발자가 테스트 그룹 간의 관계를 표현하는 데에도 도움이 된다  
내부 클래스는 해당 패키지 내에서만 접근이 가능하다  
  
@Nested 어노테이션  
-> nested 패키지 참조  
  
# **태그를 사용한 테스트**  

JUnit4의 category를 대체한다고 생각하면 간단하다  
테스트 클래스와 테스트 메서드에 @Tag 어노테이션을 사용할 수 있다  
태그를 사용하면 테스트를 발견하거나 실행할 때 필터를 적용할 수 있다  
  
-> tags 패키지 참고  
  
실무에서는 테스트를 몇몇 카테고리로 범주화할 때 태그를 사용한다  
비즈니스 로직이나 기타 기준으로 태그를 달아 테스트를 그룹으로 묶을 수 있다(각 테스트 카테고리는 고유한 태그가 있다)  
그다음 상황에 맞게 특정 카테고리의 테스트만 수행하거나 대상 태그를 다른 것으로 바꿀 수도 있다  
  
태그를 사용한 테스트를 사용하기 위한 Maven pom.xml 파일 참조  
  
mvn clean install 명령을 실행하면 @Tag("repository") 태그가 지정된 테스트는 제외되고  
@Tag("Individual") 태그가 지정된 테스트만 실행된다  
  
또 다른 방법으로는 인텔리제이에서 테스트를 실행할 때 설정 정보를 입력하는 것이다  
Run -> Edit Configurations -> Tags (JUnit5) 에서 원하는 태그를 특정하는 것이다  
이는 로컬에서 빠르게 테스트 대상을 변경할 때 적합하다  
  
자동화 도구를 사용할 때는 pom.xml 파일을 기준으로 빌드할 것이기 때문에 pom.xml 파일을 변경하는 것이 권장된다  
  
**# 단언문**  

결괏값을 검증하려면 JUnit5의 Assertions 클래스에서 제공하는 단언문 메서드를 사용해야 한다  
Assertions 클래스의 메서드들은 정적으로 가져올 수 있다  
만약 정적으로 가져오는 것이 불편하다면 일반적인 방식으로 JUnit5의 Assertions 클래스를 가져와서 메서드를 호출할 수도 있다  
  
# **자주 사용하는 단언문 메서드**  

assertAll  
오버로딩이 적용되어 있다 안에 있는 executable 객체 중 어느 것도 예외를 던지지 않는다고 단언한다  
이때 excutable은 org.junit.jupiter.api.function.Executable 타입의 객체다  
  
assertArrayEquals  
오버로딩이 적용되어 있다 예상 배열과 실제 배열이 동등하다고 단언한다  
  
assertEquals  
오버로딩이 적용되어 있다 예상 값과 실제 값이 동등하다고 단언한다  
  
assertX(..., String message)  
실패했을 경우 message를 테스트 프레임워크에 전달하는 단언문이다  
  
assetX(..., Supplier <String> messageSupplier)  
실패했을 경우 messageSupplier를 테스트 프레임워크에 전달하는 단언문이다  
실패 메서지는 messageSupplier에서 지연(lazily)전달된다  
  
JUnit5는 오버로딩이 적용되어 있는 단언문 메서드를 폭넓게 지원한다  
JUnit4의 단언문 메서드의 기능을 포함하며 일부 메서드는 자바 8 람다식을 지원한다  
JUnit5는 과거에 Hamcrest(헴크레스트) 매처와 함께 사용했던 assertThat 메서드를 더는 지원하지 않는데  
권장하는 방식은 Hamcrest의 MatcherAssert.assertThat을 오버로딩한 메서드를 사용하는 것이다  
  
Assertions 패키지 메서드 예제 -> assertions 패키지 참조 -> AssertAllTest  
  
테스트하는 데 약간 시간이 걸리는 경우가 있을 때  
JUnit4 Timeout rule을 대체하는 assertTimeout, assertTimeoutPreemptively 메서드를 사용할 수 있다  

Assertions 패키지 메서드 예제 -> assertions 패키지 참조 -> AssertTimeoutTest  
  
예외가 발생할 만한 테스트를 수행한다고 했을 때  
테스트는 적절하지 않은 조건에서 실행되거나 적절하지 않은 입력을 받을 수 있는 것이다  
예제의 시스템에 할당된 Job 객체가 없는데 run 메서드를 실행한다면 예외를 던질 것이다
JUnit4에서 @Test 어노테이션 안에 쓰이는 ExpectedException rule을 대체하는 assertThrows 메서드를 사용한다

Assertions 패키지 메서드 예제 -> assertions 패키지 참조 -> AssertThrowsTest  
  
# **가정문**  

외부 환경이나 우리가 제어할 수 없는 날짜, 시간대 같은 문제 탓에 테스트가 실패할 수도 있다  
이럴 때 가정문(assumption)을 사용한다면 부적절한 조건에서 테스트가 실행되는 것을 사전에 막을 수 있다  
  
가정문은 테스트를 수행하는 데 필수인 전제 조건이 충족되었는지를 검증하는 문장이다  
테스트를 계속 진행하는 것이 합리적이지 않을 수 있다고 생각되는 부분에 가정문을 사용할 수 있다  
테스트 리포트에서 가정문에 의해 중단된 테스트는 실패한 것으로 처리한다  
  
JUnit4 에서 지원했던 가정문 메서드가 JUnit5에서 모두 지원되지는 않는다  
JUnit5는 assumeThat 계열의 메서드를 지원하지 않는다  
대신 assumingThat 메서드를 제공하며 이 메서드는 가정이 충족된 경우에만 단언문을 실행한다  
  
assume 예제 -> assumptions 패키지 참조  
  
# **JUnit5의 의존성 주입**  

이전 JUnit 버전에서는 생성자나 메서드에 파라미터가 있는 것을 허용하지 않았고  
테스트는 반드시 기본 생성자만 사용해야 했다  
JUnit5부터는 생성자와 메서드에서 파라미터를 가질 수 있도록 허용하지만 의존성 주입으로 해결해야 한다는 점이 다르다  
  
ParameterResolver 인터페이스는 런타임에 파라미터를 동적으로 리졸브한다  
생성자나 메서드의 파라미터는 사전에 등록한 ParameterResolver에 의해 런타임에 리졸브되어야 한다  
  
사용자는 ParameterResolver 인터페이스를 활용해 어떤 파라미터든 순서에 상관없이 주입할 수 있다  
JUnit5에는 세 개의 리졸버가 기본으로 내장되어 있다 만약 다른 파라미터 리졸버를 사용하려면  
@ExtendWith를 가지고 적절한 extension을 적용하여 파라미터 리졸버를 명시해야 한다

# **TestInfoParameterResolver**  
TestInfoParameterResolver를 사용하면 테스트 클래스 생성자나 테스트 메서드에서 TestInfo 객체를 파라미터로 사용할 수 있다  
TestInfo는 현재 실행 중인 테스트나 컨테이너에 관한 정보를 제공하기 위해 사용한다  
@Test, @BeforeEach, @AfterEach, @BeforeAll, @AfterAll 어노테이션이 달린 메서드에서 TestInfo 객체를 파라미터로 사용할 수 있다  
TestInfo는 디스플레이 네임, 테스트 클래스, 테스트 메서드, 관련 태그에 관한 정보 등 현재 테스트에 대한 정보를 가져온다  

dependencyinjection 패키지 예제 -> TestInfoTest 참조  
  
# **TestReporterParameterResolver**  
TestReporterParameterResolver를 사용하면 테스트 클래스 생성자나 테스트 메서드에서 TestReporter 객체를 파라미터로 사용할 수 있다  
TestReporter는 함수형 인터페이스이므로 람다식이나 메서드 참조로 사용할 수 있다  
TestReporter는 한 개의 추상 메서드 publishEntry와 publishEntry 메서드를 오버로딩한 디폴트 메서드 여러 개를 가진다  
TestReporter 타입의 파라미터는 @BeforeEach, @AfterEach, @Test 어노테이션이 달린 테스트 메서드에 주입할 수 있다  
TestReporter 객체는 현재 실행되는 테스트에 추가적인 정보를 제공할 때 사용한다    

dependencyinjection 패키지 예제 -> TestReporterTest 참조  
  
# **RepetitionInfoParameterResolver**  
RepetitionInfoParameterResolver는 @RepeatedTest, @BeforeEach, @AfterEach 어노테이션이 달린 메서드의 파라미터가  
RepetitionInfo 타입일 때 RepetitionInfo 인스턴스를 리졸브하는 역할한 한다  
RepetitionInfo는 @RepeatedTest 어노테이션이 달린 테스트에 대한 현재 반복 인덱스와 총 반복 횟수에 대한  
정보를 가지고 있다  
  
# **반복 테스트**  
JUnit5에서드느 @RepeatedTest 어노테이션을 사용하여 반복 횟수를 지어한 후 해당 횟수만큼 테스트를 반복할 수 있다  
반복 테스트는 테스트 실행 간에 사전 조건이 변경될 수 있는 환경에서 특히 유용하다  
예를 들자면 동일한 테스트를 두 번 실행하던 중에 성공 여부를 가를 수 있는 데이터가 변했다고 가정한다면  
이때 데이터의 예기치 못한 변경은 반드시 수정해야만 하는 버그일 수 있다  
  
사용자 정의한 디스플레이 네임을 표현하려면 각 반복마다 @RepeatedTest 어노테이션의 name 속성을 사용하여 나타낼 수 있다  
@RepeatedTest 어노테이션이 지원하는 플레스르 홀더는 다음과 같다  
  
{displayName} : @RepeatedTest 어노테이션이 붙은 메서드의 디스플레이 네임  
{currentRepetition} : 현재 반복 인덱스  
{totalRepetitions} : 총 반복 횟수  
  
repeated 패키지 예제 참조  
  
# **파라미터를 사용한 테스트**
파라미터를 사용한 테스트는 하나의 테스트를 다양한 파라미터를 가지고 여러 번 실행하게 해 주는 기능이다  
파라미터를 사용한 테스트의 최대 강점은 다양한 입력을 두고 테스트를 실행할 수 있다는 것이다  
파라미터를 사용한 테스트에는 @ParameterizedTest 어노테이션이 달려 있다  
테스트에 파라미터를 사용하려면 반드시 각 반복에 대한 파라미터를 제공하는 소스를 선언해야 한다  
그러면 데이터가 파라미터로 테스트 메서드에 전달된다  
  
@ValueSource 어노테이션을 사용하면 문자열 배열을 입력 값으로 지정할 수 있다  
각 테스트 수행마다 문자열 배열로부터 파라미터를 사용한 순서대로 입력 데이터를 제공한다    
  
parameterized 패키지 -> ParameterizedWithValueSourceTest 참조  
  
@EnumSource를 사용하면 파라미터에 열거형을 사용할 수 있다  
names 속성을 통해 @EnumSource에서 사용하거나 제외할 열거형 인스턴스를 지정할 수 있다  
기본적으로는 열거형의 모든 인스턴스를 대상으로 한다 

parameterized 패키지 -> ParameterizedWithEnumSourceTest 참조  
  
@CsvSource 어노테이션을 사용하여 CSV 형식으로 파라미터를 제공할 수도 있다  
  
parameterized 패키지 -> ParameterizedWithCsvSourceTest 참조  
  
@CsvFileSource 어노테이션을 사용하면 클래스패스에 있는 CSV 파일을 파라미터의 소스로 사용할 수 있다
  
parameterized 패키지 -> ParameterizedWithCsvFileSourceTest 참조  
  
# **동적 테스트**  
JUnit5는 런타임에 테스트를 생성할 수 있는 동적 프로그래밍 모델을 도입했다  
개발자가 팩토리 메서드를 작성하기만 하면 프레임워크가 런타임에 실행할 테스트를 생성한다  
팩토리 메서드에는 @TestFactory 어노테이션을 달면 된다  
  
@TestFactory 메서드는 일반적인 테스트가 아니라 테스트를 생성하는 팩토리다  
@TestFactory 어노테이션이 달린 메서드가 반환할 수 있는 대상은 다음과 같다  
  
DynamicNode(추상 클래스, DynamicContainer나 DynamicTest가 DynamicNode를 상속하였고 인스턴스화가 가능한 구체 클래스다)  
DynamicNode 객체의 배열  
DynamicNode 객체의 스트림  
DynamicNode 객체의 컬렉션  
DynamicNode 객체의 Iterable  
DynamicNode 객체의 반복자(iterator)  
  
@TestFactory 어노테이션이 붙은 메서드 역시 가시성을 위한 최소 요구 사항으로 디폴트 접근 제어자를 사용할 수 있지만  
private이거나 정적일 수는 없다  
ParameterResolver에 의해 리졸브될 파라미터를 선언할 수는 있다  
  
DynamicTest 인터페이스는 디스플레이 네임과 Executable로 이루어져 있고 런타임에 생성되는 테스트다  
Executable은 자바 8에서 등장한 함수형 인터페이스이므로 동적 테스트는 람다식이나 메서드 참조 방식으로 구현할 수 있다  
  
동적 테스트는 @Test 어노테이션이 달린 보통의 테스트와 다른 생애 주기를 가지고 있다  
@BeforeEach, @AfterEach 어노테이션이 달린 메서드는 @TestFactory 메서드 전체에 대해 실행될 뿐 개별 테스트 각각에 대해서는 실행되지 않는다  
팩토리 메서드 외에 개별 동적 테스트에 대한 생애 주기 콜백은 없다    
  
dynamic 패키지 예제 참조  

<h3><strong>차이점 요약</strong></h3>  
<table><thead><tr><th>특징</th><th><code>assertAll</code></th><th><code>DynamicTest</code></th></tr></thead><tbody><tr><td><strong>용도</strong></td><td>여러 단언문을 하나의 테스트로 그룹화하여 실행</td><td>런타임에 테스트를 동적으로 생성하여 실행</td></tr><tr><td><strong>실행 방식</strong></td><td>하나의 테스트 안에서 여러 단언문 실행</td><td>각각 독립된 테스트 케이스로 생성 및 실행</td></tr><tr><td><strong>실패 처리</strong></td><td>하나의 단언문 실패 시에도 나머지 단언문을 모두 실행</td><td>하나의 테스트 실패 시 나머지 테스트는 독립적으로 실행</td></tr><tr><td><strong>테스트 구조</strong></td><td>정적으로 작성된 단언문</td><td>실행 시점에 동적으로 테스트 생성 가능</td></tr><tr><td><strong>테스트 케이스 수</strong></td><td>하나의 테스트 내에서 여러 단언문 실행</td><td>각각의 동적 테스트는 별도의 테스트 케이스로 처리됨</td></tr></tbody></table>  

assertAll: 여러 단언문이 같은 컨텍스트에서 실행되어야 할 때 즉 동일한 입력값이나 객체에 대해 여러 단언을 검증하고 싶을 때 유용하다  
이 경우 하나의 테스트 메서드 내에서 단언문을 모두 처리한다  
  
DynamicTest: 테스트할 데이터가 동적이거나 실행 시점에 결정되는 경우 혹은 반복 테스트나 다양한 입력값에 대해   
독립적인 테스트 케이스가 필요할 때 사용한다  
각각의 테스트가 별개의 테스트 케이스로 실행되기 때문에 테스트 결과를 개별적으로 보고할 수 있다  
  


