package bank;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {

    public static final BigDecimal AMOUNT = BigDecimal.valueOf(1000);
    public static final BigDecimal AMOUNT_X2 = AMOUNT.multiply(BigDecimal.valueOf(2));
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
        assertThat(account.getBalance()).isEqualTo(AMOUNT);
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
        assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
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
        account.withdrawal(AMOUNT_X2);
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

        account.deposit(AMOUNT_X2);
        account.withdrawal(AMOUNT);
        account.deposit(AMOUNT);

        LocalDateTime afterAction = LocalDateTime.now();

        List<Operation> history = account.getHistory();
        assertThat(history).hasSize(3);
        verify( history.get(0), Operation.OperationType.DEPOSIT, beforeAction, afterAction, AMOUNT_X2, AMOUNT_X2);
        verify( history.get(1), Operation.OperationType.WITHDRAWAL, beforeAction, afterAction, AMOUNT, AMOUNT);
        verify( history.get(2), Operation.OperationType.DEPOSIT, beforeAction, afterAction, AMOUNT, AMOUNT_X2);
    }

    private void verify(Operation operation, Operation.OperationType type, LocalDateTime beforeAction, LocalDateTime afterAction, BigDecimal amount, BigDecimal balance){
        assertThat(operation.getType()).isEqualTo(type);
        assertThat(operation.getDate()).isAfterOrEqualTo(beforeAction);
        assertThat(operation.getDate()).isBeforeOrEqualTo(afterAction);
        assertThat(operation.getAmount()).isEqualTo(amount);
        assertThat(operation.getBalance()).isEqualTo(balance);
    }


}
