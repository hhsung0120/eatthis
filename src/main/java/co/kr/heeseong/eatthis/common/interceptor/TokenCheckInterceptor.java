package co.kr.heeseong.eatthis.common.interceptor;

import co.kr.heeseong.eatthis.util.Jwt;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Log4j2
public class TokenCheckInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        log.info("user token : {}", token);

        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()){
            String name = (String)headers.nextElement();
            String value = request.getHeader(name);
            if(value != null){
                log.info("headers name: {}, value : {}", name, value);
            }
        }

        try {
            if (token == null || "".equals(token)) {
                response.sendRedirect("/users/invalidToken");
                return false;
            }
            request.setAttribute("accountUser", Jwt.verification(token));
        }catch (Exception e){
            response.sendRedirect("/users/invalidToken");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
