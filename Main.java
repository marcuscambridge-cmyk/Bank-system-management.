package OOP;
import java.util.*;

class BankSystem {
    private List<BankAccount> accounts = new ArrayList<>();

    public void createAccount(String accNo, String name, double initialDeposit) {
        accounts.add(new BankAccount(accNo, name, initialDeposit));
        System.out.println("Account created successfully.");
    }

    public BankAccount findAccount(String accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                return acc;
            }
        }
        return null;
    }

    public void deleteAccount(String accNo) {
        BankAccount acc = findAccount(accNo);
        if (acc != null) {
            accounts.remove(acc);
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transfer(String fromAcc, String toAcc, double amount) {
        BankAccount sender = findAccount(fromAcc);
        BankAccount receiver = findAccount(toAcc);

        if (sender != null && receiver != null) {
            if (amount > 0 && sender.getBalance() >= amount) {
                sender.withdraw(amount);
                receiver.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Transfer failed: insufficient funds.");
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    public void displayAllAccounts() {
        for (BankAccount acc : accounts) {
            acc.displayDetails();
        }
    }
}
