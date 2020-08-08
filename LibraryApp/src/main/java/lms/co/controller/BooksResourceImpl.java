package lms.co.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
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
import lms.co.repository.BookRepository;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;


@RestController
@RequestMapping("/library")
@Api(value = "Book", description = "Book Details")
public class BooksResourceImpl implements BooksResource {

    @Autowired
    private BookRepository booksRepo;

   @Override
    @RequestMapping(value="/book/{bkid}",
            method = RequestMethod.GET,
            produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ApiOperation(value = "Get a book using its ISBN")
    public ResponseEntity getBookById(@PathVariable int bkid) {
        Book result = booksRepo.getBook(String.valueOf(bkid));
        
        HttpStatus httpStatus;
        
        if(result != null) {
            result.removeLinks();
            httpStatus = HttpStatus.OK;
        }else{
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>("Book Not Found", httpStatus);
        }

        return new ResponseEntity<>(result, httpStatus);

    }

   @RequestMapping(value="/books",
           method = RequestMethod.GET,
           produces = APPLICATION_JSON_VALUE)
   @ResponseBody
   @Override
   @ApiOperation(value = "List all books")
   public ResponseEntity<List<Book>> getAllBooks() {
       List<Book> books = booksRepo.getAllBooks();
       books.forEach(book -> {
           book.removeLinks();
       });
       return new ResponseEntity<>(books, HttpStatus.OK);
   }

   @Override
   @RequestMapping(value = "/book",
           method = RequestMethod.POST,
           produces = APPLICATION_JSON_VALUE,
           consumes = APPLICATION_JSON_VALUE)
   @ApiOperation(value = "Add a book")
   public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
       booksRepo.addBook(book);
       
       return new ResponseEntity<>(book, HttpStatus.CREATED);
   }


}
