package com.github.config;

import com.github.interceptor.RepeatSubmitInterceptor;
import com.github.interceptor.RetryServiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 康盼Java开发工程师
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RetryServiceInterceptor retryServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(retryServiceInterceptor).addPathPatterns("/api/**");
    }
}
