package account;

import configurations.MockConfiguration;
import org.junit.jupiter.api.Test;

public class TestDefaultAccountManager {

    @Test
    public void testFindAccountByUser() {
//        Log 인터페이스를 구현하지만 실제로는 아무 일도 하지 않는 logger Mock 객체
        MockLog logger = new MockLog();
//        Mock 객체 getSQL을 실행하면 SQL 쿼리를 반환하도록 설정
        MockConfiguration configuration = new MockConfiguration();
        configuration.setSQL("SELECT * [...]");
//        생성자에 전달
        DefaultAccountManager2 am = new DefaultAccountManager2(logger, configuration);

        @SuppressWarnings("unused")
        Account account = am.findAccountForUser("1234");
    }
}
