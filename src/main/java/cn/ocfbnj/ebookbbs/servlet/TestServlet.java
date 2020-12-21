package cn.ocfbnj.ebookbbs.servlet;

import cn.ocfbnj.ebookbbs.domain.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试用，之后删掉
 * http://localhost:8080/test/login
 */
@WebServlet("/test/*")
public class TestServlet extends BasicServlet {
    public void login(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User();
        user.setId(123456);
        user.setUsername("ocfbnj");

        try {
            writeValue(resp, user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
