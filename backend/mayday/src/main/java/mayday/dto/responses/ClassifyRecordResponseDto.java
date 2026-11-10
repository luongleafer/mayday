package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassifyRecordResponseDto {

    @JsonProperty("code")
    private String code; // 1000

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private TemplateData data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateData {
        @JsonProperty("template_id")
        private String templateId; // varchar(255): TMPL_URT_CHRONIC hoặc TMPL_URT_ACUTE

        @JsonProperty("template_name")
        private String templateName;

        @JsonProperty("structure")
        private Object structure; // Cấu trúc của template để client render
    }
}
