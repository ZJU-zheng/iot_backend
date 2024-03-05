package cn.edu.zju.cs.bs.interceptors;

import cn.edu.zju.cs.bs.pojo.Result;
import cn.edu.zju.cs.bs.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> userInfos = JwtUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
