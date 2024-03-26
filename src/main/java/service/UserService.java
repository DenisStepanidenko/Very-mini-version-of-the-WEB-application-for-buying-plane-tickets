package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import entity.User;
import exception.ValidationException;
import lombok.SneakyThrows;
import mapper.CreateUserMapper;
import mapper.UserMapper;
import validator.CreateUserValidator;
import validator.ValidationResult;

import java.util.Optional;

/**
 * Сервис, который занимается бизнес логикой, связанной с БД с пользователями
 */
public class UserService {
    /**
     * Путь до папки с фотографиями на сервере
     */
    private static final String IMAGE_FOLDER = "users/";
    /**
     * Реализация singleton
     */
    private static final UserService INSTANCE = new UserService();
    /**
     * Зависимость для работы с createUserValidator
     */
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    /**
     * Зависимость для работы с userDao
     */
    private final UserDao userDao = UserDao.getInstance();
    /**
     * Зависимость для работы с createUserMapper
     */
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    /**
     * Зависимость для работы с imageService
     */
    private final ImageService imageService = ImageService.getInstance();

    /**
     * Зависимость для работы с userMapper
     */
    private final UserMapper userMapper = UserMapper.getInstance();


    /**
     * Данный метод пытается найти пользователя по email и password, и вернуть Optional<UserDto> - для загрузки потом в сессию
     */
    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }


    /**
     * Данный метод сохраняет в dao пользователя, если все данные валидны, если нет - выбрасывает исключение, которое сверху обрабатывается
     */
    @SneakyThrows
    public Integer create(CreateUserDto userDto) {
        // валидация пользователя
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        if (!validationResult.isValid()) {
            // если валидация не прошла, то выбрасываем исключение
            throw new ValidationException(validationResult.getErrors());
        }

        // если валидация прошла, то все данные корректны, можем создавать пользователя

        // мапим объект CreateUserDto в user для сохранения в БД
        User user = createUserMapper.mapFrom(userDto);

        // сохраняем аватарку на сервер (в моём случае на диск моего компьютера)
        imageService.upload(user.getImage(), userDto.getImage().getInputStream());

        // сохраняем в БД нашу текущую сущность
        userDao.save(user);


        // возвращаем id вновь добавленного пользователя
        return user.getId();
    }

    /**
     * Реализация singleton
     */
    public static UserService getInstance() {
        return INSTANCE;
    }
}
