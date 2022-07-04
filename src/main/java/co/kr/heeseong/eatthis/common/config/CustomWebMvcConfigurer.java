package co.kr.heeseong.eatthis.common.config;

import co.kr.heeseong.eatthis.common.interceptor.TokenCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public TokenCheckInterceptor tokenCheckInterceptor(){
        return new TokenCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenCheckInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/users/invalid-token")
                .excludePathPatterns("/users/sign-up")
                .excludePathPatterns("/users/login")
                //.excludePathPatterns("/users/signUpDetail") //TODO 개발 끝나고 주석
                .excludePathPatterns("/docs/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/test/**")
        ;

    }
}
