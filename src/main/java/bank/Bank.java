package bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts = new HashMap<String, Account>();

    public Account getAccount(String login, String password) throws IllegalAccessException {
        Account account = accounts.get(login);
        if( account != null && account.getPassword().equals(password)){
            return account;
        }
        throw new IllegalAccessException("Wrong authentication");
    }

    public void createAccount(String login, String password) {
        accounts.put(login, new Account(login, password));
    }
}
