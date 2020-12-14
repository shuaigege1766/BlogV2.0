package com.xiaoshuai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 10-21-下午 17:06
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket swagger(){
         return   new Docket(DocumentationType.SWAGGER_2)
                 .enable(true)
                 .apiInfo(apiInfo())
                 .select()
                 .paths(PathSelectors.any())
                 .apis(RequestHandlerSelectors.basePackage("com.xiaoshuai.controller"))
                 .build();
    }
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("我的博客文档")
                .version("v1.0")
                .description("springboot+vue的博客系统")
                .build();
    }
}
