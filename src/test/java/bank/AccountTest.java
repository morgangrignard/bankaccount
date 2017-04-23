package bank;


import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";

    @Test
    public void testDoubleLimitationForCurrency(){
        Account account = new Account(LOGIN, PASSWORD);
        BigDecimal addOperand = BigDecimal.valueOf(0.1);
        for( int i = 0; i< 1000; i++){
            account.deposit(addOperand);
        }
        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(100.0));
    }
}
