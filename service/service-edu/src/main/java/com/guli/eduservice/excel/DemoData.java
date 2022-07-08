package com.guli.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty(value = "一级分类",index = 0)
    private String  primaryClassification;
    @ExcelProperty(value = "二级分类",index = 1)
    private String  secondClassification;
}
