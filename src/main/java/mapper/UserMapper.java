package mapper;

import dto.UserDto;
import entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Данный класс для преобразования из User в UserDto для показа информации о пользователе на страничке
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)  // создание приватного конструктора
public class UserMapper implements Mapper<User, UserDto> {
    /**
     * Реализация singleton
     */
    private static final UserMapper INSTANCE = new UserMapper();

    /**
     * Реализация singleton
     */
    public static UserMapper getInstance() {
        return INSTANCE;
    }

    /**
     * Данный метод преобразует из User в UserDto
     */
    @Override
    public UserDto mapFrom(User object) {
        return UserDto.builder()
                .id(object.getId())
                .name(object.getName())
                .image(object.getImage())
                .birthday(object.getBirthday())
                .email(object.getEmail())
                .role(object.getRole())
                .gender(object.getGender())
                .build();
    }
}
