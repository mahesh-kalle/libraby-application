package lms.co.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;
public class Borrower extends ResourceSupport{

    private int userid;

	private String firstName;
    
	private String lastName;
	
	private int borrowingLimit;
	
	private String bookTitle;
	private String bookissuedDt;
	private String rerunDate;
	private String status = "none";
	

	public Borrower() {
    }
	
	public Borrower(int userid,String firstName, String lastName, int borrowingLimit, String bookTitle, String bookissuedDt, String rerunDate, String status ) {
    	this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.borrowingLimit = borrowingLimit;
        this.bookTitle = bookTitle;
        this.bookissuedDt= bookissuedDt;    
    	this.rerunDate= rerunDate;
    	this.status = status;
    }
	
	
	public int getBorrowingLimit() {
		return borrowingLimit;
	}

	public void setBorrowingLimit(int borrowingLimit) {
		this.borrowingLimit = borrowingLimit;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


    public String getStatus() {
		return status;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookissuedDt() {
		return bookissuedDt;
	}

	public void setBookissuedDt(String bookissuedDt) {
		this.bookissuedDt = bookissuedDt;
	}

	public String getRerunDate() {
		return rerunDate;
	}

	public void setRerunDate(String rerunDate) {
		this.rerunDate = rerunDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "Borrower{" + "firstname='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
