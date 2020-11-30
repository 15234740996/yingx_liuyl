package com.baizhi.controller;

import com.baizhi.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("log")
public class LogController {


    @Resource
    public LogService logService;


    @ResponseBody//查所有
    @RequestMapping("queryAllPage")
    public HashMap<String, Object> queryAllPage(Integer page, Integer rows) {
        HashMap<String, Object> map = logService.queryAllPage(page, rows);
        return map;
    }


}
