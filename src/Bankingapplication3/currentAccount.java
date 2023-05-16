package Bankingapplication3;

public class currentAccount implements Account{
    private int accountNumber;
    private String accountName;
    private double balance;
    private double minimumm;
    private String accountType = "CurrentAccount";

    public currentAccount(int accountNumber, String accountName, double balance) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = balance;
        this.minimumm = 0;
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        this.balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    public double getMinimumm() {
        return minimumm;
    }

    public void setMinimumm(double minimumm) {
        this.minimumm = minimumm;
    }

    @Override
    public String getAccountType() {
        return accountType;
    }
}
