package com.example.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String uri = request.getRequestURI();

        if (user == null) {
            response.sendRedirect("/auth/login");
            return false;
        }

        if (uri.startsWith("/admin") && !"ADMIN".equals(user.getRole())) {
            response.sendRedirect("/auth/login");
            return false;
        }

        if (uri.startsWith("/user") && !"USER".equals(user.getRole())) {
            response.sendRedirect("/auth/login");
            return false;
        }

        return true;
    }
}