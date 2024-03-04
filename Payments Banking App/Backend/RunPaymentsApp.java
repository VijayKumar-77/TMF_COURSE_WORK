import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class RunPaymentsApp 
{
//Driver class
//	static int x=10;
//	public static User[] usersList = new User[5];

public static List<User> UsersList =new ArrayList<User>();
public static List<BankAccount> BAList = new ArrayList<BankAccount>();
public static int CurrentUserId = -1;
 
   
private static Map<Integer, Wallet> UsersWallet = new HashMap<>();
public static void main(String[] args) {
		
		int selectedOption=0;		
		Scanner opt = new Scanner(System.in);
		
		while(true) {
			System.out.println("           🏦      💲💲💲💲💲💲      🏦          ");
			System.out.println("   <------- Payments App Options: ------->    ");
			System.out.println("");
			
			System.out.println("1. New User? / Register.");
			
			System.out.println("2. Existing User? / Login.");
			
			System.out.println("3. ADD New Bank Account.");
			
			System.out.println("4. Display Users List.");
			
			System.out.println("5. Current Logged In User.");
			
			System.out.println("6. Show All Users Bank Accounts.");
			
			System.out.println("7. Add Your Money To Wallet.");
			
			System.out.println("8. Delete BankAccount.");
			
			System.out.println("9. Show Users Wallet Balance.");
			
			System.out.println("10. Make a Transaction.");
			
			System.out.println("11. Logout.");
			
			System.out.println("");
			System.out.println("Choose an Option:");
			
			String optStr = opt.next();
		try {
				selectedOption = Integer.parseInt(optStr);
				
		}
		catch(NumberFormatException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Number format Error Has Been Occured Please choose another option. Thank You");
				
		}
		catch(ArithmeticException e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Arithmetic Error Has Been Occured Please choose another option. Thank You");
				
		}
		catch(Exception e) {
				e.printStackTrace();
				e.getMessage();
				System.out.println("Some Error has Been Occured Please choose another option. Thank You");
		}
		finally{
				System.out.println();
			}
			
			    System.out.println("User selected "+selectedOption);
			    UserOperations ops = new UserOperations();
		if(optStr.equalsIgnoreCase("1")) {
				registerUser();
		}
		else if(optStr.equalsIgnoreCase("2")) {
		if(CurrentUserId!=-1)
			{
				System.out.println("Please logout From Current Logged In user");
		}
		else
			{
		if(!loginUser()) {
					break;
				}
			  }
			}
		else if(optStr.equalsIgnoreCase("3")) {
		if(validateCurrentUser()) {
					addBankAccount();
				}
		}
		else if(optStr.equalsIgnoreCase("4")) {
				ops.printUserList(UsersList);
		}
		else if(optStr.equalsIgnoreCase("5")) {
		if(CurrentUserId != -1) {
					ops.printCurrUserDetails(CurrentUserId);
				}
		}
		else if(optStr.equalsIgnoreCase("6")) {
		if(CurrentUserId != -1) {
					printUserBankAcctsList();
				}
			}
		else if(optStr.equalsIgnoreCase("7"))
			{
				UserOperations u1=new UserOperations();
		if(CurrentUserId != -1) {
					Scanner sc=new Scanner(System.in);
					double Amount=sc.nextDouble();
				    u1.AddingMoneyToWallet(CurrentUserId,Amount);
				}
			}
		else if(optStr.equalsIgnoreCase("8"))
			{
		if (CurrentUserId != -1) {
			        Scanner scanner = new Scanner(System.in);
			        System.out.println("Enter the AccountNumber to Delete:");
			        String AccountNumber = scanner.next();
			        ops.DeletingBankAccount(CurrentUserId, AccountNumber);
			    }
		else {
			        System.out.println("No user logged in.");
			    }
			}
		else if(optStr.equalsIgnoreCase("9")) {
		if( CurrentUserId!= -1) {
					System.out.println(ops.checkWalletBalance(CurrentUserId));
				}
		else {
					System.out.println("Please Login to Check Wallet Balance");
				}
			}
			
		else if(optStr.equalsIgnoreCase("10")) {
				UserOperations u1=new UserOperations();
				u1.DoTransaction();
			}
			
		else if(optStr.equalsIgnoreCase("11")) {
			System.out.println("You Have Been logged Out Thank You 😄 ");
				CurrentUserId=-1;
				
			}
		else {
				
			}
			
		}
	}
	
public static void registerUser() {
		Scanner Options = new Scanner(System.in);
		System.out.println("");
		UserOperations Operations = new UserOperations();
		
		System.out.println("Please Provide Your Details As Shown Below:");
		System.out.println("");
		System.out.println("Enter First Name:");
		String FirstName = Options.next();
		System.out.println("Enter Last Name:");
		String LastName = Options.next();
		System.out.println("Enter Phone Number:");
		long PhoneNo = Long.parseLong(Options.next());
		System.out.println("Enter Date Of Birth:");
		String DOB = Options.next();
		System.out.println("Enter Address:");
		String CommunicationAddress = Options.next();
		System.out.println("Enter Password:");
		String Password = Options.next();
		
	    User Userdetails = null;
	    try {
			
Userdetails = Operations.doUserRegistration(FirstName, LastName, Password, PhoneNo, DOB, CommunicationAddress);
			
			UsersList.add(Userdetails);
		}
	    catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
public static boolean loginUser() {
		Scanner Option2 = new Scanner(System.in);
		UserOperations ops = new UserOperations();
		
		System.out.println("UserId:");
		String UserId = Option2.next();
		System.out.println("Password:");
	   	String Password = Option2.next();
	    if(ops.verifyUserLogin(UserId, Password)) {
			CurrentUserId = Integer.parseInt(UserId);
			return true;
		}
	    else {
			System.out.println("Login Failed, Please Choose an Option:");
			//break;
			return false;
		}
	}
	
public static boolean validateCurrentUser() {
	if(CurrentUserId != -1) {
			return true;
		}
	    else {
			return false;
		}
	}
	
public static void addBankAccount() {

		BankAccount BankAccount = new BankAccount();
		Scanner Option3 = new Scanner(System.in);
		
		System.out.println("Enter Bank Account Number:");
		String AccountNumber = Option3.next();
		
		System.out.println("Enter IFSC Code:");
		String IFSCCODE = Option3.next();
		
		System.out.println(" Enter Account Type : ");
		System.out.println("SA: SAVINGS");
		System.out.println("CU: CURRENT");
		System.out.println("LN: LOAN");
		System.out.println("SL: SALARY");
	    try {
			String Accounttype = Option3.next();
			AccountType Type = AccountType.valueOf(Accounttype);
			BankAccount.setBankAcctAcctType(Type);
			}
	    catch(IllegalArgumentException e) {
				System.out.println("Please Select the Correct Account Type : ");
				e.printStackTrace();
			}
    
		System.out.println("Enter Account PIN:");
	
		String AccountPin = Option3.next();
		BankAccount.setBankAccountNumber(AccountNumber);
		BankAccount.setIFSCNumber(IFSCCODE);
		BankAccount.setBankAccountPin(AccountPin);
		BankAccount.setUserId(CurrentUserId);
		
		for(User u:UsersList) {
		if(u.getUserId() == CurrentUserId) {
				u.getBankAccList().add(BankAccount);
			}
		}
		
BAList.add(BankAccount);
		
	}
	
public static void printUserBankAcctsList() {
		UserOperations ops = new UserOperations();
Map<User,List<BankAccount>> mapItems = ops.getUserBankAccounts();

	    for(User u:mapItems.keySet()) {
			List<BankAccount> //baList = new ArrayList<BankAccount>();
					baList = mapItems.get(u);
			System.out.println(u);
	    if(baList != null) {
	    for(BankAccount ba: baList) {
	  	System.out.println("--"+ba.printBankAccountDetails());
	  	
				}
			}
			
		}
	}
		 
}
		
	

	
