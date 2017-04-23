package bank;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {

    public static final int AMOUNT = 1000;
    public static final String LOGIN = "Login";
    public static final String PASSWORD = "Password";
    public static final String UNKNOWN_LOGIN = "UNKNOWN";
    public static final String UNKNOWN_PASSWORD = "UNKNOWN_PASSWORD";

    private Bank bank;


    @BeforeTest
    private void initializeBankData() {
        bank = new Bank();
        bank.createAccount(LOGIN,PASSWORD);
    }

    @Test
    public void asAClientGetAccount() throws IllegalAccessException {
        Account account = bank.getAccount(LOGIN, PASSWORD);
        assertThat(account).isNotNull();
        assertThat(account.getLogin()).isEqualTo(LOGIN);
        assertThat(account.getPassword()).isEqualTo(PASSWORD);
    }

    @Test( expectedExceptions = IllegalAccessException.class)
    public void asAnUnknownClientThrowForbiddenAccess() throws IllegalAccessException {
        bank.getAccount(UNKNOWN_LOGIN, UNKNOWN_PASSWORD);
    }

    @Test( expectedExceptions = IllegalAccessException.class)
    public void asAnClientWithWrongPasswordThrowForbiddenAccess() throws IllegalAccessException {
        bank.getAccount(LOGIN, UNKNOWN_PASSWORD);
    }
}
