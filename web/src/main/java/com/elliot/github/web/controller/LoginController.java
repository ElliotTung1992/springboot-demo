package com.elliot.github.web.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.UUID;

@RestController
public class LoginController {

    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("username", "Bruce");
        session.setMaxInactiveInterval(5 * 60);

        String tokenID = UUID.randomUUID().toString().replaceAll("-", "");
        Cookie sessionCookie = new Cookie("token", tokenID);
        sessionCookie.setMaxAge(5 * 60);
        // httpOnly: 浏览器将禁止JS对Cookie的访问和修改
        sessionCookie.setHttpOnly(Boolean.TRUE);
        response.addCookie(sessionCookie);

        Cookie usernameCookie = new Cookie("username", "Bruce");
        usernameCookie.setMaxAge(5 * 60);
        usernameCookie.setHttpOnly(Boolean.TRUE);
        response.addCookie(usernameCookie);
    }

    @GetMapping("/test")
    public void test(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.nonNull(cookies) && cookies.length > 0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("token".equals(name)) {
                    String value = cookie.getValue();
                    System.out.println(value);
                }
            }
        }

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println(username);
    }
}
