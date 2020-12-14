package com.xiaoshuai.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 10-24-上午 10:55
 **/
@Configuration
public class WebCrossConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
           registry.addMapping("/**")
                   .allowCredentials(true)
                   .allowedMethods("*")
                   .allowedHeaders("*")
                   .allowedOrigins("*")
                   .maxAge(3600);
    }



}
