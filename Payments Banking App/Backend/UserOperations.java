

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class UserOperations {
	
	List<User> users = null;
	List<BankAccount> bankAcctList = null;
	  private static Map<Integer, Wallet> userWallets = new HashMap<>();
	public UserOperations() {
		users= RunPaymentsApp.usersList;
		bankAcctList = RunPaymentsApp.bankAcctList;
	}
	
	public User doUserRegistration(String fName, String lName, String password, long phNum, String dob,String addr) throws Exception {
		User u = new User();
		u.setFirstName(fName);
		u.setLastName(lName);
		u.setPhoneNum(phNum);
		u.setDateOfBirth(dob);
		u.setCommunicationAddr(addr);
		u.setPassword(password);
		
		if((fName+lName).length() >50) {
			throw new Exception();
		}
		
		u.setUserId((int)(Math.random()*1000)+100);
		PaymentsFileOps pfOps = new PaymentsFileOps();
		pfOps.writeUserToFile(u);
		return u;
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
//		for(int i=0;i<users.size();i++) {
//			if(users.get(i).getUserId() != userId) {
//				System.out.println(users.get(i));
//				break;
//			}
//		}
	}
	
	public Map<User,List<BankAccount>> getUserBankAccounts() {
		
//		Map<User,BankAccount> userBankAcctMap = new HashMap<User,BankAccount>();
		Map<User,List<BankAccount>> userBankAcctMap = new HashMap<User,List<BankAccount>>();
		
		for(User u:users) {
			if(users != null) {
				userBankAcctMap.put(u, u.getBaList());
			}
		}
		return userBankAcctMap;
		
	}
	public void deleteBankAccount(int userId, String accountNumber) {
	    for (User user : users) {
	        if (user.getUserId() == userId) {
	            List<BankAccount> userBankAccounts = user.getBaList();
	            for (BankAccount account : userBankAccounts) {
	                if (account.getBankAcctNumber().equals(accountNumber)) {
	                    userBankAccounts.remove(account);
	                    System.out.println("Bank account deleted successfully.");
	                    return;
	                }
	            }
	            System.out.println("Bank account not found for the user.");
	            return;
	        }
	    }
	    System.out.println("User not found.");
	}


public static void addMoneyToWallet(int userId, double amount) {
		 Wallet wallet = userWallets.getOrDefault(userId, new Wallet());
		 wallet.setLimit(50000);
		    if (wallet.getCurrntBal() + amount <= wallet.getLimit()) {
		        wallet.setCurrntBal(wallet.getCurrntBal() + amount);
		        userWallets.put(userId, wallet);
		        System.out.println("New wallet balance for user " + userId + " is: " + wallet.getCurrntBal());
		        
		        
		        
		    }
		    
		    else {
		        System.out.println("Maximum wallet amount is " + wallet.getLimit());
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
                    Wallet senderWallet = userWallets.get(RunPaymentsApp.currUserId);
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
       


