package com.example.hello.Controllers;
import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.hello.services.MathService;

@Controller
public class MathController {

    private final MathService mathService;

    // Constructor Injection: Cách tốt nhất để DI
    public MathController(MathService mathService) {
        this.mathService = mathService;
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam int a, @RequestParam int b, Model model) {
        int result = mathService.add(a, b); // Gọi service
        model.addText("res");
        return "math-view";
    }
}