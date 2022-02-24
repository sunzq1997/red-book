package com.sunzq.config;

import com.sunzq.Interceptor.SMSInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author sunzq
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public SMSInterceptor smsInterceptor() {
        return new SMSInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(smsInterceptor()).addPathPatterns("/passport/getSMSCode");
    }
}
