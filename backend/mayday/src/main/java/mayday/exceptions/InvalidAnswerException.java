package mayday.exceptions;

import mayday.entities.qa.Answer;

public class InvalidAnswerException extends RuntimeException {
    public InvalidAnswerException(String message) {
        super(message);
    }

    public InvalidAnswerException(Answer answer) {
        super("Invalid answer for question " + answer.getQuestionId());
    }
}
