package validator;

/**
 * Базовый интерфейс для валидаторов
 * @param <T> Тип, который мы валидируем
 */
public interface Validator<T> {
    ValidationResult isValid(T object);
}
