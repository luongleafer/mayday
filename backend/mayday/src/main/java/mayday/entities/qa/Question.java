package mayday.entities.qa;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Question {
    private String id;
    private String text;
    private String type;
    private String description;
    private List<Option> options;

    public void addOption(Option option) {
        options.add(option);
    }
}
