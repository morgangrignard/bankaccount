package bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private String login;
    private String password;

    private List<Operation> operations = new ArrayList<Operation>();
    private BigDecimal balance = BigDecimal.ZERO;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
        operations.add(new Operation(Operation.OperationType.DEPOSIT, amount, balance));
    }

    public void withdrawal(BigDecimal amount) {
        if( balance.compareTo(amount) >= 0){
            balance = balance.subtract(amount);
            operations.add(new Operation(Operation.OperationType.WITHDRAWAL, amount, balance));
        }
        else{
            throw new IllegalArgumentException("Amount greater than current balance");
        }
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Operation> getHistory() {
        return operations;
    }
}
