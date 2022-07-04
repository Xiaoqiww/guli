package com.guli.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 当项目启动时，读取配置文件中的内容
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    /**
     *  读取配置文件中的内容
     */
    @Value("${aliyun.oss.file.endpoint}")
    private  String endPoint;
    @Value("${aliyun.oss.file.keyid}")
    private  String keyId;
    @Value("${aliyun.oss.file.keysecret}")
    private  String keySecret;
    @Value("${aliyun.oss.file.bucketname}")
    private  String bucketName;

    /**
     *  定义公开静态常量
     */
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endPoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
