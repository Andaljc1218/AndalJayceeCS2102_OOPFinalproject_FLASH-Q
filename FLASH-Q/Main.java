import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserAccount userAccount = new UserAccount();
        FlashcardManager flashcardManager = null;

        while (true) {
            System.out.println("===== Flashcard Quiz System =====");
            System.out.println("1. Register Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userAccount.registerAccount(scanner);
                    break;
                case "2":
                    String username = userAccount.login(scanner);
                    if (username != null) {
                        flashcardManager = new FlashcardManager(username);
                        userMenu(flashcardManager, scanner);
                    }
                    break;
                case "3":
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu(FlashcardManager flashcardManager, Scanner scanner) {
        while (true) {
            System.out.println("\n===== User Menu =====");
            System.out.println("1. Add Flashcard");
            System.out.println("2. Remove Flashcard");
            System.out.println("3. Edit Flashcard");
            System.out.println("4. Display Flashcards");
            System.out.println("5. Take Quiz");
            System.out.println("6. Log Out");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    flashcardManager.addFlashcard();
                    break;
                case "2":
                    flashcardManager.removeFlashcard();
                    break;
                case "3":
                    flashcardManager.editFlashcard();
                    break;
                case "4":
                    flashcardManager.displayFlashcards();
                    break;
                case "5":
                    flashcardManager.takeQuiz();
                    break;
                case "6":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}