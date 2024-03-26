package validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/**
 * Класс, где хранится список ошибок, которые возникают при валидации
 */
@Getter
public class ValidationResult {
    /**
     * Список ошибок
     */
    private final List<Error> errors = new ArrayList<>();

    /**
     * Метод, который добавляет новую ошибку в список
     */
    public void add(Error error) {
        this.errors.add(error);
    }

    /**
     * Метод, который по факту валидирует, то есть проверяет - пуст ли список ошибок
     */
    public boolean isValid() {
        return errors.isEmpty();
    }
}
