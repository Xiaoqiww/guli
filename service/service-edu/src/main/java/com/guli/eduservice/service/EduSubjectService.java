package com.guli.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.EduTeacher;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程 服务类
 * </p>
 */
public interface EduSubjectService extends IService<EduSubject> {
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);
}
