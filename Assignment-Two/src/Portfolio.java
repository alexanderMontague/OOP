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

public class Portfolio {
	
	private HashMap<String, ArrayList<Integer>> keyWordMap;
	private ArrayList<Investment> investmentList;
	private static String fileName;
	
	public Portfolio() {
		this.investmentList = new ArrayList<Investment>();
		this.keyWordMap = new HashMap<String, ArrayList<Integer>>();
	}
	
	public static void main(String[] args) {
		
		Portfolio runObj = new Portfolio();
		if(args.length != 0) {
			fileName = args[0];
			runObj.run();
		}
		else {
			System.out.println("Please add a filename as a command line argument");
			System.out.println("Usage: Java Portfolio <fileName> OR add parameter in IDE");
		}
		
	}
	
	public void run() {
		
		// Variable Declaration
		Scanner s = new Scanner(System.in);
		
		String menuInput;
		Boolean menuLoop = true;
		
		// Import Investments From File
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
					int index = 0;
					boolean isNumber = true;
					boolean isDup = false;
					boolean addStock = false;
					
					System.out.print("Stock Symbol: ");
					stockSym = s.nextLine();
					System.out.print("Stock Name: ");
					stockName = s.nextLine();
					System.out.print("Stock Quantity: ");
					try {
						stockQty = s.nextDouble();
						while(stockQty < 0) {
							System.out.println("Enter a quantity greater than 0");
							System.out.print("Stock Quantity: ");
							stockQty = s.nextDouble();
						}
					}
					catch(InputMismatchException e) {
						isNumber = false;
						s.nextLine();
					}
					System.out.print("Stock Price: ");
					try {
						stockPrice = s.nextDouble();
						while(stockPrice < 0) {
							System.out.println("Enter a stock price greater than 0");
							System.out.print("Stock Price: ");
							stockPrice = s.nextDouble();
						}
					}
					catch(InputMismatchException e) {
						isNumber = false;
					}
					
					if(isNumber == true) {
						stockBookVal = (stockQty * stockPrice) + 9.99;
						for(int i = 0; i < investmentList.size(); i++) {
							if(stockSym.equals((investmentList.get(i)).getSym())) {
								if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
									System.out.println("Duplicate Symbols are not Allowed!");
									isDup = true;
								}
								else if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")){
									index = i;
									addStock = true;
								}
							}
						}
						
