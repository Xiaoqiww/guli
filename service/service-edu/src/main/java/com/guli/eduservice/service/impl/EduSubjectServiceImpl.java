package com.guli.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.excel.ExcelSubjectData;
import com.guli.eduservice.listener.SubjectExcelListener;
import com.guli.eduservice.mapper.EduSubjectMapper;
import com.guli.eduservice.service.EduSubjectService;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Override
    //传参增加eduSubjectService，listener里面增加eduSubjectService有参构造，在new SubjectExcelListener时将service注入
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream stream = file.getInputStream();
            EasyExcel.read(stream, ExcelSubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
