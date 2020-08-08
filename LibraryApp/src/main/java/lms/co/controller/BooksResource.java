package lms.co.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;

import lms.co.model.Book;

public interface BooksResource {

    ResponseEntity getBookById(int id);
    ResponseEntity<Book> addBook(Book book);
    ResponseEntity<List<Book>> getAllBooks();

}
