package util;

import lombok.experimental.UtilityClass;

/**
 * Данный утилитный класс хранит в себе поля в виде строк, которые доступны пользователю, который не прошёл аутентификацию(публичные страницы)
 */
@UtilityClass
public class UrlPath {
    public static final String LOGIN = "/CoursedmdevServlet_war/login";
    public static final String REGISTRATION = "/CoursedmdevServlet_war/registration";

}