						if(addStock == true) {
							Stock tempStock = new Stock(stockSym, stockName, stockQty, stockPrice, stockBookVal);
							tempStock = (Stock)investmentList.get(index);
							tempStock.addQuantityStock(stockQty, stockPrice);
						}
						else if(isDup == false) {
							Stock tempStock = new Stock(stockSym, stockName, stockQty, stockPrice, stockBookVal);
							investmentList.add((Investment)tempStock);
						}
					}
					else {													// If invalid input was entered
						System.out.println("Enter nummerical values only for Quantity and Price.");
					}
				}
				else if(buyMenu.equals("mf") || buyMenu.equals("mutualfund") || buyMenu.equals("mutual fund") || buyMenu.equals("m")) {
					boolean isNumber = true;
					boolean isDup = false;
					boolean addFund = false;
					int index = 0;
					
					System.out.print("Mutual Fund Symbol: ");
					mfSym = s.nextLine();
					System.out.print("Mutual Fund Name: ");
					mfName = s.nextLine();
					System.out.print("Mutual Fund Quantity: ");
					try {
						mfQty = s.nextDouble();
						while(mfQty < 0) {
							System.out.println("Enter a quantity greater than 0");
							System.out.print("Mutual Fund Quantity: ");
							mfQty = s.nextDouble();
						}
					}
					catch(InputMismatchException e) {
						isNumber = false;
						s.nextLine();
					}
					System.out.print("Mutual Fund Price: ");
					try {
						mfPrice = s.nextDouble();
						while(mfPrice < 0) {
							System.out.println("Enter a Mutual Fund price greater than 0");
							System.out.print("Mutual Fund Price: ");
							mfPrice = s.nextDouble();
						}
					}
					catch(InputMismatchException e) {
						isNumber = false;
					}
					
					if(isNumber == true) {
					
						mfBookVal = mfQty * mfPrice;
						for(int i = 0; i < investmentList.size(); i++) {
							if(mfSym.equals((investmentList.get(i)).getSym())) {
								if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
									System.out.println("Duplicate Symbols are not Allowed!");
									isDup = true;
								}
								else if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")){
									index = i;
									addFund = true;
								}
							}
						}
						
						if(addFund == true) {
							MutualFund tempFund = new MutualFund(mfSym, mfName, mfQty, mfPrice, mfBookVal);
							tempFund = (MutualFund)investmentList.get(index);
							tempFund.addQuantityFund(mfQty, mfPrice);
						}
						else if(isDup == false) {
							MutualFund tempFund = new MutualFund(mfSym, mfName, mfQty, mfPrice, mfBookVal);
							investmentList.add((Investment)tempFund);
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
				double sellPrice = 0;
				double madeMoney = 0;
				double sellQty = 0;
				boolean isNumber = true;
				boolean sold = false;
				boolean one = true;
				boolean two = true;
				
				System.out.println();
				
				if(investmentList.size() == 0) {
					System.out.println("There are no Investments to sell in the Portfolio.\n");
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
						
						sold = false;
						for(int j = 0; j < investmentList.size(); j++) {
							if(sellSym.equals(investmentList.get(j).getSym())) {
								if(sellQty <= investmentList.get(j).getQuantity()) {
									if(investmentList.get(j).getClass().getCanonicalName().equals("Stock")) {
										madeMoney = ((Stock)investmentList.get(j)).sellQuantityStock(sellQty, sellPrice);
										System.out.println("You received $" + madeMoney + " selling " + sellQty + " shares at $" + sellPrice + " a share");
										if(investmentList.get(j).getQuantity() == 0) {
											investmentList.remove(j);
										}
										sold = true;
									}
									else if(investmentList.get(j).getClass().getCanonicalName().equals("MutualFund")) {
										madeMoney = ((MutualFund)investmentList.get(j)).sellQuantityFund(sellQty, sellPrice);
										System.out.println("You received $" + madeMoney + " selling " + sellQty + " shares at $" + sellPrice + " a share");
										if(investmentList.get(j).getQuantity() == 0) {
											investmentList.remove(j);
										}
										sold = true;
									}
								}
								else {
									if(one)
										System.out.println("You are trying to sell more investments than available.");
										one = false;
								}
							}
						}
						if(sold == false) {
							if(two)
								System.out.println("The investment you are trying to sell does not exist.");
								two = false;
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
				
				System.out.println();
				if(investmentList.size() == 0) {
					System.out.println("There are no Investments in the Portfolio.");
					System.out.println("If input hangs, press <Enter>");
				}
				else {
					System.out.println("--- Update all Investment Prices ---");
					for(int i = 0; i < investmentList.size(); i++) {
						System.out.printf("Update Price for Investment: %s (%s)\n", investmentList.get(i).getName(), investmentList.get(i).getSym());
						System.out.print("New Price: ");
						try {
							newPrice = s.nextDouble();
						}
						catch(InputMismatchException e) {
							System.out.println("Enter only nummerical values.");
							System.out.println("If input hangs, press <Enter>");
							s.nextLine();
							break;
						}
						investmentList.get(i).updatePrice(newPrice);
					}
				}
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("gain") || menuInput.equals("g")) { // Gain function
				double totalGain = 0;
				System.out.println();
				if(investmentList.size() == 0) {
					System.out.println("There are no Investments in the Portfolio.");
					System.out.println();
				}
				else {
					for(int k = 0; k < investmentList.size(); k++) {
						if(investmentList.get(k).getClass().getCanonicalName().equals("Stock") ) {
							totalGain = totalGain + ((Stock)investmentList.get(k)).getGainStock();
						}
						else if(investmentList.get(k).getClass().getCanonicalName().equals("MutualFund") ) {
							totalGain = totalGain + ((MutualFund)investmentList.get(k)).getGainFund();
						}
					}
					System.out.printf("Total Gain from Portfolio: $%.2f\n", totalGain);
					System.out.println();
				}
			}
			else if(menuInput.equals("search")) { // Search function
				String searchSym;
				String searchKeyWord;
				String searchPrice;
				boolean firstFund = true;
				boolean firstStock = true;
				
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
					if(investmentList.size() == 0) {
						System.out.println("There are no Investments in the Portfolio.");;
					}
					else {
						for(int i = 0; i < investmentList.size(); i++) {
							if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
								if(firstStock) {
									System.out.println("--- Stocks ---");
									firstStock = false;
								}
								((Stock)investmentList.get(i)).printStock();
							}
						}
						for(int i = 0; i < investmentList.size(); i++) {
							if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
								if(firstFund) {
									System.out.println("--- Mutual Funds ---");
									firstFund = false;
								}
								((MutualFund)investmentList.get(i)).printFund();
							}
						}
					}
				}
				else if(!searchSym.equals("") && searchKeyWord.equals("") && searchPrice.equals("")) { // First flag is set, search for symbol only
					searchSymVoid(searchSym);
				}
				else if(searchSym.equals("") && !searchKeyWord.equals("") && searchPrice.equals("")) { // Second flag is set, Search for Name only
					searchKeyWordVoid(searchKeyWord);
				}
				else if(searchSym.equals("") && searchKeyWord.equals("") && !searchPrice.equals("")) { // Last flag is set, search for price only
					searchPriceVoid(searchPrice);
				}
				// Everything after this point is COMBINATION SEARCH
				else if(!searchSym.equals("") && !searchKeyWord.equals("") && searchPrice.equals("")) { // First and Second flag set, search for Symbol and Name
					ArrayList<Integer> symbol = new ArrayList<>();
					ArrayList<Integer> keyWord = new ArrayList<>();
					ArrayList<Integer> goodIndex = new ArrayList<>();
					boolean foundInvestment = false;
					symbol = searchSym(searchSym);
					keyWord = searchKeyWord(searchKeyWord);
					goodIndex = symbol;
					if(goodIndex.retainAll(keyWord) || !goodIndex.retainAll(keyWord)) {
						for(int i = 0; i < goodIndex.size(); i++) {
							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
								if(firstStock) {
									System.out.println("--- Stocks ---");
									firstStock = false;
								}
								((Stock)investmentList.get(goodIndex.get(i))).printStock();
								foundInvestment = true;
							}
						}
						for(int i = 0; i < goodIndex.size(); i++) {
							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
								if(firstFund) {
									System.out.println("--- Mutual Funds ---");
									firstFund = false;
								}
								((MutualFund)investmentList.get(goodIndex.get(i))).printFund();
								foundInvestment = true;
							}
						}
						if(foundInvestment == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
				}
				else if(!searchSym.equals("") && searchKeyWord.equals("") && !searchPrice.equals("")) { // First and Last flag set, search for Symbol and price
					ArrayList<Integer> symbol = new ArrayList<>();
					ArrayList<Integer> price = new ArrayList<>();
					ArrayList<Integer> goodIndex = new ArrayList<>();
					boolean foundInvestment = false;
					symbol = searchSym(searchSym);
					price = searchPrice(searchPrice);
					goodIndex = symbol;
					if(goodIndex.retainAll(price) || !goodIndex.retainAll(price)) {
						for(int i = 0; i < goodIndex.size(); i++) {
							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
								if(firstStock) {
									System.out.println("--- Stocks ---");
									firstStock = false;
								}
								((Stock)investmentList.get(goodIndex.get(i))).printStock();
								foundInvestment = true;
							}
						}
						for(int i = 0; i < goodIndex.size(); i++) {
							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
								if(firstFund) {
									System.out.println("--- Mutual Funds ---");
									firstFund = false;
								}
								((MutualFund)investmentList.get(goodIndex.get(i))).printFund();
								foundInvestment = true;
							}
						}
						if(foundInvestment == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
				}
				else if(searchSym.equals("") && !searchKeyWord.equals("") && !searchPrice.equals("")) { // Last 2 flags set, search for name and price
					ArrayList<Integer> keyWord = new ArrayList<>();
					ArrayList<Integer> price = new ArrayList<>();
					ArrayList<Integer> goodIndex = new ArrayList<>();
					boolean foundInvestment = false;
					keyWord = searchKeyWord(searchKeyWord);
					price = searchPrice(searchPrice);
					goodIndex = keyWord;
					if(goodIndex.retainAll(price) || !goodIndex.retainAll(price)) {
						for(int i = 0; i < goodIndex.size(); i++) {
							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
								if(firstStock) {
									System.out.println("--- Stocks ---");
									firstStock = false;
								}
								((Stock)investmentList.get(goodIndex.get(i))).printStock();
								foundInvestment = true;
							}
						}
						for(int i = 0; i < goodIndex.size(); i++) {
							if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
								if(firstFund) {
									System.out.println("--- Mutual Funds ---");
									firstFund = false;
								}
								((MutualFund)investmentList.get(goodIndex.get(i))).printFund();
								foundInvestment = true;
							}
						}
						if(foundInvestment == false) {
							System.out.println("The investment searched for could not be found.");
						}
					}
				}
				else if(!searchSym.equals("") && !searchKeyWord.equals("") && !searchPrice.equals("")) { // All 3 Flags Set, search for symbol, keyword and price
					ArrayList<Integer> symbol = new ArrayList<>();
					ArrayList<Integer> keyWord = new ArrayList<>();
					ArrayList<Integer> price = new ArrayList<>();
					ArrayList<Integer> goodIndex = new ArrayList<>();
					boolean foundInvestment = false;
					symbol = searchSym(searchSym);
					keyWord = searchKeyWord(searchKeyWord);
					price = searchPrice(searchPrice);
					goodIndex = symbol;
					if(goodIndex.retainAll(keyWord) || !goodIndex.retainAll(keyWord)) {
						if(goodIndex.retainAll(price) || !goodIndex.retainAll(price)) {
							for(int i = 0; i < goodIndex.size(); i++) {
								if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("Stock")) {
									if(firstStock) {
										System.out.println("--- Stocks ---");
										firstStock = false;
									}
									((Stock)investmentList.get(goodIndex.get(i))).printStock();
									foundInvestment = true;
								}
							}
							for(int i = 0; i < goodIndex.size(); i++) {
								if(investmentList.get(goodIndex.get(i)).getClass().getCanonicalName().equals("MutualFund")) {
									if(firstFund) {
										System.out.println("--- Mutual Funds ---");
										firstFund = false;
									}
									((MutualFund)investmentList.get(goodIndex.get(i))).printFund();
									foundInvestment = true;
								}
							}
							if(foundInvestment == false) {
								System.out.println("The investment searched for could not be found.");
							}
						}
					}
				}
				System.out.println();
			}
			else if(menuInput.equals("quit") || menuInput.equals("q")) {		// Quit Function
				System.out.println();
				menuLoop = false;
				System.out.println("Thanks for using the Portfolio Program.");
				System.out.println();
				
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
			else {
				System.out.println("Invalid Input. Enter a new Option.\n");
			}
		}
		while(menuLoop == true);
		s.close();
	} // End of Run Method
	
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
	
	public void searchSymVoid(String searchSym) {
		boolean firstStock = true;
		boolean firstFund = true;
		boolean found = false;
		
		for(int i = 0; i < investmentList.size(); i++) {
			if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
				if((searchSym.toLowerCase()).equals(investmentList.get(i).getSym().toLowerCase())) {
					if(firstStock) {
						System.out.println("--- Stocks ---");
						firstStock = false;
						found = true;
					}
					((Stock)investmentList.get(i)).printStock();
				}
			}
		}
		for(int i = 0; i < investmentList.size(); i++) {
			if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
				if((searchSym.toLowerCase()).equals(investmentList.get(i).getSym().toLowerCase())) {
					if(firstFund) {
						System.out.println("--- Mutual Funds ---");
						firstFund = false;
						found = true;
					}
					((MutualFund)investmentList.get(i)).printFund();
				}
			}
		}
		if(found == false) {
			System.out.println("The investment searched for could not be found.");
		}
	}
	
	public void searchKeyWordVoid(String searchWord) {
		ArrayList<Integer> goodIndex = new ArrayList<Integer>();
		String[] splitWord;
		String tempWord = "NULL";
		boolean firstStock = true;
		boolean firstFund = true;
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
			for(int x = 0; x < goodIndex.size(); x++) {	// Print the investment at goodIndex indexes
				if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("Stock")) {
					if(firstStock) {
						System.out.println("--- Stocks ---");
						firstStock = false;
					}
					((Stock)investmentList.get(goodIndex.get(x))).printStock();
				}
				else if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("MutualFund")) {
					if(firstFund) {
						System.out.println("--- Mutual Funds ---");
						firstFund = false;
					}
					((MutualFund)investmentList.get(goodIndex.get(x))).printFund();
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
				for(int x = 0; x < goodIndex.size(); x++) {
					if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("Stock")) {
						if(firstStock) {
							System.out.println("--- Stocks ---");
							firstStock = false;
						}
						((Stock)investmentList.get(goodIndex.get(x))).printStock();
					}
					else if(investmentList.get(goodIndex.get(x)).getClass().getCanonicalName().equals("MutualFund")) {
						if(firstFund) {
							System.out.println("--- Mutual Funds ---");
							firstFund = false;
						}
						((MutualFund)investmentList.get(goodIndex.get(x))).printFund();
					}
				}
			}
		}
		goodIndex.clear();
		keyWordMap.clear();
	}
	
	public void searchPriceVoid(String price) {
		double searchVal = 0;
		boolean foundPrice = false;
		boolean firstStock = true;
		boolean firstFund = true;
		
		if(price.indexOf('-') == -1) {									// Price search if inputted value is exactly the price
			if(price.matches("[1234567890.]+")) {
				searchVal = Double.parseDouble(price);
				for(int i = 0; i < investmentList.size(); i++) {	
					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
						if(investmentList.get(i).getPrice() == searchVal) {
							if(firstStock) {
								System.out.println("--- Stocks ---");
								firstStock = false;
							}
							foundPrice = true;
							((Stock)investmentList.get(i)).printStock();
						}
					}
					else if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
						if(investmentList.get(i).getPrice() == searchVal) {
							if(firstFund) {
								System.out.println("--- Mutual Funds ---");
								firstFund = false;
							}
							foundPrice = true;
							((MutualFund)investmentList.get(i)).printFund();
						}
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
					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
						if(investmentList.get(i).getPrice() >= double1 && investmentList.get(i).getPrice() <= double2) {
							if(firstStock) {
								System.out.println("--- Stocks ---");			// If stock price falls between range, print 
								firstStock = false;
							}
							((Stock)investmentList.get(i)).printStock();
						}
					}
				}
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
						if(investmentList.get(i).getPrice() >= double1 && investmentList.get(i).getPrice() <= double2) {
							if(firstFund) {
								System.out.println("--- Mutual Funds ---");		// Do the same for funds
								firstFund = false;
							}
							((MutualFund)investmentList.get(i)).printFund();
						}
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
					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
						if(investmentList.get(i).getPrice() <= searchVal) {
							if(firstStock) {
								System.out.println("--- Stocks ---");			// Print the stock if less than searched val
								firstStock = false;
							}
							((Stock)investmentList.get(i)).printStock();
						}
					}
				}
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
						if(investmentList.get(i).getPrice() <= searchVal) {
							if(firstFund) {
								System.out.println("--- Mutual Funds ---");		// Do the same for funds
								firstFund = false;
							}
							((MutualFund)investmentList.get(i)).printFund();
						}
					}
				}
			}
			else {
				System.out.println("Enter nummerical values only.");
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
					if(investmentList.get(i).getClass().getCanonicalName().equals("Stock")) {
						if(investmentList.get(i).getPrice() >= searchVal) {
							if(firstStock) {
								System.out.println("--- Stocks ---");			// Print the stocks and funds if greater than search price
								firstStock = false;
							}	
							((Stock)investmentList.get(i)).printStock();
						}
					}
				}
				for(int i = 0; i < investmentList.size(); i++) {
					if(investmentList.get(i).getPrice() >= searchVal) {
						if(investmentList.get(i).getClass().getCanonicalName().equals("MutualFund")) {
							if(firstFund) {
								System.out.println("--- Mutual Funds ---");
								firstFund = false;
							}
							((MutualFund)investmentList.get(i)).printFund();
						}
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
	
} // End of Portfolio Class
