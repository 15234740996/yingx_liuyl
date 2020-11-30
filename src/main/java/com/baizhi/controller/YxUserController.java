package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.druid.util.StringUtils;
import com.baizhi.Util.AliyunUtil;
import com.baizhi.Util.ImageCodeUtil;
import com.baizhi.entity.Emp;
import com.baizhi.entity.YxUser;
import com.baizhi.service.YxUserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("yxuser")
public class YxUserController {
    @Autowired
    private YxUserService yxUserService;

    @RequestMapping("showAll")
    @ResponseBody
    public Map<String, Object> showAll(Integer page, Integer rows) {
        HashMap<String, Object> result = new HashMap<>();
        List<YxUser> yxUsers;
        Long counts;

        yxUsers = yxUserService.showAll(page, rows);//分页查询的结果

        counts = yxUserService.count();//总条数

        //总页数
        Long totalPage = counts % rows == 0 ? counts / rows : (counts / rows) + 1;
        result.put("page", page);
        result.put("total", totalPage);
        result.put("records", counts);
        result.put("rows", yxUsers);
        return result;


    }

    @RequestMapping("updateStatus")//更新状态
    public void updateStatus(String start, String id) {

        yxUserService.updateStatus(start, id);
    }

    @RequestMapping("exit")
    public String exit(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "redirect:/login/login.jsp";

    }


    @RequestMapping("code")
    @ResponseBody
    public Map<String, Object> code(String phone) {
        String phoneMsg = null;
        HashMap<String, Object> map = null;
        try {
            //随机获取验证码
            String code = ImageCodeUtil.getSecurityCode();
            //调用工具类发送验证码
            phoneMsg = AliyunUtil.sendPhoneMsg(phone, code);
            map = new HashMap<>();
            map.put("message", phoneMsg);
            map.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", phoneMsg);
            map.put("status", 201);
        }
        return map;
    }


    /*导出查所有文件夹----------------------------------------------------------------*/
    @RequestMapping("poi")
    @ResponseBody
    public Map<String, Object> poi() {
        HashMap<String, Object> map = new HashMap<>();

        String message = null;

        List<YxUser> all = yxUserService.findAll();
        ExportParams exportParams = new ExportParams("计算机一班学生", "学生");
        for (YxUser user : all) {
            String img = user.getPic_img();
            String path = "D:\\IDEA2019\\idearoom2\\yingx_liuyl\\src\\main\\webapp\\bootstrap\\img\\";
            String newPath = path + img;
            user.setPic_img(newPath);
        }


        //导出工具   参数:导出的参数,对应的实体类,导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, YxUser.class, all);

        try {
            //导出
            workbook.write(new FileOutputStream(new File("R:\\DcTable\\Easypois.xls")));
            message = "success";
            map.put("message", message);
        } catch (IOException e) {
            e.printStackTrace();
            message = "error";
            map.put("message", message);

        }

        return map;
    }





    @RequestMapping("doi")
    @ResponseBody
    public Map<String, Object> doi() {
        Map<String,Object> map = new HashMap<>();

        //导入设置的参数
        ImportParams params = new ImportParams();
        params.setTitleRows(1);  //标题所占行
        params.setHeadRows(1);   //表头所占行

        //导入
        List<YxUser> users = null;
        String message = null;
        try {
            users = ExcelImportUtil.importExcel(new File("R:\\DcTable\\Easypois.xls"), YxUser.class, params);
            message = "success";
            map.put("message",message);
        } catch (Exception e) {
            message = "error";
            map.put("message",message);
            e.printStackTrace();
        }

        users.forEach(user -> System.out.println(user));
        return map;
    }
}
