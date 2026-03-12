package com.example.hello.Controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hello.services.LoginService;

@Controller
public class LoginController {

    @Value("${app.cookie.max-age}")
    private int cookieMaxAge;

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String user,
                          @RequestParam String pass,
                          HttpServletResponse response) {

        if (loginService.checkLogin(user, pass)) {
            Cookie loginCookie = new Cookie("username", user);

            // Sử dụng giá trị từ file properties
            loginCookie.setMaxAge(cookieMaxAge);

            loginCookie.setPath("/");
            response.addCookie(loginCookie);
            return "redirect:/home";
        }
        return "login-failed";
    }
}