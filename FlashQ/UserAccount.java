import java.io.*;
import java.util.*;

public class UserAccount {
    private static final String ACCOUNT_FILE = "accounts.txt";

    public void registerAccount(Scanner scanner) {
        System.out.println();
        String username;
        String password;
    
        // Prompt for a valid username
        while (true) {
            System.out.print("Enter a new username: ");
            username = scanner.nextLine().trim(); // Trim to remove leading/trailing spaces
            if (username.isEmpty()) {
                System.out.println("Username cannot be blank. Please try again.");
            } else if (isUsernameTaken(username)) {
                System.out.println("This username is already taken. Please choose a different username.");
            } else {
                break;
            }
        }
    
        while (true) {
            System.out.print("Enter a new password: ");
            password = scanner.nextLine().trim(); 
            if (password.isEmpty()) {
                System.out.println("Password cannot be blank. Please try again.");
            } else {
                break;
            }
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE, true))) {
            writer.write(username + "," + password);
            writer.newLine();
            System.out.println("Account registered successfully!");
        } catch (IOException e) {
            System.out.println("Error while registering account: " + e.getMessage());
        }
    }
    
    public String login(Scanner scanner) {
        System.out.println();
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

    private boolean isUsernameTaken(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {
            System.out.println("Error while checking username: " + e.getMessage());
        }
        return false;
    }
}
