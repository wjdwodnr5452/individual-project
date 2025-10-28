package com.individual.individual_project.config;

import com.individual.individual_project.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000, https://web.together-communication.com")  // React 앱의 URL
                .allowedMethods("GET", "POST", "PUT", "PATCH","DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginCheckInterceptor())
               .order(1)
               .addPathPatterns("/**")
               .excludePathPatterns("/api/auth/*", "/api/categorys", "/api/users",  "/api/status/*","/error", "/api/images/*", "/api/kafka/*");





             /*  .excludePathPatterns("/api/login", "/api/logout",
                       "/api/categorys","/api/service/boards",
                       "/api/status/*" ,"/api/users",
                       "/api/login/status","/error");*/
    }
}
