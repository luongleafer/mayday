package mayday.entities.qa;

public class QuestionWithOptionsBuilder {

    QuestionWithOptions resultQuestion;

    public QuestionWithOptionsBuilder questionId(String id) {
        resultQuestion.setId(id);
        return this;
    }

    public QuestionWithOptionsBuilder questionText(String text) {
        resultQuestion.setText(text);
        return this;
    }

    public QuestionWithOptionsBuilder questionDescription(String description) {
        resultQuestion.setDescription(description);
        return this;
    }

    public QuestionWithOptionsBuilder questionType(String type) {
        resultQuestion.setType(type);
        return this;
    }

    public QuestionWithOptionsBuilder withOption(Option option) {
        resultQuestion.addOption(option);
        return this;
    }

    public QuestionWithOptions build() {
        return resultQuestion;
    }
}
