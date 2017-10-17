// Alexander Montague
// amontagu@uoguelph.ca
// 0959687
// October - 2017
// Portfolio Main Class
/*
 * 
 * This is the portfolio class, where main is held, and the main functionality for this program contained.
 * This class handles the user input, and storage of data in ArrayLists.
 * 
 */
import java.util.*;

public class Portfolio {
	
	private ArrayList<Stock> stockList;
	private ArrayList<MutualFund> fundList;
	
	public Portfolio() {
		this.stockList = new ArrayList<Stock>();
		this.fundList = new ArrayList<MutualFund>();
	}
	
	public static void main(String[] args) {
		
		Portfolio runObj = new Portfolio();
		runObj.run();
		
	}
	
	public void run() {
		
		// Variable Declaration
		Scanner s = new Scanner(System.in);
		
		String menuInput;
		Boolean menuLoop = true;
		
		do {
			System.out.println("--- Portfolio Program ---");
			System.out.println("BUY    -  Own a new investment, or purchase more of an existing investment");
			System.out.println("SELL   -  Sell some quantity of an existing investment");
			System.out.println("UPDATE -  Update the prices of all existing investments");
			System.out.println("GAIN   -  Calculate the total gain of all investments");
			System.out.println("SEARCH -  Search for an existing investment in the portfolio");
			System.out.println("QUIT   -  Quit the program");
			System.out.print("Choice: ");
			menuInput = s.nextLine();
			menuInput = menuInput.toLowerCase();
			
			if(menuInput.equals("buy") || menuInput.equals("b")) { // Buy function
				String buyMenu = "NULL";
				String stockSym;
				String mfSym;
				String stockName;
				String mfName;
				double stockQty = 0;
				double mfQty = 0;
				double stockPrice = 0;
				double mfPrice = 0;
				double stockBookVal = 0;
				double mfBookVal = 0;
				Boolean loop = false;
				
				System.out.println();
				System.out.println("STOCK or MUTUAL FUND?");
				System.out.print("Choice: ");
				
				while(loop == false) {
					buyMenu = s.nextLine();
					buyMenu = buyMenu.toLowerCase();
					
					if(buyMenu.equals("s") || buyMenu.equals("mf") || buyMenu.equals("stock") || buyMenu.equals("mutualfund") || buyMenu.equals("mutual fund") || buyMenu.equals("m")) {
						loop = true;
					}
					else {
						System.out.println("Invalid Input. Enter a New Option.");
					}
				}
				
				if(buyMenu.equals("s") || buyMenu.equals("stock")) {
					String returnSym;
					String returnName;
					boolean copyStock = false;
					boolean inOtherList = false;
					boolean sameName = false;
					boolean isNumber = true;
					
					System.out.print("Stock Symbol: ");
					stockSym = s.nextLine();
					System.out.print("Stock Name: ");
					stockName = s.nextLine();
					System.out.print("Stock Quantity: ");
					try {
						stockQty = s.nextDouble();
					}
					catch(InputMismatchException e) {
						isNumber = false;
						s.nextLine();
					}
					System.out.print("Stock Price: ");
					try {
						stockPrice = s.nextDouble();
					}
					catch(InputMismatchException e) {
						isNumber = false;
					}
					
					if(isNumber == true) {
					
						stockBookVal = (stockQty * stockPrice) + 9.99;
						
						if(stockList.size() == 0) {
							sameName = true;
						}
						for(int i = 0; i < stockList.size(); i++) {		// Checking for duplicate symbols in both lists
							returnSym = stockList.get(i).getSym();
							if(stockSym.equals(returnSym)) {
								inOtherList = true;
							}
						}
						for(int i = 0; i < fundList.size(); i++) {
							returnSym = fundList.get(i).getSym();
							if(stockSym.equals(returnSym)) {
								inOtherList = true;
							}
						}
						for(int i = 0; i < stockList.size(); i++) {		// Duplicate checking again
							returnName = stockList.get(i).getName();
							if(returnName.equals(stockName) && inOtherList == true) {
								sameName = true;
								inOtherList = false;
							}
							else if(!returnName.equals(stockName) && inOtherList == false) {
								sameName = true;
							}
						}
						
						if(inOtherList == false && sameName == true) {		// If duplicate, but buying more stock, add to quantity and calculate bookval
							for(int i = 0; i < stockList.size(); i++) {
								returnSym = stockList.get(i).getSym();
								returnName = stockList.get(i).getName();
								if(returnSym.equals(stockSym) && returnName.equals(stockName)) {
									stockList.get(i).addQuantity(stockQty, stockPrice);
									copyStock = true;
								}
							}
							if(copyStock == false) {							// If totally new stock, add to ArrayList
								Stock tempStock = new Stock(stockSym, stockName, stockQty, stockPrice, stockBookVal);
								stockList.add(tempStock);
							}
						}
						else {												// If duplicate on either list, and not adding stock, output duplicate stock
							System.out.println("Duplicate Symbols are not Allowed.");
						}
					}
					else {													// If invalid input was entered
						System.out.println("Enter nummerical values only for Quantity and Price.");
					}
				}
				else if(buyMenu.equals("mf") || buyMenu.equals("mutualfund") || buyMenu.equals("mutual fund") || buyMenu.equals("m")) {
					String returnSym;
					String returnName;
					boolean copyFund = false;
					boolean inOtherList = false;
					boolean sameName = false;
					boolean isNumber = true;
					
					System.out.print("Mutual Fund Symbol: ");
					mfSym = s.nextLine();
					System.out.print("Mutual Fund Name: ");
					mfName = s.nextLine();
					System.out.print("Mutual Fund Quantity: ");
					try {
						mfQty = s.nextDouble();
					}
					catch(InputMismatchException e) {
						isNumber = false;
						s.nextLine();
					}
					System.out.print("Mutual Fund Price: ");
					try {
						mfPrice = s.nextDouble();
					}
					catch(InputMismatchException e) {
						isNumber = false;
					}
					
					if(isNumber == true) {
					
						mfBookVal = mfQty * mfPrice;
						
						if(fundList.size() == 0) {
							sameName = true;
						}
						for(int i = 0; i < fundList.size(); i++) {
							returnSym = fundList.get(i).getSym();
							if(mfSym.equals(returnSym)) {
								inOtherList = true;
							}
						}
						for(int i = 0; i < stockList.size(); i++) {
							returnSym = stockList.get(i).getSym();
							if(mfSym.equals(returnSym)) {
								inOtherList = true;
							}
						}
						for(int i = 0; i < fundList.size(); i++) {
							returnName = fundList.get(i).getName();
							if(returnName.equals(mfName) && inOtherList == true) {
								sameName = true;
								inOtherList = false;
							}
							else if(!returnName.equals(mfName) && inOtherList == false) {
								sameName = true;
							}
						}
						
						if(inOtherList == false && sameName == true) {
							for(int i = 0; i < fundList.size(); i++) {
								returnSym = fundList.get(i).getSym();
								returnName = fundList.get(i).getName();
								if(returnSym.equals(mfSym) && returnName.equals(mfName)) {
									fundList.get(i).addQuantity(mfQty, mfPrice);
									copyFund = true;
								}
							}
							if(copyFund == false) {
								MutualFund tempFund = new MutualFund(mfSym, mfName, mfQty, mfPrice, mfBookVal);
								fundList.add(tempFund);
							}
						}
						else {
							System.out.println("Duplicate Symbols are not Allowed.");
						}
					}
					else {
						System.out.println("Enter nummerical values only for Quantity and Price.");
					}
				}
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("sell")) { // Sell function
				String sellSym =  "NULL";
				String investmentType = "NULL";
				double sellPrice = 0;
				double madeMoney = 0;
				double sellQty = 0;
				int index = 0;
				boolean isNumber = true;
				
				System.out.println();
				
				if(stockList.size() + fundList.size() == 0) {
					System.out.println("There are no investments to sell in the portfolio.\n");
				}
				else {
					System.out.println("--- Sell a Stock or Mutual Fund ---");
					System.out.print("Stock/Fund Symbol: ");
					sellSym = s.nextLine();
					System.out.print("Stock/Fund Sell Price: ");
					try {
						sellPrice = s.nextDouble();
					}
					catch(InputMismatchException e) {
						isNumber = false;
						s.nextLine();
					}
					System.out.print("Quantity to Sell: ");
					try {
						sellQty = s.nextInt();
					}
					catch(InputMismatchException e) {
						isNumber = false;
						s.nextLine();
					}
					
					if(isNumber == true) {
					
						for(int i = 0; i < stockList.size(); i++) {
							if(stockList.get(i).getSym().equals(sellSym)) {
								investmentType = "stock";
								index = i;
							}
						}
						for(int i = 0; i < fundList.size(); i++) {
							if(fundList.get(i).getSym().equals(sellSym)) {
								investmentType = "fund";
								index = i;
							}
						}
						if(investmentType.equals("stock")) {
							if(sellQty <= stockList.get(index).getQuantity()) {
								madeMoney = stockList.get(index).sellQuantity(sellQty, sellPrice);
								System.out.println("You received $" + madeMoney + " selling " + sellQty + " shares at $" + sellPrice + " a share");
								if(stockList.get(index).getQuantity() == 0) {
									stockList.remove(index);
								}
							}
							else {
								System.out.println("You are trying to sell more shares than available");
							}
						}
						else if(investmentType.equals("fund")) {
							if(sellQty <= fundList.get(index).getQuantity()) {
								madeMoney = fundList.get(index).sellQuantity(sellQty, sellPrice);
								System.out.println("You received $" + madeMoney + " selling " + sellQty + " shares at $" + sellPrice + " a share");
								if(fundList.get(index).getQuantity() == 0) {
									fundList.remove(index);
								}
							}
							else {
								System.out.println("You are trying to sell more shares than available");
							}
						}
						else {
							System.out.println("The investment you are trying to sell does not exist.");
						}
						System.out.println();
						s.nextLine();
						
					}
					else {
						System.out.println("Enter nummerical values only for Price and Quantity\n");
					}
				}
			}
			else if(menuInput.equals("update") || menuInput.equals("u")) { // Update function
				double newPrice = 0;
				boolean isNumber = true;
				
				System.out.println();
				if(stockList.size() + fundList.size() == 0) {
					System.out.println("There are no Investments in the Portfolio.");
					System.out.println("If input hangs, press <Enter>");
				}
				else {
					System.out.println("--- Update all Investment Prices ---");
					for(int i = 0; i < stockList.size(); i++) {
						System.out.printf("Update Price for Stock: %s (%s)\n", stockList.get(i).getName(), stockList.get(i).getSym());
						System.out.print("New Price: ");
						try {
							newPrice = s.nextDouble();
						}
						catch(InputMismatchException e) {
							System.out.println("Enter only nummerical values.");
							System.out.println("If input hangs, press <Enter>");
							s.nextLine();
							isNumber = false;
							break;
						}
						stockList.get(i).updatePrice(newPrice);
					}
					if(isNumber == true) {
						for(int i = 0; i < fundList.size(); i++) {
							System.out.printf("Update Price for Mutual Fund: %s (%s)\n", fundList.get(i).getName(), fundList.get(i).getSym());
							System.out.print("New Price: ");
							newPrice = s.nextDouble();
							fundList.get(i).updatePrice(newPrice);
						}
					}
				}
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("gain") || menuInput.equals("g")) { // Gain function
				double totalGain = 0;
				double stockGain = 0;
				double fundGain = 0;
				System.out.println();
				if(stockList.size() + fundList.size() == 0) {
					System.out.println("There are no Investments in the Portfolio.");
					System.out.println();
				}
				else {
					for(int i = 0; i < stockList.size(); i++) {
						stockGain = stockGain + stockList.get(i).getGain();
					}
					for(int j = 0; j < fundList.size(); j++) {
						fundGain = fundGain + fundList.get(j).getGain();
					}
					
					totalGain = stockGain + fundGain;
					System.out.printf("Total Gain from Portfolio: $%.2f\n", totalGain);
					System.out.println();
				}
			}
			else if(menuInput.equals("search")) { // Search function
				String searchSym;
				String searchKeyWord;
				String searchPrice;
				boolean found = false;
				
				System.out.println();
				System.out.println("--- Search for an Investment ---");
				System.out.print("Enter a Symbol: ");
				searchSym = s.nextLine();
				System.out.print("Enter Keyword(s): ");
				searchKeyWord = s.nextLine();
				System.out.print("Enter a Price Range: ");
				searchPrice = s.nextLine();
				System.out.println();
				
				if(searchSym.equals("") && searchKeyWord.equals("") && searchPrice.equals("")) { // No flags are set, print all investments
					for(int i = 0; i < stockList.size(); i++) {
						if(i == 0) {
							System.out.println("--- Stocks ---");
						}
						stockList.get(i).printStock();
					}
					for(int i = 0; i < fundList.size(); i++) {
						if(i == 0) {
							System.out.println("--- Mutual Funds ---");
						}
						fundList.get(i).printFund();
					}
					if(stockList.size() + fundList.size() == 0) {
						System.out.println("There are no Investments in the Portfolio.");
					}
				}
				else if(!searchSym.equals("") && searchKeyWord.equals("") && searchPrice.equals("")) { // First flag is set, search for symbol only
					for(int i = 0; i < stockList.size(); i++) {										 // Simple symbol comparison
						if(searchSym.equals(stockList.get(i).getSym())) {
							if(i == 0) {
								System.out.println("--- Stocks ---");
							}
							stockList.get(i).printStock();
							found = true;
						}
					}
					for(int i = 0; i < fundList.size(); i++) {
						if(searchSym.equals(fundList.get(i).getSym())) {
							if(i == 0) {
								System.out.println("--- Mutual Funds ---");
							}
							fundList.get(i).printFund();
							found = true;
						}
					}
					if(found == false) {
						System.out.println("The investment searched for could not be found.");
					}
				}
				else if(searchSym.equals("") && !searchKeyWord.equals("") && searchPrice.equals("")) { // Second flag is set, Search for Name only
					String[] splitted;
					String returnWord;
					searchKeyWord = searchKeyWord.toLowerCase();
					splitted = searchKeyWord.split("\\s+");						// Splits inputted search keywords up by whitespace into string array
					boolean keyWord = true;
					
					for(int i = 0; i < stockList.size(); i++) {
						keyWord = true;
						returnWord = stockList.get(i).getName();
						returnWord = returnWord.toLowerCase();
						for(String j : splitted) {
							if(!returnWord.matches(".*\\b" + j + "\\b.*")) {		// If the keywords are not present in the string, return false
								keyWord = false;
							}
						}
						if(keyWord == true) {
							if(i == 0) {
								System.out.println("--- Stocks ---");			// Print the stock if keywords are contained in stocks name
							}
							stockList.get(i).printStock();
						}
					}
					for(int i = 0; i < fundList.size(); i++) {					// Do the exact same thing for funds
						keyWord = true;
						returnWord = fundList.get(i).getName();
						returnWord = returnWord.toLowerCase();
						for(String j : splitted) {
							if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
								keyWord = false;
							}
						}
						if(keyWord == true) {
							if(i == 0) {
								System.out.println("--- Mutual Funds ---");
							}
							fundList.get(i).printFund();
						}
					}
					if(keyWord == false) {
						System.out.println("The investment searched for could not be found.");
					}
					if(stockList.size() + fundList.size() == 0) {
						System.out.println("There are no Investments in the Portfolio.");
					}
				}
				else if(searchSym.equals("") && searchKeyWord.equals("") && !searchPrice.equals("")) { // Last flag is set, search for price only
					double searchVal = 0;
					boolean foundPrice = false;
					
					if(searchPrice.indexOf('-') == -1) {									// Price search if inputted value is exactly the price
						if(searchPrice.matches("[1234567890.]+")) {
							searchVal = Double.parseDouble(searchPrice);
							for(int i = 0; i < stockList.size(); i++) {
								if(searchVal == stockList.get(i).getPrice()) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									foundPrice = true;
								}
							}
							for(int i = 0; i < fundList.size(); i++) {
								if(searchVal == fundList.get(i).getPrice()) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									foundPrice = true;
								}
							}
						}
						else {
							System.out.println("Enter nummerical values only.");
						}
						if(foundPrice == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
					else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf("-") != -1) {
						boolean isNum = true;											// ^ Price search if inputted value is a price range
						boolean outOfBounds = false;
						String val1;
						String val2;
						String[] searchVals;
						double double1 = 0;
						double double2 = 0;
						searchVals = searchPrice.split("-");
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
							for(int i = 0; i < stockList.size(); i++) {
								if(stockList.get(i).getPrice() >= double1 && stockList.get(i).getPrice() <= double2) {
									if(i == 0) {
										System.out.println("--- Stocks ---");			// If stock price falls between range, print 
									}
									stockList.get(i).printStock();
								}
							}
							for(int i = 0; i < fundList.size(); i++) {
								if(fundList.get(i).getPrice() >= double1 && fundList.get(i).getPrice() <= double2) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");		// Do the same for funds
									}
									fundList.get(i).printFund();
								}
							}
						}
						else if(isNum == false && outOfBounds!= true) {
							System.out.println("Enter nummerical values only.");
						}
					}
					else if(searchPrice.charAt(0) == '-') {								// If first digit is '-' will be less than value
						StringBuilder sb = new StringBuilder(searchPrice);
						String newSearchPrice;
						sb.deleteCharAt(0);
						newSearchPrice = sb.toString();
						if(newSearchPrice.matches("[1234567890.]+")) {					// Regex error checking
							searchVal = Double.parseDouble(newSearchPrice);
							for(int i = 0; i < stockList.size(); i++) {
								if(stockList.get(i).getPrice() <= searchVal) {
									if(i == 0) {
										System.out.println("--- Stocks ---");			// Print the stock if less than searched val
									}
									stockList.get(i).printStock();
								}
							}
							for(int i = 0; i < fundList.size(); i++) {
								if(fundList.get(i).getPrice() <= searchVal) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");		// Do the same for funds
									}
									fundList.get(i).printFund();
								}
							}
						}
						else {
							System.out.println("Enter nummerical values only.");
						}
					}
					else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {		// If the last digit is '-' will be greater than search price
						StringBuilder sb = new StringBuilder(searchPrice);
						String newSearchPrice;
						sb.deleteCharAt(searchPrice.length() - 1);
						newSearchPrice = sb.toString();
						if(newSearchPrice.matches("[1234567890.]+")) {					// Do same as above, error checking
							searchVal = Double.parseDouble(newSearchPrice);
							for(int i = 0; i < stockList.size(); i++) {
								if(stockList.get(i).getPrice() >= searchVal) {
									if(i == 0) {
										System.out.println("--- Stocks ---");			// Print the stocks and funds if greater than search price
									}	
									stockList.get(i).printStock();
								}
							}
							for(int i = 0; i < fundList.size(); i++) {
								if(fundList.get(i).getPrice() >= searchVal) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
								}
							}
						}
						else {
							System.out.println("Enter nummerical values only.");
						}
					}
					else {
						System.out.println("The investment symbol searched for could not be found.");
					}
				}
				
				// Everything after this point is COMBINATION SEARCH
				// It is literally the described 3 individual functions, integrated into each other
				// The same functionality is present, just in a messier and way worse way. But it works!
				// I really should have used functions but I ran out of time to modularize
				
				else if(!searchSym.equals("") && !searchKeyWord.equals("") && searchPrice.equals("")) { // First and Second flag set, search for Symbol and Name
					String[] splitted;
					String returnWord;
					searchKeyWord = searchKeyWord.toLowerCase();
					splitted = searchKeyWord.split("\\s+");
					boolean keyWord = true;
					
					for(int i = 0; i < stockList.size(); i++) {
						keyWord = true;
						returnWord = stockList.get(i).getName();
						returnWord = returnWord.toLowerCase();
						for(String j : splitted) {
							if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
								keyWord = false;
							}
						}
						if(searchSym.equals(stockList.get(i).getSym()) && keyWord == true) {
							if(i == 0) {
								System.out.println("--- Stocks ---");
							}
							stockList.get(i).printStock();
							found = true;
						}
					}
					for(int i = 0; i < fundList.size(); i++) {
						keyWord = true;
						returnWord = fundList.get(i).getName();
						returnWord = returnWord.toLowerCase();
						for(String j : splitted) {
							if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
								keyWord = false;
							}
						}
						if(searchSym.equals(fundList.get(i).getSym()) && keyWord == true) {
							if(i == 0) {
								System.out.println("--- Mutual Funds ---");
							}
							fundList.get(i).printFund();
							found = true;
						}
					}
					if(found == false) {
						System.out.println("The investment searched for could not be found.");
					}
				}
				else if(!searchSym.equals("") && searchKeyWord.equals("") && !searchPrice.equals("")) { // First and Last flag set, search for Symbol and price
					boolean isNum = true;
					boolean match = false;
					found = false;
					if(searchPrice.matches("[1234567890.-]+")) {
						for(int i = 0; i < stockList.size(); i++) {
							match = false;
							if(searchSym.equals(stockList.get(i).getSym())) {
								String[] searchVals;
								double double1 = 0;
								double double2 = 0;
								if(searchPrice.indexOf('-') == -1) {
									if(stockList.get(i).getPrice() == Double.parseDouble(searchPrice)) {
										match = true;
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Stocks ---");
										}
										stockList.get(i).printStock();
										found = true;
									}
								}
								else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf('-') != -1) {
									searchVals = searchPrice.split("-");
									for(String j : searchVals) {
										if(!j.matches("[1234567890.]+")) {
											isNum = false;
										}
									}
									if(searchVals.length != 2) {
										System.out.println("Too many search parameters. Usage: <range1-range2>");
										isNum = false;
										break;
									}
									if(isNum == true) {
										double1 = Double.parseDouble(searchVals[0]);
										double2 = Double.parseDouble(searchVals[1]);
										if(stockList.get(i).getPrice() >= double1 && stockList.get(i).getPrice() <= double2) {
											match = true;
										}
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Stocks ---");
										}
										stockList.get(i).printStock();
										found = true;
									}
								}
								else if(searchPrice.charAt(0) == '-') {
									StringBuilder sb = new StringBuilder(searchPrice);
									sb.deleteCharAt(0);
									String newPrice = sb.toString();
									if(stockList.get(i).getPrice() <= Double.parseDouble(newPrice)) {
										match = true;
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Stocks ---");
										}
										stockList.get(i).printStock();
										found = true;
									}
								}
								else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {
									StringBuilder sb = new StringBuilder(searchPrice);
									sb.deleteCharAt(searchPrice.length() - 1);
									String newPrice = sb.toString();
									if(stockList.get(i).getPrice() >= Double.parseDouble(newPrice)) {
										match = true;
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Stocks ---");
										}
										stockList.get(i).printStock();
										found = true;
									}
								}
								else {
									System.out.println("Incorrect price search format");
								}
							}
						}
						for(int i = 0; i < fundList.size(); i++) {
							match = false;
							if(searchSym.equals(fundList.get(i).getSym())) {
								String[] searchVals;
								double double1 = 0;
								double double2 = 0;
								if(searchPrice.indexOf('-') == -1) {
									if(fundList.get(i).getPrice() == Double.parseDouble(searchPrice)) {
										match = true;
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Mutual Funds ---");
										}
										fundList.get(i).printFund();
										found = true;
									}
								}
								else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf('-') != -1) {
									searchVals = searchPrice.split("-");
									for(String j : searchVals) {
										if(!j.matches("[1234567890.]+")) {
											isNum = false;
										}
									}
									if(searchVals.length != 2) {
										System.out.println("Too many search parameters. Usage: <range1-range2>");
										isNum = false;
										break;
									}
									if(isNum == true) {
										double1 = Double.parseDouble(searchVals[0]);
										double2 = Double.parseDouble(searchVals[1]);
										if(fundList.get(i).getPrice() >= double1 && fundList.get(i).getPrice() <= double2) {
											match = true;
										}
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Mutual Funds ---");
										}
										fundList.get(i).printFund();
										found = true;
									}
								}
								else if(searchPrice.charAt(0) == '-') {
									StringBuilder sb = new StringBuilder(searchPrice);
									sb.deleteCharAt(0);
									String newPrice = sb.toString();
									if(fundList.get(i).getPrice() <= Double.parseDouble(newPrice)) {
										match = true;
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Mutual Funds ---");
										}
										fundList.get(i).printFund();
										found = true;
									}
								}
								else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {
									StringBuilder sb = new StringBuilder(searchPrice);
									sb.deleteCharAt(searchPrice.length() - 1);
									String newPrice = sb.toString();
									if(fundList.get(i).getPrice() >= Double.parseDouble(newPrice)) {
										match = true;
									}
									if(match == true) {
										if(i == 0) {
											System.out.println("--- Mutual Funds ---");
										}
										fundList.get(i).printFund();
										found = true;
									}
								}
								else {
									System.out.println("Incorrect price search format");
								}
							}	
						}
						if(found == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
					else {
						System.out.println("Enter nummerical values only.");
					}
				}
				else if(searchSym.equals("") && !searchKeyWord.equals("") && !searchPrice.equals("")) { // Last 2 flags set, search for name and price
					String[] splitted;
					String returnWord;
					searchKeyWord = searchKeyWord.toLowerCase();
					splitted = searchKeyWord.split("\\s+");
					boolean keyWord = true;
					boolean match = false;
					boolean isNum = true;
					
					if(searchPrice.matches("[1234567890.-]+")) {
						for(int i = 0; i < stockList.size(); i++) {
							keyWord = true;
							match = false;
							returnWord = stockList.get(i).getName();
							returnWord = returnWord.toLowerCase();
							for(String j : splitted) {
								if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
									keyWord = false;
								}
							}
							
							String[] searchVals;
							double double1 = 0;
							double double2 = 0;
							if(searchPrice.indexOf('-') == -1) {
								if(stockList.get(i).getPrice() == Double.parseDouble(searchPrice)) {
									match = true;
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf('-') != -1) {
								searchVals = searchPrice.split("-");
								for(String j : searchVals) {
									if(!j.matches("[1234567890.]+")) {
										isNum = false;
									}
								}
								if(searchVals.length != 2) {
									System.out.println("Too many search parameters. Usage: <range1-range2>");
									isNum = false;
									break;
								}
								if(isNum == true) {
									double1 = Double.parseDouble(searchVals[0]);
									double2 = Double.parseDouble(searchVals[1]);
									if(stockList.get(i).getPrice() >= double1 && stockList.get(i).getPrice() <= double2) {
										match = true;
									}
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(0);
								String newPrice = sb.toString();
								if(stockList.get(i).getPrice() <= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(searchPrice.length() - 1);
								String newPrice = sb.toString();
								if(stockList.get(i).getPrice() >= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else {
								System.out.println("Incorrect price search format");
							}
						}
						for(int i = 0; i < fundList.size(); i++) {
							keyWord = true;
							match = false;
							returnWord = fundList.get(i).getName();
							returnWord = returnWord.toLowerCase();
							for(String j : splitted) {
								if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
									keyWord = false;
								}
							}
							
							String[] searchVals;
							double double1 = 0;
							double double2 = 0;
							if(searchPrice.indexOf('-') == -1) {
								if(fundList.get(i).getPrice() == Double.parseDouble(searchPrice)) {
									match = true;
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf('-') != -1) {
								searchVals = searchPrice.split("-");
								for(String j : searchVals) {
									if(!j.matches("[1234567890.]+")) {
										isNum = false;
									}
								}
								if(searchVals.length != 2) {
									System.out.println("Too many search parameters. Usage: <range1-range2>");
									isNum = false;
									break;
								}
								if(isNum == true) {
									double1 = Double.parseDouble(searchVals[0]);
									double2 = Double.parseDouble(searchVals[1]);
									if(fundList.get(i).getPrice() >= double1 && fundList.get(i).getPrice() <= double2) {
										match = true;
									}
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(0);
								String newPrice = sb.toString();
								if(fundList.get(i).getPrice() <= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(searchPrice.length() - 1);
								String newPrice = sb.toString();
								if(fundList.get(i).getPrice() >= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else {
								System.out.println("Incorrect price search format");
							}
						}
						if(found == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
					else {
						System.out.println("Enter nummerical values only.");
					}
				}
				else if(!searchSym.equals("") && !searchKeyWord.equals("") && !searchPrice.equals("")) { // All 3 Flags Set, search for symbol, keyword and price
					String[] splitted;
					String returnWord;
					searchKeyWord = searchKeyWord.toLowerCase();
					splitted = searchKeyWord.split("\\s+");
					boolean keyWord = true;
					boolean match = false;
					boolean isNum = true;
					boolean symCheck = false;
					
					if(searchPrice.matches("[1234567890.-]+")) {
						for(int i = 0; i < stockList.size(); i++) {
							keyWord = true;
							match = false;
							symCheck = false;
							
							if(searchSym.equals(stockList.get(i).getSym())) {
								symCheck = true;
							}
							
							returnWord = stockList.get(i).getName();
							returnWord = returnWord.toLowerCase();
							for(String j : splitted) {
								if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
									keyWord = false;
								}
							}
							
							String[] searchVals;
							double double1 = 0;
							double double2 = 0;
							if(searchPrice.indexOf('-') == -1) {
								if(stockList.get(i).getPrice() == Double.parseDouble(searchPrice)) {
									match = true;
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf('-') != -1) {
								searchVals = searchPrice.split("-");
								for(String j : searchVals) {
									if(!j.matches("[1234567890.]+")) {
										isNum = false;
									}
								}
								if(searchVals.length != 2) {
									System.out.println("Too many search parameters. Usage: <range1-range2>");
									isNum = false;
									break;
								}
								if(isNum == true) {
									double1 = Double.parseDouble(searchVals[0]);
									double2 = Double.parseDouble(searchVals[1]);
									if(stockList.get(i).getPrice() >= double1 && stockList.get(i).getPrice() <= double2) {
										match = true;
									}
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(0);
								String newPrice = sb.toString();
								if(stockList.get(i).getPrice() <= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(searchPrice.length() - 1);
								String newPrice = sb.toString();
								if(stockList.get(i).getPrice() >= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Stocks ---");
									}
									stockList.get(i).printStock();
									found = true;
								}
							}
							else {
								System.out.println("Incorrect price search format");
							}
						}
						for(int i = 0; i < fundList.size(); i++) {
							keyWord = true;
							match = false;
							symCheck = false;
							
							if(searchSym.equals(fundList.get(i).getSym())) {
								symCheck = true;
							}
							
							returnWord = fundList.get(i).getName();
							returnWord = returnWord.toLowerCase();
							for(String j : splitted) {
								if(!returnWord.matches(".*\\b" + j + "\\b.*")) {
									keyWord = false;
								}
							}
							
							String[] searchVals;
							double double1 = 0;
							double double2 = 0;
							if(searchPrice.indexOf('-') == -1) {
								if(fundList.get(i).getPrice() == Double.parseDouble(searchPrice)) {
									match = true;
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) != '-' && searchPrice.charAt(searchPrice.length() - 1) != '-' && searchPrice.indexOf('-') != -1) {
								searchVals = searchPrice.split("-");
								for(String j : searchVals) {
									if(!j.matches("[1234567890.]+")) {
										isNum = false;
									}
								}
								if(searchVals.length != 2) {
									System.out.println("Too many search parameters. Usage: <range1-range2>");
									isNum = false;
									break;
								}
								if(isNum == true) {
									double1 = Double.parseDouble(searchVals[0]);
									double2 = Double.parseDouble(searchVals[1]);
									if(fundList.get(i).getPrice() >= double1 && fundList.get(i).getPrice() <= double2) {
										match = true;
									}
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else if(searchPrice.charAt(0) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(0);
								String newPrice = sb.toString();
								if(fundList.get(i).getPrice() <= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else if(searchPrice.charAt(searchPrice.length() - 1) == '-') {
								StringBuilder sb = new StringBuilder(searchPrice);
								sb.deleteCharAt(searchPrice.length() - 1);
								String newPrice = sb.toString();
								if(fundList.get(i).getPrice() >= Double.parseDouble(newPrice)) {
									match = true;
								}
								if(match == true && keyWord == true && symCheck == true) {
									if(i == 0) {
										System.out.println("--- Mutual Funds ---");
									}
									fundList.get(i).printFund();
									found = true;
								}
							}
							else {
								System.out.println("Incorrect price search format");
							}
						}
						if(found == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
					else {
						System.out.println("Enter nummerical values only.");
					}
				}
				
				System.out.println();
			}
			else if(menuInput.equals("quit") || menuInput.equals("q")) {		// Quit Function
				System.out.println();
				menuLoop = false;
				System.out.println("Thanks for using the Portfolio Program.");
				System.out.println();
			}
			else {
				System.out.println("Invalid Input. Enter a new Option.\n");
			}
		}
		while(menuLoop == true);
		s.close();
	} // End of Run Method
	
} // End of Portfolio Class
