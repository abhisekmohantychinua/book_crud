package dev.abhisek.SpringMvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.abhisek.SpringMvc.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
