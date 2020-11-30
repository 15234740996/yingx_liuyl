package com.baizhi;

import com.baizhi.dao.CategoryDao;
import com.baizhi.po.CategoryPo;
import com.baizhi.service.AdminService;
import com.baizhi.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@SpringBootTest//test测试类
class YingxLiuylApplicationTests {

    @Autowired//注解
    private AdminService adminService;
    @Autowired
    private CategoryDao categoryDao;

    @Resource
    private CategoryService categoryService;

    @Test//登录方法
    void login(HttpServletRequest request) {
        //String lbw = adminService.login("lbw", "1", request);
        //System.out.println(lbw);


    }
 @Test
 public  void  show(){
//     System.out.println(categoryDao.counttwo("0f8927bf-c513-494b-a4ff-404a14d08ed7"));
     List<CategoryPo> categoryPos = categoryService.queryAllCategory();
     categoryPos.forEach(cate-> System.out.println(cate));
 }

}
