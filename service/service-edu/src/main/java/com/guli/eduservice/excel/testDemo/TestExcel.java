package com.guli.eduservice.excel.testDemo;

import com.alibaba.excel.EasyExcel;
import com.guli.eduservice.excel.DemoData;
import com.guli.eduservice.excel.ExcelListener;

import java.util.ArrayList;
import java.util.List;

public class TestExcel {
    public static void main(String[] args) {
        //设置写入文件夹地址和Excel名称
//        String fileName ="F:\\temp\\write.xlsx";
//        EasyExcel.write(fileName, DemoData.class).sheet("分类数据").doWrite(getData());

        //实现读操作
        String fileName ="F:\\temp\\write.xlsx";
        EasyExcel.read(fileName,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    public static List<DemoData> getData(){
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setPrimaryClassification(i+"级分类");
            demoData.setSecondClassification("Java"+i);
            list.add(demoData);
        }
        return list;
    }
}
