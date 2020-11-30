package com.baizhi.service;

import java.util.HashMap;

public interface LogService {
    //查所有分页
    HashMap<String, Object> queryAllPage(Integer page, Integer rows);
}
