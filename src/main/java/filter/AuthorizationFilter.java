package filter;

import dto.UserDto;
import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UrlPath;

import java.io.IOException;
import java.util.Set;


/**
 * Данный фильтр перехватывает запрос и проверяет - есть ли в Session объект User, иными словами проверяется прошёл ли пользователь аутентификацию или проверяет, что страничка на которой он находится - публичная и доступная всем без аутентификации
 */
@WebFilter("/*")
public class AuthorizationFilter implements Filter {
    /**
     * Список публичных страниц, для которых не нужна аутентификация
     */
    private static final Set<String> PUBLIC_PATH = Set.of(UrlPath.LOGIN, UrlPath.REGISTRATION);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(requestURI) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/CoursedmdevServlet_war/login");
        }
    }

    /**
     * Проверяется есть ли данный пользователь в текущей Session
     */
    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        UserDto user = (UserDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    /**
     * Проверяется публичный ли путь
     */
    private boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(requestURI::startsWith);
    }


}
