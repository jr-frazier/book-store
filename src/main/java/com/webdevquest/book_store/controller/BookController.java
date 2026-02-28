package com.webdevquest.book_store.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/api/v1/hello")
    public String firstAPI() {
        return "Hello World!";
    }

}
