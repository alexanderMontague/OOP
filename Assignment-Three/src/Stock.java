/*
 * 
 * This is the stock class. This class is used to hold each instance of a stock investment.
 * 
 */
public class Stock extends Investment{
	
	public Stock(String symbol, String name, double quantity, double price, double bookVal) {		// Constructor
		super(symbol, name, quantity, price, bookVal);
	}
	
	public void printStock() {												// Used to Print the object
		System.out.println("Stock Symbol: " + getSym());
		System.out.println("Stock Name: " + getName());
		System.out.println("Stock Quantity: " + getQuantity());
		System.out.println("Stock Price: " + getPrice());
		System.out.printf("Stock bookVal: %.2f\n\n", getBookVal());
	}
	
	public void addQuantityStock(double addQuantity, double newPrice) {			// Used when adding existing stocks
		bookVal = bookVal + (addQuantity * newPrice) + 9.99;
		quantity = quantity + addQuantity;
		super.price = newPrice;
	}
	
	public double sellQuantityStock(double sellQuantity, double sellPrice) {		// Used when selling stocks
		double moneyMade = 0;
		
		price = sellPrice;
		moneyMade = (sellQuantity * price) - 9.99;
		bookVal = bookVal * ((quantity - sellQuantity) / quantity);
		quantity = quantity - sellQuantity;
		
		return moneyMade;
	}
	
	public double getGainStock() {												// Calculates gain
		double stockGainFnc = 0;
		double newWorth = 0;
		
		newWorth = (price * quantity) - 9.99;
		stockGainFnc = newWorth - bookVal;
		return stockGainFnc;
	}
	
}
