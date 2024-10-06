package extensions;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

//ExtendWith 어노테이션의 파라미터로 사용할 클래스
//인터페이스 다중 구현
public class CustomExtension implements AfterEachCallback, BeforeEachCallback {
//    확장될 테스트 클래스에서 각 테스트 메서드가 실행되기 전에 실행할 beforeEach 메서드를 재정의
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " " + extensionContext.getDisplayName() + " has started");
    }

//   각 테스트 메서드가 실행된 이우헤 실행할 afterEach 메서드 재정의
    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " " + extensionContext.getDisplayName() + " has finished");
    }
}
