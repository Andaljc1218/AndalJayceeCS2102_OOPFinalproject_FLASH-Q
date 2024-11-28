import java.util.*;

public class QuizManager {
    private final List<Flashcard> flashcards;
    private final Scanner scanner = new Scanner(System.in);

    public QuizManager(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }

    public void startQuiz() {
        if (flashcards.isEmpty()) {
            System.out.println("No flashcards available for the quiz.");
            return;
        }
    
        String choice = null;
    
        // Loop again till valid input or cancel
        while (choice == null || choice.isBlank()) {
            System.out.println("\nChoose Quiz Type:");
            System.out.println("1. Identification");
            System.out.println("2. True/False");
            System.out.println("3. Randomized");
            System.out.print("Enter your choice or leave blank to cancel: ");
            choice = scanner.nextLine();
    
            if (choice.isBlank()) {
                System.out.println("Returning to User Menu!");
                return;
            }
    
            // validation
            if (!choice.equals("1") && !choice.equals("2") && !choice.equals("3")) {
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                choice = null; // reset then loop chocie
            }
        }
    
        List<Flashcard> quizFlashcards = new ArrayList<>();
        switch (choice) {

            case "1":
                quizFlashcards = filterFlashcardsByType("Identification");
                break;
            case "2":
                quizFlashcards = filterFlashcardsByType("True/False");
                break;
            case "3":
                quizFlashcards.addAll(flashcards);
                Collections.shuffle(quizFlashcards);
                break;
        }
    
        if (quizFlashcards.isEmpty()) {
            System.out.println("No flashcards of the selected type available.");
            return;
        }
    
        takeQuiz(quizFlashcards);
    }
    

    private List<Flashcard> filterFlashcardsByType(String type) {
        List<Flashcard> filteredFlashcards = new ArrayList<>();
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getType().equals(type)) {
                filteredFlashcards.add(flashcard);
            }
        }
        return filteredFlashcards;
    }

    private void takeQuiz(List<Flashcard> quizFlashcards) {
        int totalCorrect = 0;
        int idCorrect = 0;
        int tfCorrect = 0;
        int idTotal = 0;
        int tfTotal = 0;

        for (Flashcard flashcard : quizFlashcards) {
            String userAnswer = null;

            // 
            while (userAnswer == null || userAnswer.isBlank()) {
                System.out.println("\nQuestion: " + flashcard.getQuestion());
                System.out.print("Your answer (type 'skip' to skip, or 'GG' to reveal answer): ");
                userAnswer = scanner.nextLine();

                if (userAnswer.isBlank()) {
                    System.out.println("Quiz cancelled.");
                    return;
                }

                if (userAnswer.equalsIgnoreCase("skip")) {
                    System.out.println("Question skipped.");
                    break; // next question
                }

                if (userAnswer.equalsIgnoreCase("GG")) {
                    System.out.println("The correct answer is: " + flashcard.getAnswer());
                    break; // next question
                }

                if (userAnswer.equalsIgnoreCase(flashcard.getAnswer())) {
                    System.out.println("Correct!");
                    totalCorrect++;
                    if (flashcard.getType().equals("Identification")) {
                        idCorrect++;
                    } else if (flashcard.getType().equals("True/False")) {
                        tfCorrect++;
                    }
                    break; // next question
                } else {
                    System.out.println("Incorrect. The correct answer was: " + flashcard.getAnswer());
                }
            }

            if (flashcard.getType().equals("Identification")) {
                idTotal++;
            } else if (flashcard.getType().equals("True/False")) {
                tfTotal++;
            }
        }

        // Display quiz scoringggg
        System.out.println("\n===== Quiz Complete =====");
        System.out.println("Total score: " + totalCorrect + "/" + quizFlashcards.size());
        System.out.println("Identification score: " + idCorrect + "/" + idTotal);
        System.out.println("True/False score: " + tfCorrect + "/" + tfTotal);
    }
}
