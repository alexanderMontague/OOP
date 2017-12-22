import java.util.*;

public class Workbook extends Book {
	
	private int numProblems;
	
	public Workbook() {
		super("", "", "", 0, 0);
	}
	
	public Workbook(String title, String author, String ISBN, int numProblems, int year, double price) {
		super(title, author, ISBN, year, price);
		this.numProblems = numProblems;
	}
	
	public void printInfo() {
		super.printInfo();
		System.out.println("Number of Problems: " + this.numProblems);
    }
	
	public int getProblems() {
		return numProblems;
	}
	
	@Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Workbook wb = (Workbook) o;
        if (!(this.getProblems() == wb.getProblems())) return false;
        return true;
    }
	
	@Override
    public int hashCode() {
    		return Objects.hash(numProblems);
    }
}
