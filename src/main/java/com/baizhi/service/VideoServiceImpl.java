package com.baizhi.service;

import com.baizhi.annotcation.AddLog;
import com.baizhi.annotcation.DelCahe;
import com.baizhi.annotcation.RedisLog;
import com.baizhi.dao.VideoMapper;


import com.baizhi.entity.Video;
import com.baizhi.entity.VideoExample;

import com.baizhi.Util.AliyunOSSUtil;
import com.baizhi.po.VideoPO;
import org.apache.ibatis.session.RowBounds;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Resource
    VideoMapper videoMapper;

    @Resource
    HttpSession session;

    @Override
    public HashMap<String, Object> queryAllPage(Integer page, Integer rows) {

        HashMap<String, Object> map = new HashMap<>();

        //当前页   page
        map.put("page", page);

        //总条数   records
        VideoExample example = new VideoExample();
        int records = videoMapper.selectCountByExample(example);
        map.put("records", records);

        //总页数   total
        //总页数=总条数/每页展示条数   有余数加一页
        Integer total = records / rows == 0 ? records / rows : records / rows + 1;
        map.put("total", total);

        //数据    rows   参数：略过几条，要几条
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Video> videos = videoMapper.selectByRowBounds(new Video(), rowBounds);
        map.put("rows", videos);

        return map;
    }
    @AddLog("添加视频")
    @DelCahe
    @Override//添加方法
    public String add(Video video) {

        video.setId(UUID.randomUUID().toString());
        video.setUploadTime(new Date());

        System.out.println("video  service " + video);
        //执行添加
        videoMapper.insertSelective(video);

        //将id返回
        return video.getId();
    }
    @AddLog("修改视频")
    @DelCahe
    @Override//修改方法
    public void update(Video video) {

        if (video.getVideoPath() == "") {
            video.setVideoPath(null);
        }
        System.out.println("修改：" + video);
        videoMapper.updateByPrimaryKeySelective(video);

        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(video.getId());
        Video videos = videoMapper.selectOneByExample(example);
    }

    @Override
    public void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request) {

        //1.获取文件上传的路径  根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload/video");

        //2.判断文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();  //创建文件夹
        }
        //获取文件名
        String filename = videoPath.getOriginalFilename();

        //创建一个新的名字    原名称-时间戳  10.jpg-1590390153130
        String newName = new Date().getTime() + "-" + filename;

        //3.文件上传
        try {
            videoPath.transferTo(new File(realPath, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //4.修改图片路径
        //修改的条件
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(id);

        Video video = new Video();
        video.setCoverPath("aaaa"); //设置封面
        video.setVideoPath("aaaaaa"); //设置视频地址

        //修改
        videoMapper.updateByExampleSelective(video, example);
    }

    @Override
    public void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request) {

        //1.视频上传至阿里云   字节数组
        //获取文件名
        String filename = videoPath.getOriginalFilename();
        //拼接时间戳    1606185263426-草原.mp4
        String newName=new Date().getTime()+"-"+filename;

        //拼接视频文件夹
        String videoName="video/"+newName;

        /*
         * 上传视频至阿里云
         * 参数:
         *   videoPath: MultipartFile类型的文件
         *   bucketName:存储空间名
         *   objectName:文件名
         * */
        AliyunOSSUtil.uploadFileByte(videoPath,"akaliking",videoName);


        //截取文件名
        String[] split = newName.split("\\.");
        //拼接图片名
        String coverName="cover/"+split[0]+".jpg";

        /*
         * 2.截取视频第一帧
         * 参数:
         *   bucketName:存储空间名
         *   videoName:视频名  文件夹
         *   coverName:封面名
         * */
        AliyunOSSUtil.interceptVideoCover("akaliking", videoName,coverName);


        //4.修改视频的信息
        Video video = new Video();
        video.setId(id);

        video.setVideoPath("https://akaliking.oss-cn-beijing.aliyuncs.com/"+videoName);
        video.setCoverPath("https://akaliking.oss-cn-beijing.aliyuncs.com/"+coverName);

        videoMapper.updateByPrimaryKeySelective(video);
    }

    @Override
    public void delete(Video video) {

        //根据id查询数据
        Video videos = videoMapper.selectByPrimaryKey(video);

        String videoPath = videos.getVideoPath().replace("https://akaliking.oss-cn-beijing.aliyuncs.com/", "");
        String coverPath = videos.getCoverPath().replace("https://akaliking.oss-cn-beijing.aliyuncs.com/", "");

        //2.删除视频   432425435-xxx.mp4
        AliyunOSSUtil.deleteFile("akaliking",videoPath);
        //3.删除封面
        AliyunOSSUtil.deleteFile("akaliking",coverPath);
        //1.删除数据
        videoMapper.deleteByPrimaryKey(video);

    }

    @Override
    public List<VideoPO> queryByReleaseTime() {
        List<VideoPO> videoPOList = videoMapper.queryByReleaseTime();

        for (VideoPO videoPO : videoPOList) {
            //获取视频id
            String id = videoPO.getId();

            //根据视频id redis 查询点赞数
            videoPO.setLikeCount(8);
        }

        return videoPOList;
    }

    @Override
    public List<Video> queryByLikeVideoName(String content) {
        return videoMapper.queryByLikeVideoName(content);
    }
}
