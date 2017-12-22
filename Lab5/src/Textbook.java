import java.util.*;

public class Textbook extends Book {
	
	private String subject;
	private String workbookISBN;
	
	public Textbook() {
		super("", "", "", 0, 0);
	}
	
	public Textbook(String title, String author, String ISBN, String subject, String workbookISBN, int year, double price) {
		super(title, author, ISBN, year, price);
		this.subject = subject;
		this.workbookISBN = workbookISBN;
	}
	
	public void printInfo() {
		super.printInfo();
		System.out.println("Subject: " + this.subject + "\nWorkbook ISBN: " + this.workbookISBN);
    }
	
	public String getSubject() {
		return subject;
	}
	
	public String getWbISBN() {
		return workbookISBN;
	}
	
	@Override
    public boolean equals(Object o){
        if (o == this) return true;
        
        if (!(this.getClass().equals(o.getClass()))) return false;
        
        Textbook tb = (Textbook) o;
        return this.getSubject().equals(tb.getSubject()) && this.getWbISBN().equals(tb.getWbISBN()) && super.equals(o);  
    }
	
    @Override
    public int hashCode() {
    		return Objects.hash(subject, workbookISBN);
    }
}
