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
		System.out.println((registration.get(0)).getFirstName());
		System.out.println((registration.size()));
		System.out.println("A new tenant has checked-in");
        return "***You have been registerd as a tenant of this dorm***";
    }
    
    public String setBoardName (String bName){
    	return bName;
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
