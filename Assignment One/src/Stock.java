import java.util.*;
import java.io.*;
import java.text.*;

public class Stock {
 
	private String symbol;
	private String name;
	private int quantity;
	private double price;
	private double bookVal;
	
	public Stock(String symbol, String name, int quantity, double price, double bookVal) {
		this.symbol = symbol;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookVal = bookVal;
	}
	
	public void printStock() {
		System.out.println();
		System.out.println("Stocks: ");
		System.out.println("Stock Symbol: " + this.symbol);
		System.out.println("Stock Name: " + this.name);
		System.out.println("Stock Quantity: " + this.quantity);
		System.out.println("Stock Price: " + this.price);
		System.out.println("Stock bookVal: " + this.bookVal);
	}
	
	public void addQuantity(int addQuantity) {
		
		quantity = quantity + addQuantity;
		bookVal = quantity * price;
		
	}
	
	public String getSym() {
		return symbol;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
}
