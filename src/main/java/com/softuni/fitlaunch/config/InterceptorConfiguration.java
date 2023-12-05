package com.softuni.fitlaunch.config;

import com.softuni.fitlaunch.interceptor.IPBlackListInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {


    private final IPBlackListInterceptor ipBlackListInterceptor;

    public InterceptorConfiguration(IPBlackListInterceptor ipBlackListInterceptor) {
        this.ipBlackListInterceptor = ipBlackListInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipBlackListInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
