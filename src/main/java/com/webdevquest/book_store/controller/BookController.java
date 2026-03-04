package com.webdevquest.book_store.controller;

import com.webdevquest.book_store.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();

    private void initializeBooks() {
        books.addAll(List.of(
                new Book("The Hobbit", "JRR Tolken", "Fantasy"),
                new Book("The Lord of the Rings", "JRR Tolken", "Fantasy"),
                new Book("The Hunger Games", "John Doe", "Fantasy"),
                new Book("Snow Crash", "Neal Stephenson", "Science Fiction")
        ));
    }

    public BookController() {
        initializeBooks();
    }

    @GetMapping("/api/books")
    public List<Book> getBooks(@RequestParam(required = false) String category) {
        if (category != null) {
            return books.stream()
                    .filter(book -> book.getCategory().equalsIgnoreCase(category))
                    .toList();
        }

        return books;
    }

   @GetMapping("/api/book/{title}")
   public Book getBookByTitle(@PathVariable String title) {
       return books.stream()
               .filter(book -> book.getTitle().equalsIgnoreCase(title))
               .findFirst()
               .orElse(null);
   }

   @PostMapping("/api/book")
    public void addBook(@RequestBody Book book) {
        // If the book doesn't exist, add it to the list'
       boolean isNewBook = books.stream()
               .noneMatch(b -> b.getTitle().equalsIgnoreCase(book.getTitle()));

       if (isNewBook) {
           books.add(book);
       }
    }

    @PutMapping("api/book/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook) {
        books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    book.setCategory(updatedBook.getCategory());
                });
    }

    @PatchMapping("api/book/{title}")
    public void patchBook(@PathVariable String title, @RequestBody Book updatedBook) {
        books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .ifPresent(book -> {
                    if (updatedBook.getTitle() != null) {
                        book.setTitle(updatedBook.getTitle());
                    }
                    if (updatedBook.getAuthor() != null) {
                        book.setAuthor(updatedBook.getAuthor());
                    }
                    if (updatedBook.getCategory() != null) {
                        book.setCategory(updatedBook.getCategory());
                    }
                });
    }

    @DeleteMapping("/api/book/{title}")
    public void deleteBook(@PathVariable String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }
}
