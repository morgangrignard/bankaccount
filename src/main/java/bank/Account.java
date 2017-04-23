package bank;

public class Account {

    private String login;
    private String password;

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
    }

    public void withdrawal(double amount) {
        if( balance >= amount){
            balance -= amount;
        }
        else{
            throw new IllegalArgumentException("Amount greater than current balance");
        }
    }

    public double getBalance() {
        return balance;
    }
}
