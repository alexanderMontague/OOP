/*
 * 
 * This is the stock class. This class is used to hold each instance of a stock investment.
 * 
 */
public class Stock {
 
	private String symbol;
	private String name;
	private double quantity;
	private double price;
	private double bookVal;
	
	public Stock(String symbol, String name, double quantity, double price, double bookVal) {		// Constructor
		this.symbol = symbol;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookVal = bookVal;
	}
	
	public void printStock() {												// Used to Print the object
		System.out.println("Stock Symbol: " + symbol);
		System.out.println("Stock Name: " + name);
		System.out.println("Stock Quantity: " + quantity);
		System.out.println("Stock Price: " + price);
		System.out.printf("Stock bookVal: %.2f\n\n", bookVal);
	}
	
	public void addQuantity(double addQuantity, double newPrice) {			// Used when adding existing stocks
		bookVal = bookVal + (addQuantity * newPrice) + 9.99;
		quantity = quantity + addQuantity;
		price = newPrice;
	}
	
	public double sellQuantity(double sellQuantity, double sellPrice) {		// Used when selling stocks
		double moneyMade = 0;
		
		price = sellPrice;
		moneyMade = (sellQuantity * price) - 9.99;
		bookVal = bookVal * ((quantity - sellQuantity) / quantity);
		quantity = quantity - sellQuantity;
		
		return moneyMade;
	}
	
	public void updatePrice(double newPriceFnc) {							// Used to update the price
		price = newPriceFnc;
	}
	
	public double getGain() {												// Calculates gain
		double stockGainFnc = 0;
		double newWorth = 0;
		
		newWorth = (price * quantity) - 9.99;
		stockGainFnc = newWorth - bookVal;
		return stockGainFnc;
	}
	
	public String getSym() {													// Accessor Functions
		return symbol;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public double getQuantity() {
		return quantity;
	}
	
}
