package com.nutricare.user.utils;

import com.nutricare.user.dto.UserInfoResponseDTO;
import com.nutricare.user.exception.UnauthorizedActionException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class AuthContextUtil {
    public UserInfoResponseDTO getCurrentUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);
        System.out.println(session);
        if(session != null) {
            UserInfoResponseDTO user = (UserInfoResponseDTO) session.getAttribute("userInfo");
            if(user != null) return user;
        }

        throw new UnauthorizedActionException("Phiên làm việc không hợp lệ hoặc chưa đăng nhập");
    }
}
