/*
 * 
 * This is the mutual fund class. This class is used to hold each instance of a mutual fund investment.
 * 
 */
public class MutualFund extends Investment{

	public MutualFund(String symbol, String name, double quantity, double price, double bookVal) {  // Constructors
		super(symbol, name, quantity, price, bookVal);
	}
	
	public void printFund() {																	 // Used to print the object
		System.out.println("Mutual Fund Symbol: " + symbol);
		System.out.println("Mutual Fund Name: " + name);
		System.out.println("Mutual Fund Quantity: " + quantity);
		System.out.println("Mutual Fund Price: " + price);
		System.out.printf("Mutual Fund bookVal: %.2f\n\n", bookVal);
	}
	
	public void addQuantityFund(double addQuantity, double newPrice) {								// When adding more of existing funds
		bookVal = bookVal + (addQuantity * newPrice);
		quantity = quantity + addQuantity;
		price = newPrice;
	}
	
	public double sellQuantityFund(double sellQuantity, double sellPrice) {							// When selling funds
		double moneyMade = 0;
		
		price = sellPrice;
		moneyMade = (sellQuantity * price) - 45.00;
		bookVal = bookVal * ((quantity - sellQuantity) / quantity);
		quantity = quantity - sellQuantity;
		
		return moneyMade;
	}
	
	public double getGainFund() {																	// Calculates gain
		double fundGainFnc = 0;
		double newWorth = 0;
		
		newWorth = (price * quantity) - 45.00;
		fundGainFnc = newWorth - bookVal;
		return fundGainFnc;
	}
	
}
