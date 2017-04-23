package bank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {
    enum OperationType{
        DEPOSIT, WITHDRAWAL
    }

    private OperationType type;
    private LocalDateTime date;
    private BigDecimal amount;
    private BigDecimal balance;

    public Operation( OperationType type, BigDecimal amount, BigDecimal balance){
        this.type = type;
        this.amount = amount;
        this.balance = balance;
        this.date = LocalDateTime.now();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public OperationType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
