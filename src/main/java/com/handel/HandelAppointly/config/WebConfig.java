package com.handel.HandelAppointly.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer) {
        String version = "v1";
        pathMatchConfigurer.addPathPrefix("/api/"+version, c -> c.isAnnotationPresent(RestController.class));
    }
}
