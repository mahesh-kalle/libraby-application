package lms.co.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lms.co.model.Book;
import lms.co.model.LoanDetails;
import lms.co.model.Borrower;
import lms.co.repository.BookRepository;
import lms.co.repository.LoanRepository;
import lms.co.repository.BorrowerRepository;

@RestController
@RequestMapping("/library")
@Api(value = "User", description = "Borrower Details")
public class BorrowerResourceImpl implements BorrowerResource {
	
	@Autowired
    private BorrowerRepository usersRepo;
   
    @Autowired
    private BookRepository booksRepo;

    @Autowired
    private LoanRepository loanRepo;
   
    @RequestMapping(value="/users",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    @ApiOperation(value = "List all users")
    public ResponseEntity<List<Borrower>> getAllUsers() {
        List<Borrower> users = usersRepo.getAllUsers();
        users.forEach(user -> {
            user.removeLinks();
        });
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    
    @Override
    @RequestMapping(value="/user/{userid}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a user using its ISBN")
    public ResponseEntity getUserById(@PathVariable int userid) {
        Borrower result = usersRepo.getUser(String.valueOf(userid));
        HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>("User Not Found", httpStatus);
        }
        
        return new ResponseEntity<>(result, httpStatus);

    }
    
    
    @Override
    @RequestMapping(value = "/user",
            method = RequestMethod.POST,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a user")
    public ResponseEntity<Borrower> addUser(@Valid @RequestBody Borrower user) {
    	System.out.println("Add User ::::::::: "+user);
        usersRepo.addUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    //@Override
    @RequestMapping(value = "lendbook/{userid}/{bookid}",
            method = RequestMethod.GET)
    @ApiOperation(value = "Lend Books To Users")
	public ResponseEntity lendBooksToUsers(@PathVariable int  userid,@PathVariable int bookid) {
    	
    	HttpStatus httpStatus;
		List<Borrower> users = usersRepo.getAllUsers();
		List<Book> books = booksRepo.getAllBooks();
		List<LoanDetails> loans = loanRepo.getAllBooksBorrowed();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
 
		Borrower borroweruser = new Borrower();
		borroweruser.setUserid(userid);
		
		String bkStatus = null;
		
		Iterator<Book> it = books.iterator();
		Book currentBook = null;
		while(it.hasNext()){
			currentBook = it.next();
		   if((currentBook.getBkid() == bookid) && currentBook.getStatus().equals("Available")
           		&& currentBook.getBorrower().equals("none")){
		      
			   bkStatus = "Available";
		   }
		}
		httpStatus = HttpStatus.OK;
		
		if(bkStatus != null) {
	 
		books.forEach(book -> {
            if(String.valueOf(book.getBkid()).equals(String.valueOf(bookid))
            		&& book.getStatus().equals("Available")
            		&& book.getBorrower().equals("none"))
            		{
            			book.setBorrower(String.valueOf(userid));
            			book.setStatus("Not Available");
             			
            			String toDate = sdf.format(c.getTime()); 
            			c.add(Calendar.DAY_OF_MONTH, 15);
        				String newDate = sdf.format(c.getTime()); 
        				
        				String title = book.getTitle();
        				
        				int lp = 1;
        				LoanDetails ld = new LoanDetails();
        				
        				Iterator<LoanDetails> itss = loans.iterator();
        				LoanDetails lgg = null;
        				while(itss.hasNext()){
        					ld = itss.next();
        					System.out.println("loan id--"+ld.getLoanid());
        					lp = ld.getLoanid();
        					lp = lp+1;
        				}
        				
        						
        				loanRepo.addBookDetails(new LoanDetails(lp,book.getTitle(),String.valueOf(bookid),String.valueOf(userid),toDate,newDate));
            			
        				
            			users.forEach(user -> {
            	        	
            				if(user.getUserid() == (userid))
	    	             		{
				            			borroweruser.setFirstName(user.getFirstName());
				             			borroweruser.setLastName(user.getLastName());
				             			borroweruser.setBorrowingLimit(1);
				             			borroweruser.setBookTitle(title);
				             			borroweruser.setBookissuedDt(toDate);
				             			borroweruser.setRerunDate(newDate);
				             			borroweruser.setStatus("1 book issued");
	    	             		}
            				});
            			
            		}
           });
		
		}
		else {
			httpStatus = HttpStatus.NOT_FOUND;
			return new ResponseEntity<>("Requested book not available.", httpStatus);
		}
		
        return new ResponseEntity<>(borroweruser, httpStatus);
	}
    
    
}
