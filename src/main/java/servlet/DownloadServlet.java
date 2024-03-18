package servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-Disposition" , "attachment; filename= \"filename.jpeg\"");
        resp.setContentType("image/jpeg");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        //DownloadServlet.class.getClassLoader().getResourceAsStream("test_png.jpg");
        try(OutputStream writer = resp.getOutputStream();
            InputStream stream = DownloadServlet.class.getClassLoader().getResourceAsStream("test_png.jpg")){
            assert stream != null;
            writer.write(stream.readAllBytes());
        }
    }



}
