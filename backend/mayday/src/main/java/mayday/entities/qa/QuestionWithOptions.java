package mayday.entities.qa;

import lombok.Getter;

import java.util.List;

public class QuestionWithOptions extends Question {

    @Getter
    List<Option> options;

    void addOption(Option option) {
        options.add(option);
    }
}
