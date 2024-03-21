package mapper;

import dto.CreateUserDto;
import entity.Gender;
import entity.Role;
import entity.User;
import util.LocalDateFormatter;

/**
 * Данный класс мапит объект CreateUserDto в объект User для дальнейшего сохранения в БД
 */
public class CreateUserMapper implements Mapper<CreateUserDto, User> {
    /**
     * Базовая папочка, где лежат все фотографии пользователей на сервере
     */
    private static final String IMAGE_FOLDER = "users/";

    /**
     * Реализация singleton
     */
    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    /**
     * Реализация singleton
     */
    private CreateUserMapper() {
    }

    /**
     * Реализация singleton
     */
    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }


    /**
     * Метод, который производит необходимый маппинг
     */
    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .image(IMAGE_FOLDER + object.getName() + "/" + object.getImage().getSubmittedFileName())
                .password(object.getPassword())
                .gender(Gender.valueOf(object.getGender()))
                .role(Role.valueOf(object.getRole()))
                .birthday(LocalDateFormatter.format(object.getBirthday()))
                .build();
    }
}
