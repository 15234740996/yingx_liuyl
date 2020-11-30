package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Resource
    HttpSession session;

    @Override//Ajax
    public HashMap<String, Object> login(Admin admin, String enCode) {
        HashMap<String, Object> map = new HashMap<>();

        //获取验证码
        String imageCode = (String) session.getAttribute("ServerCode");
        System.out.println("系统的"+imageCode);

        //判断验证码
        if(imageCode.equals(enCode)){
            System.out.println("我输入的"+enCode);
            Admin admins = adminDao.login(admin.getUsername());
            System.out.println("11111111"+admins);
            if(admins!=null){
                if(admins.getPassword().equals(admin.getPassword())){
                    session.setAttribute("admin",admins);
                    map.put("message","登陆成功");
                    map.put("status","200");
                }else{
                    map.put("message","密码错误");
                    map.put("status","201");
                }
            }else{
                map.put("message","用户不存在");
                map.put("status","201");
            }
        }else{
            map.put("message","验证码不正确");
            map.put("status","201");
        }
        return map;
    }
    }


    //普通方法

   /* @Override
    public String login(String enCode,String username, String password, HttpServletRequest request) {
        String message = null;
        HttpSession session = request.getSession();

        String code = (String) session.getAttribute("ServerCode");
        System.out.println(code);
        System.out.println(enCode);
        if (code.equals(enCode)) {
            Admin admin = adminDao.login(username);
            if (admin != null) {

                if (admin.getPassword().equals(password)) {
                    message = "success";

                    System.out.println(admin);
                    session.setAttribute("admin", admin);


                } else {
                    message = "密码错误";
                }


            } else {
                message = "用户不存在";
            }


        } else {
            message = "验证码错误";
        }
        return message;
    }*/


