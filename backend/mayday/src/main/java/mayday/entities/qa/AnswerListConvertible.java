package mayday.entities.qa;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface AnswerListConvertible {

    void updateFromListOfAnswers(List<Answer> answers);
}
