package cn.ocfbnj.ebookbbs.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BasicServlet extends HttpServlet {
    private static final long serialVersionUID = 7560237200720263402L;

    /**
     * 将obj序列化为JSON，然后写回客户端。
     *
     * @param resp servlet返回给客户端的响应
     * @param obj  要序列化的对象
     */
    public void writeValue(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        JSON.writeJSONString(writer, obj);
        writer.close();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        uri = uri.substring(uri.lastIndexOf('/') + 1);

        Method method;
        try {
            method = this.getClass().getMethod(uri, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            super.service(req, resp);

            return;
        }

        try {
            method.invoke(this, req, resp);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
