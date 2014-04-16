package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.Ioc;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

public class ValidationCode {
	
    @At("/admin/getValidationCode")
    public void getValidationCode(HttpServletRequest req,HttpServletResponse rep) throws IOException{
    	
    	BufferedImage img = new BufferedImage(92, 26,BufferedImage.TYPE_INT_RGB);
        // 得到该图片的绘图对象

        Graphics g = img.getGraphics();

        Random r = new Random();

        Color c = new Color(228, 228, 230);

        g.setColor(c);

        // 填充整个图片的颜色

        g.fillRect(0, 0, 92,26);
        
        // 随机产生20条干扰线，使图象中的认证码不易被其它程序探测到。  
        g.setColor(Color.BLACK); 
        // 创建一个随机数生成器类  
        Random random = new Random();  
        for (int i = 0; i < 20; i++) {  
            int x  = random.nextInt(92);	//代码意为产生一个范围在width以内的随机数    即 产生的随机干扰线的x坐标必须在图片的宽度以内    
            int y  = random.nextInt(26); //释意同上
            int xl = random.nextInt(12);  	//
            int yl = random.nextInt(12);  
            //要绘制一条线段    首先得有两个点  而每个点在整个图片中  是以坐标来确定其位置的     因此drawLine的前两个参数我们可以看成是一个线段中第一个点的坐标    后两个参数是第二个点的坐标
            g.drawLine(x, y, x + xl, y + yl); //将两个坐标点练成一条线
        }  
        
        // 向图片中输出数字和字母

        StringBuffer sb = new StringBuffer();

        char[] ch = "abcdefghijkmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();

        int index, len = ch.length;

        for (int i = 0; i < 4; i ++) {

               index = r.nextInt(len);

               g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));

               g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));// 输出的字体和大小                 

               g.drawString("" + ch[index], (i * 15) + 12, 22);//写什么数字，在图片的什么位置画

               sb.append(ch[index]);

        }  
        req.getSession().setAttribute("rand", sb.toString()); //把随机数放在session中      
        ImageIO.write(img, "JPG", rep.getOutputStream());
    }
    
	/**
	 * 获取验证码
	 */
	@At("/getValidateCode")
	@Ok("raw")
	public String getValidateCode(Ioc ioc,HttpServletRequest req){
		return req.getSession().getAttribute("rand").toString();
	}
    
}
