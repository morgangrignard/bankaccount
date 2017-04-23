package bank;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String login;
    private String password;

    private List<Operation> operations = new ArrayList<Operation>();
    private double balance;

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

    public void deposit(double amount) {
        balance += amount;
        operations.add(new Operation(Operation.OperationType.DEPOSIT, amount, balance));
    }

    public void withdrawal(double amount) {
        if( balance >= amount){
            balance -= amount;
            operations.add(new Operation(Operation.OperationType.WITHDRAWAL, amount, balance));
        }
        else{
            throw new IllegalArgumentException("Amount greater than current balance");
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<Operation> getHistory() {
        return operations;
    }
}
