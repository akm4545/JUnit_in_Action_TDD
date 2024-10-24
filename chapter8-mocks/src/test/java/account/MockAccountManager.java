package account;

import java.util.HashMap;
import java.util.Map;

public class MockAccountManager implements AccountManager{
    private Map<String, Account> accounts = new HashMap<String, Account>();

    public void addAccount(String userId, Account account){
        this.accounts.put(userId, account);
    }

    public Account findAccountForUser(String userId){
        return this.accounts.get(userId);
    }

    public void updateAccount(Account account){
//        아무것도 하지 않는다.
    }
}
