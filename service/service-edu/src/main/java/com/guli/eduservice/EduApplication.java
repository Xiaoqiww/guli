package com.guli.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@ComponentScan(basePackages = "com.guli")
@EnableSwagger2
public class EduApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(EduApplication.class, args);
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
