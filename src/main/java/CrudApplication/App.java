package CrudApplication;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
	
	public static void main(String[] args) throws SQLException {
		
		UserDao dao = new UserDao();
		
		System.out.println("--------------------------------------------");
		System.out.println("|    Press 1 for user Sign-up              |");
		System.out.println("|    Press 2 for user login                |");
		System.out.println("|    Press 3 for password reset            |");
		System.out.println("|    Press 4 for updating user details     |");
		System.out.println("|    Press 5 to exit system                |");
		System.out.println("--------------------------------------------");
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		int user_ip = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		while(user_ip!=5) {
			// validating user_ip
			while (user_ip < 1 | user_ip > 5) {
				System.out.println("Warning: Please enter a valid input!!");
				user_ip = sc.nextInt();
				if(user_ip == 1 | user_ip == 2 | user_ip == 3 | user_ip == 4 | user_ip == 5) {
					break;
				}
			} 
				User user = new User();
				
				
				
				switch(user_ip) {
				case 1:
						signupInput(dao,user,sc);
						break;
						
				case 2:
						
						int count = login(dao,user,sc);
						while (count != 1) {
							System.out.println("Warning: wrong credentials");
							System.out.println();
							count = login(dao,user,sc);
						}
						System.out.println("login successfull!!");
						System.out.println();
						System.out.println("-----------------------------------------");
						System.out.println("Hi, user what would you like to do today?");
						System.out.println("-----------------------------------------");
						System.out.println("---------Press 1 to order book-----------");
						System.out.println("---Press 2 to view you previous orders---");
						System.out.println("------Press 3 to cancel your order-------");
						System.out.println("------Press 4 to delete you account------");
						System.out.println("----Press 5 to go back to main menu------");
						System.out.println("-----------------------------------------");
						System.out.println();
						int input = sc.nextInt();
						Order order = new Order();
						while (input != 4) {
							while (user_ip < 1 | user_ip > 5) {
								System.out.println("Warning: Please enter a valid input!!");
								user_ip = sc.nextInt();
								if(user_ip == 1 | user_ip == 2 | user_ip == 3 | user_ip == 4 | user_ip == 5) {
									break;
								}
							} 
							switch(input) {
							case 1:
								List<Book> books = dao.getAllBooks();
								for(Book bk: books) {
									System.out.println(bk.toString());
								}
								System.out.println("Please enter book id to make order");
								int book_id = sc.nextInt();
								order.setBookid(book_id);
								System.out.println("Please enter book quantity");
								int quantity = sc.nextInt();
								order.setQuantity(quantity);
								String str = dao.makeOrder(order,book_id,quantity);
								System.out.println(str);
								break;
							case 2:
								dao.viewOrders();
								break;
							case 3:
								System.out.println("Please enter your order id to cancel your order");
								int orderId = sc.nextInt();
								dao.cancelOrder(orderId);
								break;
							case 4:
								dao.deleteUser();
								System.out.println("Login back to use our portal");				
								break;
							case 5:
								break;
							}
							if(input == 4 | input == 5) {
								user_ip = 5;
								break;
							}
							System.out.println();
							System.out.println();
							System.out.println("What would you like to do next?");
							System.out.println("-----------------------------------------");
							System.out.println("---------Press 1 to order book-----------");
							System.out.println("---Press 2 to view you previous orders---");
							System.out.println("------Press 3 to cancel your order-------");
							System.out.println("------Press 4 to delete you account------");
							System.out.println("----Press 5 to go back to main menu------");
							System.out.println("-----------------------------------------");
							System.out.println();
							input = sc.nextInt();
						}
						
						break;	
				case 3:
						pwdReset(dao, user, sc);
						break;
				case 4:
						userUpdate(dao, user, sc);
						break;
						
				/*case 4:
						for (User iter : dao.getAllUsers()) {
							System.out.println(iter);
						}
						break;*/

				}
				System.out.println();
				System.out.println();
				System.out.println("--------------------------------------------");
				System.out.println("|    Press 1 for user Sign-up              |");
				System.out.println("|    Press 2 for user login                |");
				System.out.println("|    Press 3 for password reset            |");
				System.out.println("|    Press 4 for updating user details     |");
				System.out.println("|    Press 5 to exit system                |");
				System.out.println("--------------------------------------------");
				System.out.println();
				
				
				System.out.println("Enter next cmd: ");
				
				user_ip = sc.nextInt();
			
			

			
		}
			
		
		
	userExit();		
	sc.close();	
	}
	public static void userExit() {
		try {
			ConnectionInit.getConnection().close();
			
			System.out.println("Successfull exit!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void userUpdate(UserDao dao,User user,Scanner sc) {
		System.out.println("Enter userid to update");
		int userid = sc.nextInt();
		sc.nextLine();
		user.setUserid(userid);
		
		System.out.println("Enter updated firstname: ");
		String upfname = sc.nextLine();
		while(!validateName(upfname)) {
			System.out.println("Please enter valid first name.");
			upfname = sc.nextLine();
		}
		user.setFirstName(upfname);
		
		//passwd
		System.out.println("Enter password: ");
		String uppaswd = sc.nextLine();
		while(!validatePswd(uppaswd)) {
			System.out.println("A password contains at least eight characters, including at least one number and includes both lower and uppercase letters and special characters");
			System.out.println("Please enter a valid password.");
			
			uppaswd = sc.nextLine();
		}
		
		System.out.println("Enter updated lastname: ");
		String uplname = sc.nextLine();
		while(!validateName(uplname)) {
			System.out.println("Please enter valid first name.");
			uplname = sc.nextLine();
		}
		user.setLastName(uplname);
		
		System.out.println("Enter updated email: ");
		String upemail = sc.nextLine();
		
		while(!validateEmail(upemail)) {
			System.out.println("Please enter valid email.");
			upemail = sc.nextLine();
		} user.setEmail(upemail);
		
		//dob
		try {
			System.out.println("Enter updated DOB: ");
			String updobUnFiltered = sc.nextLine();
			while(!validateDate(updobUnFiltered)) {
				System.out.println("Please enter valid date format (yyyy-mm-dd)");
				updobUnFiltered = sc.nextLine();
			}
			Date updob = new SimpleDateFormat("yyyy-MM-dd").parse(updobUnFiltered);
			user.setDob(updob);
		} catch (ParseException pe){
			pe.printStackTrace();
		}
		
		//city
		System.out.println("Enter your updated city: ");
		String upcity = sc.nextLine();
		while(!validateName(upcity)) {
			System.out.println("Please enter a valid city name.");
			upcity = sc.nextLine();
		} user.setCity(upcity);
	
		dao.updateUser(user);
		
	}
	
	public static void pwdReset(UserDao dao,User user,Scanner sc) {
		//email
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		while(!validateEmail(email)) {
			System.out.println("Please enter valid email.");
			email = sc.nextLine();
		} user.setEmail(email);
		
		//passwd
		System.out.println("Enter password: ");
		String paswd = sc.nextLine();
		while(!validatePswd(paswd)) {
			System.out.println("A password contains at least eight characters, including at least one number and includes both lower and uppercase letters and special characters");
			System.out.println("Please enter a valid password.");
			
			paswd = sc.nextLine();
		}
		
		
		
		dao.pwdReset(email, paswd);
		
	}

	
	public static String signupInput(UserDao dao,User user,Scanner sc) {
		//fname
		System.out.println("Enter firstname: ");
		String fname = sc.nextLine();
		while(!validateName(fname)) {
			System.out.println("Please enter valid first name.");
			fname = sc.nextLine();
		} user.setFirstName(fname);
		
		//passwd
		System.out.println("Enter password: ");
		String paswd = sc.nextLine();
		while(!validatePswd(paswd)) {
			System.out.println("A password contains at least eight characters, including at least one number and includes both lower and uppercase letters and special characters");
			System.out.println("Please enter a valid password.");
			
			paswd = sc.nextLine();
		} user.setPasswd(paswd);
		
		
		//lname
		System.out.println("Enter lastname: ");
		String lname = sc.nextLine();
		while(!validateName(lname)) {
			System.out.println("Please enter valid last name.");
			lname = sc.nextLine();
		}
		user.setLastName(lname);
		
		//email
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		while(!validateEmail(email)) {
			System.out.println("Please enter valid email.");
			email = sc.nextLine();
		} user.setEmail(email);
		
		//dob
		try {
			System.out.println("Enter DOB: ");
			String dobUnFiltered = sc.nextLine();
			while(!validateDate(dobUnFiltered)) {
				System.out.println("Please enter valid date format (yyyy-mm-dd)");
				dobUnFiltered = sc.nextLine();
			}
			Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(dobUnFiltered);
			user.setDob(dob);
		} catch (ParseException pe){
			pe.printStackTrace();
		}
		
		//city
		System.out.println("Enter your city: ");
		String city = sc.nextLine();
		while(!validateName(city)) {
			System.out.println("Please enter a valid city name.");
			city = sc.nextLine();
		} user.setCity(city);

					
		dao.addUser(user);
		return "User added to user database successfully!";
	}
	
	
	public static int login(UserDao dao,User user,Scanner sc) {
		System.out.println("Enter firstname: ");
		String fname = sc.nextLine();
		while(!validateName(fname)) {
			System.out.println("Please enter valid first name.");
			fname = sc.nextLine();
		} 
		
		//passwd
		System.out.println("Enter password: ");
		String paswd = sc.nextLine();
		while(!validatePswd(paswd)) {
			System.out.println("A password contains at least eight characters, including at least one number and includes both lower and uppercase letters and special characters");
			System.out.println("Please enter a valid password.");
			
			paswd = sc.nextLine();
		} 
		
		//email
		System.out.println("Enter email: ");
		String email = sc.nextLine();
		while(!validateEmail(email)) {
			System.out.println("Please enter valid email.");
			email = sc.nextLine();
		} 
		
		int count = dao.login(fname, paswd, email);
		
		return count;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static boolean validateEmail(String str) {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
       
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);
        
        while (matcher.find()) {
        	return true;
        }
        
        return false;
	}
	
	public static boolean validateName(String str) {
		String regex = "^[a-zA-Z]+$";
       
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);
        
        while (matcher.find()) {
        	return true;
        }
        
        return false;
	}
	
	public static boolean validatePswd(String str) {
		String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
/*
You may use this regex with multiple lookahead assertions (conditions):

^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$
This regex will enforce these rules:

At least one upper case English letter, (?=.*?[A-Z])
At least one lower case English letter, (?=.*?[a-z])
At least one digit, (?=.*?[0-9])
At least one special character, (?=.*?[#?!@$%^&*-])
Minimum eight in length .{8,} (with the anchors)
 */
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);
        
        while (matcher.find()) {
        	return true;
        }
        
        return false;
	}
	
	public static boolean validateDate(String str) {
		String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
       
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(str);
        
        while (matcher.find()) {
        	return true;
        }
        
        return false;
	}
	

}