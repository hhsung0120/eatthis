package co.kr.heeseong.eatthis.interceptor;

import co.kr.heeseong.eatthis.util.Jwt;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenCheckInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        try {
            if (token == null || "".equals(token)) {
                response.sendRedirect("/users/invalidToken");
            }
            request.setAttribute("accountUser", Jwt.verification(token));
        }catch (Exception e){
            response.sendRedirect("/users/invalidToken");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
