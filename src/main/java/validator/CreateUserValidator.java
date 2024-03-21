package validator;

import dao.UserDao;
import dto.CreateUserDto;
import entity.User;

import java.util.Optional;


/**
 * Класс для валидации CreateUserDto
 */
public class CreateUserValidator implements Validator<CreateUserDto> {
    /**
     * Реализация singleton
     */
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    /**
     * Зависимость для работы с Dao
     */
    private static final UserDao userDao = UserDao.getInstance();

    /**
     * Данный метод валидирует данные с формы для регистрации и возвращает объект ValidationResult, где есть List с ошибками
     */
    @Override
    public ValidationResult isValid(CreateUserDto object) {

        ValidationResult validationResult = new ValidationResult();

        // теперь нужно написать валидацию
        // какие некорректные данные у нас могут быть
        // 1. Имя пользователя уже занято
        // 2. Почта пользователя уже занята
        // 3. Аватарка не jpg или png формата

        // валидация почты
        Optional<User> checkEmail = userDao.findByEmail(object.getEmail());

        if (checkEmail.isPresent()) {
            // если мы вдруг нашли такой же email
            validationResult.add(Error.of("email.error", "Данная почта уже занята!"));
        }

        // теперь валидация имени
        Optional<User> checkName = userDao.findByName(object.getName());
        if (checkName.isPresent()) {
            validationResult.add(Error.of("name.error", "Данное имя уже занято!"));
        }

        // теперь валидация аватарки
        // нужно проверить что файл типа png/jpg

        String fileName = object.getImage().getSubmittedFileName();
        if (!isFileNameOfPhotoValid(fileName)) {
            validationResult.add(Error.of("image.error", "Изображение должно иметь png/jpg формат!"));
        }

        return validationResult;
    }


    /**
     * Данный метод смотрит на расширение файла и сверяет, чтобы оно было валидным для нас
     */
    private boolean isFileNameOfPhotoValid(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        String expansion = fileName.substring(lastIndex + 1);


        return isInEnum(expansion);
    }

    /**
     * Данный метод проверяет - доступный ли формат для загрузки аватарки
     */
    private boolean isInEnum(String value) {
        try {
            PossibleFormatForImage possibleFormatForImage = PossibleFormatForImage.valueOf(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Данный метод возвращает объект CreateUserValidator (реализация singleton)
     */
    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }

    /**
     * Реализация singleton
     */
    private CreateUserValidator() {
    }
}
