package com.xiaoshuai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 10-21-上午 10:57
 **/
@SpringBootApplication
@MapperScan("com.xiaoshuai.mapper")
public class MyblogMain {
    public static void main(String[] args) {
        SpringApplication.run(MyblogMain.class, args);
    }
}
