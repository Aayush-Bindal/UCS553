package Lab_5;

public class Q6 {
    public static void main(String[] args) {
        LoginSystem login = new LoginSystem("student", "Java@123", 3);
        for (String[] attempt : new String[][]{{"student", "wrong"}, {"guest", "x"},
                {"student", "Java@123"}}) {
            try {
                login.login(attempt[0], attempt[1]);
                System.out.println("Login successful.");
                break;
            } catch (InvalidUsernameException | InvalidPasswordException |
                     AccountLockedException exception) {
                System.out.println("Login failed: " + exception.getMessage());
            } finally {
                System.out.println("Login attempt processed.");
            }
        }

        LoginSystem lockedLogin = new LoginSystem("student", "Java@123", 3);
        for (int attempt = 1; attempt <= 4; attempt++) {
            try {
                lockedLogin.login("student", "wrong");
            } catch (InvalidUsernameException | InvalidPasswordException |
                     AccountLockedException exception) {
                System.out.println("Lockout test: " + exception.getMessage());
            } finally {
                System.out.println("Lockout attempt processed.");
            }
        }
    }

    static class LoginSystem {
        private final String username;
        private final String password;
        private final int maximumAttempts;
        private int failures;
        private boolean locked;
        LoginSystem(String username, String password, int maximumAttempts) {
            this.username = username; this.password = password; this.maximumAttempts = maximumAttempts;
        }
        void login(String enteredUsername, String enteredPassword)
                throws InvalidUsernameException, InvalidPasswordException, AccountLockedException {
            if (locked) throw new AccountLockedException("Account is locked.");
            try {
                if (!username.equals(enteredUsername)) throw new InvalidUsernameException("Invalid username.");
                if (!password.equals(enteredPassword)) throw new InvalidPasswordException("Invalid password.");
            } catch (InvalidUsernameException | InvalidPasswordException exception) {
                if (++failures >= maximumAttempts) locked = true;
                throw exception;
            }
        }
    }
    static class InvalidUsernameException extends Exception { InvalidUsernameException(String m) { super(m); } }
    static class InvalidPasswordException extends Exception { InvalidPasswordException(String m) { super(m); } }
    static class AccountLockedException extends Exception { AccountLockedException(String m) { super(m); } }
}
