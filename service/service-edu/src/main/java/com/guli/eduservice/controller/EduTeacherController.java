package com.guli.eduservice.controller;


import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.eduservice.entity.EduTeacher;
import com.guli.eduservice.entity.vo.TeacherQuery;
import com.guli.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-02-24
 */
@Api(description="讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    //访问地址： http://localhost:8001/eduservice/teacher/findAll
    //把service注入

   @Resource
    private EduTeacherService teacherService;

    //1 查询讲师表所有数据
    //rest风格
    @GetMapping("findAll")
    public List<EduTeacher> selectAll(){
       return teacherService.list(null);
    }

    //2 逻辑删除讲师的方法{id}表试通过路径传id

    @DeleteMapping("delete/{id}")
    public Boolean deleteTeacherById(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        return b;
    }

    //3 分页查询讲师的方法
    //current 当前页
    //limit 每页记录数
    @GetMapping("page")
    public R teacherPage(@RequestParam Integer pageSize, @RequestParam Integer pageNum){
        //创建page对象
        Page<EduTeacher> teacherPage = new Page<>(pageNum,pageSize);
        Page<EduTeacher> page = teacherService.page(teacherPage, null);

        HashMap map = new HashMap();
        map.put("total",teacherPage.getTotal());
        map.put("rows",page);
        return R.ok().data(map);
    }

    //4 条件查询带分页的方法
    @PostMapping("page/condition")
    public R teacherPageCondition(@RequestParam Integer pageSize, @RequestParam Integer pageNum,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> eduTeacherPage = new Page<>(pageSize,pageNum);
        //调用方法实现查询
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());
        }
        if (teacherQuery.getLevel()!=null){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQuery.getBegin())){
            wrapper.ge("begin",teacherQuery.getBegin());
        }
        if (!StringUtils.isEmpty(teacherQuery.getEnd())){
            wrapper.le("end",teacherQuery.getEnd());
        }
        //调用方法来实现分页查询
        teacherService.page(eduTeacherPage,wrapper);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();

        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师接口的方法


    //讲师新增功能
    @PostMapping("addTeacher")
    public Boolean addTeacher(@RequestBody EduTeacher eduTeacher) {
        return teacherService.save(eduTeacher);
    }
}

