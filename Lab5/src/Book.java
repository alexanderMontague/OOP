import java.util.*;

public class Book {
	
	private String title;
	private String author;
	private String ISBN;
	private int year;
	private double price;
	
	// Default Constructor
	public Book() {
		this.title = "";
		this.author = "";
		this.ISBN = "";
		this.year = 0;
		this.price = 0;
	}
	
	public Book(String title, String author, String ISBN, int year, double price) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.year = year;
		this.price = price;
	}
	
	
	  
    public void printInfo() {
		System.out.println("Title: " + this.title + "\nAuthors: " + this.author + "\nYear Published: " + this.year + "\nISBN: " + this.ISBN + "\nPrice: " + price);
    }

    public String getTitle() {
        return title;
    }
    
	public String getAuthor() {
        return author;
    }
    
    public double getPrice() {
        return price;
    }
    
    public String getISBN() {
        return ISBN;
    }
    
    public int getYear() {
        return year;
    }
	
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Book b = (Book) o;
        if (!(this.getTitle().equals(b.getTitle()))) return false;
        if (!(this.getAuthor().equals(b.getAuthor()))) return false;
        if (!(this.getISBN().equals(b.getISBN()))) return false;
        if (!(this.getPrice() == b.getPrice())) return false;
        if (!(this.getYear() == b.getYear())) return false;
        return true;
    }
    
    @Override
    public int hashCode() {
    		return Objects.hash(title, author, ISBN, year, price);
    }
}
