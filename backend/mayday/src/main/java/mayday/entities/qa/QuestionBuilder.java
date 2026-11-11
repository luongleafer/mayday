package mayday.entities.qa;

public class QuestionBuilder {
    public static SimpleQuestionBuilder simple() {
        return new SimpleQuestionBuilder();
    }

    public static QuestionWithOptionsBuilder withOptions() {
        return new QuestionWithOptionsBuilder();
    }
}
