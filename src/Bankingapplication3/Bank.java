package Bankingapplication3;

import java.sql.*;

public class Bank {
    private String bankName;

    public Bank() {
    }

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName(){
        return this.bankName;
    }

    public void listAccount() {
        try {
            Connection con = BankConnection.connect();
            Statement statement = con.createStatement();
            String sql = "select * from account";
            ResultSet results = statement.executeQuery(sql);

            while (results.next()) {
                System.out.println(results.getString(1) + " " + results.getString(2) + " " +
                        results.getString(3) + " " + results.getString(4));
            }
            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void openAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "insert into account(accountID,accountName,accountBalance,accountType)"
                + "values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccountNumber());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setString(4,account.getAccountType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void closeAccount(Account account) {
        Connection con = BankConnection.connect();
        String sql = "delete from account where accountID = ? AND accountType = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,account.getAccountNumber());
            preparedStatement.setString(2,account.getAccountType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void depositMoney(Account account, double amount) {
        account.deposit(amount);
        Connection con = BankConnection.connect();
        try {
            Statement statement = con.createStatement();
            String sql = "update account set accountBalance = ? where accountID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void withdrawMoney(Account account, double amount) {
        account.withdraw(amount);
        Connection con = BankConnection.connect();
        try {
            Statement statement = con.createStatement();
            String sql = "update account set accountBalance = ? where accountID = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDouble(1, account.getBalance());
            preparedStatement.setInt(2, account.getAccountNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccount(int accountnumber,String accountType) {
        Connection con = BankConnection.connect();
        Account account = null;
        String sql = "SELECT * FROM account WHERE accountID = ? AND accountType = ?";
        ResultSet result = null;
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1,accountnumber);
            preparedStatement.setString(2,accountType);
            result = preparedStatement.executeQuery();

            result.next();
            String accname = result.getString(2);
            double balance = result.getDouble(3);
            String acctype = result.getString(4);
            if (acctype.equals("SavingAccount")){
                account = new SavingsAccount(accountnumber, accname, balance);
            } else if (acctype.equals("CurrentAccount")) {
                account = new currentAccount(accountnumber,accname,balance);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return account;
    }

}
