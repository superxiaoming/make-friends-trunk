package com.example.makefriends.config;

import com.example.makefriends.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @program: makefriends
 * @description: webmvc CORS 配置
 * @author: YinShm
 * @date: 2019-12-10 22:00
 **/

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/makefriends")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .maxAge(1800);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(myInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns();
    }

    @Override
    public void addResourceHandlers (ResourceHandlerRegistry registry){
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:/data/uploadPics/");
//                .addResourceLocations("file:///Users/yinshiming/uploadPics/");
    }
}
