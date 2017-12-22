// Alexander Montague
// amontagu@uoguelph.ca
// 0959687
// November - 2017
// Portfolio Main Class
// Assignment 2

/*
 * 
 * This is the portfolio class, where main is held, and the main functionality for this program contained.
 * This class handles the user input, and storage of data in ArrayLists.
 * 
 */
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Portfolio extends JFrame{
	
	private HashMap<String, ArrayList<Integer>> keyWordMap;
	private ArrayList<Investment> investmentList;
	private static String fileName;
	
	private String type = "NULL";
	private String Symbol;
	private String Name;
	private String Quantity;
	private String Price;
	private int index = 0;
	private int updateIndex = 0;
	private double BookVal = 0;
	private boolean valid = true;
	private boolean isDup = false;
	private boolean addStock = true;
	private boolean firstStock = true;
	private boolean firstFund = true;
	private boolean sold = false;
	
	public Portfolio() {
		
		this.investmentList = new ArrayList<Investment>();
		this.keyWordMap = new HashMap<String, ArrayList<Integer>>();
		
		// JFrame Layout
		setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel baseLayout = new JPanel(new GridLayout(2, 1));
		
		// Title
		setTitle("Investment Portfolio");
		
		// Menu Bar
		JMenuItem buy = new JMenuItem("BUY");
		JMenuItem sell = new JMenuItem("SELL");
		JMenuItem update = new JMenuItem("UPDATE");
		JMenuItem gain = new JMenuItem("GAIN");
		JMenuItem search = new JMenuItem("SEARCH");
		JMenuItem quit = new JMenuItem("QUIT");
		JMenu menu = new JMenu("Commands");
		menu.add(buy);
		menu.add(sell);
		menu.add(update);
		menu.add(gain);
		menu.add(search);
		menu.add(quit);
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		// JPanel Layouts
		// Top Panel
		JPanel top = new JPanel(new GridLayout());
		top.setBorder(new EmptyBorder(20, 20, 20, 20));
		JTextArea textArea = new JTextArea(50, 200);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setText("Welcome to the Investment Portfolio\n\nChoose a command from the \"Commands\" menu!\n"
        				 + "The commands are as follows: \n\n" +
        				   "BUY\t-      Own a new investment, or purchase more of an existing investment\n" + 
        				   "SELL\t-      Sell some quantity of an existing investment\n" + 
        				   "UPDATE\t-      Update the prices of all existing investments\n" + 
        				   "GAIN\t-      Calculate the total gain of all investments\n" + 
        				   "SEARCH\t-      Search for an existing investment in the portfolio\n" + 
        				   "QUIT\t-      Quit the program\n"
        				 + "\nThis program will automatically import contacts from the specified file if available,\n"
        				 + "as well as automatically save to the specified file on GUI closure.");
        top.add(textArea);
		baseLayout.add(top);
		add(baseLayout);
		
		// Bottom Panel
		JPanel bottom = new JPanel(new BorderLayout(20, 20));
		
		buy.addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent ae) {
            	
            		// Top Panel Portion
            		top.removeAll();
            		top.invalidate();
            		bottom.removeAll();
            		bottom.invalidate();
            		top.setLayout(new GridLayout(1, 2));
            		JPanel textFields = new JPanel(new GridLayout(5, 1));
            		JPanel buttons = new JPanel(new GridLayout(2, 1));
            		textFields.setBorder(BorderFactory.createTitledBorder("Buying an Investment"));
            		buttons.setBorder(BorderFactory.createTitledBorder(""));
            		JComboBox<String> dropDown = new JComboBox<>();
            		dropDown.addItem("Stock");
            		dropDown.addItem("Mutual Fund");
            		
            		JLabel typeL = new JLabel("Type:");
            		JTextField symbol = new JTextField(15);
            		JLabel symbolL = new JLabel("Symbol:");
            		JTextField name = new JTextField(15);
            		JLabel nameL = new JLabel("Name:");
            		JTextField quantity = new JTextField();
            		JLabel quantityL = new JLabel("Quantity:");
            		JTextField price = new JTextField(15);
            		JLabel priceL = new JLabel("Price:");
            		
            		JButton reset = new JButton("Reset");
            		JButton buyButton = new JButton("Buy");
            		
            		baseLayout.add(top);
            		top.add(textFields);
            		top.add(buttons);
            		textFields.add(typeL);
            		textFields.add(dropDown);
            		textFields.add(symbolL);
            		textFields.add(symbol);
            		textFields.add(nameL);
            		textFields.add(name);
            		textFields.add(quantityL);
            		textFields.add(quantity);
            		textFields.add(priceL);
            		textFields.add(price);
            		buttons.add(reset);
            		buttons.add(buyButton);
            		top.revalidate();
            		top.repaint();
            		
            		// Bottom Panel Portion
            		JTextArea display = new JTextArea();
            		display.setEditable(false);
                display.setLineWrap(true);
                display.setText("Enter Investment Information in text boxes Above!\n");
                JScrollPane textArea2 = new JScrollPane(display);
                textArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            		bottom.setBorder(BorderFactory.createTitledBorder("Messages"));
            		bottom.add(textArea2);
            		baseLayout.add(bottom);
            		bottom.revalidate();
            		bottom.repaint();
            		
            		dropDown.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		        JComboBox<String> combo = (JComboBox<String>)event.getSource();
            		        String investment = (String)combo.getSelectedItem();
            		        if (investment.equals("Stock")) {
            		            type = "Stock";
            		        } else if (investment.equals("Mutual Fund")) {
            		        		type = "Mutual Fund";
            		        }
            		    }
            		});
            		
            		reset.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		        symbol.setText("");
            		        name.setText("");
            		        quantity.setText("");
            		        price.setText("");
            		    }
            		});
            		
            		buyButton.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		    		valid = true;
            		    		Quantity = "";
            		    		Price = "";
            		    		display.setText("");
        		    			if(type.equals("NULL")) {
        		    				display.append("You must select a Type!\n");
        		    				valid = false;
        		    			}
            		    		Symbol = symbol.getText();
                    		if(Symbol.equals("")) {
                    			display.append("You must enter a Symbol!\n");
                    			valid = false;
                    		}
                    		Name = name.getText();
                    		if(Name.equals("")) {
                    			display.append("You must enter a Name!\n");
                    			valid = false;
                    		}
                    		Quantity = quantity.getText();
                    		if(Quantity.equals("") || !Quantity.matches("[1234567890-]+")) {
                    			display.append("You must enter a valid Quantity!\n");
                    			valid = false;
                    			
                    		}
                    		else {
                    			if(Double.parseDouble(Quantity) <= 0) {
                    				display.append("You must enter a quantity greater than 0!\n");
                    				valid = false;
                    			}
                    		}
                    		Price = price.getText();
                    		if(Price.equals("") || !Price.matches("[1234567890-]+")) {
                    			display.append("You must enter a valid Price!\n");
                    			valid = false;
                    			
                    		}
                    		else {
                    			if(Double.parseDouble(Price) <= 0) {
                    				display.append("You must enter a price greater than 0!\n");
                    				valid = false;
                    			}
                    		}
                    		
                    		if(type.equals("Stock") && valid == true) {                    			
                				isDup = false;
                				addStock = false;
        						BookVal = (Double.parseDouble(Quantity) * Double.parseDouble(Price)) + 9.99;
        						for(int i = 0; i < investmentList.size(); i++) {
        							if(Symbol.equals((investmentList.get(i)).getSym())) {
        								if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
        									display.setText("Duplicate Symbols are not Allowed!");
        									isDup = true;
        								}
        								else if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")){
        									if(Name.equals(investmentList.get(i).getName())) {
        										index = i;
            									addStock = true;
        									}
        									else {
            									isDup = true;
        									}
        								}
        							}
        						}
        						if(addStock == true) {
        							Stock tempStock = new Stock(Symbol, Name, Double.parseDouble(Quantity), Double.parseDouble(Price), BookVal);
        							tempStock = (Stock)investmentList.get(index);
        							tempStock.addQuantityStock(Double.parseDouble(Quantity), Double.parseDouble(Price));
        							display.setText("Successfully Added " + Quantity + " share(s) to the Stock");
        							symbol.setText("");
                    		        name.setText("");
                    		        quantity.setText("");
                    		        price.setText("");
        						}
        						else if(isDup == false) {
        							Stock tempStock2 = new Stock(Symbol, Name, Double.parseDouble(Quantity), Double.parseDouble(Price), BookVal);
        							investmentList.add((Investment)tempStock2);
        							display.append("Successfully Added the Stock\n");
        							symbol.setText("");
                    		        name.setText("");
                    		        quantity.setText("");
                    		        price.setText("");
        						}
        						else {
        							display.setText("Duplicate Symbols are not Allowed.");
        						}	
                    		}
                    		else if(type.equals("Mutual Fund") && valid == true) {
                    			isDup = false;
                				addStock = false;
        						BookVal = (Double.parseDouble(Quantity) * Double.parseDouble(Price));
        						for(int i = 0; i < investmentList.size(); i++) {
        							if(Symbol.equals((investmentList.get(i)).getSym())) {
        								if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
        									display.setText("Duplicate Symbols are not Allowed!");
        									isDup = true;
        								}
        								else if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")){
        									if(Name.equals(investmentList.get(i).getName())) {
        										index = i;
            									addStock = true;
        									}
        									else {
            									isDup = true;
        									}
        								}
        							}
        						}
        						if(addStock == true) {
        							MutualFund tempFund = new MutualFund(Symbol, Name, Double.parseDouble(Quantity), Double.parseDouble(Price), BookVal);
        							tempFund = (MutualFund)investmentList.get(index);
        							tempFund.addQuantityFund(Double.parseDouble(Quantity), Double.parseDouble(Price));
        							display.setText("Successfully Added " + Quantity + " share(s) to the Mutual Fund");
        							symbol.setText("");
                    		        name.setText("");
                    		        quantity.setText("");
                    		        price.setText("");
        						}
        						else if(isDup == false) {
        							MutualFund tempFund = new MutualFund(Symbol, Name, Double.parseDouble(Quantity), Double.parseDouble(Price), BookVal);
        							investmentList.add((Investment)tempFund);
        							display.append("Successfully Added the Mutual Fund\n");
        							symbol.setText("");
                    		        name.setText("");
                    		        quantity.setText("");
                    		        price.setText("");
        						}
        						else {
        							display.setText("Duplicate Symbols are not Allowed.");
        						}
                    		}
            		    }
            		});
            }
        });
		
		sell.addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent ae) {
	            	// Top Panel Portion
	        		top.removeAll();
	        		top.invalidate();
	        		bottom.removeAll();
	        		bottom.invalidate();
	        		top.setLayout(new GridLayout(1, 2));
	        		JPanel textFields = new JPanel(new GridLayout(3, 1));
	        		JPanel buttons = new JPanel(new GridLayout(2, 1));
	        		textFields.setBorder(BorderFactory.createTitledBorder("Selling an Investment"));
	        		buttons.setBorder(BorderFactory.createTitledBorder(""));
	        		
	        		JTextField symbol = new JTextField(15);
	        		JLabel symbolL = new JLabel("Symbol:");
	        		JTextField quantity = new JTextField(15);
	        		JLabel quantityL = new JLabel("Quantity:");
	        		JTextField price = new JTextField(15);
	        		JLabel priceL = new JLabel("Price:");
	        		
	        		JButton reset = new JButton("Reset");
	        		JButton sellButton = new JButton("Sell");
	        		
	        		baseLayout.add(top);
	        		top.add(textFields);
	        		top.add(buttons);
	        		textFields.add(symbolL);
	        		textFields.add(symbol);
	        		textFields.add(quantityL);
	        		textFields.add(quantity);
	        		textFields.add(priceL);
	        		textFields.add(price);
	        		buttons.add(reset);
	        		buttons.add(sellButton);
	        		top.revalidate();
	        		top.repaint();
	        		
	        		// Bottom Panel Portion
	        		JTextArea display = new JTextArea();
	        		display.setEditable(false);
	            display.setLineWrap(true);
	            display.setText("Enter Investment Information in text boxes Above!\n");
	            JScrollPane textArea2 = new JScrollPane(display);
	            textArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        		bottom.setBorder(BorderFactory.createTitledBorder("Messages"));
	        		bottom.add(textArea2);
	        		baseLayout.add(bottom);
	        		bottom.revalidate();
	        		bottom.repaint();
	        		
	        		reset.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		        symbol.setText("");
            		        quantity.setText("");
            		        price.setText("");
            		    }
            		});
            		
            		sellButton.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		    		valid = true;
            		    		Quantity = "";
            		    		Price = "";
            		    		display.setText("");
            		    		Symbol = symbol.getText();
                    		if(Symbol.equals("")) {
                    			display.append("You must enter a Symbol!\n");
                    			valid = false;
                    		}
                    		Quantity = quantity.getText();
                    		if(Quantity.equals("") || !Quantity.matches("[1234567890-]+")) {
                    			display.append("You must enter a valid Quantity!\n");
                    			valid = false;
                    			
                    		}
                    		else {
                    			if(Double.parseDouble(Quantity) <= 0) {
                    				display.append("You must enter a quantity greater than 0!\n");
                    				valid = false;
                    			}
                    		}
                    		Price = price.getText();
                    		if(Price.equals("") || !Price.matches("[1234567890-]+")) {
                    			display.append("You must enter a valid Price!\n");
                    			valid = false;
                    			
                    		}
                    		else {
                    			if(Double.parseDouble(Price) <= 0) {
                    				display.append("You must enter a price greater than 0!\n");
                    				valid = false;
                    			}
                    		}
                    		
                    		if(valid == true) {
                    			sold = false;
                    			boolean one = true;
                    			display.setText("");
        						for(int j = 0; j < investmentList.size(); j++) {
        							if(Symbol.equals(investmentList.get(j).getSym())) {
        								if(Double.parseDouble(Quantity) <= investmentList.get(j).getQuantity()) {
        									if(investmentList.get(j).getClass().getCanonicalName().equals("Stock")) {
        										double madeMoney = ((Stock)investmentList.get(j)).sellQuantityStock(Double.parseDouble(Quantity), Double.parseDouble(Price));
        										display.append("You received $" + madeMoney + " selling " + Quantity + " shares at $" + Price + " a share\n");
        										if(investmentList.get(j).getQuantity() == 0) {
        											investmentList.remove(j);
        										}
        										sold = true;
        									}
        									else if(investmentList.get(j).getClass().getCanonicalName().equals("MutualFund")) {
        										double madeMoney = ((MutualFund)investmentList.get(j)).sellQuantityFund(Double.parseDouble(Quantity), Double.parseDouble(Price));
        										display.append("You received $" + madeMoney + " selling " + Quantity + " shares at $" + Price + " a share");
        										if(investmentList.get(j).getQuantity() == 0) {
        											investmentList.remove(j);
        										}
        										sold = true;
        									}
        								}
        								else {
        									if(one)
        										display.append("You are trying to sell more investments than available.");
        										one = false;
        								}
        							}
        						}
        						if(sold == false && one == true) {
        							display.setText("The investment you are trying to sell does not exist.");
        						}
                    		}
            		    }
            		});
            }
        });
		
		update.addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent ae) {
	            	// Top Panel Portion
	        		top.removeAll();
	        		top.invalidate();
	        		bottom.removeAll();
	        		bottom.invalidate();
	        		top.setLayout(new GridLayout(1, 2));
	        		JPanel textFields = new JPanel(new GridLayout(3, 1));
	        		JPanel buttons = new JPanel(new GridLayout(3, 1));
	        		textFields.setBorder(BorderFactory.createTitledBorder("Updating Investments"));
	        		buttons.setBorder(BorderFactory.createTitledBorder(""));
	        		
	        		JTextPane symbol = new JTextPane();
	        		symbol.setEditable(false);
	        		symbol.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	        		JLabel symbolL = new JLabel("Symbol:");
	        		JTextPane name = new JTextPane();
	        		name.setEditable(false);
	        		name.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	        		JLabel nameL = new JLabel("Name:");
	        		JTextField price = new JTextField(15);
	        		price.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
	        		JLabel priceL = new JLabel("Price:");
	        		
	        		JButton prevButton = new JButton("Previous");
	        		JButton nextButton = new JButton("Next");
	        		JButton saveButton = new JButton("Save");
	        		
	        		baseLayout.add(top);
	        		top.add(textFields);
	        		top.add(buttons);
	        		textFields.add(symbolL);
	        		textFields.add(symbol);
	        		textFields.add(nameL);
	        		textFields.add(name);
	        		textFields.add(priceL);
	        		textFields.add(price);
	        		buttons.add(prevButton);
	        		buttons.add(nextButton);
	        		buttons.add(saveButton);
	        		top.revalidate();
	        		top.repaint();
	        		
	        		// Bottom Panel Portion
	        		JTextArea display = new JTextArea();
	        		display.setEditable(false);
	            display.setLineWrap(true);
	            display.setText("Use the previous and next buttons to update investment prices!\n");
	            JScrollPane textArea2 = new JScrollPane(display);
	            textArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        		bottom.setBorder(BorderFactory.createTitledBorder("Messages"));
	        		bottom.add(textArea2);
	        		baseLayout.add(bottom);
	        		bottom.revalidate();
	        		bottom.repaint();
	        		
	        		updateIndex = 0;
	        		if(investmentList.size() == 0) {
	        			display.setText("There are no investments in the Portfolio.");
	        			prevButton.setEnabled(false);
	        			nextButton.setEnabled(false);
	        		}
	        		else {
	        			prevButton.setEnabled(false);
	        			if(investmentList.get(0).getClass().getCanonicalName().equals("Stock")) {
	        				Stock print = new Stock("", "", 0, 0, 0);
	        				print = (Stock)investmentList.get(0);
		        			symbol.setText("\n " + print.getSym());
		        			name.setText("\n " + print.getName());
	        			}
	        			else {
	        				MutualFund print = new MutualFund("", "", 0, 0, 0);
	        				print = (MutualFund)investmentList.get(0);
		        			symbol.setText("\n " + print.getSym());
		        			name.setText("\n " + print.getName());
	        			}
	        			if(investmentList.size() == 1) {
	        				nextButton.setEnabled(false);
	        			}
	        		}
	        		
	        		prevButton.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		       if(updateIndex != 0) {
            		    	   		updateIndex--;
            		    	   		if(updateIndex == 0) {
            		    	   			prevButton.setEnabled(false);
            		    	   		}
            		    	   		if(updateIndex != investmentList.size() - 1) {
	        		    	   			nextButton.setEnabled(true);
	        		    	   		}
            		    	   		if(investmentList.get(updateIndex).getClass().getCanonicalName().equals("Stock")) {
            	        				Stock print = new Stock("", "", 0, 0, 0);
            	        				print = (Stock)investmentList.get(updateIndex);
            		        			symbol.setText("\n " + print.getSym());
            		        			name.setText("\n " + print.getName());
            	        			}
            	        			else {
            	        				MutualFund print = new MutualFund("", "", 0, 0, 0);
            	        				print = (MutualFund)investmentList.get(updateIndex);
            		        			symbol.setText("\n " + print.getSym());
            		        			name.setText("\n " + print.getName());
            	        			}
            		       }
            		    }
            		});
	        		
	        		nextButton.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
	            		    	if(updateIndex != investmentList.size() - 1) {
	        		    	   		updateIndex++;
	        		    	   		if(updateIndex == investmentList.size() - 1) {
	        		    	   			nextButton.setEnabled(false);
	        		    	   		}
	        		    	   		if(updateIndex != 0) {
	        		    	   			prevButton.setEnabled(true);
	        		    	   		}
	        		    	   		if(investmentList.get(updateIndex).getClass().getCanonicalName().equals("Stock")) {
            	        				Stock print = new Stock("", "", 0, 0, 0);
            	        				print = (Stock)investmentList.get(updateIndex);
            		        			symbol.setText("\n " + print.getSym());
            		        			name.setText("\n " + print.getName());
            	        			}
            	        			else {
            	        				MutualFund print = new MutualFund("", "", 0, 0, 0);
            	        				print = (MutualFund)investmentList.get(updateIndex);
            		        			symbol.setText("\n " + print.getSym());
            		        			name.setText("\n " + print.getName());
            	        			}
	            		    	}
            		    }
            		});
            		
            		saveButton.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		    		valid = true;
                    		Price = price.getText();
                    		if(Price.equals("") || !Price.matches("[1234567890-]+")) {
                    			display.append("You must enter a valid Price!\n");
                    			valid = false;
                    			
                    		}
                    		else {
                    			if(Double.parseDouble(Price) <= 0) {
                    				display.append("You must enter a price greater than 0!\n");
                    				valid = false;
                    			}
                    		}
                    		if(valid == true) {
                    			investmentList.get(updateIndex).updatePrice(Double.parseDouble(price.getText()));
                    			price.setText("");
                    			display.setText("Price Successfully Updated: \n\n");
                    			if(investmentList.get(updateIndex).getClass().getCanonicalName().equals("Stock")) {
                    				NumberFormat formatter = new DecimalFormat("#0.00"); 
    								display.append("Stock Symbol: " + investmentList.get(updateIndex).getSym() + "\n" +
    											   "Stock Name: " + investmentList.get(updateIndex).getName() + "\n" +
    											   "Stock Quantity: " + investmentList.get(updateIndex).getQuantity() + "\n" +
    											   "Stock Price: " + investmentList.get(updateIndex).getPrice() + "\n" +
    											   "Stock bookVal: " + formatter.format(investmentList.get(updateIndex).getBookVal()) + "\n\n");
                    			}
                    			else {
                    				NumberFormat formatter = new DecimalFormat("#0.00"); 
    								display.append("Mutual Fund Symbol: " + investmentList.get(updateIndex).getSym() + "\n" +
    											   "Mutual Fund Name: " + investmentList.get(updateIndex).getName() + "\n" +
    											   "Mutual Fund Quantity: " + investmentList.get(updateIndex).getQuantity() + "\n" +
    											   "Mutual Fund Price: " + investmentList.get(updateIndex).getPrice() + "\n" +
    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(updateIndex).getBookVal()) + "\n\n");
                    			}
                    		}
            		    }
            		});
            }
        });
		
		gain.addActionListener(new ActionListener() {		
            @Override
            public void actionPerformed(ActionEvent ae) {
            		
	            	// Top Panel Portion
	            	top.removeAll();
	        		top.invalidate();
	        		bottom.removeAll();
	        		bottom.invalidate();
	        		top.setLayout(new GridLayout());
	        		JPanel textFields = new JPanel(new BorderLayout());
	        		textFields.setBorder(BorderFactory.createTitledBorder("Getting Total Gain"));
	        		JLabel gainL = new JLabel("Total Gain: ");
	        		JTextArea Gain = new JTextArea();
	        		Gain.setEditable(false);
	        		Font font = new Font("Comic Sans MS", Font.BOLD, 45);
	        		Gain.setFont(font);
	        		
	        		baseLayout.add(top);
	        		top.add(textFields);
	        		textFields.add(gainL, BorderLayout.WEST);
	        		textFields.add(Gain);
	        		top.revalidate();
	        		top.repaint();
	        		
	        		// Bottom Panel Portion
	        		JTextArea display = new JTextArea();
	        		display.setEditable(false);
	            display.setLineWrap(true);
	            display.setText("Individual Gains will be Here!\n");
	            JScrollPane textArea2 = new JScrollPane(display);
	            textArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        		bottom.setBorder(BorderFactory.createTitledBorder("Individual Gains"));
	        		bottom.add(textArea2);
	        		baseLayout.add(bottom);
	        		bottom.revalidate();
	        		bottom.repaint();
	            	
	        		// Actual Code Portion
	        		double totalGain = 0;
	        		double stockGain = 0;
	        		double fundGain = 0;
				if(investmentList.size() == 0) {
					display.setText("There are no Investments in the Portfolio.");
				}
				else {
					display.setText("");
					for(int k = 0; k < investmentList.size(); k++) {
						if(investmentList.get(k).getClass().getCanonicalName().equals("Stock") ) {
							stockGain = ((Stock)investmentList.get(k)).getGainStock();
							totalGain = totalGain + stockGain;
							NumberFormat formatter = new DecimalFormat("#0.00");
							display.append("Stock Symbol: " + investmentList.get(k).getSym() + "\n" +
									   "Stock Name: " + investmentList.get(k).getName() + "\n" +
									   "Stock Quantity: " + investmentList.get(k).getQuantity() + "\n" +
									   "Stock Price: " + investmentList.get(k).getPrice() + "\n" +
									   "Stock Gain: " + formatter.format(stockGain) + "\n" +
									   "Stock bookVal: " + formatter.format(investmentList.get(k).getBookVal()) + "\n\n");
						}
						else {
							fundGain = ((MutualFund)investmentList.get(k)).getGainFund();
							totalGain = totalGain + fundGain; 
							NumberFormat formatter = new DecimalFormat("#0.00");
							display.append("Mutual Fund Symbol: " + investmentList.get(k).getSym() + "\n" +
									   "Mutual Fund Name: " + investmentList.get(k).getName() + "\n" +
									   "Mutual Fund Quantity: " + investmentList.get(k).getQuantity() + "\n" +
									   "Mutual Fund Price: " + investmentList.get(k).getPrice() + "\n" +
									   "Mutual Fund Gain: " + formatter.format(stockGain) + "\n" +
									   "Mutual Fund bookVal: " + formatter.format(investmentList.get(k).getBookVal()) + "\n\n");
						}
						
					}
					NumberFormat formatter = new DecimalFormat("#0.00");
					Gain.setText("\n  " + formatter.format(totalGain));
				}
        		
            }
        });
		
		// EVERYTHING BELOW IS SEARCH
		search.addActionListener(new ActionListener() {	
            @Override
            public void actionPerformed(ActionEvent ae) {
            		
	            	// Top Panel Portion
	            	top.removeAll();
	        		top.invalidate();
	        		bottom.removeAll();
	        		bottom.invalidate();
	        		top.setLayout(new GridLayout(1, 2));
	        		JPanel textFields = new JPanel(new GridLayout(4, 1));
	        		JPanel buttons = new JPanel(new GridLayout(2, 1));
	        		textFields.setBorder(BorderFactory.createTitledBorder("Searching Investments"));
	        		buttons.setBorder(BorderFactory.createTitledBorder(""));
	        		JLabel searchSymL = new JLabel("Symbol:");
	        		JTextField searchSym = new JTextField(15);
	        		JLabel searchKeyL = new JLabel("Name Keywords:");
	        		JTextField searchKey = new JTextField(15);
	        		JLabel searchLowL = new JLabel("Low Price:");
	        		JTextField searchLow = new JTextField(15);
	        		JLabel searchHighL = new JLabel("High Price:");
	        		JTextField searchHigh = new JTextField(15);
	        		
	        		JButton reset = new JButton("Reset");
	        		JButton searchButton = new JButton("Search");
	        		
	        		baseLayout.add(top);
	        		top.add(textFields);
	        		top.add(buttons);
	        		textFields.add(searchSymL);
	        		textFields.add(searchSym);
	        		textFields.add(searchKeyL);
	        		textFields.add(searchKey);
	        		textFields.add(searchLowL);
	        		textFields.add(searchLow);
	        		textFields.add(searchHighL);
	        		textFields.add(searchHigh);
	        		buttons.add(reset);
	        		buttons.add(searchButton);
	        		top.revalidate();
	        		top.repaint();
	        		
	        		// Bottom Panel Portion
	        		JTextArea display = new JTextArea();
	        		display.setEditable(false);
	            display.setLineWrap(true);
	            display.setText("Search Results will be Here!\n");
	            JScrollPane textArea2 = new JScrollPane(display);
	            textArea2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	        		bottom.setBorder(BorderFactory.createTitledBorder("Search Results"));
	        		bottom.add(textArea2);
	        		baseLayout.add(bottom);
	        		bottom.revalidate();
	        		bottom.repaint();
	        		
	        		reset.addActionListener(new ActionListener() {
            		    @Override
            		    public void actionPerformed(ActionEvent event) {
            		        searchSym.setText("");
            		        searchKey.setText("");
            		        searchLow.setText("");
            		        searchHigh.setText("");
            		    }
            		});
	        		
	        		// Actual Logic Code Portion
	        		searchButton.addActionListener(new ActionListener() {	
                    @Override
                    public void actionPerformed(ActionEvent ae) {
	                    	display.setText("");
                    		if(searchSym.getText().equals("") && searchKey.getText().equals("") && searchLow.getText().equals("") && searchHigh.getText().equals("")) { // No flags are set, print all investments
	        					firstStock = true;
	        					firstFund = true;
	        	    				if(investmentList.size() == 0) {
	        						display.append("There are no Investments in the Portfolio.");
	        					}
	        					else {
	        						for(int i = 0; i < investmentList.size(); i++) {
	        							if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
	        								if(firstStock) {
	        									display.setText("--- Stocks ---\n");
	        									firstStock = false;
	        								}
	        								NumberFormat formatter = new DecimalFormat("#0.00"); 
	        								display.append("Stock Symbol: " + investmentList.get(i).getSym() + "\n" +
	        											   "Stock Name: " + investmentList.get(i).getName() + "\n" +
	        											   "Stock Quantity: " + investmentList.get(i).getQuantity() + "\n" +
	        											   "Stock Price: " + investmentList.get(i).getPrice() + "\n" +
	        											   "Stock bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
	        							}
	        						}
	        						for(int i = 0; i < investmentList.size(); i++) {
	        							if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(firstFund) {
	        									display.append("--- Mutual Funds ---\n");
	        									firstFund = false;
	        								}
	        								NumberFormat formatter = new DecimalFormat("#0.00"); 
	        								display.append("Mutual Fund Symbol: " + investmentList.get(i).getSym() + "\n" +
	        											   "Mutual Fund Name: " + investmentList.get(i).getName() + "\n" +
	        											   "Mutual Fund Quantity: " + investmentList.get(i).getQuantity() + "\n" +
	        											   "Mutual Fund Price: " + investmentList.get(i).getPrice() + "\n" +
	        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
	        							}
	        						}
	        					}
	        				}
                    		else if(!searchSym.getText().equals("") && searchKey.getText().equals("") && searchLow.getText().equals("") && searchHigh.getText().equals("")) {	// Only searching symbol
                    			boolean firstStock = true;
                    			boolean firstFund = true;
                    			boolean found = false;
                    			display.setText("");
                    			for(int i = 0; i < investmentList.size(); i++) {
                    				if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
                    					if((searchSym.getText().toLowerCase()).equals(investmentList.get(i).getSym().toLowerCase())) {
                    						if(firstStock) {
                    							display.append("--- Stocks ---\n");
                    							firstStock = false;
                    							found = true;
                    						}
                    						NumberFormat formatter = new DecimalFormat("#0.00"); 
	        								display.append("Stock Symbol: " + investmentList.get(i).getSym() + "\n" +
	        											   "Stock Name: " + investmentList.get(i).getName() + "\n" +
	        											   "Stock Quantity: " + investmentList.get(i).getQuantity() + "\n" +
	        											   "Stock Price: " + investmentList.get(i).getPrice() + "\n" +
	        											   "Stock bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    					}
                    				}
                    			}
                    			for(int i = 0; i < investmentList.size(); i++) {
                    				if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
                    					if((searchSym.getText().toLowerCase()).equals(investmentList.get(i).getSym().toLowerCase())) {
                    						if(firstFund) {
                    							display.append("--- Mutual Funds ---\n");
                    							firstFund = false;
                    							found = true;
                    						}
	        								NumberFormat formatter = new DecimalFormat("#0.00"); 
	        								display.append("Mutual Fund Symbol: " + investmentList.get(i).getSym() + "\n" +
	        											   "Mutual Fund Name: " + investmentList.get(i).getName() + "\n" +
	        											   "Mutual Fund Quantity: " + investmentList.get(i).getQuantity() + "\n" +
	        											   "Mutual Fund Price: " + investmentList.get(i).getPrice() + "\n" +
	        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    					}
                    				}
                    			}
                    			if(found == false) {
                    				display.append("The investment searched for could not be found.");
                    			}
                    		}
                    		else if(searchSym.getText().equals("") && !searchKey.getText().equals("") && searchLow.getText().equals("") && searchHigh.getText().equals("")) {	// Only searching keywords
                    			display.setText("");
                    			ArrayList<Integer> goodIndex = new ArrayList<Integer>();
                    			String[] splitWord;
                    			String tempWord = "NULL";
                    			boolean firstStock = true;
                    			boolean firstFund = true;
                    			searchKey.getText().toLowerCase();
                    			splitWord = searchKey.getText().split("\\s+");						// Splits inputted search keywords up by whitespace into string array
                    			
                    			for(String s : splitWord) {
                    				ArrayList<Integer> tempIndex = new ArrayList<Integer>();
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					tempWord = investmentList.get(i).getName();
                    					tempWord = tempWord.toLowerCase();
                    					if(tempWord.matches(".*\\b" + s + "\\b.*")) {
                    						tempIndex.add(i);		// Add all indexes in investmentList where keyword is present
                    					}
                    				}
                    				if(tempIndex.size() != 0) {
                    					keyWordMap.put(s, tempIndex);	// Add to hashmap
                    				}
                    			}
                    			
                    			if(splitWord.length > 1) {	// If more than one keyword being searched
                    				int count = 0;
                    				for(String t : splitWord) {			// Cross Reference if more than one keyword
                    					ArrayList<Integer> tempIndex = new ArrayList<Integer>();
                    					if(count == 0) {
                    						goodIndex = keyWordMap.get(t);
                    						count++;
                    					}
                    					else {
                    						tempIndex = keyWordMap.get(t);
                    						goodIndex.retainAll(tempIndex);
                    					}
                    				}
                    				for(int x = 0; x < goodIndex.size(); x++) {	// Print the investment at goodIndex indexes
                    					if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("Stock")) {
                    						if(firstStock) {
                    							display.append("--- Stocks ---\n");
                    							firstStock = false;
                    						}
                    						NumberFormat formatter = new DecimalFormat("#0.00"); 
	        								display.append("Stock Symbol: " + investmentList.get(goodIndex.get(x)).getSym() + "\n" +
	        											   "Stock Name: " + investmentList.get(goodIndex.get(x)).getName() + "\n" +
	        											   "Stock Quantity: " + investmentList.get(goodIndex.get(x)).getQuantity() + "\n" +
	        											   "Stock Price: " + investmentList.get(goodIndex.get(x)).getPrice() + "\n" +
	        											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(x)).getBookVal()) + "\n\n");
                    					}
                    					else if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("MutualFund")) {
                    						if(firstFund) {
                    							display.append("--- Mutual Funds ---\n");
                    							firstFund = false;
                    						}
                    						NumberFormat formatter = new DecimalFormat("#0.00"); 
                    						display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(x)).getSym() + "\n" +
     											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(x)).getName() + "\n" +
     											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(x)).getQuantity() + "\n" +
     											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(x)).getPrice() + "\n" +
     											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(x)).getBookVal()) + "\n\n");
                    					}
                    				}
                    			}
                    			else {	// If only one keyword being searched
                    				if(keyWordMap.size() == 0) {
                    					display.append("The investment searched for could not be found.");
                    				}
                    				else {
                    					for(String u : splitWord) {
                    						goodIndex = keyWordMap.get(u);	// Get hash key
                    					}
                    					for(int x = 0; x < goodIndex.size(); x++) {
                    						if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("Stock")) {
                    							if(firstStock) {
                    								display.append("--- Stocks ---\n");
                    								firstStock = false;
                    							}
                        						NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(x)).getSym() + "\n" +
    	        											   "Stock Name: " + investmentList.get(goodIndex.get(x)).getName() + "\n" +
    	        											   "Stock Quantity: " + investmentList.get(goodIndex.get(x)).getQuantity() + "\n" +
    	        											   "Stock Price: " + investmentList.get(goodIndex.get(x)).getPrice() + "\n" +
    	        											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(x)).getBookVal()) + "\n\n");
                    						}
                    						else if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("MutualFund")) {
                    							if(firstFund) {
                    								display.append("--- Mutual Funds ---\n");
                    								firstFund = false;
                    							}
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
                    							display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(x)).getSym() + "\n" +
          											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(x)).getName() + "\n" +
          											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(x)).getQuantity() + "\n" +
          											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(x)).getPrice() + "\n" +
          											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(x)).getBookVal()) + "\n\n");
                    						}
                    						else {
                    							display.setText("The investment searched for could not be found.");
                    						}
                    					}
                    				}
                    			}
                    			goodIndex.clear();
                    			keyWordMap.clear();
                    		}
                    		else if(searchSym.getText().equals("") && searchKey.getText().equals("") && !searchLow.getText().equals("") && searchHigh.getText().equals("")) { // search for prices higher
                    			firstStock = true;
                    			firstFund = true;
                    			display.setText("");
                    			double searchVal = 0;
                    			if(searchLow.getText().matches("[1234567890.]+")) {					// Do same as above, error checking
                    				searchVal = Double.parseDouble(searchLow.getText());
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
                    						if(investmentList.get(i).getPrice() >= searchVal) {
                    							if(firstStock) {
                    								display.append("--- Stocks ---\n");			// Print the stocks and funds if greater than search price
                    								firstStock = false;
                    							}	
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        								display.append("Stock Symbol: " + investmentList.get(i).getSym() + "\n" +
    	        											   "Stock Name: " + investmentList.get(i).getName() + "\n" +
    	        											   "Stock Quantity: " + investmentList.get(i).getQuantity() + "\n" +
    	        											   "Stock Price: " + investmentList.get(i).getPrice() + "\n" +
    	        											   "Stock bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    						}
                    					}
                    				}
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					if(investmentList.get(i).getPrice() >= searchVal) {
                    						if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
                    							if(firstFund) {
                    								display.append("--- Mutual Funds ---\n");
                    								firstFund = false;
                    							}
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        								display.append("Mutual Fund Symbol: " + investmentList.get(i).getSym() + "\n" +
    	        											   "Mutual Fund Name: " + investmentList.get(i).getName() + "\n" +
    	        											   "Mutual Fund Quantity: " + investmentList.get(i).getQuantity() + "\n" +
    	        											   "Mutual Fund Price: " + investmentList.get(i).getPrice() + "\n" +
    	        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    						}
                    					}
                    				}
                    			}
                    			else {
                    				display.append("Enter nummerical values only.");
                    			}
                    		}
                    		else if(searchSym.getText().equals("") && searchKey.getText().equals("") && searchLow.getText().equals("") && !searchHigh.getText().equals("")) { // search for prices lower
                    			firstStock = true;
                    			firstFund = true;
                    			display.setText("");
                    			double searchVal = 0;
                    			if(searchHigh.getText().matches("[1234567890.]+")) {					// Do same as above, error checking
                    				searchVal = Double.parseDouble(searchHigh.getText());
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
                    						if(investmentList.get(i).getPrice() <= searchVal) {
                    							if(firstStock) {
                    								display.append("--- Stocks ---\n");			// Print the stocks and funds if greater than search price
                    								firstStock = false;
                    							}	
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        								display.append("Stock Symbol: " + investmentList.get(i).getSym() + "\n" +
    	        											   "Stock Name: " + investmentList.get(i).getName() + "\n" +
    	        											   "Stock Quantity: " + investmentList.get(i).getQuantity() + "\n" +
    	        											   "Stock Price: " + investmentList.get(i).getPrice() + "\n" +
    	        											   "Stock bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    						}
                    					}
                    				}
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					if(investmentList.get(i).getPrice() <= searchVal) {
                    						if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
                    							if(firstFund) {
                    								display.append("--- Mutual Funds ---\n");
                    								firstFund = false;
                    							}
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        								display.append("Mutual Fund Symbol: " + investmentList.get(i).getSym() + "\n" +
    	        											   "Mutual Fund Name: " + investmentList.get(i).getName() + "\n" +
    	        											   "Mutual Fund Quantity: " + investmentList.get(i).getQuantity() + "\n" +
    	        											   "Mutual Fund Price: " + investmentList.get(i).getPrice() + "\n" +
    	        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    						}
                    					}
                    				}
                    			}
                    			else {
                    				display.append("Enter nummerical values only.");
                    			}	
                    		}
                    		else if(searchSym.getText().equals("") && searchKey.getText().equals("") && !searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
                    			firstStock = true;
                    			firstFund = true;
                    			display.setText("");
                    			double searchValLow = 0;
                    			double searchValHigh = 0;
                    			if(searchLow.getText().matches("[1234567890.]+") && searchHigh.getText().matches("[1234567890.]+")) {					// Do same as above, error checking
                    				searchValLow = Double.parseDouble(searchLow.getText());
                    				searchValHigh = Double.parseDouble(searchHigh.getText());
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
                    						if(investmentList.get(i).getPrice() <= searchValHigh && investmentList.get(i).getPrice() >= searchValLow) {
                    							if(firstStock) {
                    								display.append("--- Stocks ---\n");			// Print the stocks and funds if greater than search price
                    								firstStock = false;
                    							}	
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        									display.append("Stock Symbol: " + investmentList.get(i).getSym() + "\n" +
    	        											   "Stock Name: " + investmentList.get(i).getName() + "\n" +
    	        											   "Stock Quantity: " + investmentList.get(i).getQuantity() + "\n" +
    	        											   "Stock Price: " + investmentList.get(i).getPrice() + "\n" +
    	        											   "Stock bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    						}
                    					}
                    				}
                    				for(int i = 0; i < investmentList.size(); i++) {
                    					if(investmentList.get(i).getPrice() <= searchValHigh && investmentList.get(i).getPrice() >= searchValLow) {
                    						if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
                    							if(firstFund) {
                    								display.append("--- Mutual Funds ---\n");
                    								firstFund = false;
                    							}
                    							NumberFormat formatter = new DecimalFormat("#0.00"); 
    	        									display.append("Mutual Fund Symbol: " + investmentList.get(i).getSym() + "\n" +
    	        											   "Mutual Fund Name: " + investmentList.get(i).getName() + "\n" +
    	        											   "Mutual Fund Quantity: " + investmentList.get(i).getQuantity() + "\n" +
    	        											   "Mutual Fund Price: " + investmentList.get(i).getPrice() + "\n" +
    	        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(i).getBookVal()) + "\n\n");
                    						}
                    					}
                    				}
                    			}
                    			else {
                    				display.append("Enter nummerical values only.");
                    			}
                    		}
                    		else if(!searchSym.getText().equals("") && !searchKey.getText().equals("") && searchLow.getText().equals("") && searchHigh.getText().equals("")) {
                    			display.setText("");
                    			ArrayList<Integer> symbol = new ArrayList<>();
            					ArrayList<Integer> keyWord = new ArrayList<>();
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					symbol = searchSym(searchSym.getText());
            					keyWord = searchKeyWord(searchKey.getText());
            					goodIndex = symbol;
            					if(goodIndex.retainAll(keyWord) || !goodIndex.retainAll(keyWord)) {
            						for(int i = 0; i < goodIndex.size(); i++) {
            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
            								if(firstStock) {
            									display.append("--- Stocks ---\n");
            									firstStock = false;
            								}
            								NumberFormat formatter = new DecimalFormat("#0.00"); 
        									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
        											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
        											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
        											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
        											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
        									foundInvestment = true;
            							}
            						}
            						for(int i = 0; i < goodIndex.size(); i++) {
            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
            								if(firstFund) {
            									display.append("--- Mutual Funds ---\n");
            									firstFund = false;
            								}
            								NumberFormat formatter = new DecimalFormat("#0.00"); 
        									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
        											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
        											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
        											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
            								foundInvestment = true;
            							}
            						}
            						if(foundInvestment == false) {
            							display.append("The investment searched for could not be found.");
            						}
            					}
                    		}
                    		else if(!searchSym.getText().equals("") && searchKey.getText().equals("") && !searchLow.getText().equals("") && searchHigh.getText().equals("")) {
                    			display.setText("");
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					goodIndex = searchSym(searchSym.getText());
            					if(searchLow.getText().matches("[1234567890.]+")) {
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
		        							if(investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {	
	        									if(firstStock) {
		        									display.append("--- Stocks ---\n");
		        									firstStock = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		    									foundInvestment = true;
		        							}
	        							}
	        						}
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {	
		        								if(firstFund) {
		        									display.append("--- Mutual Funds ---\n");
		        									firstFund = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						if(foundInvestment == false) {
	        							display.append("The investment searched for could not be found.");
	        						}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(!searchSym.getText().equals("") && searchKey.getText().equals("") && searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
                    			display.setText("");
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					goodIndex = searchSym(searchSym.getText());
            					if(searchHigh.getText().matches("[1234567890.]+")) {
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
		        							if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText())) {	
	        									if(firstStock) {
		        									display.append("--- Stocks ---\n");
		        									firstStock = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		    									foundInvestment = true;
		        							}
	        							}
	        						}
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText())) {	
		        								if(firstFund) {
		        									display.append("--- Mutual Funds ---\n");
		        									firstFund = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						if(foundInvestment == false) {
	        							display.append("The investment searched for could not be found.");
	        						}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(!searchSym.getText().equals("") && searchKey.getText().equals("") && !searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
                    			display.setText("");
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					goodIndex = searchSym(searchSym.getText());
            					if(searchLow.getText().matches("[1234567890.]+") && searchHigh.getText().matches("[1234567890.]+")) {
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
		        							if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText()) && investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {	
	        									if(firstStock) {
		        									display.append("--- Stocks ---\n");
		        									firstStock = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		    									foundInvestment = true;
		        							}
	        							}
	        						}
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText()) && investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		        								if(firstFund) {
		        									display.append("--- Mutual Funds ---\n");
		        									firstFund = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						if(foundInvestment == false) {
	        							display.append("The investment searched for could not be found.");
	        						}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(searchSym.getText().equals("") && !searchKey.getText().equals("") && !searchLow.getText().equals("") && searchHigh.getText().equals("")) {
            					display.setText("");
                    			ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					goodIndex = searchKeyWord(searchKey.getText());
            					if(searchLow.getText().matches("[1234567890.]+")) {
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		        								if(firstStock) {
		        									display.append("--- Stocks ---\n");
		        									firstStock = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		        								if(firstFund) {
		        									display.append("--- Mutual Funds ---\n");
		        									firstFund = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						if(foundInvestment == false) {
	        							display.append("The investment searched for could not be found.\n");
	        						}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(searchSym.getText().equals("") && !searchKey.getText().equals("") && searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
            					firstStock = true;
            					firstFund = true;
                    			display.setText("");
                    			ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					goodIndex = searchKeyWord(searchKey.getText());
            					if(searchHigh.getText().matches("[1234567890.]+")) {
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText())) {
		        								if(firstStock) {
		        									display.append("--- Stocks ---\n");
		        									firstStock = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText())) {
		        								if(firstFund) {
		        									display.append("--- Mutual Funds ---\n");
		        									firstFund = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						if(foundInvestment == false) {
	        							display.append("The investment searched for could not be found.\n");
	        						}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(searchSym.getText().equals("") && !searchKey.getText().equals("") && !searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
                    			display.setText("");
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					goodIndex = searchKeyWord(searchKey.getText());
            					if(searchLow.getText().matches("[1234567890.]+") && searchHigh.getText().matches("[1234567890.]+")) {
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
		        							if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText()) && investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {	
	        									if(firstStock) {
		        									display.append("--- Stocks ---\n");
		        									firstStock = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		    									foundInvestment = true;
		        							}
	        							}
	        						}
	        						for(int i = 0; i < goodIndex.size(); i++) {
	        							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	        								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText()) && investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		        								if(firstFund) {
		        									display.append("--- Mutual Funds ---\n");
		        									firstFund = false;
		        								}
		        								NumberFormat formatter = new DecimalFormat("#0.00"); 
		    									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		    											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		    											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		    											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		    											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        								foundInvestment = true;
	        								}
	        							}
	        						}
	        						if(foundInvestment == false) {
	        							display.append("The investment searched for could not be found.");
	        						}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(!searchSym.getText().equals("") && !searchKey.getText().equals("") && !searchLow.getText().equals("") && searchHigh.getText().equals("")) {
                    			display.setText("");
                    			ArrayList<Integer> symbol = new ArrayList<>();
            					ArrayList<Integer> keyWord = new ArrayList<>();
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					symbol = searchSym(searchSym.getText());
            					keyWord = searchKeyWord(searchKey.getText());
            					goodIndex = symbol;
            					if(searchLow.getText().matches("[1234567890.]+")) {
	            					if(goodIndex.retainAll(keyWord) || !goodIndex.retainAll(keyWord)) {
	            						for(int i = 0; i < goodIndex.size(); i++) {
	            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
	            								if(investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		            								if(firstStock) {
		            									display.append("--- Stocks ---\n");
		            									firstStock = false;
		            								}
		            								NumberFormat formatter = new DecimalFormat("#0.00"); 
		        									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		        											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		        											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		        											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		        											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        									foundInvestment = true;
	            								}
	            							}
	            						}
	            						for(int i = 0; i < goodIndex.size(); i++) {
	            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	            								if(investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		            								if(firstFund) {
		            									display.append("--- Mutual Funds ---\n");
		            									firstFund = false;
		            								}
		            								NumberFormat formatter = new DecimalFormat("#0.00"); 
		        									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		        											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		        											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		        											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		            								foundInvestment = true;
	            								}
	            							}
	            						}
	            						if(foundInvestment == false) {
	            							display.append("The investment searched for could not be found.");
	            						}
	            					}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(!searchSym.getText().equals("") && !searchKey.getText().equals("") && searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
                    			display.setText("");
                    			ArrayList<Integer> symbol = new ArrayList<>();
            					ArrayList<Integer> keyWord = new ArrayList<>();
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					symbol = searchSym(searchSym.getText());
            					keyWord = searchKeyWord(searchKey.getText());
            					goodIndex = symbol;
            					if(searchHigh.getText().matches("[1234567890.]+")) {
	            					if(goodIndex.retainAll(keyWord) || !goodIndex.retainAll(keyWord)) {
	            						for(int i = 0; i < goodIndex.size(); i++) {
	            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
	            								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText())) {
		            								if(firstStock) {
		            									display.append("--- Stocks ---\n");
		            									firstStock = false;
		            								}
		            								NumberFormat formatter = new DecimalFormat("#0.00"); 
		        									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		        											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		        											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		        											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		        											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        									foundInvestment = true;
	            								}
	            							}
	            						}
	            						for(int i = 0; i < goodIndex.size(); i++) {
	            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	            								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText())) {
		            								if(firstFund) {
		            									display.append("--- Mutual Funds ---\n");
		            									firstFund = false;
		            								}
		            								NumberFormat formatter = new DecimalFormat("#0.00"); 
		        									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		        											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		        											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		        											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		            								foundInvestment = true;
	            								}
	            							}
	            						}
	            						if(foundInvestment == false) {
	            							display.append("The investment searched for could not be found.");
	            						}
	            					}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    		else if(!searchSym.getText().equals("") && !searchKey.getText().equals("") && !searchLow.getText().equals("") && !searchHigh.getText().equals("")) {
                    			display.setText("");
                    			ArrayList<Integer> symbol = new ArrayList<>();
            					ArrayList<Integer> keyWord = new ArrayList<>();
            					ArrayList<Integer> goodIndex = new ArrayList<>();
            					boolean foundInvestment = false;
            					firstStock = true;
            					firstFund = true;
            					symbol = searchSym(searchSym.getText());
            					keyWord = searchKeyWord(searchKey.getText());
            					goodIndex = symbol;
            					if(searchLow.getText().matches("[1234567890.]+") && searchHigh.getText().matches("[1234567890.]+")) {
	            					if(goodIndex.retainAll(keyWord) || !goodIndex.retainAll(keyWord)) {
	            						for(int i = 0; i < goodIndex.size(); i++) {
	            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
	            								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText()) && investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		            								if(firstStock) {
		            									display.append("--- Stocks ---\n");
		            									firstStock = false;
		            								}
		            								NumberFormat formatter = new DecimalFormat("#0.00"); 
		        									display.append("Stock Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		        											   "Stock Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		        											   "Stock Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		        											   "Stock Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		        											   "Stock bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		        									foundInvestment = true;
	            								}
	            							}
	            						}
	            						for(int i = 0; i < goodIndex.size(); i++) {
	            							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
	            								if(investmentList.get(goodIndex.get(i)).getPrice() <= Double.parseDouble(searchHigh.getText()) && investmentList.get(goodIndex.get(i)).getPrice() >= Double.parseDouble(searchLow.getText())) {
		            								if(firstFund) {
		            									display.append("--- Mutual Funds ---\n");
		            									firstFund = false;
		            								}
		            								NumberFormat formatter = new DecimalFormat("#0.00"); 
		        									display.append("Mutual Fund Symbol: " + investmentList.get(goodIndex.get(i)).getSym() + "\n" +
		        											   "Mutual Fund Name: " + investmentList.get(goodIndex.get(i)).getName() + "\n" +
		        											   "Mutual Fund Quantity: " + investmentList.get(goodIndex.get(i)).getQuantity() + "\n" +
		        											   "Mutual Fund Price: " + investmentList.get(goodIndex.get(i)).getPrice() + "\n" +
		        											   "Mutual Fund bookVal: " + formatter.format(investmentList.get(goodIndex.get(i)).getBookVal()) + "\n\n");
		            								foundInvestment = true;
	            								}
	            							}
	            						}
	            						if(foundInvestment == false) {
	            							display.append("The investment searched for could not be found.");
	            						}
	            					}
            					}
            					else {
            						display.append("Enter a Valid Price\n");
            					}
                    		}
                    	}
                });
            }
        });
		
		quit.addActionListener(new ActionListener() {		// When the program is exited from the GUI
            @Override
            public void actionPerformed(ActionEvent ae) {
            		saveFile();
            		System.exit(0);
            }
        });
		
		addWindowListener(new WindowAdapter() {				// When the program is exited from the 'x' close window button
            @Override
            public void windowClosing(WindowEvent e) {
                saveFile();
            }
        });
		
	}
	
	public static void main(String[] args) {
		
		Portfolio runObj = new Portfolio();
		if(args.length != 0) {
			fileName = args[0];
			runObj.setVisible(true);
			runObj.run();
		}
		else {
			System.out.println("Please add a filename as a command line argument");
			System.out.println("Usage: Java Portfolio <fileName> OR add parameter in IDE");
		}
		
	}
	
	public void run() {

		importFile();

	}
	
	public ArrayList<Integer> searchSym(String searchSym) {
		ArrayList<Integer> investmentIndex = new ArrayList<>();
		for(int i = 0; i < investmentList.size(); i++) {
			if((searchSym.toLowerCase()).equals(investmentList.get(i).getSym().toLowerCase())) {
				investmentIndex.add(i);
			}
		}
		return investmentIndex;
	}
	
	public ArrayList<Integer> searchKeyWord(String searchWord) {
		ArrayList<Integer> goodIndex = new ArrayList<Integer>();
		goodIndex.clear();
		keyWordMap.clear();
		String[] splitWord;
		String tempWord = "NULL";
		searchWord = searchWord.toLowerCase();
		splitWord = searchWord.split("\\s+");						// Splits inputted search keywords up by whitespace into string array
		
		for(String s : splitWord) {
			ArrayList<Integer> tempIndex = new ArrayList<Integer>();
			for(int i = 0; i < investmentList.size(); i++) {
				tempWord = investmentList.get(i).getName();
				tempWord = tempWord.toLowerCase();
				if(tempWord.matches(".*\\b" + s + "\\b.*")) {
					tempIndex.add(i);		// Add all indexes in investmentList where keyword is present
				}
			}
			if(tempIndex.size() != 0) {
				keyWordMap.put(s, tempIndex);	// Add to hashmap
			}
		}
		
		if(splitWord.length > 1) {	// If more than one keyword being searched
			int count = 0;
			for(String t : splitWord) {			// Cross Reference if more than one keyword
				ArrayList<Integer> tempIndex = new ArrayList<Integer>();
				if(count == 0) {
					goodIndex = keyWordMap.get(t);
					count++;
				}
				else {
					tempIndex = keyWordMap.get(t);
					goodIndex.retainAll(tempIndex);
				}
			}
		}
		else {	// If only one keyword being searched
			if(keyWordMap.size() == 0) {
				System.out.println("The investment searched for could not be found.");
			}
			else {
				for(String u : splitWord) {
					goodIndex = keyWordMap.get(u);	// Get hash key
				}
			}
		}
		return goodIndex;
	}
	
	public ArrayList<Integer> searchPrice(String price) {
		ArrayList<Integer> investmentIndex = new ArrayList<>();
		double searchVal = 0;
		
		if(price.indexOf('-') == -1) {									// Price search if inputted value is exactly the price
			if(price.matches("[1234567890.]+")) {
				searchVal = Double.parseDouble(price);
				for(int i = 0; i < investmentList.size(); i++) {	
					if(investmentList.get(i).getPrice() == searchVal) {
						investmentIndex.add(i);
					}
				}
			}
			else {
				System.out.println("Enter nummerical values only.");
			}
		}
		else if(price.charAt(0) != '-' && price.charAt(price.length() - 1) != '-' && price.indexOf("-") != -1) {
			boolean isNum = true;											// ^ Price search if inputted value is a price range
			boolean outOfBounds = false;
			String val1;
			String val2;
			String[] searchVals;
			double double1 = 0;
			double double2 = 0;
			searchVals = price.split("-");
			for(String j : searchVals) {
				if(!j.matches("[1234567890.]+")) {
					isNum = false;
				}
			}
			if(searchVals.length != 2) {										// Error checking and with regex above ^
				System.out.println("Too many search parameters. Usage: <range1-range2>");
				isNum = false;
				outOfBounds = true;
			}
			if(isNum == true) {
				val1 = searchVals[0];
				val2 = searchVals[1];
				double1 = Double.parseDouble(val1);							// If valid answers, split values up
				double2 = Double.parseDouble(val2);
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getPrice() >= double1 && investmentList.get(i).getPrice() <= double2) {
						investmentIndex.add(i);
					}
				}
			}
			else if(isNum == false && outOfBounds!= true) {
				System.out.println("Enter nummerical values only.");
			}
		}
		else if(price.charAt(0) == '-') {								// If first digit is '-' will be less than value
			StringBuilder sb = new StringBuilder(price);
			String newSearchPrice;
			sb.deleteCharAt(0);
			newSearchPrice = sb.toString();
			if(newSearchPrice.matches("[1234567890.]+")) {					// Regex error checking
				searchVal = Double.parseDouble(newSearchPrice);
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getPrice() <= searchVal) {
						investmentIndex.add(i);
					}
				}
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getPrice() <= searchVal) {
						investmentIndex.add(i);
					}
				}
			}
		}
		else if(price.charAt(price.length() - 1) == '-') {		// If the last digit is '-' will be greater than search price
			StringBuilder sb = new StringBuilder(price);
			String newSearchPrice;
			sb.deleteCharAt(price.length() - 1);
			newSearchPrice = sb.toString();
			if(newSearchPrice.matches("[1234567890.]+")) {					// Do same as above, error checking
				searchVal = Double.parseDouble(newSearchPrice);
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getPrice() >= searchVal) {
						investmentIndex.add(i);
					}
				}
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getPrice() >= searchVal) {
						investmentIndex.add(i);	
					}
				}
			}
		}
		return investmentIndex;
	}
	
	public void importFile() {
		BufferedReader fileReader;
		String tempSymbol;
		String tempName;
		double tempQuantity;
		double tempPrice;
		double tempBookVal;
		try {
			fileReader = new BufferedReader(new FileReader(fileName));
			String readLine = fileReader.readLine();
			while(readLine != null) {
				String[] splitArray = readLine.split("~");
				if(splitArray[0].equals("STOCK")) {
					tempSymbol = splitArray[1];
					tempName = splitArray[2];
					tempQuantity = Double.parseDouble(splitArray[3]);
					tempPrice = Double.parseDouble(splitArray[4]);
					tempBookVal = Double.parseDouble(splitArray[5]);
					Stock tempStock = new Stock(tempSymbol, tempName, tempQuantity, tempPrice, tempBookVal);
					investmentList.add((Investment)tempStock);
				}
				else if(splitArray[0].equals("MUTUALFUND")) {
					tempSymbol = splitArray[1];
					tempName = splitArray[2];
					tempQuantity = Double.parseDouble(splitArray[3]);
					tempPrice = Double.parseDouble(splitArray[4]);
					tempBookVal = Double.parseDouble(splitArray[5]);
					MutualFund tempMF = new MutualFund(tempSymbol, tempName, tempQuantity, tempPrice, tempBookVal);
					investmentList.add((Investment)tempMF);
				}
				else {
					System.out.println("File Reading Failure");
				}
				readLine = fileReader.readLine();
			}
		}
		catch(IOException e) {
			System.out.println("Failed to read the specified file");
			System.out.println("You may still run the program to save and create a new file");
		}
	}
	
	public void saveFile() {
		// Save Investments to File
		BufferedWriter fileWriter;
		
		try {
			fileWriter = new BufferedWriter(new FileWriter(fileName));
			if(investmentList.size() != 0) {
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
						fileWriter.write("STOCK~" +
						 investmentList.get(i).symbol + "~" + 
					     investmentList.get(i).name + "~" +
					     investmentList.get(i).quantity + "~" +
					     investmentList.get(i).price + "~" +
					     investmentList.get(i).bookVal + "~" + "\n");
					}
					else if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
						fileWriter.write("MUTUALFUND~" +
						 investmentList.get(i).symbol + "~" + 
					     investmentList.get(i).name + "~" +
					     investmentList.get(i).quantity + "~" +
					     investmentList.get(i).price + "~" +
					     investmentList.get(i).bookVal + "~" + "\n");
					}
				}
			}
			fileWriter.close();
		}
		catch(IOException e) {
			System.out.println("Failed to write to the specified file");
		}
	}
	
} // End of Portfolio Class
