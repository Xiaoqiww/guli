package com.guli.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.guli")
public class EduApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(EduApplication.class, args);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
