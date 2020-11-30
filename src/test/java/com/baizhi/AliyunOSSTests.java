package com.baizhi;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.URL;
import java.util.List;

@SpringBootTest
class AliyunOSSTests {


    @Test
    void addes(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G7GPEdeRf1tuXmeFD8m";
        String accessKeySecret = "IzG0uQl3prdBjuY4yrhqSrcVl1mAfM";
        String bucketName="yingx-2005";  //存储空间名  yingx-2005
        String objectName="aaaa/草原.jpg";  //保存的文件名   1.MP4  aaa.mp4
        //String localFile="C:\\Users\\Administrator\\Desktop\\video\\草原.mp4";   //本地文件位置
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传网络流。
        InputStream inputStream = null;
        try {
            inputStream = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605778905901&di=956684ceb3537184b563fc7c7f55fee8&imgtype=0&src=http%3A%2F%2Fpic8.nipic.com%2F20100703%2F145234_114912056232_2.jpg").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    void adds(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G7GPEdeRf1tuXmeFD8m";
        String accessKeySecret = "IzG0uQl3prdBjuY4yrhqSrcVl1mAfM";
        String bucketName="yingx-2005";  //存储空间名  yingx-2005
        String objectName="草原.mp4";  //保存的文件名   1.MP4  aaa.mp4
        String localFile="C:\\Users\\Administrator\\Desktop\\video\\草原.mp4";   //本地文件位置

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(localFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    void addFile(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G7GPEdeRf1tuXmeFD8m";
        String accessKeySecret = "IzG0uQl3prdBjuY4yrhqSrcVl1mAfM";
        String bucketName="yingx-2005";  //存储空间名  yingx-2005
        String objectName="huohua.jpg";  //保存的文件名   1.MP4  aaa.mp4
        String localFile="C:\\Users\\Administrator\\Desktop\\video\\huohua.jpg";   //本地文件位置

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(localFile));

        // 上传文件。
        ossClient.putObject(putObjectRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    @Test
    void testqueryPhone(){


        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G7GPEdeRf1tuXmeFD8m";
        String accessKeySecret = "IzG0uQl3prdBjuY4yrhqSrcVl1mAfM";
        String bucketName = "yingx205";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建存储空间。
        ossClient.createBucket(bucketName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    void queryAllBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G7GPEdeRf1tuXmeFD8m";
        String accessKeySecret = "IzG0uQl3prdBjuY4yrhqSrcVl1mAfM";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();

        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    void deleteBucket(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI4G7GPEdeRf1tuXmeFD8m";
        String accessKeySecret = "IzG0uQl3prdBjuY4yrhqSrcVl1mAfM";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除存储空间。
        ossClient.deleteBucket("yingx205");

        // 关闭OSSClient。
        ossClient.shutdown();
    }


}
