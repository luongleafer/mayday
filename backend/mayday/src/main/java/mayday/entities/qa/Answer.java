package mayday.entities.qa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.exceptions.InvalidOptionException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @JsonProperty("question_id")
    private String questionId;

    @JsonProperty("answer_value")
    private Object answerValue;
}
