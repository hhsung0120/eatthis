package co.kr.heeseong.eatthis.config;

import co.kr.heeseong.eatthis.interceptor.TokenCheckInterceptor;
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

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenCheckInterceptor())
				.excludePathPatterns("/docs/**")
				.excludePathPatterns("/api/**")
				.excludePathPatterns("/test/**")
				.excludePathPatterns("/users/login")
				.excludePathPatterns("/users/invalidToken")
				.excludePathPatterns("/users/signUp")
				.excludePathPatterns("/users/signUpDetail")
				;

	}
}
