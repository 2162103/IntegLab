import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;
import java.util.*;
/**
 * @(#)Tenant.java
 * This is the module for the tenants in the dorm. To successfully use this the dorm name should be seet first by the Landlord program
 * 
 *
 * @author Bernabe, Casey Steven P.
 * @author Dela Cruz, Marcus Leviticus M.
 * @author Gayaso, Genrie L.
 * @author Maniti, Janxiene Azly
 * @author Pua Phee, Ashley Chester D.
 * @author Rosal, Jericho Dave B.
 * @author Sese, John Patrick D.
 * @author Velasco, Rizalde Jr. A.
 *
 *
 * @version 1.00 2018/5/22
 */

public class Tenant{
    public static Scanner kbd = new Scanner(System.in);
    public static Registry registry;
    public static MMADorm stub;
    public static String dormName = "";
    public static String username = "";
    public static String password = "";
    
    public static void main(String[] args) {
        try{
	        reg();
	        dormName = stub.getBoardingHouseName();
	        if(dormName.equals("")){
	        	System.out.println("\n\n**********The Boarding House is not yet ready**********");
	        	System.out.println("Exiting Program.............");
	        	System.exit(0);
	        	
	        }
			tenantChoice1();
		}catch(Exception e){
    		e.printStackTrace();
    	}
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
        System.out.println("\n\n\n**********Welcome to " + dormName + " Dormitory**********");
        System.out.println("1: Tenant Login");
        System.out.println("2: Show dormitory info");
        System.out.println("3: Show Tenants");
        System.out.println("4: Check-in the boarding house");
        System.out.println("5: Exit");
    }
    
    public static void menu2(){
        System.out.println("\n\n\n**********Loged in as " + username + "**********");
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
                        case 1 :  loginMenu(); break;
                        case 2 :  status(); break;
                        case 3 : showCoTenants(); break;
                        case 4 : registerMenu(); break;
                        case 5 : System.exit(0); break;
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
                        case 1 :  System.out.println("\n\n\n**********Notification from the Landlord*********\n" + stub.getNotif());;break;
                        case 2 :  checkOut();break;
                        case 3 :  System.exit(0); break;
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
    		System.out.println("\n\n\n**********Login**********");
	    	System.out.print("\n\nEnter Username: ");
	        username = kbd.nextLine();
			System.out.print("Enter Password: ");
			password = kbd.nextLine();
			
			String status = stub.login(username, password);
			if( (status.equals("***Login dennied***")) ) {
				System.out.println(status);
				System.out.println("Press Enter to Continue...");
				String cont = kbd.nextLine();
			} else if ( (status.equals("***You have Login***")) ){
				tenantChoice2();
			}
			
    	}catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of loginMenu method
    
    public static void registerMenu(){
    	try{
    		System.out.println("\n\n\n**********Checking In**********");
	    	System.out.print("\n\nEnter First Name: ");
	        String fName = kbd.nextLine();
			System.out.print("Enter Last Name: ");
			String lName = kbd.nextLine();
			System.out.print("Enter email: ");
			String email = kbd.nextLine();
			System.out.print("Enter Phone Number: ");
			String phoneNum = kbd.nextLine();
			System.out.print("Enter Username: ");
			String username = kbd.nextLine();
			System.out.print("Enter Password: ");
			String pswd = kbd.nextLine();
			System.out.println(stub.register(fName, lName, email, phoneNum, username, pswd));


			System.out.println("Press Enter to Continue...");
			String cont = kbd.nextLine();
    	}catch(Exception e) {
        	e.printStackTrace();
        }
    }//end of registerMenu method
    
    public static void status(){
    	try{
    		System.out.println("\n\n\n**********Welcome to " + dormName + " Dormitory**********");
    		if(stub.getCapacity() == stub.getNumberOfTenants()){
    			System.out.println("THE DORM IS FULL!");
    		} else {
    			System.out.println("AVAILABLE SPACES");
    		}
    		System.out.println("The maximum capacity of this dormitory is "+ stub.getCapacity());
    		System.out.println("There are currently "+ stub.getNumberOfTenants() + " tenants in the dormitory");
    		System.out.println("And there are still "+ (stub.getCapacity() - stub.getNumberOfTenants()) + " available bed space/s");
    		
    		System.out.println("");
    		
    		System.out.println("Press Enter to Continue...");
			String cont = kbd.nextLine();
    		
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    
    }
    
    public static void checkOut(){
    	try{
    		System.out.println("\n\n\n**********Checking Out of " +dormName+ "**********");
    		System.out.println("*To check out please re-enter username and password for confirmation");
			System.out.print("Enter Username: ");
			String username = kbd.nextLine();
			System.out.print("Enter Password: ");
			String pswd = kbd.nextLine();
			
			System.out.println(stub.checkOut(username, pswd));
    		
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    
    }
    
    public static void showCoTenants()
  {
    try {
      System.out.println("\n\n******************Current Tenants of " +dormName+ "******************");
      System.out.println(stub.seeAllCoTentants());
    } catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
    

}
