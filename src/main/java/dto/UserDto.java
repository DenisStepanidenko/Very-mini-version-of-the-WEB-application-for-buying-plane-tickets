package dto;

import entity.Gender;
import entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

/**
 * Класс, который используется для показа информации о пользователе
 */
@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    LocalDate birthday;
    String email;
    String image;
    Role role;
    Gender gender;
}
