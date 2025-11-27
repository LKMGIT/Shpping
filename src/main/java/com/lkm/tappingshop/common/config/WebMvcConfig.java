package com.lkm.tappingshop.common.config;

import com.lkm.tappingshop.common.interceptor.ApiInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {


    private final ApiInterceptor apiInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/v1/api/**") // 인터셉터 적용할 경로
                .excludePathPatterns("/v1/api/account/**", "v1/api/items/**"); // 적용하지 않을 경로

    }
}
