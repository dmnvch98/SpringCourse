package org.example.springmvc.config;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.interceptor.AuthInterceptor;
import org.example.springmvc.session.AuthContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthContext authContext;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MappedInterceptor(new String[]{"/view/allusers"}, new AuthInterceptor(authContext)));
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/accessDenied").setViewName("access_denied");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
