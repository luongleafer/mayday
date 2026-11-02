package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyRecordRequestDto {

    @JsonProperty("answers")
    private List<AnswerItem> answers;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerItem {
        @JsonProperty("question_id")
        private String questionId;

        @JsonProperty("option_value")
        private String optionValue;
    }
}