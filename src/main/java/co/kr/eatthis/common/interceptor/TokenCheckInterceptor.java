package co.kr.eatthis.common.interceptor;

import co.kr.eatthis.common.util.Jwt;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Log4j2
public class TokenCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //헤더에서 넘어오는 토큰 값이 Bearer 토큰값 이런형식으로 넘어옴
        String token = request.getHeader("authorization").replace("Bearer", "").trim();
        log.info("user token : {}", token);

        Enumeration headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String name = (String) headers.nextElement();
            String value = request.getHeader(name);
            if (value != null) {
                //log.info("headers name: {}, value : {}", name, value);
            }
        }

        try {
            if (token == null || "".equals(token)) {
                response.sendRedirect("/users/invalid-token");
                return false;
            }
            request.setAttribute("accountUser", Jwt.verification(token));
        } catch (Exception e) {
            response.sendRedirect("/users/invalid-token");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
