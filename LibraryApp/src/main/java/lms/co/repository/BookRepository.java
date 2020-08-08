package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.Book;

@Component
public class BookRepository {
    HashMap<String, Book> books = new HashMap<>();

    public void addBook(Book book){
        books.put(String.valueOf(book.getBkid()), book);
    }

    public Book getBook(String bkid){
        return books.get(bkid);
    }

    public List<Book> getAllBooks(){
        return new ArrayList<>(books.values());
    }

}
