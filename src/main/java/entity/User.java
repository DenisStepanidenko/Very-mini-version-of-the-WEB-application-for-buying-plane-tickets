package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * Класс, который соответствует сущности User в БД
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String email;
    private String password;
    private Role role;
    private Gender gender;
    private String image;
}
