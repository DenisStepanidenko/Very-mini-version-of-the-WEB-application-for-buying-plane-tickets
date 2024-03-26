package validator;

import lombok.Value;

/**
 * Класс с ошибкой, где хранится описание ошибки и код ошибки
 */
@Value(staticConstructor = "of")
public class Error {
    String code;
    String message;
}
