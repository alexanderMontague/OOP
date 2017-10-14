import java.util.*;
import java.io.*;
import java.text.*;

public class MutualFund {

	private String symbol;
	private String name;
	private int quantity;
	private double price;
	private double bookVal;
	
	public MutualFund(String symbol, String name, int quantity, double price, double bookVal) {
		this.symbol = symbol;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookVal = bookVal;
	}
	
	public void printFund() {
		System.out.println();
		System.out.println("Mutual Funds: ");
		System.out.println("Mutual Fund Symbol: " + this.symbol);
		System.out.println("Mutual Fund Name: " + this.name);
		System.out.println("Mutual Fund Quantity: " + this.quantity);
		System.out.println("Mutual Fund Price: " + this.price);
		System.out.println("Mutual Fund bookVal: " + this.bookVal);
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
