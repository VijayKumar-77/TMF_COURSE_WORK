import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserOperations {
	
	List<User> users = null;
	List<BankAccount> BankAccountList = null;
	  private static Map<Integer, Wallet> userWallets = new HashMap<>();
	public UserOperations() {
		users= RunPaymentsApp.UsersList;
		BankAccountList = RunPaymentsApp.BAList;
	}
	
	public User doUserRegistration(String FirstName, String LastName, String Password, long PhoneNum, String DOB,String Address) throws Exception {
		User User = new User();
		User.setFirstName(FirstName);
		User.setLastName(LastName );
		User.setPhoneNum(PhoneNum);
		User.setDateOfBirth(DOB);
		User.setCommunicationAddr(Address);
		User.setPassword(Password);
		
		if((FirstName+LastName).length() >50) {
			throw new Exception();
		}
		
		User.setUserId((int)(Math.random()*1000)+100);
		PaymentsFileOps pfOps = new PaymentsFileOps();
		pfOps.writeUserToFile(User);
		return User;
	}
	
	public void printUserList(List<User> users){
		for(User u:users) {
			if(users != null) {
				System.out.println("User Details of "+ u.getFirstName());
				System.out.println(u);
			}
		}
	}
	
	public boolean verifyUserLogin(String userId, String password){
		for(int i=0;i<users.size();i++) {
			if(String.valueOf(users.get(i).getUserId()).equals(userId)) {
				if(password.equals(users.get(i).getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void printCurrUserDetails(int userId){
		for(User u:users) {
			if(u.getUserId() == userId) {
				System.out.println(u);
			}else {
				System.out.println("No User Logged in.");
			}
		}
	}
//		for(int i=0;i<users.size();i++) {
//			if(users.get(i).getUserId() != userId) {
//				System.out.println(users.get(i));
//				break;
//			}
//		}
	
	
public Map<User,List<BankAccount>> getUserBankAccounts() {
//		Map<User,BankAccount> userBankAcctMap = new HashMap<User,BankAccount>();
	
	   Map<User,List<BankAccount>> userBankAcctMap = new HashMap<User,List<BankAccount>>();
		
		for(User u:users) {
			if(users != null) {
				userBankAcctMap.put(u, u.getBankAccList());
			}
		}
		return userBankAcctMap;
		
	}
	public void DeletingBankAccount(int userId, String AccountNumber) {
	    for (User user : users) {
	        if (user.getUserId() == userId) {
	            List<BankAccount> userBankAccounts = user.getBankAccList();
	            for (BankAccount account : userBankAccounts) {
	                if (account.getBankAccountNumber().equals(AccountNumber)) {
	                    userBankAccounts.remove(account);
	                    System.out.println("Your Bank account deleted successfully.");
	                    return;
	                }
	            }
	            System.out.println("Bank account not found for the user.");
	            return;
	        }
	    }
	    System.out.println("User not found.");
	}


public static void AddingMoneyToWallet(int userId, double amount) {
		 Wallet Wallet = userWallets.getOrDefault(userId, new Wallet());
		 Wallet.setLimit(50000);
		    if (Wallet.getCurrntBal() + amount <= Wallet.getLimit()) {
		        Wallet.setCurrntBal(Wallet.getCurrntBal() + amount);
		        userWallets.put(userId, Wallet);
		        System.out.println("New wallet balance for user " + userId + " is: " + Wallet.getCurrntBal());
		        
		        
		        
		    }
		    
		    else {
		        System.out.println("Maximum wallet amount is " + Wallet.getLimit());
		    }
    }
public double checkWalletBalance(int userId){
    Wallet wallet = userWallets.get(userId);
    if (wallet != null) {
        return wallet.getCurrntBal();
    } else {
        return 0;
    }
}

public void DoTransaction() {
	
    Scanner sc = new Scanner(System.in);
    Transaction txn = new Transaction();
    System.out.println("Select The Option to Send Money From Which Account: ");
    try {
        String srcType = sc.next();
        TransactionSource srccType = TransactionSource.valueOf(srcType);
        txn.setTrnxsrc(srccType);
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
    }

    if (txn.getTrnxsrc() == TransactionSource.CASH) {
    	
        double amt = sc.nextDouble();
        int uId=sc.nextInt();
        for (User user : users) {
            if (user.getUserId() == uId) {
                Wallet w = user.getWallet();
                w.setCurrntBal(w.getCurrntBal() + amt);
                System.out.println(w.getCurrntBal());
            }
        }
    } else if (txn.getTrnxsrc() == TransactionSource.WALLET) {
        System.out.println("enter destination type");
        try {
            String desType = sc.next();
            TransactionDestination des = TransactionDestination.valueOf(desType);
            txn.setTrnxDest(des);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        if (txn.getTrnxDest() == TransactionDestination.WALLET) {
            double amt = sc.nextDouble();
            int uId=sc.nextInt();
            for (User user : users) {
                if (user.getUserId() == uId) {
                    Wallet senderWallet = userWallets.get(RunPaymentsApp.CurrentUserId);
                    if (senderWallet.getCurrntBal() >= amt) {
                        senderWallet.setCurrntBal(senderWallet.getCurrntBal() - amt);
                        Wallet receiverWallet = user.getWallet();
                        receiverWallet.setCurrntBal(receiverWallet.getCurrntBal() + amt);
                    } else {
                        System.out.println("Insufficient balance in wallet.");
                    }
                }
            }
        }
    }
}

        }
       


