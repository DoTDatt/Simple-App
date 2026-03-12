package com.example.hello;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    // HOME PAGE
    @GetMapping("/")
    public String home(
            @CookieValue(name = "login", required = false) String login,
            Model model
    ) {
        boolean loggedIn = (login != null);
        model.addAttribute("loggedIn", loggedIn);
        return "home";
    }

    // LOGIN PAGE - GET
    @GetMapping("/login")
    public String loginPage(
            @CookieValue(name = "login", required = false) String login,
            Model model
    ) {
        if (login != null) {
            return "redirect:/";
        }
        model.addAttribute("error", null);
        model.addAttribute("username", "");
        model.addAttribute("password", "");
        return "login";
    }

    // LOGIN PAGE - POST
    @PostMapping("/login")
    public String login(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            Model model
    ) {
        if ("admin".equals(username) && "123456".equals(password)) {

            ResponseCookie cookie = ResponseCookie
                    .from("login", "true")
                    .path("/")
                    .maxAge(3600)
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());
            return "redirect:/";
        }

        model.addAttribute("error", "Wrong username or password");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "login";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie
                .from("login", "")
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        return "redirect:/";
    }
}
