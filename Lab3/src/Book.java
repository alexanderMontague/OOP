import java.util.*;

public class Book {
	
	private String title;
	private String author;
	private String ISBN;
	private int year;
	private double price;
	
	public Book(String title, String author, String ISBN, int year, double price) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.year = year;
		this.price = price;
	}
	  
    public void printInfo(){
		System.out.println("Title: " + this.title + "\nAuthors: " + this.author + "\nYear Published: " + this.year + "\nISBN: " + this.ISBN + "\nPrice: " + price);
    }
    

    public String getAuthor() {
        return author;
    }
    
    public double getPrice() {
        return price;
    }
	
}
