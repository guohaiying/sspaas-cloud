package com.sspaas.sspaascloud.controller.commons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 生成登陆页验证码
 *
 */
@Controller
public class CodeController {
  private int width = 90;
  private int height = 30;
  private int codeCount = 4;
  
  private int xx = 15;
  private int fontHeight = 25;
  private int codeY = 25;
  char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
      'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

  @RequestMapping("/code")
  public void getCode(HttpServletResponse resp, HttpSession httpSession)
      throws IOException {

    BufferedImage buffImg = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics gd = buffImg.getGraphics();
    Random random = new Random();
    gd.setColor(Color.WHITE);
    gd.fillRect(0, 0, width, height);
    Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
    gd.setFont(font);
    gd.setColor(Color.BLACK);
    gd.drawRect(0, 0, width - 1, height - 1);
    gd.setColor(Color.BLACK);
    for (int i = 0; i < 45; i++) {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      gd.drawLine(x, y, x + xl, y + yl);
    }
    StringBuffer randomCode = new StringBuffer();
    int red = 0, green = 0, blue = 0;
    for (int i = 0; i < codeCount; i++) {
      String code = String.valueOf(codeSequence[random.nextInt(36)]);
      red = random.nextInt(255);
      green = random.nextInt(255);
      blue = random.nextInt(255);
      gd.setColor(new Color(red, green, blue));
      gd.drawString(code, (i + 1) * xx, codeY);
      randomCode.append(code);
    }
    httpSession.setAttribute("sysLoginCode", randomCode.toString());
    resp.setHeader("Pragma", "no-cache");
    resp.setHeader("Cache-Control", "no-cache");
    resp.setDateHeader("Expires", 0);
    resp.setContentType("image/jpeg");
    ServletOutputStream sos = resp.getOutputStream();
    ImageIO.write(buffImg, "jpeg", sos);
    sos.close();
  }
  
}
