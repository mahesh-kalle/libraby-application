package lms.co.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;

import lms.co.model.Book;
import lms.co.model.Borrower;

public interface BorrowerResource {
	
	ResponseEntity getUserById(int id);
	ResponseEntity<List<Borrower>> getAllUsers();
	
    ResponseEntity<Borrower> addUser(Borrower user);
    ResponseEntity<Borrower> lendBooksToUsers(int userid, int bookid);
    
}
