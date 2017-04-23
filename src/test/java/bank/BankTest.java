package bank;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankTest {

    public static final int AMOUNT = 1000;
    public static final String LOGIN = "Login";
    public static final String PASSWORD = "Password";

    /**
     * In order to save money
     As a bank client
     I want to make a deposit in my account
     */
    @Test
    public void asABankClientMakeADeposit() throws IllegalAccessException {

        Bank bank = new Bank();
        bank.createAccount(LOGIN,PASSWORD);
        Account account = bank.getAccount(LOGIN, PASSWORD);
        account.deposit(AMOUNT);
        assertThat(account.getBalance()).isEqualTo(1000);
    }
}
