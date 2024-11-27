package extensions;

import jdbc.PassengerExistsException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.util.logging.Logger;

//TestExecutionExceptionHandler 인터페이스를 구현하여 예외가 발생했을 때 그 예외를 처리하는 handleTestExecutionException 메서드를 재정의
public class LogPassengerExistsExceptionExtension implements TestExecutionExceptionHandler {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
//        발생한 예외의 타입이 PassengerExistsException 이라면 로그를 남기고 끝낸다
        if(throwable instanceof PassengerExistsException){
            logger.severe("Passenger exists:" + throwable.getMessage());
            return;
        }

//        그렇지 않다면 예외를 다시 던져서 다른 곳에서 처리될 수 있게 한다
        throw throwable;
    }
}
