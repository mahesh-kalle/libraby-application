package lms.co.controller;

import lms.co.model.LoanDetails;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface LoanResource {

	ResponseEntity getLoanByBookId(int bkid);
	ResponseEntity getLoanByBorrowerId(String brwrid);
	ResponseEntity<List<LoanDetails>> getAllBooksBorrowed();
}
