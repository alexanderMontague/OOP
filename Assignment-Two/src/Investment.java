/*
 * 
 * This is the investment class. This class is used as a superclass for MutualFund and Stock.
 * 
 */
public class Investment {
	
	protected String symbol;
	protected String name;
	protected double quantity;
	protected double price;
	protected double bookVal;
	
	// Default Constructor
	public Investment() {
		symbol = "";
		name = "";
		quantity = 0;
		price = 0;
		bookVal = 0;
	}
	
	public Investment(String symbol, String name, double quantity, double price, double bookVal) {  // Constructors
		this.symbol = symbol;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.bookVal = bookVal;
	}
	
	public void updatePrice(double newPriceFnc) {							// Used to update the price
		price = newPriceFnc;
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
	public double getBookVal() {
		return bookVal;
	}
	
	@Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Stock s = (Stock) o;
        MutualFund mf = (MutualFund) o;
        if ((!(this.getSym().equals(s.getSym()))) || (!(this.getSym().equals(mf.getSym())))) return false;
        if ((!(this.getName().equals(s.getName()))) || (!(this.getName().equals(mf.getName())))) return false;
        if ((!(this.getQuantity() == s.getQuantity())) || (!(this.getQuantity() == mf.getQuantity()))) return false;
        if ((!(this.getPrice() == s.getPrice())) || (!(this.getPrice() == mf.getPrice()))) return false;
        if ((!(this.getBookVal() == s.getBookVal())) || (!(this.getBookVal() == mf.getBookVal()))) return false;
        return true;
    }
	
}
