package Lab_10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q2 {
    private static final String DATABASE_URL = "jdbc:sqlite:BankDB.db";

    public static void main(String[] args) {
        try (Connection connection = openConnection()) {
            createAccountTable(connection);
            insertAccount(connection, 1001, "Aarav Sharma", 10000.00);
            insertAccount(connection, 1002, "Diya Kapoor", 5000.00);
            transferMoney(connection, 1001, 1002, 2500.00);
            System.out.println("Accounts after successful transfer:");
            displayAccounts(connection);
        } catch (SQLException exception) {
            System.out.println("Bank transaction failed: " + exception.getMessage());
        }
    }

    private static Connection openConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    private static void createAccountTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Account (
                    account_no INTEGER PRIMARY KEY,
                    account_holder TEXT NOT NULL,
                    balance REAL NOT NULL CHECK (balance >= 0)
                )
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private static void insertAccount(Connection connection, int accountNumber,
            String accountHolder, double balance) throws SQLException {
        String sql = "INSERT OR IGNORE INTO Account "
                + "(account_no, account_holder, balance) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountNumber);
            statement.setString(2, accountHolder);
            statement.setDouble(3, balance);
            statement.executeUpdate();
        }
    }

    private static void transferMoney(Connection connection, int senderAccount,
            int receiverAccount, double amount) throws SQLException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }

        boolean originalAutoCommit = connection.getAutoCommit();
        connection.setAutoCommit(false);
        try {
            double senderBalance = getBalance(connection, senderAccount);
            getBalance(connection, receiverAccount);
            if (senderBalance < amount) {
                throw new SQLException("Sender has insufficient balance.");
            }

            updateBalance(connection, senderAccount, -amount);
            updateBalance(connection, receiverAccount, amount);
            connection.commit();
            System.out.printf("Transferred Rs. %.2f from %d to %d.%n",
                    amount, senderAccount, receiverAccount);
        } catch (SQLException | RuntimeException exception) {
            connection.rollback();
            System.out.println("Transfer rolled back: " + exception.getMessage());
            throw exception;
        } finally {
            connection.setAutoCommit(originalAutoCommit);
        }
    }

    private static double getBalance(Connection connection, int accountNumber)
            throws SQLException {
        String sql = "SELECT balance FROM Account WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, accountNumber);
            try (ResultSet results = statement.executeQuery()) {
                if (!results.next()) {
                    throw new SQLException("Account not found: " + accountNumber);
                }
                return results.getDouble("balance");
            }
        }
    }

    private static void updateBalance(Connection connection, int accountNumber,
            double amountChange) throws SQLException {
        String sql = "UPDATE Account SET balance = balance + ? WHERE account_no = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, amountChange);
            statement.setInt(2, accountNumber);
            if (statement.executeUpdate() != 1) {
                throw new SQLException("Account update failed: " + accountNumber);
            }
        }
    }

    private static void displayAccounts(Connection connection) throws SQLException {
        String sql = "SELECT account_no, account_holder, balance "
                + "FROM Account ORDER BY account_no";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet results = statement.executeQuery()) {
            while (results.next()) {
                System.out.printf("%d | %s | Rs. %.2f%n",
                        results.getInt("account_no"),
                        results.getString("account_holder"),
                        results.getDouble("balance"));
            }
        }
    }
}
