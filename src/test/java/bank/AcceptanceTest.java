package bank;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {

    public static final int AMOUNT = 1000;
    public static final String LOGIN = "Login";
    public static final String PASSWORD = "Password";

    private Bank bank;


    @BeforeMethod
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

    /**
     * In order to retrieve some or all of my savings
     As a bank client
     I want to make a withdrawal from my account
     */
    @Test( expectedExceptions = IllegalArgumentException.class)
    public void asABankClientBeRefuseToMakeAWithdrawalGreaterThanBalance() throws IllegalAccessException {
        Account account = bank.getAccount(LOGIN, PASSWORD);
        account.deposit(AMOUNT);
        account.withdrawal(AMOUNT*2);
    }

    /**
     * In order to check my operations
     As a bank client
     I want to see the history (operation, date, amount, balance) of my operations
     */
    @Test
    public void asABankClientGetHistory() throws IllegalAccessException {
        Account account = bank.getAccount(LOGIN, PASSWORD);

        LocalDateTime beforeAction = LocalDateTime.now();

        account.deposit(AMOUNT*2);
        account.withdrawal(AMOUNT);
        account.deposit(AMOUNT);

        LocalDateTime afterAction = LocalDateTime.now();

        List<Operation> history = account.getHistory();
        assertThat(history).hasSize(3);
        verify( history.get(0), Operation.OperationType.DEPOSIT, beforeAction, afterAction, AMOUNT*2, AMOUNT*2);
        verify( history.get(1), Operation.OperationType.WITHDRAWAL, beforeAction, afterAction, AMOUNT, AMOUNT);
        verify( history.get(2), Operation.OperationType.DEPOSIT, beforeAction, afterAction, AMOUNT, AMOUNT*2);
    }

    private void verify(Operation operation, Operation.OperationType type, LocalDateTime beforeAction, LocalDateTime afterAction, double amount, double balance){
        assertThat(operation.getType()).isEqualTo(type);
        assertThat(operation.getDate()).isAfterOrEqualTo(beforeAction);
        assertThat(operation.getDate()).isBeforeOrEqualTo(afterAction);
        assertThat(operation.getAmount()).isEqualTo(amount);
        assertThat(operation.getBalance()).isEqualTo(balance);
    }


}
