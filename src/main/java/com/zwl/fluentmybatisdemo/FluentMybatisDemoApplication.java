package com.zwl.fluentmybatisdemo;

import com.zwl.fluentmybatisdemo.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.zwl.fluentmybatisdemo.mapper")
public class FluentMybatisDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(FluentMybatisDemoApplication.class, args);

  }

}
