package extensions;

import jdbc.ConnectionManager;
import jdbc.PassengerDao;
import jdbc.PassengerDaoImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

//ParameterResolver 인터페이스를 구현한다 ParameterResolver는 테스트가 필요로 하는 파라미터를 리졸브할 때 사용한다
public class DataAccessObjectParameterResolver implements ParameterResolver {

//    supportsParameter 메서드는 테스트가 필요로 하는 파라미터가 PassengerDao 타입일 경우 true를 반환한다
//    PassengerTest 클래스 생성자에서 주입하지 못했던 파라미터였다
//    이렇게 해서 파라미터 리졸버는 PassengerDao 객체를 지원할 수 있다
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter()
                .getType()
                .equals(PassengerDao.class);
    }

//    resolveParameter 메서드는 초기화된 PassengerDaoImpl 객체를 반환하다
//    이때 PassengerDaoImpl 객체는 ConnectionManager에서 가져온 커넥션을 파라미터로 사용하여 생성된다
//    connection은 런타임에서 테스트 생성자에 주입될 것이다
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new PassengerDaoImpl(ConnectionManager.getConnection());
    }
}
