package account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountService {
    @Test
    public void testTransferOk() {
//        테스트 설정하기
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

//        모의 객체를 활용할 때는 테스트가 모의 객체를 이용하기 전에 기대를 정의한다는 특징이 있다.
        MockAccountManager mockAccountManager = new MockAccountManager();
        mockAccountManager.addAccount("1", senderAccount);
        mockAccountManager.addAccount("2", beneficiaryAccount);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);

//        테스트 실행하기
        accountService.transfer("1", "2", 50);

//        테스트 결과 검증하기
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }
}
