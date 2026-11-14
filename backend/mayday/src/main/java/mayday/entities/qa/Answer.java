package mayday.entities.qa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.exceptions.InvalidOptionException;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @JsonProperty("question_id")
    private String questionId;

    @JsonProperty("answer_value")
    private Object answerValue;

    public void update(Answer newAnswer) {
        if (!newAnswer.questionId.equals(questionId)) return;
        if (newAnswer.answerValue == null) return;
        answerValue = newAnswer.answerValue;
    }

    public void updateFromList(List<Answer> answers) {
        answers.forEach(this::update);
    }
}
