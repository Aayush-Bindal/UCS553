package Lab_5;

public class Q5 {
    private static final BankAccount ACCOUNT = new BankAccount("A1001", 1000);

    public static void main(String[] args) {
        try {
            BankAccount account = find("A1001");
            account.deposit(500);
            account.withdraw(250);
            account.withdraw(2000);
        } catch (AccountNotFoundException | InvalidAmountException | InsufficientBalanceException exception) {
            System.out.println("Transaction failed: " + exception.getMessage());
        }
        try {
            System.out.printf("Balance after successful transactions: Rs. %.2f%n", find("A1001").balance);
            find("missing");
        } catch (AccountNotFoundException exception) {
            System.out.println("Account lookup failed: " + exception.getMessage());
        }
    }

    private static BankAccount find(String number) throws AccountNotFoundException {
        if (!"A1001".equals(number)) throw new AccountNotFoundException("Account does not exist.");
        return ACCOUNT;
    }

    static class BankAccount {
        private final String number;
        private double balance;
        BankAccount(String number, double balance) { this.number = number; this.balance = balance; }
        void deposit(double amount) throws InvalidAmountException {
            validate(amount); balance += amount;
        }
        void withdraw(double amount) throws InvalidAmountException, InsufficientBalanceException {
            validate(amount);
            if (amount > balance) throw new InsufficientBalanceException("Insufficient balance.");
            balance -= amount;
        }
        private void validate(double amount) throws InvalidAmountException {
            if (amount <= 0) throw new InvalidAmountException("Amount must be positive.");
        }
    }
    static class AccountNotFoundException extends Exception {
        AccountNotFoundException(String message) { super(message); }
    }
    static class InvalidAmountException extends Exception {
        InvalidAmountException(String message) { super(message); }
    }
    static class InsufficientBalanceException extends Exception {
        InsufficientBalanceException(String message) { super(message); }
    }
}
