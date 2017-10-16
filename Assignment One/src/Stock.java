
public class Stock {
 
	private String symbol;
	private String name;
	private double quantity;
	private double price;
	private double bookVal;
	
	public Stock(String symbol, String name, double quantity, double price, double bookVal) {
		this.symbol = symbol;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookVal = bookVal;
	}
	
	public void printStock() {
		System.out.println("Stock Symbol: " + symbol);
		System.out.println("Stock Name: " + name);
		System.out.println("Stock Quantity: " + quantity);
		System.out.println("Stock Price: " + price);
		System.out.printf("Stock bookVal: %.2f\n\n", bookVal);
	}
	
	public void addQuantity(double addQuantity, double newPrice) {
		bookVal = bookVal + (addQuantity * newPrice) + 9.99;
		quantity = quantity + addQuantity;
		price = newPrice;
	}
	
	public double sellQuantity(double sellQuantity, double sellPrice) {
		double moneyMade = 0;
		
		price = sellPrice;
		moneyMade = (sellQuantity * price) - 9.99;
		bookVal = bookVal * ((quantity - sellQuantity) / quantity);
		quantity = quantity - sellQuantity;
		
		return moneyMade;
	}
	
	public void updatePrice(double newPriceFnc) {
		price = newPriceFnc;
	}
	
	public double getGain() {
		double stockGainFnc = 0;
		double newWorth = 0;
		
		newWorth = (price * quantity) - 9.99;
		stockGainFnc = newWorth - bookVal;
		return stockGainFnc;
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
	public double getQuantity() {
		return quantity;
	}
	
}
