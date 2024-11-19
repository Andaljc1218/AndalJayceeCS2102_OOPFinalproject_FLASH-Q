import java.io.*;
import java.util.*;

public class FileHandler {

    public static Map<String, String> loadUserAccounts(String filename) {
        Map<String, String> userAccounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    userAccounts.put(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            // no accounts to load since file dont exist
        } catch (IOException e) {
            System.out.println("Error reading user accounts: " + e.getMessage());
        }

        return userAccounts;
    }

    public static void saveUserAccounts(String filename, Map<String, String> userAccounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : userAccounts.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving user accounts: " + e.getMessage());
        }
    }

    public static List<String> loadFlashcards(String filename) {
        List<String> flashcards = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                flashcards.add(line);
            }
        } catch (FileNotFoundException e) {
            // no accounts to load since file dont exist
        } catch (IOException e) {
            System.out.println("Error reading flashcards: " + e.getMessage());
        }

        return flashcards;
    }

    public static void saveFlashcards(String filename, List<String> flashcards) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String flashcard : flashcards) {
                writer.write(flashcard);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving flashcards: " + e.getMessage());
        }
    }
}