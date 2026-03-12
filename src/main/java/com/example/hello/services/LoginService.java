package com.example.hello.services;


import org.springframework.stereotype.Service;

@Service // Đánh dấu đây là một Bean để Spring quản lý
public class LoginService {

    public boolean checkLogin(String username, String password) {
        // Logic kiểm tra đăng nhập tách ra từ Controller
        return "admin".equals(username) && "123".equals(password);
    }
}