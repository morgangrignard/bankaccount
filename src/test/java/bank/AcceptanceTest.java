package bank;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {

    public static final int AMOUNT = 1000;
    public static final String LOGIN = "Login";
    public static final String PASSWORD = "Password";

    private Bank bank;


    @BeforeTest
    private void initializeBankData() {
        bank = new Bank();
        bank.createAccount(LOGIN,PASSWORD);
    }

    /**
     * In order to save money
     As a bank client
     I want to make a deposit in my account
     */
    @Test
    public void asABankClientMakeADeposit() throws IllegalAccessException {
        Account account = bank.getAccount(LOGIN, PASSWORD);
        account.deposit(AMOUNT);
        assertThat(account.getBalance()).isEqualTo(1000);
    }

    /**
     * In order to retrieve some or all of my savings
     As a bank client
     I want to make a withdrawal from my account
     */
    @Test
    public void asABankClientMakeAWithdrawal() throws IllegalAccessException {
        Account account = bank.getAccount(LOGIN, PASSWORD);
        account.deposit(AMOUNT);
        account.withdrawal(AMOUNT);
        assertThat(account.getBalance()).isEqualTo(0);
    }


}
