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
public class CreateMedicalRecordRequestDto {
    @JsonProperty("patient_id")
    private Long patientId;

    @JsonProperty("template_id")
    private String templateId; // TMPL_URT_ACUTE hoặc TMPL_URT_CHRONIC

    @JsonProperty("answers")
    private List<AnswerItem> answers;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnswerItem {
        @JsonProperty("question_id")
        private String questionId;

        @JsonProperty("question_option_id")
        private Integer questionOptionId;

        @JsonProperty("answer_text")
        private String answerText;

        @JsonProperty("answer_value")
        private Object answerValue;
    }

}
