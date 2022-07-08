package com.guli.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.eduservice.entity.EduSubject;
import com.guli.eduservice.entity.excel.ExcelSubjectData;
import com.guli.eduservice.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    //SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;
    //通过有参构造将值传过来
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }
    public SubjectExcelListener() {}

    //读取excel内容 一行一行读取
    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {
        if (excelSubjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        //一行一行读取，每次读取俩个值，第一个值一级分类，第二个值二级分类
        EduSubject eduSubject = existOneSubject(subjectService, excelSubjectData.getOneSubjectName());
        if ( eduSubject==null){
            eduSubject = new EduSubject();
            eduSubject.setParentId("0");
            eduSubject.setTitle(excelSubjectData.getOneSubjectName());
            subjectService.save(eduSubject);
        }
        EduSubject subject = existTwoSubject(subjectService, excelSubjectData.getOneSubjectName(), eduSubject.getId());
        if ( subject==null){
            subject = new EduSubject();
            subject.setParentId(eduSubject.getId());
            subject.setTitle(excelSubjectData.getTwoSubjectName());
            subjectService.save(subject);
        }
    }


    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("title",name)
                        .eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;
    }
    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String parentId) {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", name)
                .eq("parent_id", parentId);
        EduSubject one = eduSubjectService.getOne(queryWrapper);
        return one;

    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    @Override
    public boolean hasNext(AnalysisContext context) {
        return true;
    }
}
