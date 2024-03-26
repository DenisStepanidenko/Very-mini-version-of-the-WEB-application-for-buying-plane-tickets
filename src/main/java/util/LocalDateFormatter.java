package util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Класс для форматирования даты из строки в объект LocalDate
 */
@UtilityClass
public class LocalDateFormatter {
    private static final String PATTERN = "yyyy-MM-dd";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    /**
     * Метод, который по строке выдаёт объект LocalDate
     */
    public LocalDate format(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    /**
     * Метод, который проверяет правильную валидацию даты
     */
    public boolean isValid(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }
}
