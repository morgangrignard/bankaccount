package bank;

public class Account {

    private String login;
    private String password;

    private double balance;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
