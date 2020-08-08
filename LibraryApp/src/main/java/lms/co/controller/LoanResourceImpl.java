package lms.co.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
@Api(value = "Books Issued Details", description = "Operations about books issue")
public class LoanResourceImpl implements LoanResource{

	@Autowired
    private BorrowerRepository usersRepo;
   
    @Autowired
    private BookRepository booksRepo;

    @Autowired
    private LoanRepository loanRepo;
    
    @RequestMapping(value="/record",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    @Override
    @ApiOperation(value = "List all books borrowed")
    public ResponseEntity<List<LoanDetails>> getAllBooksBorrowed() {
        List<LoanDetails> loans = loanRepo.getAllBooksBorrowed();
        loans.forEach(user -> {
            user.removeLinks();
        });
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }
    
    
    @Override
    @RequestMapping(value="/record/book/{bookid}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a lended book using book id")
    public ResponseEntity getLoanByBookId(@PathVariable int bookid) {
    	
        LoanDetails result = loanRepo.getByBookId(String.valueOf(bookid));
        HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>("Record Not Found", httpStatus);
        }
        
        return new ResponseEntity<>(result, httpStatus);

    }
    
    @Override
    @RequestMapping(value="/record/borrower/{issuedTo}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a lended book using book id")
    public ResponseEntity getLoanByBorrowerId(@PathVariable String issuedTo) {
        LoanDetails result = loanRepo.getByBorrowerId(issuedTo);
        System.out.println("issuedTo -- "+issuedTo);
        System.out.println("result -- "+result);
        HttpStatus httpStatus;
        if(result != null) {
            result.removeLinks();
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>("Record Not Found", httpStatus);
        }
        
        return new ResponseEntity<>(result, httpStatus);

    }
}
