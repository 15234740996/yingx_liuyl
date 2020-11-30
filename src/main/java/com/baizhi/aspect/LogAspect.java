package com.baizhi.aspect;

import com.baizhi.annotcation.AddLog;
import com.baizhi.dao.LogMapper;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class LogAspect {

    @Resource
    HttpServletRequest request;
    @Autowired
    private LogMapper logmapper;


    @Around("@annotation(com.baizhi.annotcation.AddLog)")//切自定义注解
    public Object addLog(ProceedingJoinPoint pro){


        /*谁在什么时间做了什么操作是否成功*/

        //获取用户数据
        Admin admin = (Admin) request.getSession().getAttribute("admin");


        //获取方法名
        String methodName = pro.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) pro.getSignature();
        Method method = signature.getMethod();

        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解对应的属性值
        String value = addLog.value();

        String message=null;
        Object result =null;
        //放行方法
        try {
            result = pro.proceed();
            System.out.println(result);


            message="success";
        } catch (Throwable throwable) {
            message="error";
        }

        Log log = new Log(UUID.randomUUID().toString(),admin.getUsername(),new Date(),methodName+" ("+value+")",message);

        logmapper.insert(log);
        System.out.println("数据入库"+log);


        return result;
    }


}
