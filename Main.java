package Procedural;

import java.util.ArrayList;
import java.util.Scanner;

class Account {
    String name;
    String accountNumber;
    double balance;
} // main data that will be displayed for an account

public class Main {
    static ArrayList<Account> accounts = new ArrayList<>();
    //an array that will store all accounts

    static Scanner input = new Scanner(System.in);
    //takes input from the user

    static String generateaccountNumber(){
        String acNum = "";
        for (int i = 0; i <12 ; i++){
            acNum += (int)(Math.random() * 10);
        }
        return acNum;
    }
    //generates a 12 digit account number for every account created

    public static void main(String[] args) {
        menu();
    }

    static void menu() {

        while (true){

            System.out.printf("%50s\n", "BANK ACCOUNT MANAGEMENT SYSTEM");
            System.out.println("1. Create an Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposits");
            System.out.println("4. Withdrawals");
            System.out.println("5. Transfer between accounts");
            System.out.println("6. Account Search");
            System.out.println("7. Delete an Account");
            System.out.println("\n \n Input your selection here:");
            //Displays a menu for the the user

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> viewAccounts();
                case 3 -> deposits();
                case 4 -> withdrawals();
                case 5 -> transfers();
                case 6 -> searchAccount();
                case 7 -> deleteAccount();
                case 8 -> System.exit(0);
                default -> System.out.println("Your choice is invalid");
                //Matches the number with their respective function/option
            }
        }

    }



    static void createAccount() {
        Account ac = new Account();

        System.out.print("NAME: ");
        ac.name = input.nextLine();

        ac.accountNumber = generateaccountNumber();
        System.out.println("Generated Account Number: " + ac.accountNumber);

        ac.balance = 0;

        accounts.add(ac);

        System.out.println("Congratulations Your Account Was Successfully Created!");

    }
    //Creates a new account and auto generate an account number


    static void viewAccounts(){
        for (Account ac : accounts){
            System.out.println(ac.accountNumber + " - " + ac.name + " - $" + ac.balance);
        }
    } //Views all account and their details


    static void deposits(){
        System.out.printf("Enter account number:  ");
        String number = input.next();

        for (Account ac : accounts) {
            if (ac.accountNumber.equals(number)) {
                System.out.println("Enter the deposit amount: ");
                double amount = input.nextDouble();

                ac.balance += amount;
                System.out.println("Deposit Successful!");
                System.out.println("New Balance: $" + ac.balance);

                return;
                //This function allows deposits for an existing account and provides a balance update after.
            }

        }

    }

    static void withdrawals(){
        System.out.printf("Enter account number:  ");
        String number = input.next();

        for (Account ac : accounts) {
            if (ac.accountNumber.equals(number)) {
                System.out.println("Enter the withdrawal amount: ");
                double amount = input.nextDouble();

                if (amount > ac.balance){
                    System.out.println("Unable to Process Transaction due to Insuffient Funds!");
                }

                else{
                    ac.balance -= amount;
                    System.out.println("Withdrawal Successful!");
                    System.out.println("New Balance: $" + ac.balance);
                }

                return;
                //This function allows withdrawal of funds from an existing account and provides a balance update after.
            }
        }

    }

    static Account searchAccount(){
        System.out.printf("Enter account number:  ");
        String number = input.next();

        for (Account acc : accounts) {
            if (acc.accountNumber.equals(number)) {
                System.out.printf("%50s\n", "ACCOUNT FOUND!");
                System.out.println("NAME:  " + acc.name);
                System.out.println("TOTAL AVAILABLE BALANCE:  $" + acc.balance);
                return acc;

            }
        }

        System.out.printf("%50s\n", "Account not found");
        return null;
    }
// Allows user to search for an account by account number



    static void transfers(){
        System.out.printf("Enter debit account number:  ");
        String debitNum = input.next();
        //asks user for sender's account number and stores the value in debitNum

        System.out.printf("Enter credit account number:  ");
        String creditNum = input.next();
        //asks user for receiver's account number and stores the value in creditNum

        Account sender = null;
        Account receiver = null;

        for (Account ac : accounts) {
            if (ac.accountNumber.equals(debitNum)){
                sender = ac;
            }
            if (ac.accountNumber.equals(creditNum)){
                receiver = ac;
            }
        }

        if (sender == null || receiver == null) {
            System.out.printf("One or both account does not exist.");

            return;
            //Checks if both accounts exist
        }

        System.out.printf("Enter the amount to transfer:  ");
        double amount = input.nextDouble();
        if (amount > sender.balance) {
            System.out.println("Insuffient balance");

            return;
            //Checks if the sender has suffient funds to process the transfer
        }

        sender.balance -= amount;
        receiver.balance += amount;

        System.out.println("Transfer Successful!");
        System.out.println("Sender's New balance: $" + sender.balance);
        System.out.println("Receiver's New balance: $" + receiver.balance);

        //Transfers money between two existing accounts and provides the new balance for both acounts
    }


    static void deleteAccount() {
        System.out.printf("Enter account number:  ");
        String number = input.next();

        for (int i = 0; i < accounts.size(); i++) { //index can later be used to delete an account
            if (accounts.get(i).accountNumber.equals(number)) {
                accounts.remove(i);
                System.out.println("Account Was Deleted Successfully!");

                return;
                //This function allows the user to delete an existing account.
            }
        }
    }


}