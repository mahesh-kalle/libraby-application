package lms.co.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import lms.co.model.LoanDetails;

@Component
public class LoanRepository {
	
	HashMap<String, LoanDetails> loans = new HashMap<>();
	
	public void addBookDetails(LoanDetails loan){

		loans.put(String.valueOf(loan.getBkid()), loan);
    }
	
	public LoanDetails getByBookId(String id){
        return loans.get(id);
    }
	
	public LoanDetails getByBorrowerId(String id){
		 return loans.get(id);
    }
	
	public List<LoanDetails> getAllBooksBorrowed(){
        return new ArrayList<>(loans.values());
    }
}
