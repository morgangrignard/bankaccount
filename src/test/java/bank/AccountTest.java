package bank;


import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    public static final String LOGIN = "LOGIN";
    public static final String PASSWORD = "PASSWORD";
    public static final int THREAD_NUMBER = 5;
    public static final int LOOP = 100;

    private class Producer implements Runnable {

        private final Account account;

        public Producer( Account account){
            this.account = account;
        }
        @Override
        public void run() {
            for(int i = 0; i < LOOP; i++){
                account.deposit(BigDecimal.ONE);
            }
        }
    }

    private class Consumer implements Runnable {

        private final Account account;

        public Consumer( Account account){
            this.account = account;
        }
        @Override
        public void run() {
            for(int i = 0; i < LOOP; i++){
                account.withdrawal(BigDecimal.ONE);
            }
        }
    }

    @Test
    public void testDoubleLimitationForCurrency(){
        Account account = new Account(LOGIN, PASSWORD);
        BigDecimal addOperand = BigDecimal.valueOf(0.1);
        for( int i = 0; i< 1000; i++){
            account.deposit(addOperand);
        }
        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(100.0));
    }

    /**
     * Total deposit equals total withdrawal
     * Final balance must be equals to initial balance
     * @throws InterruptedException
     */
    @Test
    public void testConcurrentAccess() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
        Account account = new Account(LOGIN, PASSWORD);
        account.deposit(BigDecimal.valueOf(LOOP*THREAD_NUMBER));
        for(int i = 0; i< THREAD_NUMBER; i++){
            executorService.submit(new Producer(account));
        }
        for(int i = 0; i< THREAD_NUMBER; i++){
            executorService.submit(new Consumer(account));
        }

        executorService.awaitTermination(2, TimeUnit.SECONDS);


        assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(LOOP*THREAD_NUMBER));
    }
}
