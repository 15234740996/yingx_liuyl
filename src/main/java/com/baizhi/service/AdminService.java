package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AdminService {
    HashMap<String, Object> login(Admin admin, String enCode);
}
