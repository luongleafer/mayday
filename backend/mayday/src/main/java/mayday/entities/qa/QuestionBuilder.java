package mayday.entities.qa;

public class QuestionBuilder {
    Question result;

    public QuestionBuilder() {
        result = new Question();
    }

    public QuestionBuilder id(String id) {
        result.setId(id);
        return this;
    }

    public QuestionBuilder text(String text) {
        result.setText(text);
        return this;
    }

    public QuestionBuilder type(String type) {
        result.setType(type);
        return this;
    }

    public QuestionBuilder description(String description) {
        result.setDescription(description);
        return this;
    }

    public QuestionBuilder option(Option option) {
        result.addOption(option);
        return this;
    }

    public Question build() {
        return result;
    }


}
