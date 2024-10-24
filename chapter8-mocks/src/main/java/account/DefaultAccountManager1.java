package account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class DefaultAccountManager1 implements AccountManager{
    private static final Log logger = LogFactory.getLog(DefaultAccountManager1.class);

    public Account findAccountForUser(String userId){
        logger.debug("Getting account for user [" + userId + "]");
        ResourceBundle bundle = PropertyResourceBundle.getBundle("technical");
        String sql = bundle.getString("FIND_ACCOUNT_FOR_USER");

//        Some code logic to load a user account using JDBC
        return null;
    }

    public void updateAccount(Account account){
        //Perform database access here
    }
}
