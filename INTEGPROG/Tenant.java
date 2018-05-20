import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.*;
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

public class Tenant{
    public static Scanner kbd = new Scanner(System.in);
    public static Registry registry;
    public static MMADorm stub;
    
    public static void main(String[] args) {
        
        reg();
		tenantChoice1();
    }//end of main method
    
    
    //This method will create the registry
    public static void reg(){
    	try{
    		registry = LocateRegistry.getRegistry();
    		stub = (MMADorm)registry.lookup("dorm");
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of method reg

    public static void menu1(){
        System.out.println("\n\n\n**********Welcome to MMA Dormitory**********:");
        System.out.println("1: Tenant Login");
        System.out.println("2: Show dormitory info");
        System.out.println("3 Check-in the boarding house");
        System.out.println("4 Exit");
    }
    
    public static void menu2(){
        System.out.println("*******************************************:");
        System.out.println("1: Check notification messages");
        System.out.println("2: Check-out");
        System.out.println("3: Exit");
    }//end of method menu2
    
    
    
    public static void tenantChoice1(){
        try {
        	while(true){
        		menu1();
        		System.out.print("Enter your choice: ");
				int choice = Integer.parseInt(kbd.nextLine());
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid input!");
                    System.out.println("Press enter to continue...");
                } else {
                    switch (choice) {
                        case 1 :  loginMenu();
                        case 2 :  break;
                        case 3 : registerMenu(); break;
                        case 4 : System.exit(0); break;
                        default : break;
                    }
                }
        	}
			
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of method tenantChoice1
    
    
    public static void tenantChoice2(){
        try {
        	while(true){
        		menu2();
        		System.out.print("Enter your choice: ");
				int choice = Integer.parseInt(kbd.nextLine());
                if (choice < 1 || choice > 3) {
                    System.out.println("Invalid input!");
                    System.out.println("Press enter to continue...");
                } else {
                    switch (choice) {
                        case 1 :  break;
                        case 2 :  break;
                        case 3 : System.exit(0); break;
                        default :  break;
                    }
                }
        	}
			
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of method tenantChoice2

    public static void loginMenu(){
    	try{
	    	System.out.print("Enter Username: ");
	        String username = kbd.nextLine();
			System.out.print("Enter Password: ");
			String password = kbd.nextLine();
			
			System.out.println(stub.login(username, password));
    	}catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of loginMenu method
    
    public static void registerMenu(){
    	try{
	    	System.out.print("Enter First Name: ");
	        String fName = kbd.nextLine();
			System.out.print("Enter Last Name: ");
			String lName = kbd.nextLine();
			System.out.print("Enter Username: ");
			String username = kbd.nextLine();
			System.out.print("Enter Password: ");
			String pswd = kbd.nextLine();
			System.out.println(stub.register(fName, lName, username, pswd));


			System.out.println("Press Enter to Continue...");
			String cont = kbd.nextLine();
    	}catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of registerMenu method
}
