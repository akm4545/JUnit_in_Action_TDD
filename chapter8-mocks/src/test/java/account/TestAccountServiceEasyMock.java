package account;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//EasyMock 라이브러리 메서드 가져오기
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountServiceEasyMock {

//    모의하려는 객체를 인스턴스 변수로 선언
//    AccountManager는 인터페이스다
//    EasyMock 프레임워크는 인터페이스만 모의할 수 있다
    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
//        createMock 메서드를 호출하여 원하는 클래스의 모의 객체를 생성
        mockAccountManager = createMock("mockAccountManager", AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

//        기대를 정의한다
        mockAccountManager.updateAccount(senderAccount);
        mockAccountManager.updateAccount(beneficiaryAccount);

//        EasyMock을 사용할 때는 두 가지 방법으로 기대를 선언할 수 있다
//        메서드 반환 타입이 void인 경우 위처럼 모의 객체에서 간단하게 호출할 수 있다
//        메서드가 어떤 종류든 객체를 반환할 때 EasyMock API인 expect나 andReturn 메서드를 사용한다
//        메서드를 정의하는 과정이라고 보면 될듯?
        expect(mockAccountManager.findAccountForUser("1")).andReturn(senderAccount);
        expect(mockAccountManager.findAccountForUser("2")).andReturn(beneficiaryAccount);

//        기대 정의가 끝나면 replay를 호출한다
//        replay 메서드를 호출하면 모의 객체의 행동을 기록하는 단계에서 모의 객체의 동작을 활성화하는 단계로 넘어간다
//        단순히 모의 객체의 행동을 기록하는 것만으로는 모의 객체가 동작하지 않는다
//        replay 메서드를 호출해야 모의 객체가 기대한 대로 동작한다
        replay(mockAccountManager);

        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }

    @AfterAll
    public void tearDown() {
//        EasyMock을 사용하면 어떤 모의 객체든 verify 메서드를 호출하여 이전에 선언했던 메서드 호출에 대한 기대가 충족되었는지
//        검증할 수 있다
        verify(mockAccountManager);
    }
}
