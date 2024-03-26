package exception;

import lombok.Getter;
import validator.Error;

import java.util.List;

/**
 * Данный класс обрабатывает исключения, которые возникают при валидации пароля и логина пользователя
 */
public class ValidationException extends RuntimeException {
    @Getter
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
