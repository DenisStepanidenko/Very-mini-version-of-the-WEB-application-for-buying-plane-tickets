package validator;

import lombok.Value;

/**
 * Класс с ошибкой, где описывает код и сообщение ошибки
 */
@Value(staticConstructor = "of")
public class Error {
    String code;
    String message;
}
