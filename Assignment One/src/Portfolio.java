// Alexander Montague
// amontagu@uoguelph.ca
// 0959687
// October - 2017
// Portfolio Main Class

import java.util.*;
import java.io.*;
import java.text.*;

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
			
			if(menuInput.equals("buy") || menuInput.equals("b")) {
				String buyMenu = "NULL";
				String stockSym;
				String mfSym;
				String stockName;
				String mfName;
				int stockQty = 0;
				int mfQty = 0;
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
					double returnPrice;
					boolean copyStock = false;
					
					System.out.print("Stock Symbol: ");
					stockSym = s.nextLine();
					System.out.print("Stock Name: ");
					stockName = s.nextLine();
					System.out.print("Stock Quantity: ");
					stockQty = s.nextInt();
					System.out.print("Stock Price: ");
					stockPrice = s.nextDouble();
					stockBookVal = stockQty * stockPrice;
										
					for(int i = 0; i < stockList.size(); i++) {
						returnSym = stockList.get(i).getSym();
						returnName = stockList.get(i).getName();
						returnPrice = stockList.get(i).getPrice();
						if(returnSym.equals(stockSym) && returnName.equals(stockName) && returnPrice == stockPrice) {
							stockList.get(i).addQuantity(stockQty);
							copyStock = true;
						}
					}
					if(copyStock == false) {
						Stock tempStock = new Stock(stockSym, stockName, stockQty, stockPrice, stockBookVal);
						stockList.add(tempStock);
					}
				}
				else if(buyMenu.equals("mf") || buyMenu.equals("mutualfund") || buyMenu.equals("mutual") || buyMenu.equals("m")) {
					String returnSym;
					String returnName;
					double returnPrice;
					boolean copyFund = false;
					
					System.out.print("Mutual Fund Symbol: ");
					mfSym = s.nextLine();
					System.out.print("Mutual Fund Name: ");
					mfName = s.nextLine();
					System.out.print("Mutual Fund Quantity: ");
					mfQty = s.nextInt();
					System.out.print("Mutual Fund Price: ");
					mfPrice = s.nextDouble();
					mfBookVal = mfQty * mfPrice;
										
					for(int i = 0; i < fundList.size(); i++) {
						returnSym = fundList.get(i).getSym();
						returnName = fundList.get(i).getName();
						returnPrice = fundList.get(i).getPrice();
						if(returnSym.equals(mfSym) && returnName.equals(mfName) && returnPrice == mfPrice) {
							fundList.get(i).addQuantity(mfQty);
							copyFund = true;
						}
					}
					if(copyFund == false) {
						MutualFund tempFund = new MutualFund(mfSym, mfName, mfQty, mfPrice, mfBookVal);
						fundList.add(tempFund);
					}
				}
				
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("sell")) {
				System.out.println();
				
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("update") || menuInput.equals("u")) {
				System.out.println();
				
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("gain") || menuInput.equals("g")) {
				System.out.println();
				
				System.out.println();
				s.nextLine();
			}
			else if(menuInput.equals("search")) {
				for(int i = 0; i < stockList.size(); i++) {
					stockList.get(i).printStock();
				}
				for(int i = 0; i < fundList.size(); i++) {
					fundList.get(i).printFund();
				}
				
				System.out.println();
			}
			else if(menuInput.equals("quit") || menuInput.equals("q")) {
				System.out.println();
				menuLoop = false;
				System.out.println();
			}
			else {
				System.out.println("Invalid Input. Enter a new Option.\n");
			}
		}
		while(menuLoop == true);
	}
	
	public void buy() {
		
	}

}
