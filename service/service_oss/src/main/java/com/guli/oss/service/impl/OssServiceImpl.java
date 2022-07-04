package com.guli.oss.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.guli.oss.service.OssService;
import com.guli.oss.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String upLoadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //上传到oss文件路径和文件名称 /aa/bb/1.jpg
            String fileName = file.getOriginalFilename();
            // 调用oss方法实现上传
            ossClient.putObject(bucketName, fileName, inputStream);
            //关闭ossClient
            ossClient.shutdown();
            //把上上传之后的文件路径返回
            //https://online-edu-011.oss-cn-hangzhou.aliyuncs.com/010121.png
            String url="https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
