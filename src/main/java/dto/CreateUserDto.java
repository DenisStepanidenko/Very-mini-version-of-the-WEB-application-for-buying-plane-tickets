package dto;

import jakarta.servlet.http.Part;
import lombok.Builder;
import lombok.Value;

/**
 * Данный класс dto предназначается для создания объекта из регистрации
 */
@Value
@Builder
public class CreateUserDto {
    String name;
    String birthday;
    Part image;
    String email;
    String password;
    String role;
    String gender;
}
