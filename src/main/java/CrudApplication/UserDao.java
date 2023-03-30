package CrudApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
	private String addUserQuery = "INSERT INTO SampleDB.users(firstname,passwd,lastname,email,dob,city) VALUES (?, ?, ?, ? ,? ,? )";
	PreparedStatement addUserPs = null;
	
	private String updateUserQuery = "UPDATE SampleDB.users SET firstname=?, passwd=?, lastname=?, email=?, dob=?, city=?" + "WHERE userid=?";
	PreparedStatement updateUserPs = null;
	
	private String getAllUserQuery = "SELECT * FROM SampleDB.users";
	PreparedStatement getAllUserPs = null;
	
	private String pwdResetQuery = "UPDATE SampleDB.users SET passwd=?" + "WHERE email=?";
	PreparedStatement pwdResetPs = null;
	
	private String userLoginQuery = "SELECT count(firstname) AS count_name FROM SampleDB.users WHERE firstname=? AND passwd=? AND email=?";
	PreparedStatement userLoginPs = null;
	
	private String userIdFetchQuery = "SELECT * FROM SampleDB.users WHERE email=?";
	PreparedStatement userIdFetchPs = null;
	
	private String getAllBookQuery = "SELECT * FROM SampleDB.books";
	PreparedStatement getAllBookPs = null;
	
	private String makeOrderQuery = "INSERT INTO `SampleDB`.`order` (userid, firstname, book_id, bookName, quantity, bookPrice) VALUES (?,?,?,?,?,?);";
	PreparedStatement makeOrderPs = null;
	
	private String getBookIdQuery = "SELECT * FROM SampleDB.books WHERE book_id=?";
	PreparedStatement getBookIdPs = null;
	
	private String viewOrdersQuery = "SELECT * FROM SampleDB.order WHERE userid=?";
	PreparedStatement viewOrdersPs = null;
	
	private String deleteUserQuery = "DELETE FROM users WHERE userid=?";
	PreparedStatement deleteUserPs = null;
	
	private String cancelOrderQuery = "DELETE FROM SampleDB.order WHERE orderid = ?";
	PreparedStatement cancelOrderPs = null;
	
	public static String globalEmail = "";
	public static int globalId = -1;
	public static String globalFname = "";
	private Connection connection;


	public UserDao() {
		connection = ConnectionInit.getConnection();
	}
	
	//sign-up
	public void addUser(User user) {
		try {
			addUserPs = connection.prepareStatement(addUserQuery);
			addUserPs.setString(1, user.getFirstName());
			addUserPs.setString(2, user.getPasswd());
			addUserPs.setString(3, user.getLastName());
			addUserPs.setString(4, user.getEmail());
			addUserPs.setDate(5, new java.sql.Date(user.getDob().getTime()));
			addUserPs.setString(6, user.getCity());
			addUserPs.executeUpdate();
			System.out.println("User added to database successfully!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//user update
	public void updateUser(User user) {
		try {
			updateUserPs = connection.prepareStatement(updateUserQuery);
			updateUserPs.setString(1, user.getFirstName());
			updateUserPs.setString(2, user.getPasswd());
			updateUserPs.setString(3, user.getLastName());
			updateUserPs.setString(4, user.getEmail());
			updateUserPs.setDate(5, new java.sql.Date(user.getDob().getTime()));
			updateUserPs.setString(6, user.getCity());
			updateUserPs.setInt(7, user.getUserid());
			updateUserPs.executeUpdate();
			System.out.println("User updated successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//get all user list for admin
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			ResultSet rs = getAllUserPs.executeQuery(getAllUserQuery);
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setDob(rs.getDate("dob"));
				user.setEmail(rs.getString("email"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	// user passwd reset
	public void pwdReset(String email, String passwd) {
		try {
			pwdResetPs = connection.prepareStatement(pwdResetQuery);
			pwdResetPs.setString(1, passwd);
			pwdResetPs.setString(2, email);
			pwdResetPs.executeUpdate();
			System.out.println("Password updated successfully!");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// user login
	public int login(String firstname, String passwd, String email) {
		//User user = new User();
		globalFname = firstname;
		globalEmail = email;
		int count = -1;
		try {
			userLoginPs = connection.prepareStatement(userLoginQuery);
			userLoginPs.setString(1, firstname);
			userLoginPs.setString(2, passwd);
			userLoginPs.setString(3, email);			
			ResultSet rs = userLoginPs.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("count_name");
			}
			rs.close();
			System.out.println();
			if (count == 1 ) {
				userIdFetchPs = connection.prepareStatement(userIdFetchQuery);
				userIdFetchPs.setString(1, email);				
				ResultSet rs2 = userIdFetchPs.executeQuery();
				while (rs2.next()) {
					System.out.println("Welcome, userid: "+rs2.getInt("userid"));
					globalId = rs2.getInt("userid");
				}
				rs2.close();
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}
	
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<Book>();
		try {
			getAllBookPs = connection.prepareStatement(getAllBookQuery);
			ResultSet rs = 	getAllBookPs.executeQuery();
			while (rs.next()) {
				Book book = new Book();		
				book.setBookid(rs.getInt("book_id"));
				book.setBookName(rs.getString("bookName"));
				book.setBookDept(rs.getString("bookDept"));
				book.setBookPrice(rs.getInt("bookPrice"));				
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}
	
	//genereate a receipt after order also
	//make changes in order table
	//orderid, userid, firstname, bookid, bookName, quantity, price
	public String makeOrder(Order order,int book_id, int quantity) throws SQLException {
		
		makeOrderPs = connection.prepareStatement(makeOrderQuery);		
		makeOrderPs.setInt(1, globalId); //userid
		makeOrderPs.setString(2, globalFname); // firstname
		makeOrderPs.setInt(3, book_id);
		makeOrderPs.setInt(5, quantity);
		
		getBookIdPs = connection.prepareStatement(getBookIdQuery);
		getBookIdPs.setInt(1, book_id);
		String bookName = "";
		int bookPrice = -1;
		ResultSet rs = getBookIdPs.executeQuery();
		while(rs.next()) {
			bookName = rs.getString("bookName");
			bookPrice = rs.getInt("bookPrice");
		}
		rs.close();
		makeOrderPs.setString(4, bookName);
		makeOrderPs.setInt(6, bookPrice);
		makeOrderPs.executeUpdate();		
		String str = "Dear user, You have placed order for " + bookName + " Quantity: " + quantity + " successfully!";
		
		return str;
	}
	
	public void viewOrders() throws SQLException {
		viewOrdersPs = connection.prepareStatement(viewOrdersQuery);
		viewOrdersPs.setInt(1, globalId);
		ResultSet rs = viewOrdersPs.executeQuery();
		System.out.println();
		System.out.println("Here's the record of previous orders made by you: ");
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println("orderid userid  firstname  bookid  bookName\t Quantity  bookPrice");
		while(rs.next()) {
			System.out.println(rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t" + rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getInt(6) + "\t" + rs.getInt(7));			
		}
		rs.close();
	}
	
	public void cancelOrder(int orderId) throws SQLException {
		cancelOrderPs = connection.prepareStatement(cancelOrderQuery);
		cancelOrderPs.setInt(1, orderId);
		cancelOrderPs.executeUpdate();
		System.out.println("Order with order id: " + orderId + " deleted successfully!!");
	}
	
	public void deleteUser() {
		try {
			deleteUserPs = connection.prepareStatement(deleteUserQuery);
			deleteUserPs.setInt(1, globalId);
			deleteUserPs.executeUpdate();
			System.out.println("User " + globalId + " deleted successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}

