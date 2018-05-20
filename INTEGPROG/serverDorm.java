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
    public static String dormName = "";
    public static int capacity = 0;
    public static int occupants = 0;
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
    
    public String register (String fName, String lName, String username, String pswd){
        String resp = "\nFirst Name: " + fName + "\nLast Name: " + lName + "\nUser Name: " + username + "\nPassword: " + pswd;
		CheckTenants register = new CheckTenants(fName, lName, username, pswd);
		registration.add(register);
		System.out.println("A new tenant has checked-in");
		occupants ++;
		System.out.println("The number of tenants now is " + occupants);
        return "***You have been registerd as a tenant of this dorm***";
    }
    
    public String createBoardingHouse(String name, int cap){
		dormName = name;
		capacity = cap;
		return "You have successfully created the Boarding House " + dormName + ".\nWith a capacity of " + capacity;
		
    }//end of border name method
    
    public String checkOut(String username, String pswd){
    	String status = "Checking out failed, please input correct information";
    	for(int i = 0; i < registration.size(); i++){
    	
	    	if ( (((registration.get(i)).getUsername()).equals(username)) && (((registration.get(i)).getPassword()).equals(pswd))  ){
	    	
	    		registration.remove(i);
	    		occupants --;
	    		System.out.println("The number of tenants now is " + occupants);
	    		status = "You have successfully checked out of " + dormName + " dormitory";
	    		
	    	}
	    	
    	}
    	
    	return status;
    	
    	
    }
    
    public String setNotif(String notif){
    	notification = notif;
    	return "Notification has been set";
    }
    
    public String seeAllTenants(){
    	String allTenants = "";
    	for(int i = 0; i < registration.size(); i++){
    		allTenants +=  registration.get(i).getFirstName() + "\t\t" + registration.get(i).getLastName() + "\t\t" + registration.get(i).getUsername() + "\t\t" + registration.get(i).getPassword() +"\n\n";
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
		return occupants;
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
