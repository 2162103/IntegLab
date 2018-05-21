import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @(#)Quiz.java
 *
 *
 * @author
 * @version 1.00 2018/3/27
 */


public interface MMADorm extends Remote {
	public String login(String username, String password) throws RemoteException;
	
	public String register(String fName, String lName, String email, String phoneNum, String username, String pswd) throws RemoteException;
	
	public String createBoardingHouse(String name, int cap) throws RemoteException;
	
	public String setNotif(String notif) throws RemoteException;
	
	public String checkOut(String username, String pswd) throws RemoteException;
	
	public String getBoardingHouseName()throws RemoteException;
	
	String editCapacity(int cap) throws RemoteException;
	
	public String getNotif() throws RemoteException;
	
	public int getCapacity()throws RemoteException;
	
	public String seeAllRegistrationReq() throws RemoteException;
	
	public int getNumberOfTenants()throws RemoteException;
	
	public String seeAllTentants() throws RemoteException;
	
	public String seeAllCoTentants() throws RemoteException;
	
	public String acceptReg(int regNum) throws RemoteException;
	
	public String declineReg(int regNum) throws RemoteException;
}