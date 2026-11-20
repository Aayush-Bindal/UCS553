package Lab_9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Q4 {
    private static final String FILE_NAME = "user-account.dat";

    public static void main(String[] args) {
        UserAccount account = new UserAccount(301, "aayush.bindal", "secret123");
        UserAccount.loginCount = 4;

        try {
            serializeAccount(account);
            UserAccount restoredAccount = deserializeAccount();
            System.out.println("Deserialized account:");
            System.out.println("User ID: " + restoredAccount.userId);
            System.out.println("Username: " + restoredAccount.username);
            System.out.println("Password after deserialization: " + restoredAccount.password);
            System.out.println("Shared login count: " + UserAccount.loginCount);
            System.out.println("The password is transient and loginCount is static, "
                    + "so neither is part of serialized instance state.");
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("User account serialization failed: " + exception.getMessage());
        }
    }

    private static void serializeAccount(UserAccount account) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            output.writeObject(account);
        }
    }

    private static UserAccount deserializeAccount() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (UserAccount) input.readObject();
        }
    }

    private static class UserAccount implements Serializable {
        private static final long serialVersionUID = 1L;
        private final int userId;
        private final String username;
        private transient String password;
        private static int loginCount;

        private UserAccount(int userId, String username, String password) {
            this.userId = userId;
            this.username = username;
            this.password = password;
        }
    }
}
