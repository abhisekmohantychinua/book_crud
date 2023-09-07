package dev.abhisek.SpringMvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.abhisek.SpringMvc.entities.User;
import dev.abhisek.SpringMvc.services.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {
    private final AuthService service;

    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("login")
    public String login(@ModelAttribute User user, HttpSession session) {
        User authUser = service.verifyUser(user);
        session.setAttribute("auth", authUser);
        return "redirect:/";
    }

    @GetMapping("signup")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("signup")
    public String register(@ModelAttribute User user) {
        service.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("auth");
        session.setAttribute("msg", "Logout Successfully");
        return "redirect:/login";
    }
}
