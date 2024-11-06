package account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

//MockitoExtension을 사용하여 JUnit5 테스트를 확장한다
//@ExtendWith는 테스트 클래스나 테스트 메서드에 대한 확장을 등록하는 데 사용한다
//MockitoExtension은 @Mock으로 모의 객체를 만드는 데 필요하다
@ExtendWith(MockitoExtension.class)
public class TestAccountServiceMockito {

    //@Mock을 사용하면 Mockito가 모의 AccountManager 객체를 생성할 수 있다
    @Mock
    private AccountManager mockAccountManager;

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

//        when 메서드를 사용하여 모의 객체가 수행할 동작을 기대한다
//        추가적으로 테스트에서 모의 객체 메서드를 엄격하게(strict)호출하지 못하도록 lenient 메서드를 사용한다
//        lenient 메서드가 없으면 동일한 findAccountForUser 메서드에 대해 기대를 하나밖에 선언할 수 없는데
//        현재는 두 개의 기대가 필요한 상황이다
        Mockito.lenient().when(mockAccountManager.findAccountForUser("1")).thenReturn(senderAccount);
        Mockito.lenient().when(mockAccountManager.findAccountForUser("2")).thenReturn(beneficiaryAccount);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }
}
