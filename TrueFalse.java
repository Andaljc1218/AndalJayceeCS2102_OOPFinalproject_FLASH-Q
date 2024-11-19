public class TrueFalseFlashcard extends Flashcard {
    public TrueFalseFlashcard(String question, String answer) {
        super(question, answer);
    }

    @Override
    public String getType() {
        return "True/False";
    }
}