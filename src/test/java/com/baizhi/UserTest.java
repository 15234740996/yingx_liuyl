package com.baizhi;

import com.baizhi.entity.YxUser;
import com.baizhi.service.YxUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest {

    @Autowired
    private YxUserService yxUserService;
    @Test
    public  void  show(){
        List<YxUser> yxUsers = yxUserService.showAll(1,2);
        for (YxUser yxUser : yxUsers) {
            System.out.println(yxUser);
        }
    }
}
