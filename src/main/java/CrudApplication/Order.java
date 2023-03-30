package CrudApplication;

public class Order {
	
	private int orderid;
	private int userid;
	private String firstname;
	private int bookid;
	private String bookName;
	private int quantity;
	private int bookPrice;
	
	@Override
	public String toString() {
		return "Order \t orderid=" + orderid + "\t userid: " + userid + "\t firstname: " + firstname + "\t bookid: " + bookid
				+ "\t bookName: " + bookName + "\t quantity: " + quantity + "\t bookPrice: " + bookPrice ;
	}
	public int getOrderid() {
		return orderid;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	
	
}
