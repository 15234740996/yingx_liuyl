package com.baizhi.controller;

import com.baizhi.Util.ImageCodeUtil;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;//控制业务层

    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(Admin admin, String enCode) {

        return adminService.login(admin, enCode);


    }

    //验证码
    @RequestMapping("creatImg")
    public String creatImg(HttpServletResponse response, HttpServletRequest request) throws IOException {
        // 1 获取验证码随机字符
        String code = ImageCodeUtil.getSecurityCode();
        System.out.println("验证码:" + code);
        BufferedImage bufferedImage = ImageCodeUtil.createImage(code);

        ServletOutputStream out = response.getOutputStream();
        // 2 使用响应输出流 把bufferedImage 输出到 client
        ImageIO.write(bufferedImage, "gif", out);


        // 3 把随机验证码，存入session作用域
        HttpSession session = request.getSession(true);
        session.setAttribute("ServerCode", code);
        return null;
    }
}
