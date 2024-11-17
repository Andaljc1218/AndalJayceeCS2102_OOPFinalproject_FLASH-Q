import java.io.*;
import java.util.*;

public class UserAccount {
    private static final String ACCOUNT_FILE = "accounts.txt";

    public void registerAccount(Scanner scanner) {
        System.out.print("Enter a new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a new password: ");
        String password = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Account registered successfully!");
        } catch (IOException e) {
            System.out.println("Error while registering account: " + e.getMessage());
        }
    }

    public String login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    System.out.println("Login successful!");
                    return username;
                }
            }
            System.out.println("Invalid username or password.");
        } catch (IOException e) {
            System.out.println("Error while logging in: " + e.getMessage());
        }
        return null;
    }
}