package mayday.entities.qa;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Question {
    private String id;
    private String text;
    private String type;
    private String description;
}
