package dev.abhisek.SpringMvc.services;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.abhisek.SpringMvc.entities.Book;
import dev.abhisek.SpringMvc.repository.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book addBook(Book book) {
        return repository
                .save(book);
    }

    public List<Book> getAllBook() {
        return repository
                .findAll();
    }

    public void deleteBook(int id) {
        repository
                .deleteById(id);
    }

    public Book updateBook(int id, Book book) {
        Book book2 = repository.findById(id).get();
        book.setId(book2.getId());
        return repository
                .save(book);
    }

    public List<Book> searchBookByName(String name) {
        return repository
                .findByNameContainingIgnoreCase(name);
    }
}
