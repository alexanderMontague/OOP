import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Bookstore extends JFrame{
	
	
	private HashMap<Integer, Book> bookHash;
	private int hashKey = 0;
	public JTextField textField; // Edit Field
    public JTextArea textArea;   // Display Field
    private JLabel inputLabel;	// Input Label
    private String menuInput = "NULL";
    private Boolean menuLoop = false;
    
	private String bookTitle = "NULL";
	private String bookAuthor = "NULL";
	private String ISBN = "NULL";
	private String subject = "NULL";
	private String workBookISBN = "NULL";
	private int bookOption = 0;
	private int publishYear = -99999;
	private int numProblems = 0;
	private double bookPrice = -1;
	private boolean unique = true;
	
	public Bookstore() {
		super("*** Book Library ***");
		
		Scanner s = new Scanner(System.in);
		
		this.bookHash = new HashMap<>();
		setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        textField = new JTextField(30);
        inputLabel = new JLabel("Enter an Option:");

        // TextArea should be used to store output
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
		textArea.setText("1. Add a unique book, textbook or workbook to the library."
					 + "\n2. Print out information on each book"
				     + "\n3. Print out all unique authors"
				     + "\n4. Print out the average book price, along with the total number of books"
				     + "\n5. Print out all Textbook-Workbook pairs in the inventory"
				     + "\n6. Save the state of the inventory to a file"
				     + "\n7. Load the state of the inventory from a file"
				     + "\n8. Quit\n\n");
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(inputLabel);
        add(textField);
        add(scrollPane);
        
        textField.addActionListener(new ActionListener(){
        		@Override
        		public void actionPerformed(ActionEvent ae) {
        	        	menuInput = textField.getText();
        	        
        	        
            		
        	        if(menuInput.equals("1")) {
            			textArea.setText("");
        		    		textArea.append("1. Add a Book\n");
        		    		textArea.append("2. Add a Textbook\n");
        		    		textArea.append("3. Add a Workbook\n\n");
        		    		bookOption = Integer.parseInt(textField.getText());
        		    		
        		        	
        		    		if(bookOption == 1) {
        		    			textArea.append("Please enter the book's title\n");
        		    			bookTitle = textField.getText();

        			    		textArea.append("Please enter the book's author\n");
        			    		bookAuthor = textField.getText();

        			    		textArea.append("Please enter the book's publication date\n");
        			    		publishYear = Integer.parseInt(textField.getText());

        			    		textArea.append("Please enter the book's ISBN\n");
        			    		ISBN = textField.getText();

        			    		textArea.append("Please enter the book's price\n");
        			    		bookPrice = Double.parseDouble(textField.getText());
 
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
        			    			textArea.append("\nBook is already in the store!");
        			    		}
        		    		}
        		    		else if(bookOption == 2) {
        		    			System.out.print("Please enter the TextBook's title : ");
        			    		bookTitle = s.nextLine();
        			    		while(bookTitle.equals("")) {
        			    			textArea.append("Invalid Input. Enter Again.");
        			    			bookTitle = s.nextLine();
        			    		}
        			    		
        			    		System.out.print("Please enter the TextBook's author: ");
        			    		bookAuthor = s.nextLine();
        			    		while(bookAuthor.equals("")) {
        			    			textArea.append("Invalid Input. Enter Again.");
        			    			bookAuthor = s.nextLine();
        			    		}
        			    		
        			    		System.out.print("Please enter the TextBook's publication date: ");
        			    		publishYear = s.nextInt();
        			    		while(publishYear < -2600 || publishYear > 2017) {
        			    			try {
        			    				publishYear = s.nextInt();
        			    			}
        			    			catch(InputMismatchException ex) {
        			    				textArea.append("Invalid Input");
        			    				s.nextLine();
        			    			}
        			    		}	
        			    		s.nextLine();
        			    		
        			    		System.out.print("Please enter the TextBook's ISBN: ");
        			    		textField.addActionListener(new ActionListener(){
	        		        		public void actionPerformed(ActionEvent ae) {
	        		        			ISBN = s.nextLine();
	        		        		}
        			    		});
        			    		while(ISBN.length() != 10 && ISBN.length() != 15) {
        			    			textArea.append("ISBN must be 10 or 15 characters long");
        			    			textField.addActionListener(new ActionListener(){
    	        		        			public void actionPerformed(ActionEvent ae) {
    	        		        				ISBN = s.nextLine();
    	        		        			}
        			    			});
        			    		}
        			    		
        			    		System.out.print("Please enter the TextBook's price: ");
        			    		try {
        			    			textField.addActionListener(new ActionListener(){
    	        		        			public void actionPerformed(ActionEvent ae) {
    	        		        				bookPrice = Double.parseDouble(textField.getText());
    	        		        			}
        			    			});
        					}
        					catch(InputMismatchException ex) {
        						textArea.append("Invalid Input");
        					}
        			    		
        			    		System.out.print("Please enter the TextBook's Subject: ");
        			    		textField.addActionListener(new ActionListener(){
	        		        		public void actionPerformed(ActionEvent ae) {
	        		        			subject = s.nextLine();
	        		        		}
        			    		});
        			    		
        			    		System.out.print("Please enter the TextBook's Workbook ISBN: ");
        			    		workBookISBN = s.nextLine();
        			    		while(workBookISBN.length() != 10 && workBookISBN.length() != 15) {
        			    			textArea.append("workbook ISBN must be 10 or 15 characters long");
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
        			    			textArea.append("\nBook is already in the store!");
        			    		}
        		    		}
        		    		else if(bookOption == 3) {
        		    			System.out.print("Please enter the Workbook's title : ");
        			    		bookTitle = s.nextLine();
        			    		while(bookTitle.equals("")) {
        			    			textArea.append("Invalid Input. Enter Again.");
        			    			bookTitle = s.nextLine();
        			    		}
        			    		
        			    		System.out.print("Please enter the Workbook's author: ");
        			    		bookAuthor = s.nextLine();
        			    		while(bookAuthor.equals("")) {
        			    			textArea.append("Invalid Input. Enter Again.");
        			    			bookAuthor = s.nextLine();
        			    		}
        			    		
        			    		System.out.print("Please enter the Workbook's publication date: ");
        			    		publishYear = s.nextInt();
        			    		while(publishYear < -2600 || publishYear > 2017) {
        			    			try {
        			    				publishYear = s.nextInt();
        			    			}
        			    			catch(InputMismatchException ex) {
        			    				textArea.append("Invalid Input");
        			    				s.nextLine();
        			    			}
        			    		}	
        			    		s.nextLine();
        			    		
        			    		System.out.print("Please enter the Workbook's ISBN: ");
        			    		ISBN = s.nextLine();
        			    		while(ISBN.length() != 10 && ISBN.length() != 15) {
        			    			textArea.append("ISBN must be 10 or 15 characters long");
        			    			ISBN = s.nextLine();
        			    		}
        			    		
        			    		System.out.print("Please enter the Workbook's price: ");
        			    		try {
        			    				bookPrice = s.nextDouble();
        					}
        					catch(InputMismatchException ex) {
        						textArea.append("Invalid Input");
        						s.nextLine();
        					}
        			    		while(bookPrice < 0) {
        			    			try {
        			    				bookPrice = s.nextDouble();
        			    			}
        			    			catch(InputMismatchException ex) {
        			    				textArea.append("Invalid Input");
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
        			    			textArea.append("\nBook is already in the store!");
        			    		}
        		    		}
        		    		else {
        		    			textArea.append("Invalid Option.");
        		    		}
                }
                else if(menuInput.equals("2")) {
                		textArea.setText("");
                		if(bookHash.size() != 0) {
        					textArea.append("The Bookstore Contains:\n");
        					Set<Integer> keys = bookHash.keySet();
        		            Iterator<Integer> iter = keys.iterator();
        		            while (iter.hasNext()) {
        		                Integer key = iter.next();
        		                Book content = bookHash.get(key);
        		                //if(content.toString()) {
        		                		textArea.append("Title: " + content.getTitle() + "\nAuthors: " + content.getAuthor() + "\nYear Published: " + content.getYear() + "\nISBN: " + content.getISBN() + "\nPrice: " + content.getPrice());
        		               // }
        		            }
        				}
        				else {
        					textArea.append("The bookstore contains no books!\n");
        				}
                }
                else if(menuInput.equals("3")) {
                		textArea.setText("");
                		ArrayList<String> printAuthors = new ArrayList<String>();
        				printAuthors.clear();
        				
        				if(bookHash.size() != 0) {
        					textArea.append("Unique Authors:");
        					Set<Integer> keys = bookHash.keySet();
        		            Iterator<Integer> iter = keys.iterator();
        					while (iter.hasNext()) {
        		                Integer key = iter.next();
        		                Book content = bookHash.get(key);
        		                String author = content.getAuthor();
        		                if((printAuthors.contains(author)) == false) {
        							textArea.append(author);
        							printAuthors.add(author);
        						}
        		            }
        				}
        				else {
        					textArea.append("The bookstore contains no books!\n");
        				}
                }
                else if(menuInput.equals("4")) {
                		textArea.setText("");
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
        					textArea.append("Average Book Price = " + df.format(averagePrice / bookHash.size()));
        					textArea.append("Number of Books: " + bookHash.size());
        				}
        				else {
        					textArea.append("The bookstore contains no books!\n");
        				}	
                }
                else if(menuInput.equals("5")) {
                		textArea.setText("");	
                		if(bookHash.size() != 0) {
        					for(int i = 0; i < bookHash.size(); i++) {
        						if (bookHash.get(i).getClass().getCanonicalName().equals("Textbook")) {
        		            			String textBookISBN = ((Textbook)bookHash.get(i)).getWbISBN();
        		            			for (int j = 0; j < bookHash.size(); j++) {
        		            				if (bookHash.get(j).getClass().getCanonicalName().equals("Workbook")) {
        		            					if (textBookISBN.equals(bookHash.get(j).getISBN())) {
        		            						textArea.append("Textbook: " + bookHash.get(i).getTitle() + " ¡=¿ Workbook: " + bookHash.get(j).getTitle());	            						
        		            					}
        		            				}
        		            			}
        						}
        					}
        				}
        				else {
        					textArea.append("The bookstore contains no books!\n");
        				}
                }
                else if(menuInput.equals("6")) {
                		textArea.setText("");
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
        		            textArea.append("Failed to write to "+fileName+".");
        		        }
                }
                else if(menuInput.equals("7")) {
                		textArea.setText("");
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
        		            textArea.append("Failed to read " + fileName + ".");
        		        }
                }
                else if(menuInput.equals("8")) {
                		textArea.setText("");
                		menuLoop = false;
                		System.exit(0);
                }
                else {
                		textArea.setText("");
                		textArea.setText("Invalid Selection!\n");
                }
        	        textField.setText("");
        	        textArea.append("\n1. Add a unique book, textbook or workbook to the library."
       					 + "\n2. Print out information on each book"
       				     + "\n3. Print out all unique authors"
       				     + "\n4. Print out the average book price, along with the total number of books"
       				     + "\n5. Print out all Textbook-Workbook pairs in the inventory"
       				     + "\n6. Save the state of the inventory to a file"
       				     + "\n7. Load the state of the inventory from a file"
       				     + "\n8. Quit\n\n");
	        }
        }); 
	}
	
	public void run() {
		System.out.println("What do I put here?!");
	}
	
}
