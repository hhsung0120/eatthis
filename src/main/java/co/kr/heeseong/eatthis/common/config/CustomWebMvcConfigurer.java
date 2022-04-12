package co.kr.heeseong.eatthis.common.config;

import co.kr.heeseong.eatthis.common.interceptor.TokenCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    TokenCheckInterceptor tokenCheckInterceptor() {
        return new TokenCheckInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenCheckInterceptor())
                .excludePathPatterns("/users/signUp")
                .excludePathPatterns("/users/login")
                .excludePathPatterns("/users/invalidToken")
                //.excludePathPatterns("/users/signUpDetail") //TODO 개발 끝나고 주석
                .excludePathPatterns("/docs/**")
                .excludePathPatterns("/api/**")
                .excludePathPatterns("/test/**")
                .addPathPatterns("/**")
        ;

    }
}
