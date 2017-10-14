import java.util.*;
import java.io.*;
import java.text.*;

public class Bookstore {
	
	
	private ArrayList<Book> bookList;
	
	public Bookstore() {
		this.bookList = new ArrayList<>();	// array list to store books (class book)
	}
	
	public void run() {
		
		Scanner userInput = new Scanner(System.in);
		boolean menuLoop = true;
		int menuInput = -1;	
		String printInfo;
		
		do {
			
			System.out.println("");
			System.out.println("1. Add a unique book to the inventory");
			System.out.println("2. Print out information on each book");
			System.out.println("3. Print out all unique authors");
			System.out.println("4. Print out the average book price, along with the total number of books");
			System.out.println("5. Quit");
			System.out.println("");
			System.out.print("Enter an option: ");
			 
			
			// ERROR CHECK
			try {
				menuInput = userInput.nextInt();
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid Input");
				userInput.nextLine();
			}
			
			if(menuInput == 1) {
					
				String bookTitle;
		    		String bookAuthor;
		    		String ISBN;
		    		int publishYear = -99999;
		    		double bookPrice = -1;
		    		userInput.nextLine();
		    		
		    		System.out.print("Please enter the book's title : ");
		    		bookTitle = userInput.nextLine();
		    		while(bookTitle.equals("")) {
		    			System.out.println("Invalid Input. Enter Again.");
		    			bookTitle = userInput.nextLine();
		    		}
		    		
		    		System.out.print("Please enter the book's author: ");
		    		bookAuthor = userInput.nextLine();
		    		while(bookAuthor.equals("")) {
		    			System.out.println("Invalid Input. Enter Again.");
		    			bookAuthor = userInput.nextLine();
		    		}
		    		
		    		System.out.print("Please enter the book's publication date: ");
		    		publishYear = userInput.nextInt();
		    		while(publishYear < -2600 || publishYear > 2017) {
		    			try {
		    				publishYear = userInput.nextInt();
		    			}
		    			catch(InputMismatchException ex) {
		    				System.out.println("Invalid Input");
		    				userInput.nextLine();
		    			}
		    		}	
		    		userInput.nextLine();
		    		
		    		System.out.print("Please enter the book's ISBN: ");
		    		ISBN = userInput.nextLine();
		    		
		    		System.out.print("Please enter the book's price: ");
		    		try {
		    				bookPrice = userInput.nextDouble();
				}
				catch(InputMismatchException ex) {
					System.out.println("Invalid Input");
					userInput.nextLine();
				}
		    		while(bookPrice < 0) {
		    			try {
		    				bookPrice = userInput.nextDouble();
		    			}
		    			catch(InputMismatchException ex) {
		    				System.out.println("Invalid Input");
		    				userInput.nextLine();
		    			}
		    		}
		    		
		    		
		    		Book tempBook = new Book(bookTitle, bookAuthor, ISBN, publishYear, bookPrice);
		    		
		    		if(bookList.contains(tempBook) == false) {
		    			bookList.add(tempBook);
		    		}
		    		else {
		    			System.out.println("\nBook is already in the store!");
		    		}
			}
			else if(menuInput == 2) {
				if(bookList.size() != 0) {
					System.out.println("The Bookstore Contains:\n");
					for(int i = 0; i < bookList.size(); i++) {
						bookList.get(i).printInfo();
						System.out.println("");
					}
				}
				else {
					System.out.println("The bookstore contains no books!");
				}
			}
			else if(menuInput == 3) {
				
				ArrayList<String> printAuthors = new ArrayList<String>();
				printAuthors.clear();
				
				if(bookList.size() != 0) {
					System.out.println("Unique Authors:");
					for(int i = 0; i < bookList.size(); i++) {
						if((printAuthors.contains(bookList.get(i).getAuthor())) == false) {
							System.out.println(bookList.get(i).getAuthor());
							printAuthors.add(bookList.get(i).getAuthor());
						}
					}
				}
				else {
					System.out.println("The bookstore contains no books!");
				}
			}
			else if(menuInput == 4) {
				
				double averagePrice = 0;
				DecimalFormat df = new DecimalFormat("#.00");
				
				if(bookList.size() != 0) {
					for(int i = 0; i < bookList.size(); i++) {
						averagePrice = averagePrice + bookList.get(i).getPrice();
					}
					System.out.println("Average Book Price = " + df.format(averagePrice / bookList.size()));
				}
				else {
					System.out.println("The bookstore contains no books!");
				}
				
			}
			else if(menuInput == 5) {
				
				menuLoop = false;
			}
			else {
				System.out.println("Invalid Input");
			}
		}
		while(menuLoop == true);
	}
}
