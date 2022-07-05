package com.guli.oss.controller;

import com.atguigu.commonutils.R;
import com.guli.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("eduoss/fileoss")
@CrossOrigin
@Api("头像上传")
public class OssController {
    @Autowired
    OssService ossService;

    @ApiModelProperty(name = "上传头像")
    @PostMapping
    public R upLoadOssFile(@RequestParam MultipartFile file) {
        //获取上传文件
        //返回上传到oss的路径
        String url =ossService.upLoadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
