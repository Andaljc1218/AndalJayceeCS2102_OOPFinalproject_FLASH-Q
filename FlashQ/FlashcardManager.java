import java.io.*;
import java.util.*;

public class FlashcardManager {
    public final String username;
    private final String flashcardFile;
    private final List<Flashcard> flashcards = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public FlashcardManager(String username) {
        this.username = username;
        this.flashcardFile = username + "_flashcards.txt";
        loadFlashcards();
    }

    private void loadFlashcards() {
        try (BufferedReader reader = new BufferedReader(new FileReader(flashcardFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0];
                String question = parts[1];
                String answer = parts[2];

                if (type.equals("Identification")) {
                    flashcards.add(new IdentificationFlashcard(question, answer));
                } else if (type.equals("True/False")) {
                    flashcards.add(new TrueFalseFlashcard(question, answer));
                }
            }
        } catch (FileNotFoundException e) {
            // no file yet
        } catch (IOException e) {
            System.out.println("Error loading flashcards: " + e.getMessage());
        }
    }

    private void saveFlashcards() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(flashcardFile))) {
            for (Flashcard flashcard : flashcards) {
                writer.write(flashcard.getType() + "|" + flashcard.getQuestion() + "|" + flashcard.getAnswer());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving flashcards: " + e.getMessage());
        }
    }

    public void addFlashcard() { //fixed
        while (true) {
            System.out.println("\nChoose Flashcard Type:");
            System.out.println("1. Identification");
            System.out.println("2. True/False");
            System.out.print("Enter your choice or leave blank to cancel: ");
            String choice = scanner.nextLine();

            if (choice.isBlank()) {
                System.out.println("Returning to User Menu!");
                return;
            }

            Flashcard flashcard = null;
            switch (choice) {
                case "1":
                    System.out.print("Enter the question: ");
                    String idQuestion = scanner.nextLine();
                    if (idQuestion.isBlank()) {
                        System.out.println("Adding flashcard canceled.");
                        return;
                    }
                    System.out.print("Enter the answer: ");
                    String idAnswer = scanner.nextLine();
                    if (idAnswer.isBlank()) {
                        System.out.println("Adding flashcard canceled.");
                        return;
                    }
                    flashcard = new IdentificationFlashcard(idQuestion, idAnswer);
                    break;
                case "2":
                    System.out.print("Enter the question: ");
                    String tfQuestion = scanner.nextLine();
                    if (tfQuestion.isBlank()) {
                        System.out.println("Adding flashcard canceled.");
                        return;
                    }
                    System.out.print("Enter the answer (True/False): ");
                    String tfAnswer = scanner.nextLine().trim();
                    if (!tfAnswer.equalsIgnoreCase("true") && !tfAnswer.equalsIgnoreCase("false")) {
                        System.out.println("Invalid answer. Please enter 'True' or 'False'.");
                        return;
                    }
                    flashcard = new TrueFalseFlashcard(tfQuestion, tfAnswer);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    continue;
            }

            if (flashcard != null) {
                flashcards.add(flashcard);
                System.out.println("Flashcard added successfully!");
                saveFlashcards();
            }

            System.out.print("Do you want to add another flashcard? (yes/no): ");
            String continueChoice = scanner.nextLine().trim().toLowerCase();
            if (continueChoice.equals("no")) {
                return;
            } else if (!continueChoice.equals("yes")) {
                System.out.println("Returning to User Menu.");
                return;
            }
        }
    }

    public void removeFlashcard() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards to remove.");
            return;
        }
    
        String choice = "";
        while (true) {
            System.out.println("\nChoose Flashcard Type to Remove:");
            System.out.println("1. Identification");
            System.out.println("2. True/False");
            System.out.println("3. All Types");
            System.out.print("Enter your choice or leave blank to cancel: ");
            choice = scanner.nextLine();
    
            if (choice.isBlank()) {
                System.out.println("No flashcards removed. Returning to User Menu!");
                return;
            }
    
            if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter 1, 2, or 3, or leave blank to cancel.");
            }
        }
    
        String selectedType = null;
        switch (choice) {
            case "1":
                selectedType = "Identification";
                break;
            case "2":
                selectedType = "True/False";
                break;
            case "3":
                break; // All types selected
            default:
                return;
        }
    
        List<Flashcard> filteredFlashcards = new ArrayList<>();
        if (selectedType != null) {
            for (Flashcard flashcard : flashcards) {
                if (flashcard.getType().equals(selectedType)) {
                    filteredFlashcards.add(flashcard);
                }
            }
        } else {
            filteredFlashcards.addAll(flashcards);
        }
    
        if (filteredFlashcards.isEmpty()) {
            System.out.println("No flashcards of the selected type(s) found.");
            return;
        }
    
        System.out.println("\nFlashcards to Remove:");
        for (int i = 0; i < filteredFlashcards.size(); i++) {
            Flashcard flashcard = filteredFlashcards.get(i);
            System.out.println((i + 1) + ". " + flashcard.getQuestion());
        }
    
        String input = "";
        while (true) {
            System.out.print("Enter the number(s) of flashcards to remove (comma-separated), or type 'all' to remove all: ");
            input = scanner.nextLine();
    
            if (input.isBlank()) {
                System.out.println("No flashcards removed. Returning to User Menu!");
                return;
            }
    
            if (input.equalsIgnoreCase("all") || input.matches("([0-9]+,)*[0-9]+")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter valid number(s) or 'all' to remove all.");
            }
        }
    
        if (input.equalsIgnoreCase("all")) {
            if (selectedType != null) {
                final String typeToRemove = selectedType; 
                flashcards.removeIf(flashcard -> flashcard.getType().equals(typeToRemove));
                System.out.println("All flashcards of type " + selectedType + " removed.");
            } else {
                flashcards.clear();
                System.out.println("All flashcards removed.");
            }
        } else {
            String[] indices = input.split(",");
            Set<Integer> invalidIndices = new HashSet<>();
            Set<Integer> validIndices = new HashSet<>();
    
            for (String index : indices) {
                try {
                    int idx = Integer.parseInt(index.trim()) - 1; 
                    if (idx < 0 || idx >= filteredFlashcards.size()) {
                        invalidIndices.add(idx + 1); 
                    } else {
                        validIndices.add(idx);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + index.trim() + " is not a number.");
                    return;
                }
            }
    
            if (validIndices.isEmpty()) {
                System.out.println("No valid indices selected. No flashcards removed.");
                return;
            }
    
            List<Flashcard> toRemove = new ArrayList<>();
            for (int validIndex : validIndices) {
                toRemove.add(filteredFlashcards.get(validIndex));
            }
    
            flashcards.removeAll(toRemove);
    
            if (!invalidIndices.isEmpty()) {
                System.out.println("Invalid indices ignored: " + invalidIndices);
            }
            System.out.println("Selected flashcards removed successfully.");
        }
    
        saveFlashcards();
    }
    
    public void editFlashcard() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards to edit.");
            return;
        }
    
        System.out.println("\nChoose Flashcard Type to Edit:");
        System.out.println("1. Identification");
        System.out.println("2. True/False");
        System.out.println("3. All Types");
        String choice = null;
    
        // looooooop
        while (choice == null || choice.isBlank()) {
            System.out.print("Enter your choice or leave blank to cancel: ");
            choice = scanner.nextLine();
    
            if (choice.isBlank()) {
                System.out.println("No flashcards edited. Returning to User Menu!");
                return;
            }
    
            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                choice = null; // Reset choice to loop againnnnn
            }
        }
    
        String selectedType = null;
        switch (choice) {
            case "1":
                selectedType = "Identification";
                break;
            case "2":
                selectedType = "True/False";
                break;
            case "3":
                break; // All types selected
        }
    
        // Filtererrr
        List<Flashcard> filteredFlashcards = new ArrayList<>();
        if (selectedType != null) {
            for (Flashcard flashcard : flashcards) {
                if (flashcard.getType().equals(selectedType)) {
                    filteredFlashcards.add(flashcard);
                }
            }
        } else {
            filteredFlashcards.addAll(flashcards); // all flashcards
        }
    
        if (filteredFlashcards.isEmpty()) {
            System.out.println("No flashcards of the selected type(s) found.");
            return;
        }
    
        System.out.println("\nFlashcards to Edit:");
        for (int i = 0; i < filteredFlashcards.size(); i++) {
            Flashcard flashcard = filteredFlashcards.get(i);
            System.out.println((i + 1) + ". " + flashcard.getQuestion() + " | Answer: " + flashcard.getAnswer());
        }
    
        String input = null;
    
        // Looopp until correct ansawerere
        while (input == null || input.isBlank()) {
            System.out.print("Enter the number of the flashcard to edit or leave blank to cancel: ");
            input = scanner.nextLine();
    
            if (input.isBlank()) {
                System.out.println("No flashcards edited. Returning to User Menu!");
                return;
            }
    
            try {
                int index = Integer.parseInt(input.trim()) - 1;  
                if (index < 0 || index >= filteredFlashcards.size()) {
                    System.out.println("Invalid index. Please enter a number between 1 and " + filteredFlashcards.size() + ".");
                    input = null; // Reset then loop input
                } else {
                    Flashcard flashcard = filteredFlashcards.get(index);
    
                    System.out.println("\nEditing Flashcard:");
                    System.out.println("Current Question: " + flashcard.getQuestion());
                    System.out.println("Current Answer: " + flashcard.getAnswer());
    
                    System.out.print("Enter new question (or leave blank to keep the same): ");
                    String newQuestion = scanner.nextLine();
                    if (!newQuestion.isBlank()) {
                        flashcard.setQuestion(newQuestion);
                    }
    
                    System.out.print("Enter new answer (or leave blank to keep the same): ");
                    String newAnswer = scanner.nextLine();
                    if (!newAnswer.isBlank()) {
                        flashcard.setAnswer(newAnswer);
                    }
    
                    System.out.println("Flashcard updated successfully!");
                    saveFlashcards();
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input = null; // Reset then loop input
            }
        }
    }

    public void displayFlashcards() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards to display.");
            return;
        }

        displayFlashcardsByType();
    }

    private void displayFlashcardsByType() {
        System.out.println("\nIdentification Questions:");
        int count = 1;
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getType().equals("Identification")) {
                System.out.println(count++ + ". " + flashcard.getQuestion() + " | " + flashcard.getAnswer() + " | Identification");
            }
        }

        System.out.println("\nTrue/False Questions:");
        count = 1;
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getType().equals("True/False")) {
                System.out.println(count++ + ". " + flashcard.getQuestion() + " | " + flashcard.getAnswer() + " | True/False");
            }
        }
    }

    public void takeQuiz() {
        // quizmanager file class along with socring yes
        QuizManager quizManager = new QuizManager(flashcards);
        quizManager.startQuiz();
    }
}
