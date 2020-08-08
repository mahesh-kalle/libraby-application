package lms.co;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import lms.co.model.Author;
import lms.co.model.Book;
import lms.co.model.LoanDetails;
import lms.co.model.Borrower;
import lms.co.repository.BookRepository;
import lms.co.repository.LoanRepository;
import lms.co.repository.BorrowerRepository;

@ComponentScan
@EnableAutoConfiguration
public class LibraryApp
{
    @Autowired
    private BookRepository booksRepo;
    
    @Autowired
    private BorrowerRepository usersRepo;

    @Autowired
    private LoanRepository loanRepo;
    
    public static void main( String[] args ){
        SpringApplication.run(LibraryApp.class, args);

    }

    @PostConstruct
    public void initApplication() throws IOException {
    	
    	ArrayList genre1 = new ArrayList();
    	Author author= new Author("Terri","Favro");
    	genre1.add("Comedy");
        booksRepo.addBook(new Book(101,"Sputnik's Children",author,genre1, 160,"Available","none"));
        Author author2= new Author("Jane","Austen");
        ArrayList genre2 = new ArrayList();
        genre2.add("Comedy");
        genre2.add("Romance");
        booksRepo.addBook(new Book(102,"Pride and Prejudice",author2,genre2, 150,"Available","none"));
        Author author3= new Author("Jane","Austen");
        ArrayList genre3 = new ArrayList();
        genre3.add("Fantasy");
        genre3.add("Science Fiction");
        booksRepo.addBook(new Book(103,"The Lord of the Rings",author3,genre3,220,"Available","none" ));
      
        usersRepo.addUser(new Borrower(201,"Jordan","Peterson",1,"Sputnik's Children","2020-08-07","2020-08-18","1 book issued"));
        usersRepo.addUser(new Borrower(202,"Lawrence","Hill",1,"Pride and Prejudice","2020-08-07","2020-08-18","1 book issued"));
        usersRepo.addUser(new Borrower(203,"Margaret","Atwood",1,"The Lord of the Rings","2020-08-07","2020-08-18","1 book issued"));
        
		
        loanRepo.addBookDetails(new LoanDetails(301,"Sputnik's Children","101","201","2020-08-07","2020-08-18"));
        //loanRepo.addBookDetails(new LoanDetails(302,"Pride and Prejudice","102","202","2020-08-07","2020-08-18"));
       // loanRepo.addBookDetails(new LoanDetails(303,"The Lord of the Rings","103","203","2020-08-07","2020-08-18"));
        
    }
}

