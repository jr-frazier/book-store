package com.webdevquest.book_store.controller;

import com.webdevquest.book_store.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("The Hobbit", "JRR Tolken", "Fantasy"),
                new Book("The Lord of the Rings", "JRR Tolken", "Fantasy"),
                new Book("The Hunger Games", "John Doe", "Fantasy")
        ));
    }

    public BookController() {
        initializeBooks();
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return books;
    }

}
