package lms.co.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

public class LoanDetails extends ResourceSupport{

	private int loanid;
	private String bkid;
	private String title;
	private String issuedTo;
	private String issuedDate;
	private String returnDate;
	
	
	public int getLoanid() {
		return loanid;
	}
	public void setLoanid(int loanid) {
		this.loanid = loanid;
	}
	public String getBkid() {
		return bkid;
	}
	public void setBkid(String bkid) {
		this.bkid = bkid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIssuedTo() {
		return issuedTo;
	}
	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}
	public String getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(String c) {
		this.issuedDate = c;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String newDate) {
		this.returnDate = newDate;
	}
	
	public LoanDetails() {
    }
	
	public LoanDetails( int loanid, String title, String bkid, String issuedTo, String issuedDate, String returnDate) {
		this.loanid=loanid;
		this.title = title;
		this.bkid = bkid;
		this.issuedTo = issuedTo;
		this.issuedDate = issuedDate;
		this.returnDate = returnDate;
    }
	
	@Override
    public String toString() {
        return "LoanDetails{" + "bkid='" + bkid + '\'' + ", title='"+ title + '\'' + ", issuedTo='" + issuedTo + '\'' + '}';
    }
}
