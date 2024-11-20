package extensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;

//ExecutionCondition 인터페이스를 구현하여 조건부 테스트 실행 extension을 만든다
public class ExecutionContextExtension implements ExecutionCondition {

//    evaluateExecutionCondition 메서드를 재정의하여 테스트를 활성화할지 말지 결정하는 ConditionEvaluationResult 객체를 반환한다
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context){
        Properties properties = new Properties();
        String executionContext = "";

        try {
//            properties 파일에서 설정값 읽기
            properties.load(ExecutionContextExtension.class.getClassLoader().getResourceAsStream("context.properties"));
            executionContext = properties.getProperty("context");

//            설정값이 조건에 맞지 않으면 반환한 ConditionEvaluationResult는 테스트가 비활성화되도록 한다
            if(!"regular".equalsIgnoreCase(executionContext) && !"low".equalsIgnoreCase(executionContext)){
                return ConditionEvaluationResult.disabled("regular와 low이외의 context(예컨대 peak)에서는 테스트가 실행되지 않는다.");
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }

//        설정값이 조건에 맞는 정상적인 상황이라면 테스트 실핼
        return ConditionEvaluationResult.enabled("Test enabled on the " + executionContext + " context");
    }
}
