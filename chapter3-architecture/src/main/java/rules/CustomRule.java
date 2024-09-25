package rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

//TestRule 인터페이스를 구현하는 CustomRule 클래스 선언
public class CustomRule implements TestRule {
//    참조 유지를 위해 Statement 객체와 Description 객체를 인스턴스 필드로 선언

//    JUnit 런타임 내의 테스트를 나타내며 evaluate 메서드로 실행
    private Statement base;
//    현재 테스트를 설명하는 데 사용
//    리플렉션으로 테스트에 관한 정보를 읽어낼 수 있다
    private Description description;

//    위의 필드를 파라미터로 전달받아 CustomStatement 객체를 반환할 수 있는 apply 메서드를 재정의
    @Override
    public Statement apply(Statement base, Description description){
        this.base = base;
        this.description = description;

        return new CustomStatement(base, description);
    }
}
