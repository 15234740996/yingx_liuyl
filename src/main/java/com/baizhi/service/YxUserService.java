package com.baizhi.service;

import com.baizhi.entity.YxUser;

import java.util.List;

public interface YxUserService {
    List<YxUser> showAll(Integer page,Integer rows);//分页查所有

     Long count();


    public void updateStatus(String start, String id);

    List<YxUser> findAll();

}
