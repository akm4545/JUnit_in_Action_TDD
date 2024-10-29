package account;

import configuration.Configuration;
import configuration.DefaultConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


// Log, Configuration을 구현한 객체를 파라미터로 받는 생성자를 사용할 시
// DefaultAccountManager2 클래스를 재사용할 수 있으므로 설계가 더 좋아진다
// 클래스는 호출자가 외부에서 제어할 수 있게 된다
public class DefaultAccountManager2 implements AccountManager{
    private Log logger;

//    PropertyResourceBundle을 사용하지 않기 위해 서로운 configuration 필드 정의
//    이렇게 바꾸면 상대적으로 모의하기 쉬운 인터페이스를 사용할 수 있으므로 코드를 더 유연하게 만들 수 있고
//    Configuration 객체를 구현하는 것으로 원하는 작업을 수행할 수 있다
    private Configuration configuration;

    public DefaultAccountManager2() {
        this(LogFactory.getLog(DefaultAccountManager2.class),
                new DefaultConfiguration("technical"));
    }

    public DefaultAccountManager2(Log logger, Configuration configuration){
        this.logger = logger;
        this.configuration = configuration;
    }

    public Account findAccountForUser(String userId){
        this.logger.debug("Getting account for user [" + userId + "]");
        this.configuration.getSQL("FIND_ACCOUNT_FOR_USER");

        return null;
    }

    public void updateAccount(Account account){

    }
}
