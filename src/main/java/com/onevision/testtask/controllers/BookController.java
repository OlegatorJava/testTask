package com.onevision.testtask.controllers;

import com.onevision.testtask.model.Book;
import com.onevision.testtask.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/grouped-by-author")
    public Map<String, List<Book>> getBooksGroupedByAuthor() {
        return bookService.getBooksGroupedByAuthor();
    }

    @GetMapping("/top-authors")
    public List<Map.Entry<String, Long>> getTopAuthorsByCharCount(@RequestParam char c) {
        return bookService.getTopAuthorsByCharCount(c);
    }
}

