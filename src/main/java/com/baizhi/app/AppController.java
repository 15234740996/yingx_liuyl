package com.baizhi.app;

import com.baizhi.Util.AliyunUtil;
import com.baizhi.Util.ImageCodeUtil;
import com.baizhi.common.CommonResult;
import com.baizhi.entity.Category;
import com.baizhi.entity.Video;
import com.baizhi.po.CategoryPo;
import com.baizhi.po.VideoPO;
import com.baizhi.service.CategoryService;
import com.baizhi.service.VideoService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    VideoService videoService;

    @Resource
       CategoryService categoryService;


    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone){

        //生成验证码随机数
        String randomCode = ImageCodeUtil.getSecurityCode();
        System.out.println("手机验证码: "+randomCode);

        String message = null;
        try {
            //发送手机验证码
            message = AliyunUtil.sendPhoneMsg(phone, randomCode);

            return new CommonResult().success(message,phone);
        } catch (Exception e) {
            return new CommonResult().failed(message);
        }
    }

    @RequestMapping("queryByReleaseTime")//视频
    public CommonResult queryByReleaseTime(){
        try {
            List<VideoPO> videoPOS = videoService.queryByReleaseTime();
            return new CommonResult().success(videoPOS);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }


    @RequestMapping("queryAllCategory")//分类
    public CommonResult queryAllCategory(){
        try {
            List<CategoryPo> categories = categoryService.queryAllCategory();
            return new CommonResult().success(categories);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

    @RequestMapping("queryByLikeVideoName")//分类
    public CommonResult queryByLikeVideoName(String content){
        try {
            List<Video> videos = videoService.queryByLikeVideoName(content);
            return new CommonResult().success(videos);
        } catch (Exception e) {
            return new CommonResult().failed();
        }
    }

}
