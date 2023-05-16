package Bankingapplication3;

import java.util.Random;
import java.util.Scanner;

public class Bankingapplication3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank("XYZ");
        Account account = null;
        String accname = "",accType;
        int accnumber = 0, option = 0;
        double balance = 0, amount = 0, minimum=1000;

        while (option != 6) {
            System.out.println("Main menu");
            System.out.println("1. Display All Account");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");

            System.out.println();
            System.out.print("Enter your choice: ");

            option = sc.nextInt();
            sc.nextLine();
            switch (option){
                case 1 -> {
                    bank.listAccount();
                    break;
                }
                case 2 -> {
                    accnumber = generateAccountNumber();
                    System.out.print("Enter your name: ");
                    accname = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    balance = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Account (s --> savings Account or c --> current Account): ");
                    accType = sc.nextLine();
                    
                    if (accType.toLowerCase().equals("s")){
                        account = new SavingsAccount(accnumber,accname,balance);
                    }else if (accType.toLowerCase().equals("c")){
                        account = new currentAccount(accnumber,accname,balance);
                    }

                    bank.openAccount(account);
                    break;
                }
                case 3 -> {
                    System.out.print("Enter Account Number: ");
                    accnumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Account (s --> savings Account or c --> current Account): ");
                    accType = sc.nextLine();
                    if (accType.toLowerCase().equals("s")){
                        account = bank.getAccount(accnumber,"SavingAccount");
                    }else if (accType.toLowerCase().equals("c")){
                        account = bank.getAccount(accnumber,"CurrentAccount");
                    }
                    bank.closeAccount(account);
                    System.out.println("Account is Delete");
                    System.out.println();
                    break;
                }
                case 4 -> {
                    System.out.print("Enter Account number: ");
                    accnumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Account (s --> savings Account or c --> current Account): ");
                    accType = sc.nextLine();
                    if (accType.toLowerCase().equals("s")){
                        account = bank.getAccount(accnumber,"SavingAccount");
                    }else if (accType.toLowerCase().equals("c")){
                        account = bank.getAccount(accnumber,"CurrentAccount");
                    }
                    System.out.print("Enter Amount: ");
                    amount = sc.nextDouble();
                    bank.depositMoney(account,amount);
                    System.out.printf("Account[%d] Balance[%.2f]%n",account.getAccountNumber(),account.getBalance());
                    System.out.println();
                    break;
                }
                case 5 -> {
                    System.out.print("Enter Account number: ");
                    accnumber = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Account (s --> savings Account or c --> current Account): ");
                    accType = sc.nextLine();
                    if (accType.toLowerCase().equals("s")){
                        account = bank.getAccount(accnumber,"SavingAccount");
                    }else if (accType.toLowerCase().equals("c")){
                        account = bank.getAccount(accnumber,"CurrentAccount");
                    }
                    System.out.print("Enter Amount: ");
                    amount = sc.nextDouble();
                    bank.withdrawMoney(account,amount);
                    System.out.printf("Account[%d] Balance[%.2f]%n",account.getAccountNumber(),account.getBalance());
                    System.out.println();
                    break;
                }
            }

        }
    }

    public static int generateAccountNumber(){
        Random random = new Random();
        int accNumber = 100000 + random.nextInt(900000);
        return accNumber;
    }
}
