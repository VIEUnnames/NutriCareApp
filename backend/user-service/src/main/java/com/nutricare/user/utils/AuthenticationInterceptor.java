package com.nutricare.user.utils;

import com.nutricare.user.dto.UserInfoResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthContextUtil authContextUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    {
        UserInfoResponseDTO user = authContextUtil.getCurrentUser(request);
        return true;
    }
}
