package bank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Operation {
    enum OperationType{
        DEPOSIT, WITHDRAWAL
    }

    private OperationType type;
    private LocalDateTime date;
    private double amount;
    private double balance;

    public Operation( OperationType type, double amount, double balance){
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.date = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }


    public OperationType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
