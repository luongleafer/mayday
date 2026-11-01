package mayday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyQuestionsResponseDto {

    @JsonProperty("code")
    private String code; // 1000

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<ClassifyQuestionDto> data;
}