package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.VideoPO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoService {

    HashMap<String, Object> queryAllPage(Integer page, Integer rows);

    String add(Video video);



    void update(Video video);


    //将视频数据上传至本地
    void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request);


    void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request);

    void delete(Video video);

    List<VideoPO> queryByReleaseTime();

    //模糊查询
    List<Video> queryByLikeVideoName(String content);

}
