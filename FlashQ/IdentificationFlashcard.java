public class IdentificationFlashcard extends Flashcard {
    public IdentificationFlashcard(String question, String answer) {
        super(question, answer);
    }

    @Override
    public String getType() {
        return "Identification";
    }
}