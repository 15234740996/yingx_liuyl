package com.baizhi.dao;

import com.baizhi.entity.YxUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YxUserDao {

    List<YxUser> showAll(@Param("start")Integer start,@Param("rows")Integer rows);//分页查所有

    Long count();//数量

    void updateStatus(@Param("start") String start,@Param("id") String id);//根据id修改状态


    List<YxUser> findAll();//导出查所有

}

