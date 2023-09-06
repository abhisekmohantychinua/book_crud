package dev.abhisek.SpringMvc.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.abhisek.SpringMvc.entities.Book;
import dev.abhisek.SpringMvc.services.BookService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BookController {

    private final BookService service;

    @GetMapping
    public String home(Model model) {
        List<Book> books = service.getAllBook();
        model.addAttribute("books", books);
        return "home";
    }

    @GetMapping("addBook")
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addbook";
    }

    @PostMapping("addBook")
    public String addBook(@ModelAttribute Book book) {
        service.addBook(book);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteBook(@PathVariable int id) {
        service.deleteBook(id);
        return "redirect:/";
    }

    @PostMapping("update/{id}")
    public String updateBook(@ModelAttribute Book book, @PathVariable int id) {
        service.updateBook(id, book);
        return "redirect:/";
    }
}
