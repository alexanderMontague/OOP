import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Bookstore extends JFrame{
	
	
	private HashMap<Integer, Book> bookHash = new HashMap<>();
	private int hashKey = 0;
    
	private String bookTitle = "NULL";
	private String bookAuthor = "NULL";
	private String ISBN = "NULL";
	private String subject = "NULL";
	private String workBookISBN = "NULL";
	private int publishYear = -99999;
	private int numProblems = 0;
	private double bookPrice = -1;
	private boolean unique = true;
	private boolean badInput = false;
	
	public Bookstore() {
		super("*** Book Library ***");
				
		// Title
		setTitle("Book Library Application");
		
		// Menu Bar
		JMenuItem save = new JMenuItem("Save As...");
		JMenuItem load = new JMenuItem("Load File...");
		JMenuItem quit = new JMenuItem("Quit");
		JMenu menu = new JMenu("File");
		menu.add(save);
		menu.add(load);
		menu.add(quit);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		// JPanels Top/Bot left right
		JPanel topLeft = new JPanel(new GridLayout(2, 2));
		JPanel topRight = new JPanel(new GridLayout(3, 0));
		JPanel botLeft = new JPanel(new GridLayout());
		botLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		JPanel botRight = new JPanel(new GridLayout(8, 1));
		
		// Buttons
		JButton button1 = new JButton("List Books");
        JButton button2 = new JButton("Unique Authors");
        JButton button3 = new JButton("Average Price");
        JButton button4 = new JButton("Textbook-Workbook Pairs");
        JButton button5 = new JButton("Add Book");
        JButton button6 = new JButton("Add Textbook");
        JButton button7 = new JButton("Add Workbook");
        
        // Text Display Area
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setText("Choose an Option or Enter Book Information\n");
        JScrollPane textArea2 = new JScrollPane(textArea);
        textArea2.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        
        // Text Input Boxes
        JTextField title = new JTextField(5);
        JLabel titleL = new JLabel("Title: ");
        JTextField author = new JTextField(15);
        JLabel authorL = new JLabel("Author: ");
        JTextField year = new JTextField(15);
        JLabel yearL = new JLabel("Year Published: ");
        JTextField ISBNbox = new JTextField(15);
        JLabel ISBNL = new JLabel("ISBN: ");
        JTextField price = new JTextField(15);
        JLabel priceL = new JLabel("Price: ");
        JTextField subjectBox = new JTextField(15);
        JLabel subjectL = new JLabel("Subject (Textbook Only): ");
        JTextField workbookISBN = new JTextField(15);
        JLabel workbookISBNL = new JLabel("Workbook ISBN (Textbook Only): ");
        JTextField numProblemsBox = new JTextField(15);
        JLabel numProblemsL = new JLabel("Number of Problems (Workbook Only): ");
		
		// JFrame Layout
		setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2, 5, 5));
        
        // Logic Code
        
        // MAIN OPTIONS TOP LEFT
        button1.addActionListener(new ActionListener() {	// LIST BOOKS
            @Override
            public void actionPerformed(ActionEvent ae) {
            	textArea.setText("");
	        		if(bookHash.size() != 0) {
					textArea.append("The Bookstore Contains:\n");
					Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
		            while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                if(content.getClass().getCanonicalName().equals("Book")) {
		                		textArea.append("\nTitle: " + content.getTitle() + "\nAuthors: " + content.getAuthor() + "\nYear Published: " + content.getYear() + "\nISBN: " + content.getISBN() + "\nPrice: " + content.getPrice() + "\n");
		                }
		                else if(content.getClass().getCanonicalName().equals("Textbook")) {
		                		Textbook content2 = (Textbook)content;
		                		textArea.append("\nTitle: " + content2.getTitle() + "\nAuthors: " + content2.getAuthor() + "\nYear Published: " + content2.getYear() + "\nISBN: " + content2.getISBN() + "\nPrice: " + content2.getPrice() + "\nSubject: " + content2.getSubject() + "\nWorkbook ISBN: " + content2.getWbISBN() + "\n");
		                }
		                else if(content.getClass().getCanonicalName().equals("Workbook")) {
		                		Workbook content3 = (Workbook)content;
		                		textArea.append("\nTitle: " + content3.getTitle() + "\nAuthors: " + content3.getAuthor() + "\nYear Published: " + content3.getYear() + "\nISBN: " + content3.getISBN() + "\nPrice: " + content3.getPrice() + "\nNumber of Problems: " + content3.getProblems() + "\n");
		                }
		            }
				}
				else {
					textArea.setText("The bookstore contains no books!\n");
				}
            }
        });
        button2.addActionListener(new ActionListener() {	// UNIQUE AUTHORS
            @Override
            public void actionPerformed(ActionEvent ae) {
            		textArea.setText("");
            		ArrayList<String> printAuthors = new ArrayList<String>();
				printAuthors.clear();
				if(bookHash.size() != 0) {
					textArea.append("Unique Authors: ");
					Set<Integer> keys = bookHash.keySet();
		            Iterator<Integer> iter = keys.iterator();
					while (iter.hasNext()) {
		                Integer key = iter.next();
		                Book content = bookHash.get(key);
		                String author = content.getAuthor();
		                if((printAuthors.contains(author)) == false) {
							textArea.append(author + "\n");
							printAuthors.add(author);
						}
		            }
				}
				else {
					textArea.setText("The bookstore contains no books!\n");
				}
            }
        });
        button3.addActionListener(new ActionListener() {	// AVERAGE PRICE
            @Override
            public void actionPerformed(ActionEvent ae) {
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
					textArea.append("\nNumber of Books: " + bookHash.size());
				}
				else {
					textArea.setText("The bookstore contains no books!\n");
				}
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
        			textArea.setText("");	
        			if(bookHash.size() != 0) {
        				Set<Integer> keys = bookHash.keySet();
    		            Iterator<Integer> iter = keys.iterator();
    					while (iter.hasNext()) {
    						Integer i = iter.next();
						if (bookHash.get(i).getClass().getCanonicalName().equals("Textbook")) {
		            			String textBookISBN = ((Textbook)bookHash.get(i)).getWbISBN();
		            			Set<Integer> keys2 = bookHash.keySet();
		    		            Iterator<Integer> iter2 = keys2.iterator();
		    					while (iter2.hasNext()) {
		    						Integer j = iter2.next();
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
					textArea.setText("The bookstore contains no books!\n");
				}
            }
        });
        button5.addActionListener(new ActionListener() {	// ADD BOOK
            @Override
            public void actionPerformed(ActionEvent ae) {
	            	unique = true;
	            	badInput = false;
	            	textArea.setText("");
	    			bookTitle = title.getText();
		    		bookAuthor = author.getText();
		    		String temp = year.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a year!\n");
		    			badInput = true;
		    		}
		    		else {
		    			publishYear = Integer.parseInt(year.getText());
		    		}
		    		ISBN = ISBNbox.getText();
		    		temp = price.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a price!\n");
		    			badInput = true;
		    		}
		    		else {
		    			bookPrice = Double.parseDouble(price.getText());
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
		    		if(unique == true && badInput != true) {
		    			hashKey = tempBook.hashCode();
		    			bookHash.put(hashKey, tempBook);
		    			textArea.setText("Book Succesfully Added.");
		    			title.setText("");
		    			author.setText("");
		    			year.setText("");
		    			ISBNbox.setText("");
		    			price.setText("");
		    			subjectBox.setText("");
		    			workbookISBN.setText("");
		    			numProblemsBox.setText("");
		    		}
		    		else if(badInput == true) {
		    			// Dont add anything
		    		}
		    		else if(unique == false) {
		    			textArea.setText("Book is already in the store!");
		    		}
            }
        });
        
        button6.addActionListener(new ActionListener() {	// ADD TEXTBOOK
            @Override
            public void actionPerformed(ActionEvent ae) {
	            	unique = true;
	            	badInput = false;
	            	textArea.setText("");
	    			bookTitle = title.getText();
		    		bookAuthor = author.getText();
		    		String temp = year.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a year!\n");
		    			badInput = true;
		    		}
		    		else {
		    			publishYear = Integer.parseInt(year.getText());
		    		}
		    		ISBN = ISBNbox.getText();
		    		temp = price.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a price!\n");
		    			badInput = true;
		    		}
		    		else {
		    			bookPrice = Double.parseDouble(price.getText());
		    		}
		    		temp = subjectBox.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a subject!\n");
		    			badInput = true;
		    		}
		    		else {
		    			subject = subjectBox.getText();
		    		}
		    		temp = workbookISBN.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a workbook ISBN!\n");
		    			badInput = true;
		    		}
		    		else {
		    			workBookISBN = workbookISBN.getText();
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
		    		if(unique == true && badInput != true) {
		    			hashKey = tb.hashCode();
		    			bookHash.put(hashKey, tb);
		    			textArea.setText("Textbook Succesfully Added.");
		    			title.setText("");
		    			author.setText("");
		    			year.setText("");
		    			ISBNbox.setText("");
		    			price.setText("");
		    			subjectBox.setText("");
		    			workbookISBN.setText("");
		    			numProblemsBox.setText("");
		    		}
		    		else if(badInput == true) {
		    			// Dont add anything
		    		}
		    		else if(unique == false) {
		    			textArea.setText("Textbook is already in the store!");
		    		}
            }
        });
        
        button7.addActionListener(new ActionListener() {	// ADD WORKBOOK
            @Override
            public void actionPerformed(ActionEvent ae) {
	            	unique = true;
	            	badInput = false;
	            	textArea.setText("");
	    			bookTitle = title.getText();
		    		bookAuthor = author.getText();
		    		String temp = year.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a year!\n");
		    			badInput = true;
		    		}
		    		else {
		    			publishYear = Integer.parseInt(year.getText());
		    		}
		    		ISBN = ISBNbox.getText();
		    		temp = price.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter a price!\n");
		    			badInput = true;
		    		}
		    		else {
		    			bookPrice = Double.parseDouble(price.getText());
		    		}
		    		temp = numProblemsBox.getText();
		    		if(temp.equals("")) {
		    			textArea.append("You must enter the number of problems!\n");
		    			badInput = true;
		    		}
		    		else {
		    			numProblems = Integer.parseInt(numProblemsBox.getText());
		    		}
		    		
		    		Workbook tempTB = new Workbook(bookTitle, bookAuthor, ISBN, numProblems, publishYear, bookPrice);
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
		    		if(unique == true && badInput != true) {
		    			hashKey = tb.hashCode();
		    			bookHash.put(hashKey, tb);
		    			textArea.setText("Workbook Succesfully Added.");
		    			title.setText("");
		    			author.setText("");
		    			year.setText("");
		    			ISBNbox.setText("");
		    			price.setText("");
		    			subjectBox.setText("");
		    			workbookISBN.setText("");
		    			numProblemsBox.setText("");
		    		}
		    		else if(badInput == true) {
		    			// Dont add anything
		    		}
		    		else if(unique == false) {
		    			textArea.setText("Workbook is already in the store!");
		    		}
            }
        });
        
        save.addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent ae) {
            		String fileName = JOptionPane.showInputDialog("Enter a File Name / Path to Save the Library to\nEx. 'output.txt'");
            		textArea.setText("");
    		        BufferedWriter writer;
    		        try{
    		            writer = new BufferedWriter(new FileWriter(fileName));
    		    			if (bookHash.size() != 0) {
    		    				Set<Integer> keys = bookHash.keySet();
    	    		            Iterator<Integer> iter = keys.iterator();
    	    					while (iter.hasNext()) {
    	    						Integer i = iter.next();
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
    		    				JOptionPane.showMessageDialog(null, "There are no Books in the Library.", "No Books Fam", 0);
    		    			}
    		            writer.close();
    		        } catch(IOException e){
    		            textArea.append("Failed to write to "+fileName+".");
    		        }
            }
        });
        
        load.addActionListener(new ActionListener() {	// ADD WORKBOOK
            @Override
            public void actionPerformed(ActionEvent ae) {
            		String fileName = JOptionPane.showInputDialog("Enter a File Name / Path to Load the Library From\nEx. 'output.txt'");
            		textArea.setText("");
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
        });
        quit.addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent ae) {
            		System.exit(0);
            }
        });
        
        // Adding to GUI
        topLeft.add(button1);
        topLeft.add(button2);
        topLeft.add(button3);
        topLeft.add(button4);
        
        topRight.add(button5);
        topRight.add(button6);
        topRight.add(button7);
       
        botLeft.add(textArea2);
        
        botRight.add(titleL);
        botRight.add(title);
        botRight.add(authorL);
        botRight.add(author);
        
        botRight.add(yearL);
        botRight.add(year);
        botRight.add(ISBNL);
        botRight.add(ISBNbox);
        botRight.add(priceL);
        botRight.add(price);
        botRight.add(subjectL);
        botRight.add(subjectBox);
        botRight.add(workbookISBNL);
        botRight.add(workbookISBN);
        botRight.add(numProblemsL);
        botRight.add(numProblemsBox);
        
       
        add(topLeft);
        add(topRight);
        add(botLeft);
        add(botRight);
         
	}
	
	public void run() {
		System.out.println("What do I put here?!");
	}
	
}
