package rules;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;

//Statement 클래스를 상속하는 CustomStatement 클래스를 선언
public class CustomStatement extends Statement {
//    참조 유지를 위한 인스턴스 변수
    private Statement base;
    private Description description;

//    생성자 초기화
    public CustomStatement(Statement base, Description description){
        this.base = base;
        this.description = description;
    }

//    evaluate 메서드 재정의
    @Override
    public void evaluate() throws Throwable {
        System.out.println(this.getClass().getSimpleName() + " " + description.getMethodName() + " has started");

        try {
//            원래의 테스트를 진행
            base.evaluate();
        }finally {
            System.out.println(this.getClass().getSimpleName() + " " + description.getMethodName() + " has finished");
        }
    }
}
