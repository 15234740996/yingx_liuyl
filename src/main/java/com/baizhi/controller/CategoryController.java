package com.baizhi.controller;

import com.alibaba.druid.util.StringUtils;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(Category category, String oper) {
        //判断这是什么操作
        HashMap<String, Object> map = new HashMap<>();
        if (StringUtils.equals("add", oper)) {
            categoryService.add(category);
        }
        if (StringUtils.equals("edit", oper)) {
            categoryService.update(category);
        }
        if (StringUtils.equals("del", oper)) {
            String message = categoryService.delete(category.getId());
            map.put("message",message);
            map.put("status",200);
        }
        return map;

    }

    @RequestMapping("findAll")
    @ResponseBody
    public Map<String, Object> findAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        List<Category> categories;
        Long counts;

        categories = categoryService.findAll(page, rows);//分页查询的结果
        counts = categoryService.count();//总条数
        System.out.println("1"+counts);

        //总页数
        Long totalPage = counts % rows == 0 ? counts / rows : (counts / rows) + 1;
        map.put("page",page);
        map.put("total",totalPage);
        map.put("records",counts);
        map.put("rows",categories);
        System.out.println(rows);
        return map;


    }


    /*二级类别*/
    @RequestMapping("findAlltwo")
    @ResponseBody
    public Map<String, Object> findAlltwo(Integer page, Integer rows,String id) {
        HashMap<String, Object> map = new HashMap<>();
        List<Category> categories;
        Long counts;

        categories = categoryService.findAlltwo(page, rows,id);//分页查询的结果
        counts = categoryService.counttwo(id);//总条数
        System.out.println("er"+counts);

        //总页数
        Long totalPage = counts % rows == 0 ? counts / rows : (counts / rows) + 1;
        map.put("page",page);
        map.put("total",totalPage);
        map.put("records",counts);
        map.put("rows",categories);
        System.out.println(rows);
        return map;


    }


/*一级类别的下拉列表*/
    @RequestMapping("show")
    public void findAll(HttpServletResponse response) throws IOException {
        //查询所有
        List<Category> categories = categoryService.show();
        //html   <select><option value="1">研发部</option>....</select>
        StringBuilder sb = new StringBuilder();
        sb.append("<select>");
        //遍历部分 构建option标签
        categories.forEach(category->{
            sb.append("<option value="+category.getId()+">"+ category.getCate_name() +"</opion>");
        });
        sb.append("</select>");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        response.getWriter().println(sb.toString());
    }





}
