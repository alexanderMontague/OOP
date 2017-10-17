/*
 * 
 * This is the mutual fund class. This class is used to hold each instance of a mutual fund investment.
 * 
 */
public class MutualFund {

	private String symbol;
	private String name;
	private double quantity;
	private double price;
	private double bookVal;
	
	public MutualFund(String symbol, String name, double quantity, double price, double bookVal) {  // Constructors
		this.symbol = symbol;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookVal = bookVal;
	}
	
	public void printFund() {																	 // Used to print the object
		System.out.println("Mutual Fund Symbol: " + symbol);
		System.out.println("Mutual Fund Name: " + name);
		System.out.println("Mutual Fund Quantity: " + quantity);
		System.out.println("Mutual Fund Price: " + price);
		System.out.printf("Mutual Fund bookVal: %.2f\n\n", bookVal);
	}
	
	public void addQuantity(double addQuantity, double newPrice) {								// When adding more of existing funds
		bookVal = bookVal + (addQuantity * newPrice);
		quantity = quantity + addQuantity;
		price = newPrice;
	}
	
	public double sellQuantity(double sellQuantity, double sellPrice) {							// When selling funds
		double moneyMade = 0;
		
		price = sellPrice;
		moneyMade = (sellQuantity * price) - 45.00;
		bookVal = bookVal * ((quantity - sellQuantity) / quantity);
		quantity = quantity - sellQuantity;
		
		return moneyMade;
	}
	
	public void updatePrice(double newPriceFnc) {												// Updates the price
		price = newPriceFnc;
	}
	
	public double getGain() {																	// Calculates gain
		double fundGainFnc = 0;
		double newWorth = 0;
		
		newWorth = (price * quantity) - 45.00;
		fundGainFnc = newWorth - bookVal;
		return fundGainFnc;
	}
	
	public String getSym() {																		// Accessor Functions
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
