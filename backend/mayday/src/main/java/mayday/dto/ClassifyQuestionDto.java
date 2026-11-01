package mayday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyQuestionDto {

    @JsonProperty("question_id")
    private String questionId;

    @JsonProperty("question_text")
    private String questionText;

    @JsonProperty("type_question")
    private String typeQuestion; // radio, checkbox, number...

    @JsonProperty("options")
    private List<OptionItem> options;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OptionItem {
        @JsonProperty("id")
        private Integer id;

        @JsonProperty("option_text")
        private String optionText;

        @JsonProperty("option_value")
        private String optionValue;
    }
}