package mayday.entities.qa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.exceptions.InvalidOptionException;

@Getter
@Setter
@NoArgsConstructor
public class Answer {
    private String id;
    private Question question;
    private Option chosenOption;
    private Object value;

    public Answer(String id, Question question, Object value) {
        this.id = id;
        this.question = question;
        this.chosenOption = null;
        this.value = value;
    }

    public Answer(String id, Question question, Option option) {
        this.id = id;
        this.value = null;
        this.question = question;
        if (!question.getOptions().contains(option))
            throw new InvalidOptionException("Invalid option for question with id " + question.getId());
        this.chosenOption = option;

    }

    public Object getValue() {
        if (chosenOption == null) {
            return chosenOption;
        } else return value;
    }
}
