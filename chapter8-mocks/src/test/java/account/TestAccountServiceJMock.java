package account;

//EasyMock과 달리 JMock은 정적으로 가져올 필요가 없다
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.junit5.JUnit5Mockery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAccountServiceJMock {

//    JUnit5 에서는 프로그래밍 방식으로 확장을 등록할 수 있다
//    JMock의 경우 JUnit5Mockery 인스턴스 필드에 @RegisterExtension을 추가하는 것으로 확장을 등록한다
//    context는 모의 객체를 생성하고 기대를 정의하는 데 사용한다
    @RegisterExtension
    Mockery context = new JUnit5Mockery();

    private AccountManager mockAccountManager;

    @BeforeEach
    public void setUp() {
//        context를 이용해 프로그래밍 방식으로 모의 객체를 만든다
        mockAccountManager = context.mock(AccountManager.class);
    }

    @Test
    public void testTransferOk() {
        Account senderAccount = new Account("1", 200);
        Account beneficiaryAccount = new Account("2", 100);

//        Expectations 객체를 생성하여 기대를 선언한다
//        기대는 다음과 같은 문법으로 사용할 수 있다
//        invocation-count(mock-object).method(argument-constraints);
//        inSequence(sequence-name);
//        when(state-machine.is(state-name));
//        will(action);
//        then(state-machine.is(new-state-name));
        
//        몇 번 호출했고 어떤 객체를 호출했는지를 구체적으로 적을 수 있다
//        이후 메서드가 객체를 반환하면 반환할 객체를 will(return Value()) 메서드를 호출하여 선언할 수 있다
        context.checking(new Expectations() {
            {
                oneOf(mockAccountManager).findAccountForUser("1");
                will(returnValue(senderAccount));
                oneOf(mockAccountManager).findAccountForUser("2");
                will(returnValue(beneficiaryAccount));

                oneOf(mockAccountManager).updateAccount(senderAccount);
                oneOf(mockAccountManager).updateAccount(beneficiaryAccount);
            }
        });

//        계좌 이체를 시작
        AccountService accountService = new AccountService();
        accountService.setAccountManager(mockAccountManager);
        accountService.transfer("1", "2", 50);

//        예상 결과 단언
        assertEquals(150, senderAccount.getBalance());
        assertEquals(150, beneficiaryAccount.getBalance());
    }
}
