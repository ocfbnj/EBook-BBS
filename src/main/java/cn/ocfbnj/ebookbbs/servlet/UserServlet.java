package cn.ocfbnj.ebookbbs.servlet;

import cn.ocfbnj.ebookbbs.domain.CheckCode;
import cn.ocfbnj.ebookbbs.domain.ResultInfo;
import cn.ocfbnj.ebookbbs.domain.User;
import cn.ocfbnj.ebookbbs.service.UserService;
import cn.ocfbnj.ebookbbs.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
/*
 * 类：public class UserServlet
 *
 * 访问路径：
 * " /userServlet/ "+ 方法名
 *
 * 生成验证码：    public void imgInit
 *      登录：    public void login
 *      注册：    public void regist
 *
 */


@WebServlet("/user/*")
public class UserServlet extends BasicServlet {
    UserService userService = new UserServiceImpl();
    ResultInfo resultInfo = new ResultInfo();

    /*
     *
     * 生成验证码并发送给客户端
     *@param WIDTH   验证码图片宽度
     *@param HEIGHT  验证码图片高度
     *
     */

    private static final int WIDTH = 60;
    private static final int HEIGHT = 20;

    //生成验证码
    private char[] generateCheckCode() {
        String chars = "0123456789abcdefghijklmnopqrstuvmxyz";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    //验证码绘制
    private void drawRands(Graphics g, char[] rands) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);
        // System.out.println(rands);
    }

    //背景绘制
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }

    public void imgInit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //在响应客户端之前获取客户端的Session对象
        HttpSession session = req.getSession();

        //设置响应格式
        resp.setContentType("image/jpeg");
        ServletOutputStream sos = resp.getOutputStream();

        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        char[] rands = generateCheckCode();

        drawBackground(g);
        drawRands(g, rands);

        g.dispose();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", bos);
        byte[] buf = bos.toByteArray();
        resp.setContentLength(buf.length);

        sos.write(buf);
        bos.close();
        sos.close();
        session.setAttribute("check_code", new String(rands));
    }

    public void check(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();

        try {
            CheckCode code = new CheckCode();
            BeanUtils.populate(code, map);

            System.out.println(code);

            String check_code = (String) req.getSession().getAttribute("check_code");
            System.out.println(check_code);
            if (code.getValue().equals(check_code)) {
                resultInfo.setFlag(true);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMessage("验证码错误");
            }

            writeValue(resp, resultInfo);
        } catch (IllegalAccessException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
    }

    //登录
    public void login(HttpServletRequest req, HttpServletResponse resp) {


    }

    //注册
    public void register(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String[]> map = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(user);

        boolean ok = userService.register(user);
        if (ok) {
            resultInfo.setFlag(true);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMessage("注册失败");
        }

        try {
            writeValue(resp, resultInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
