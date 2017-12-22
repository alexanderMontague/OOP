import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bookstore2 extends JFrame{
	
	
	private HashMap<Integer, Book> bookHash;
	private int hashKey = 0;
	private JTextField textField; // Edit Field
    private JTextArea textArea;   // Display Field
    private JLabel inputLabel = new JLabel("Enter an Option:");	 // Input Label
    private String menuInput;
    private Boolean menuLoop = false;
	
	public Bookstore2() {
		
		this.bookHash = new HashMap<>();
	
	}
	
	public void run() {
		
		Scanner s = new Scanner(System.in);
		boolean menuLoop = true;
		int menuInput = -1;
		
		do {
			
			System.out.println("");
			System.out.println("1. Add a unique book, textbook or workbook to the library.");
			System.out.println("2. Print out information on each book");
			System.out.println("3. Print out all unique authors");
			System.out.println("4. Print out the average book price, along with the total number of books");
			System.out.println("5. Print out all Textbook-Workbook pairs in the inventory");
			System.out.println("6. Save the state of the inventory to a file");
			System.out.println("7. Load the state of the inventory from a file");
			System.out.println("8. Quit");
			System.out.println("");
			System.out.print("Enter an option: ");
			 
			
			// ERROR CHECK
			try {
				menuInput = s.nextInt();
			}
			catch(InputMismatchException ex) {
				System.out.println("Invalid Input");
				s.nextLine();
			}
			
			if(menuInput == 1) {
					
				String bookTitle;
		    		String bookAuthor;
		    		String ISBN;
		    		String subject;
		    		String workBookISBN;
		    		int bookOption = 0;
		    		int publishYear = -99999;
		    		int numProblems = 0;
		    		double bookPrice = -1;
		    		boolean unique = true;
		    		s.nextLine();
		    		
		    		System.out.println("");
		    		System.out.println("1. Add a Book");
		    		System.out.println("2. Add a Textbook");
		    		System.out.println("3. Add a Workbook");
		    		System.out.print("Enter an option: ");
		    		bookOption = s.nextInt();
		    		s.nextLine();
		    		
		    		if(bookOption == 1) {
		    			System.out.print("Please enter the book's title : ");
			    		bookTitle = s.nextLine();
			    		while(bookTitle.equals("")) {
			    			System.out.println("Invalid Input. Enter Again.");
			    			bookTitle = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the book's author: ");
			    		bookAuthor = s.nextLine();
			    		while(bookAuthor.equals("")) {
			    			System.out.println("Invalid Input. Enter Again.");
			    			bookAuthor = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the book's publication date: ");
			    		publishYear = s.nextInt();
			    		while(publishYear < -2600 || publishYear > 2017) {
			    			try {
			    				publishYear = s.nextInt();
			    			}
			    			catch(InputMismatchException ex) {
			    				System.out.println("Invalid Input");
			    				s.nextLine();
			    			}
			    		}	
			    		s.nextLine();
			    		
			    		System.out.print("Please enter the book's ISBN: ");
			    		ISBN = s.nextLine();
			    		while(ISBN.length() != 10 && ISBN.length() != 15) {
			    			System.out.println("ISBN must be 10 or 15 characters long");
			    			ISBN = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the book's price: ");
			    		try {
			    				bookPrice = s.nextDouble();
					}
					catch(InputMismatchException ex) {
						System.out.println("Invalid Input");
						s.nextLine();
					}
			    		while(bookPrice < 0) {
			    			try {
			    				bookPrice = s.nextDouble();
			    			}
			    			catch(InputMismatchException ex) {
			    				System.out.println("Invalid Input");
			    				s.nextLine();
			    			}
			    		}
			    		
			    		Book tempBook = new Book(bookTitle, bookAuthor, ISBN, publishYear, bookPrice);
			    		
			    		Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
		            while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                if(tempBook.equals(content)) {
		                		unique = false;
		                }
		            }
			    		if(unique == true) {
			    			bookHash.put(hashKey, tempBook);
			    			hashKey++;
			    		}
			    		else {
			    			System.out.println("\nBook is already in the store!");
			    		}
		    		}
		    		else if(bookOption == 2) {
		    			System.out.print("Please enter the TextBook's title : ");
			    		bookTitle = s.nextLine();
			    		while(bookTitle.equals("")) {
			    			System.out.println("Invalid Input. Enter Again.");
			    			bookTitle = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the TextBook's author: ");
			    		bookAuthor = s.nextLine();
			    		while(bookAuthor.equals("")) {
			    			System.out.println("Invalid Input. Enter Again.");
			    			bookAuthor = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the TextBook's publication date: ");
			    		publishYear = s.nextInt();
			    		while(publishYear < -2600 || publishYear > 2017) {
			    			try {
			    				publishYear = s.nextInt();
			    			}
			    			catch(InputMismatchException ex) {
			    				System.out.println("Invalid Input");
			    				s.nextLine();
			    			}
			    		}	
			    		s.nextLine();
			    		
			    		System.out.print("Please enter the TextBook's ISBN: ");
			    		ISBN = s.nextLine();
			    		while(ISBN.length() != 10 && ISBN.length() != 15) {
			    			System.out.println("ISBN must be 10 or 15 characters long");
			    			ISBN = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the TextBook's price: ");
			    		try {
			    				bookPrice = s.nextDouble();
					}
					catch(InputMismatchException ex) {
						System.out.println("Invalid Input");
						s.nextLine();
					}
			    		while(bookPrice < 0) {
			    			try {
			    				bookPrice = s.nextDouble();
			    			}
			    			catch(InputMismatchException ex) {
			    				System.out.println("Invalid Input");
			    				s.nextLine();
			    			}
			    		}
			    		
			    		s.nextLine();
			    		System.out.print("Please enter the TextBook's Subject: ");
			    		subject = s.nextLine();
			    		
			    		System.out.print("Please enter the TextBook's Workbook ISBN: ");
			    		workBookISBN = s.nextLine();
			    		while(workBookISBN.length() != 10 && workBookISBN.length() != 15) {
			    			System.out.println("workbook ISBN must be 10 or 15 characters long");
			    			workBookISBN = s.nextLine();
			    		}
			    		
			    		Textbook tempTB = new Textbook(bookTitle, bookAuthor, ISBN, subject, workBookISBN, publishYear, bookPrice);
		    			Book tb = (Book)tempTB;
		    			
		    			Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
		            while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                if(tb.equals(content)) {
		                		unique = false;
		                }
		            }
			    		if(unique == true) {
			    			bookHash.put(hashKey, tb);
			    			hashKey++;
			    		}
			    		else {
			    			System.out.println("\nBook is already in the store!");
			    		}
		    		}
		    		else if(bookOption == 3) {
		    			System.out.print("Please enter the Workbook's title : ");
			    		bookTitle = s.nextLine();
			    		while(bookTitle.equals("")) {
			    			System.out.println("Invalid Input. Enter Again.");
			    			bookTitle = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the Workbook's author: ");
			    		bookAuthor = s.nextLine();
			    		while(bookAuthor.equals("")) {
			    			System.out.println("Invalid Input. Enter Again.");
			    			bookAuthor = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the Workbook's publication date: ");
			    		publishYear = s.nextInt();
			    		while(publishYear < -2600 || publishYear > 2017) {
			    			try {
			    				publishYear = s.nextInt();
			    			}
			    			catch(InputMismatchException ex) {
			    				System.out.println("Invalid Input");
			    				s.nextLine();
			    			}
			    		}	
			    		s.nextLine();
			    		
			    		System.out.print("Please enter the Workbook's ISBN: ");
			    		ISBN = s.nextLine();
			    		while(ISBN.length() != 10 && ISBN.length() != 15) {
			    			System.out.println("ISBN must be 10 or 15 characters long");
			    			ISBN = s.nextLine();
			    		}
			    		
			    		System.out.print("Please enter the Workbook's price: ");
			    		try {
			    				bookPrice = s.nextDouble();
					}
					catch(InputMismatchException ex) {
						System.out.println("Invalid Input");
						s.nextLine();
					}
			    		while(bookPrice < 0) {
			    			try {
			    				bookPrice = s.nextDouble();
			    			}
			    			catch(InputMismatchException ex) {
			    				System.out.println("Invalid Input");
			    				s.nextLine();
			    			}
			    		}
			    		
			    		s.nextLine();
			    		System.out.print("Please enter the TextBook's Number of Problems: ");
			    		numProblems = s.nextInt();
			    		
			    		Workbook tempWB = new Workbook(bookTitle, bookAuthor, ISBN, numProblems, publishYear, bookPrice);
		    			Book tb = (Book)tempWB;
		    			
		    			Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
		            while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                if(tb.equals(content)) {
		                		unique = false;
		                }
		            }
			    		if(unique == true) {
			    			bookHash.put(hashKey, tb);
			    			hashKey++;
			    		}
			    		else {
			    			System.out.println("\nBook is already in the store!");
			    		}
		    		}
		    		else {
		    			System.out.println("Invalid Option.");
		    		}
			}
			else if(menuInput == 2) {
				if(bookHash.size() != 0) {
					System.out.println("The Bookstore Contains:\n");
					Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
		            while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                content.printInfo();
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
				
				if(bookHash.size() != 0) {
					System.out.println("Unique Authors:");
					Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
					while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                String author = content.getAuthor();
		                if((printAuthors.contains(author)) == false) {
							System.out.println(author);
							printAuthors.add(author);
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
				if(bookHash.size() != 0) {
					Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
					while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                averagePrice = averagePrice + content.getPrice();
		            }
					System.out.println("Average Book Price = " + df.format(averagePrice / bookHash.size()));
					System.out.println("Number of Books: " + bookHash.size());
				}
				else {
					System.out.println("The bookstore contains no books!");
				}	
			}
			else if(menuInput == 5) {
				if(bookHash.size() != 0) {
					for(int i = 0; i < bookHash.size(); i++) {
						if (bookHash.get(i).getClass().getCanonicalName().equals("Textbook")) {
		            			String textBookISBN = ((Textbook)bookHash.get(i)).getWbISBN();
		            			for (int j = 0; j < bookHash.size(); j++) {
		            				if (bookHash.get(j).getClass().getCanonicalName().equals("Workbook")) {
		            					if (textBookISBN.equals(bookHash.get(j).getISBN())) {
		            						System.out.println("Textbook: " + bookHash.get(i).getTitle() + " ¡=¿ Workbook: " + bookHash.get(j).getTitle());	            						
		            					}
		            				}
		            			}
						}
					}
				}
				else {
					System.out.println("The bookstore contains no books!");
				}
			}
			else if(menuInput == 6) {
				String fileName = "output.txt";
				// Write to a file
		        BufferedWriter writer;
		        try{
		            writer = new BufferedWriter(new FileWriter(fileName));
		            int i = 0;
		    			if (bookHash.size() != 0) {
		    				for (i = 0; i < bookHash.size(); i++) {
		    					if (bookHash.get(i).getClass().getCanonicalName().equals("Book")){
		    						writer.write("normalbook-" + bookHash.get(i).getTitle() + "-" +
	    								bookHash.get(i).getAuthor() + "-" +
	    								bookHash.get(i).getYear() + "-" +
	    								bookHash.get(i).getISBN() + "-" +
	    								bookHash.get(i).getPrice() + "\n");
		    					}
		    					if (bookHash.get(i).getClass().getCanonicalName().equals("Textbook")){
		    						writer.write("textbook-" + bookHash.get(i).getTitle() + "-" +
	    								bookHash.get(i).getAuthor() + "-" +
	    								bookHash.get(i).getYear() + "-" +
	    								bookHash.get(i).getISBN() + "-" +
	    								bookHash.get(i).getPrice() + "-" +
	    								((Textbook) bookHash.get(i)).getSubject() + "-" +
	    								((Textbook) bookHash.get(i)).getWbISBN()+ "\n");
		    					}
		    					if (bookHash.get(i).getClass().getCanonicalName().equals("Workbook")){
		    						writer.write("workbook-" + bookHash.get(i).getTitle() + "-" +
	    								bookHash.get(i).getAuthor() + "-" +
	    								bookHash.get(i).getYear() + "-" +
	    								bookHash.get(i).getISBN() + "-" +
	    								bookHash.get(i).getPrice() + "-" +
	    								((Workbook) bookHash.get(i)).getProblems() + "\n");
		    					}			
		    				}
		    			} else {
		    				
		    			}
		            writer.close();
		        } catch(IOException e){
		            System.out.println("Failed to write to "+fileName+".");
		        }
			}
			else if(menuInput == 7) {
				String fileName = "output.txt";
		        // Read the file
		        BufferedReader reader;
		        try{
		            reader = new BufferedReader(new FileReader(fileName));
		            String line = reader.readLine();
		            bookHash.clear();
		            while (line != null){
		            		String [] wordSplit;
		            		wordSplit = line.split("-");
		            		if (wordSplit[0].equals("normalbook")) {
		            			String title = wordSplit[1];
		            			String author = wordSplit[2];
		            			int year = Integer.parseInt(wordSplit[3]);
		            			String ISBN = wordSplit[4];
		            			double price = Double.parseDouble(wordSplit[5]);
		            			Book b = new Book (title, author, ISBN, year, price);
		        				bookHash.put(hashKey, b);
		        				hashKey++;
		            		}
		            		if (wordSplit[0].equals("textbook")) {
		            			String title = wordSplit[1];
		            			String author = wordSplit[2];
		            			int year = Integer.parseInt(wordSplit[3]);
		            			String ISBN = wordSplit[4];
		            			double price = Double.parseDouble(wordSplit[5]);
		            			String subject = wordSplit[6];
		            			String wbISBN = wordSplit[7];
		            			Textbook b = new Textbook (title, author, ISBN, subject, wbISBN, year, price);
		            			bookHash.put(hashKey, b);
		        				hashKey++;
		            		}
		            		if (wordSplit[0].equals("workbook")) {
		            			String title = wordSplit[1];
		            			String author = wordSplit[2];
		            			int year = Integer.parseInt(wordSplit[3]);
		            			String ISBN = wordSplit[4];
		            			double price = Double.parseDouble(wordSplit[5]);
		            			int numProblems = Integer.parseInt(wordSplit[6]);
		            			Workbook b = new Workbook (title, author, ISBN, numProblems, year, price);
		            			bookHash.put(hashKey, b);
		        				hashKey++;
		            		}
		            		// goto next line in file
		                line = reader.readLine();
		            }
		            reader.close();
		        } catch(IOException e){
		            System.out.println("Failed to read " + fileName + ".");
		        }
			}
			else if(menuInput == 8) {
				
				menuLoop = false;
			}
			else {
				System.out.println("Invalid Input");
			}
		}
		while(menuLoop == true);
		s.close();
	}
}
