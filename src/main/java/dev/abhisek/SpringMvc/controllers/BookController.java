package dev.abhisek.SpringMvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.abhisek.SpringMvc.entities.Book;
import dev.abhisek.SpringMvc.services.BookService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BookController {

    private final BookService service;

    @GetMapping
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/login";
        }
        List<Book> books = service.getAllBook();
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("addBook")
    public String addBook(Model model, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/login";
        }
        Book book = new Book();
        model.addAttribute("book", book);
        return "addbook";
    }

    @PostMapping("addBook")
    public String addBook(@ModelAttribute Book book, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/login";
        }
        service.addBook(book);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable int id, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/login";
        }
        service.deleteBook(id);
        return "redirect:/";
    }

    @PostMapping("update/{id}")
    public String updateBook(@ModelAttribute Book book, @PathVariable int id, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/login";
        }
        service.updateBook(id, book);
        return "redirect:/";
    }

    @GetMapping("search")
    public String searchBook(@RequestParam String query, Model model, HttpSession session) {
        if (session.getAttribute("auth") == null) {
            return "redirect:/login";
        }
        model.addAttribute("q", query);
        List<Book> books = service.searchBookByName(query);
        model.addAttribute("books", books);
        return "results";
    }

}
