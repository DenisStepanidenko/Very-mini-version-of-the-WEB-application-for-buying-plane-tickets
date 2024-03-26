package util;

import lombok.experimental.UtilityClass;

/**
 * Данный утилитный класс возвращает полное путь до jsp файла
 */
@UtilityClass
public class JspHelper {
    public static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    /**
     * Данный метод возвращает полный путь до файла с именем jspName
     */
    public static String getPath(String jspName){
        return String.format(JSP_FORMAT , jspName);
    }
}
