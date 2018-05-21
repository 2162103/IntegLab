import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.*;
/**
 * To run this client program do the following:
 * 1. Open server.policy in Notepad and set the file folder to the correct location of the server program
 *    Example:
 *      Assuming that the server program is found in D:\IntegLec\RMI Programming
 *
 *      Then you should change the file server.policy with the following entry:
 *        grant codeBase "file:D:/IntegLec/RMI Programming/" {
 *            permission java.security.AllPermission;
 *        };
 *
 * 2. Run the client from the command prompt using the following:
 *      java -Djava.security.policy=server.policy ServerRMI
 *
 * @author Laurence F. Balmeo
 * @version 1.00 2018/3/27
 */

public class serverDorm implements MMADorm {

	public static Scanner kbd = new Scanner(System.in);
    public static ArrayList<CheckTenants> registration = new ArrayList<>();
    public static ArrayList<CheckTenants> tenants = new ArrayList<>();
    public static String dormName = "";
    public static int capacity;
    public static String notification = "There are not notification";
    
    public String login(String username, String password){
    	String result = "***Login dennied***";
    	for(int i = 0; i < registration.size(); i++){
	    	if ( (((registration.get(i)).getUsername()).equals(username)) && (((registration.get(i)).getPassword()).equals(password))  ){
	            result = "***You have Login***";
	            break;
	        } else {
	            result = "***Login dennied***";
	        }	
        }
        
        return result;               
        
    }
    
    public String register (String fName, String lName, String email, String phoneNum, String username, String pswd){
		CheckTenants register = new CheckTenants(fName, lName, email, phoneNum, username, pswd);
		registration.add(register);
        return "***Your Registration Will be Forwarded to the LandLord For Confirmation***";
    }
    
    public String createBoardingHouse(String name, int cap){
		dormName = name;
		capacity = cap;
		return "You have successfully created the Boarding House " + dormName + ".\nWith a capacity of " + capacity;
		
    }
    
    public String editCapacity(int cap){
		capacity = cap;
		return "You have successfully changed the capacity to" + capacity;
		
    }
    
    public String checkOut(String username, String pswd){
    	String status = "Checking out failed, please input correct information";
    	for(int i = 0; i < tenants.size(); i++){
    	
	    	if ( (((tenants.get(i)).getUsername()).equals(username)) && (((tenants.get(i)).getPassword()).equals(pswd))  ){
	    	
	    		tenants.remove(i);
	    		System.out.println("The number of tenants now is " + tenants.size());
	    		status = "You have successfully checked out of " + dormName + " dormitory";
	    		
	    	}
	    	
    	}
    	
    	return status;
    	
    	
    }
    
    public String acceptReg(int regNum){
    	tenants.add(registration.get(regNum-1));
    	registration.remove(regNum-1);
    	return "Tenant Accpeted";
    }
    
    
     public String declineReg(int regNum){
    	registration.remove(regNum-1);
    	return "Tenant Rejected";
    }
    
    public String setNotif(String notif){
    	notification = notif;
    	return "Notification has been set";
    }
    
    public String seeAllRegistrationReq(){
    	String allRegistration = "";
    	for(int i = 0; i < registration.size(); i++){
    		allRegistration += "Reg#: " + (i+1) + "\tFirst Name: " + registration.get(i).getFirstName() + "\tLast Name: " + registration.get(i).getLastName() + "\tEmail: " + registration.get(i).getEmail() + "\tPhoneNum: " + registration.get(i).getPhoneNum() + "\tUsername: " + registration.get(i).getUsername() + "\tPassword: " + registration.get(i).getPassword() +"\n\n";
    	}
    	
    	return allRegistration;
    }
    
    public String seeAllTentants(){
    	String allTenants = "";
    	for(int i = 0; i < tenants.size(); i++){
    		allTenants +=  "Tnt#: " + (i+1) + "\tFirst Name: " + tenants.get(i).getFirstName() + "\tLast Name: " + tenants.get(i).getLastName() + "\tEmail: " + tenants.get(i).getEmail() + "\tPhone Number: " + tenants.get(i).getPhoneNum() + "\tUsername: " + tenants.get(i).getUsername() + "\tPassword: " + tenants.get(i).getPassword() +"\n\n";
    	}
    	
    	return allTenants;
    }
    
    public String seeAllCoTentants(){
    	String allTenants = "";
    	for(int i = 0; i < tenants.size(); i++){
    		allTenants +=  "Tnt#: " + (i+1) + "\tFirst Name: " + tenants.get(i).getFirstName() + "\tLast Name: " + tenants.get(i).getLastName() + "\n\n";
    	}
    	
    	return allTenants;
    }
    
	public String getBoardingHouseName(){
		return dormName;
    }
    
    public int getCapacity(){
		return capacity;
    }

	public int getNumberOfTenants(){
		return tenants.size();
    }
    
    public String getNotif(){
		return notification;
    }
    /************************************************
     **** This section implements the RMI server ****
     ************************************************/
    public static void main(String[] args) {
        try {
        	serverDorm server = new serverDorm();
        	MMADorm stub = (MMADorm)UnicastRemoteObject.exportObject(server, 0);

        	Registry registry = LocateRegistry.getRegistry();
        	registry.rebind("dorm", stub);
        	
        	System.out.println("Server is running.......");        

        } catch(Exception e) {
        	e.printStackTrace();
        	System.exit(1);
        }
    }
}
