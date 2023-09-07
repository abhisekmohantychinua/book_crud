package dev.abhisek.SpringMvc.exception;

import java.util.NoSuchElementException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.abhisek.SpringMvc.entities.User;
import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException e, HttpSession session, Model model) {
        model.addAttribute("user", new User());
        session.setAttribute("msg", "Requested User not found on server");
        return "login";
    }
}
