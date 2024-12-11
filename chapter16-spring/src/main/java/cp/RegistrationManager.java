package cp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

//ApplicationContextAware 인터페이스를 구현하여 이벤트를 발행할 때 사용할 애플리케이션 콘텍스트에 대한 참조를 가질 수 있다
@Service
public class RegistrationManager implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

//    스프링이 제공해 주는 파라미터를 가지고 applicationContext 필드를 초기화한다
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
