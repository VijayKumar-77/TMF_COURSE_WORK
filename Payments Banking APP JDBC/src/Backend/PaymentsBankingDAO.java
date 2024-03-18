import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*; 


public class PaymentsBankingDAO {

	public void storeUserDetails(User u) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		String Query = "insert into  User_Details(First_Name,Last_Name,Phone_Num,DOB,Address,Password,Wallet_Balance) "
		+ "values('"+u.getFirstName()+"','"+u.getLastName()+"','"+u.getPhoneNum()+"','"+u.getDateOfBirth()+"','"+u.getCommunicationAddress()+"','"+u.getPassword()+"',"+0+")";
		System.out.println(Query);
		stmt.executeUpdate(Query);
		con.close();
	}
	public void storeBankAcctDetails(BankAccount b) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		Statement stmt = con.createStatement();
		String Query ="insert into Bank_Acct_Details"
		+"values('"+b.getUserId()+"','"+b.getBankAccountNumber()+"','"+b.getBankBalance()+"','"+b.getIFSCNumber()+"','"+b.getBankName()+"','"+b.getBankAccountType()+"')";
		System.out.println(Query);
		stmt.executeUpdate(Query);
		con.close();
	}
	public boolean verifyLoginDetails() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ Payments_Banking_App","root","root");
		User u = new User();
		Statement stmt = con.createStatement();
		Scanner sc = new Scanner(System.in);
		int uId = sc.nextInt();
		System.out.println("Enter Your Password :");
	    String Password = sc.next();
		String Query ="select * from User_Details where User_ID = '"+u.getUserId()+"' AND Password = '"+u.getPassword()+"'";
		ResultSet rs = stmt.executeQuery(Query);
		boolean loginSuccessful =rs.next();
		return loginSuccessful;
	}
}



