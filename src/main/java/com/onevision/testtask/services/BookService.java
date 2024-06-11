package com.onevision.testtask.services;

import com.onevision.testtask.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM book ORDER BY title DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO book (id, title, author, description) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, book.getId(), book.getTitle(), book.getAuthor(), book.getDescription());
    }

    public Map<String, List<Book>> getBooksGroupedByAuthor() {
        String sql = "SELECT * FROM book";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return books.stream().collect(Collectors.groupingBy(Book::getAuthor));
    }

    public List<Map.Entry<String, Long>> getTopAuthorsByCharCount(char c) {
        String sql = "SELECT author, title FROM book";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));

        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.summingLong(book -> book.getTitle().toLowerCase().chars().filter(ch -> ch == Character.toLowerCase(c)).count())
                ))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .limit(10)
                .collect(Collectors.toList());
    }
}

