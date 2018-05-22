import java.io.PrintStream;
import java.rmi.registry.Registry;
import java.util.Scanner;
/**
 * @(#)Landlord.java
 * This is the Module for the Landlord. If running for the first time it will promt the Landlord to set the BoarderHose name and Capacity
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

public class Landlord
{
  public static Scanner kbd = new Scanner(System.in);
  public static Registry registry;
  public static MMADorm stub;
  public static String dormName = "";
  
  public Landlord() {}
  
  public static void main(String[] args) { try { reg();
      dormName = stub.getBoardingHouseName();
      if (dormName.equals("")) {
        editDorm();
      }
      landlordChoice();
    } catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  

  public static void menu()
  {
    System.out.println("\n\n**********Welcome to " + dormName + " Dormitory Landlord Module**********");
    System.out.println("1: Send notification to all tenants");
    System.out.println("2: Show Current Status of Dormitory");
    System.out.println("3: Change Current Capacity");
    System.out.println("4: Show Current Tenants");
    System.out.println("5: Show Registration Requests");
    System.out.println("6: Exit");
  }
  
  public static void editDorm()
  {
  	try{
  		System.out.println("\n\n**********New Boarding House**********:");
        System.out.print("Enter Boarding House Name: ");
        String bHName = kbd.nextLine();
        System.out.println("What is the Capacity of the Boarding House? ");
        int capacity = Integer.parseInt(kbd.nextLine());
        System.out.println(stub.createBoardingHouse(bHName, capacity));
        
        dormName = stub.getBoardingHouseName();
  	} catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
    	
  }
  
  public static void editCapacity()
  {
  	try{
        System.out.println("\n\nWhat is the new Capacity of the Boarding House ");
        int capacity = Integer.parseInt(kbd.nextLine());
        System.out.println(stub.editCapacity(capacity));
  	} catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
    	
  }
  
  public static void accpetMenu()
  {
    System.out.println("\n\n1: Accpet a request");
    System.out.println("2: Decline a request");
    System.out.println("3: Back to main menu");
  }
  
  public static void reg() {
    try {
      registry = java.rmi.registry.LocateRegistry.getRegistry();
      stub = (MMADorm)registry.lookup("dorm");
    } catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void landlordChoice() {
   for (;;) {
    try {
      
        menu();
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(kbd.nextLine());
        if ((choice < 1) || (choice > 7)) {
          System.out.println("Invalid input!");
          System.out.println("Press enter to continue...");
        } else {
          switch (choice) {
          case 1:  sendNotif(); break;
          case 2:  status(); break;
          case 3:  editCapacity(); break;
          case 4:  showTenants(); break;
          case 5:  showRegistrations(); break;
          case 6:  System.exit(0);
          }
          
        }
      }
   
    catch (Exception e)
    {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
     }
  }
  
    public static void registrationChoice() {
    try {

        accpetMenu();
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(kbd.nextLine());
        if ((choice < 1) || (choice > 5)) {
          System.out.println("Invalid input!");
          System.out.println("Press enter to continue...");
        } else {
          switch (choice) {
          case 1:  acceptReg(); break;
          case 2:  declineReg(); break;
          case 3:  break;
          }
          
        }

    }
    catch (Exception e)
    {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void acceptReg()
  {
    try
    {
      System.out.print("\n\nEnter Registration Number :");
      int regNum = Integer.parseInt(kbd.nextLine());
      
      System.out.print(stub.acceptReg(regNum));
    }
    catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void declineReg()
  {
    try
    {
      System.out.print("\n\nEnter Registration Number :");
      int regNum = Integer.parseInt(kbd.nextLine());
      
      System.out.println(stub.declineReg(regNum));
    }
    catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void status()
  {
    try {
    	System.out.println("\n\n\n**********Status of " + dormName + " Dormitory**********");
    	if(stub.getCapacity() == stub.getNumberOfTenants()){
    			System.out.println("THE DORM IS FULL!");
    		} else {
    			System.out.println("AVAILABLE SPACES");
    		}
    System.out.println("\n\nThe maximum capacity of this dormitory is "+ stub.getCapacity());
      System.out.println("There are currently " + stub.getNumberOfTenants() + " tentants in the dormitory");
      System.out.println("And there are still " + (stub.getCapacity() - stub.getNumberOfTenants()) + " available bed space/s");
      
      System.out.println("");
      
      System.out.println("Press Enter to Continue...");
      String str = kbd.nextLine();
    }
    catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void sendNotif()
  {
    try
    {
      System.out.println("\n\nNotification to all tenants");
      String notif = kbd.nextLine();
      
      System.out.println(stub.setNotif(notif));
      
      System.out.println("Press Enter to Continue...");
      String str = kbd.nextLine();
    }
    catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void showRegistrations()
  {
    try {
      System.out.println("\n\n**********************************************Registration**********************************************");
      System.out.println(stub.seeAllRegistrationReq());
      registrationChoice();
      System.out.println("Press Enter to Continue...");
      String str = kbd.nextLine();
    } catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
  
  public static void showTenants()
  {
    try {
      System.out.println("\n\n**********************************************Tenants**********************************************");
      System.out.println(stub.seeAllTentants());
      System.out.println("Press Enter to Continue...");
      String str = kbd.nextLine();
    } catch (Exception e) {
      System.out.println("\nSomething went wrong. Redirecting to Main Menu....");
    }
  }
}
