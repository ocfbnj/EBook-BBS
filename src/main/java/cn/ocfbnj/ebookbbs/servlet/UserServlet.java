package cn.ocfbnj.ebookbbs.servlet;

import cn.ocfbnj.ebookbbs.pro.User;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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


@WebServlet("/userServlet/*")
public class UserServlet extends BasicServlet{
    /*
     *
     * 生成验证码并发送给客户端
     *@param WIDTH   验证码图片宽度
     *@param HEIGHT  验证码图片高度
     *
     */

    private static int WIDTH = 60;
    private static int HEIGHT = 20;

    //生成验证码
    private char[] generateCheckCode(){
        String chars = "0123456789abcdefghijklmnopqrstuvmxyz";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random()*36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    //验证码绘制
    private void drawRands(Graphics g, char[] rands){
        g.setColor(Color.BLACK);
        g.setFont(new Font(null,Font.ITALIC|Font.BOLD,18));

        g.drawString(""+rands[0],1,17);
        g.drawString(""+rands[1],16,15);
        g.drawString(""+rands[2],31,18);
        g.drawString(""+rands[3],46,16);
        // System.out.println(rands);
    }

    //背景绘制
    private void drawBackground(Graphics g){
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0,0,WIDTH,HEIGHT);

        for (int i = 0; i < 120; i++) {
            int x = (int)(Math.random() * WIDTH);
            int y = (int)(Math.random() * HEIGHT);
            int red = (int)(Math.random() * 255);
            int green = (int)(Math.random() * 255);
            int blue = (int)(Math.random() * 255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x,y,1,0);
        }
    }

    public void imgInit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, IOException {
        //在响应客户端之前获取客户端的Session对象
        HttpSession session = req.getSession();

        //设置响应格式
        resp.setContentType("image/jpeg");
        ServletOutputStream sos = resp.getOutputStream();

        resp .setHeader("Pragma","No-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",0);

        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

        char[] rands = generateCheckCode();

        drawBackground(g);
        drawRands(g,rands);

        g.dispose();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image,"JPEG",bos);
        byte[] buf = bos.toByteArray();
        resp.setContentLength(buf.length);

        sos.write(buf);
        bos.close();
        sos.close();
        session.setAttribute("check_code",new String(rands));
    }

    //登录
    public void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, ServletException {


    }

    //注册
    public void regist(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{

    }
}
