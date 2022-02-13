package com.cos.photogramstart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer { // web 설정파일
    @Value("${file.path}")
    private String uploaderFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry
                .addResourceHandler("/upload/**")//jsp페이지에서 /upload/**이런 주소패턴 나오면 발동
                .addResourceLocations("file:///"+uploaderFolder)
                .setCachePeriod(60*10*6) //1시간 동안 이미지 캐시상태로 유지
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
