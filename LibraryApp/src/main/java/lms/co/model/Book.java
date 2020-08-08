package lms.co.model;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;
public class Book extends ResourceSupport{

    
    private int bkid;
    private int pageCount;
    private Author author;
    private List genre;
    private String status;
    private String borrower;
    private String title;

    
    public Book() {
    }
	
	public Book(int bkid, String title, Author author,
			List genre, int pageCount, String status, String borrower) {
        this.bkid = bkid;
        this.title = title;
        this.author =author;
        this.genre = genre;
        this.pageCount = pageCount;
        this.status = status;
        this.borrower = borrower;
      
    }
	

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List getGenre() {
		return genre;
	}

	public void setGenre(List genre) {
		this.genre = genre;
	}

	public int getBkid() {
		return bkid;
	}

	public void setBkid(int bkid) {
		this.bkid = bkid;
	}

	public String getTitle() {
        return title;
    }

	public void setTitle(String title) {
        this.title = title;
    }
    
    public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

    @Override
    public String toString() {
        return "Book{" + "id='" + bkid + '\'' + ", author='"+ author+ ", title='" + title + ", status='"+ status +'\'' + '}';
    }
}
