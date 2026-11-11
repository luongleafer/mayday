package mayday.entities.qa;

public class SimpleQuestionBuilder {

    private Question resultQuestion;

    public SimpleQuestionBuilder questionId(String id) {
        resultQuestion.setId(id);
        return this;
    }

    public SimpleQuestionBuilder questionText(String text) {
        resultQuestion.setText(text);
        return this;
    }

    public SimpleQuestionBuilder questionDescription(String description) {
        resultQuestion.setDescription(description);
        return this;
    }

    public SimpleQuestionBuilder questionType(String type) {
        resultQuestion.setType(type);
        return this;
    }

    public Question build() {
        return resultQuestion;
    }
}
