package com.guli.oss.controller;

import com.atguigu.commonutils.R;
import com.guli.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("eduoss/fileoss")
@CrossOrigin
@EnableSwagger2
public class OssController {
    @Autowired
    OssService ossService;

    public R upLoadOssFile(MultipartFile file) {
        //获取上传文件
        //返回上传到oss的路径
        String url =ossService.upLoadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
