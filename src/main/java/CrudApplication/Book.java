package CrudApplication;

public class Book {
	private int bookid;
	private String bookName;
	private String bookDept;
	private int bookPrice;
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookDept() {
		return bookDept;
	}
	public void setBookDept(String bookDept) {
		this.bookDept = bookDept;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	@Override
	public String toString() {
		return "Book \t bookid: " + bookid + "\t bookName: " + bookName + "\t bookDept: " + bookDept + "\t bookPrice: "
				+ bookPrice;
	}
	
	
}
