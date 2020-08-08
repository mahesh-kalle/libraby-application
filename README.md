# libraby-application
Library management system to add book, get book by id, get all the books and borrower info

Book Operartions:

Get all Books:
http://localhost:9080/library/books -- Get method



Get a Book by id:
http://localhost:9080/library/book/102 -- Get method 

Returns book by id in success. Not found message if book not found.


Add a Book:
http://localhost:9080/library/book -- Post method

Request Pay load
{
        "bkid": 104,
        "pageCount": 100,
        "author": {
            "firstName": "Rowling",
            "lastName": "JK"
        },
        "genre": [
            "comedy",
            "fiction"
        ],
        "title": "Harry Potter",
        "status":"Available",
        "borrower":"121"
    }



--------------------------

Borrower Operartions:

Get all Borrowers:
http://localhost:9080/library/users  -- Get method


Get a Borrower by id:
http://localhost:9080/library/user/201 -- Get method

Returns Borrower by id in success. Not found message if Borrower not found.


Add a Borrower:
http://localhost:9080/library/user  -- Post method

Request Pay load
{
    "userid": 121,
    "firstName": "Glen",
    "lastName": "Ping",
    "borrowingLimit": 1,
    "bookTitle": "Stars in the Sky",
    "bookissuedDt": "2020-08-07",
    "rerunDate": "2020-08-22",
    "status": "1 book issued"
}



Lend a Book:

Lend a book to the user by passing book id and user id.
http://localhost:9080/library/lendbook/userid/bookid

http://localhost:9080/library/lendbook/202/102  -- Get method

Returns lending details in success. Not found message for wrong entry.
--------------------------

Create a loan record for a given book/borrower:
http://localhost:9080/library/record  -- Get method

Displays a loan records. 

Get a list of past borrowers for a book:
http://localhost:9080/library/record/book/101  -- Get method



Get a list of past books borrowed by a given borrower:
http://localhost:9080/library/record/borrower/201  -- Get method



## Installation
* Java 8 and Maven 3.2 are needed to run the application

#To Run the application
Navigate to the directory into which you copied the project and execute this: mvn spring-boot:run


Once server started you can access the portal on port 9080, e.g.
`http://localhost:9080/library/books`

The port number can be changed by editing the port property in `src/main/resources/application.yml`

Requirements given below.

* Get a list of books
* Get an individual book
* Add a book
* Get a list of borrowers
* Get an individual borrower
* Add a borrower
* Create a loan record for a given book/borrower
* Get a list of past borrowers for a book
* Get a list of past books borrowed by a given borrower
