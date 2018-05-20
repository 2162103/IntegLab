import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

/**
 * To run this client program do the following:
 * 1. Open client.policy in Notepad and set the file folder to the correct location of the client program
 *    Example:
 *      Assuming that the client program is found in D:\2161234\RMI Programming
 *
 *      Then you should change the file client.policy with the following entry:
 *        grant codeBase "file:D:/2161234/RMI Programming/" {
 *            permission java.security.AllPermission;
 *        };
 *
 * 2. Run the client from the command prompt using the following:
 *      java -Djava.security.policy=client.policy ClientRMI
 *
 * @author Laurence F. Balmeo
 * @version 1.00 2018/3/27
 */

public class clientDorm {
    public static Scanner kbd = new Scanner(System.in);
    public static Registry registry;
    public static MMADorm stub;
    
    public static void menu(){
        System.out.println("**********Welcome to MMA Dormitory**********:");
        System.out.println("1: Log-in");
        System.out.println("2: Register");
        System.out.println("3: Exit");
    }
    
    public static void reg(){
    	try{
    		registry = LocateRegistry.getRegistry();
    		stub = (MMADorm)registry.lookup("dorm");
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        menu();
        reg();
        try {
        	while(true){
        		
				int choice = Integer.parseInt(kbd.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid input!");
                    System.out.println("Press enter to continue...");
                } else {
                    switch (choice) {
                        case 1 : loginMenu(); break;
                        case 2 : stub.register(); break;
                        case 3 : System.exit(0); break;
                        default : break;
                    }
                }
                
        	}
			
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    
    
    
    
    public static void loginMenu(){
    	try{
	    	System.out.print("Enter Username: ");
	        String username = kbd.nextLine();
			System.out.print("Enter Password");
			String password = kbd.nextLine();
			
			System.out.println(stub.login(username, password));
    	}catch(Exception e) {
        	e.printStackTrace();
        }
    	
    }
}
