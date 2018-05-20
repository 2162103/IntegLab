import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class Landlord {
    public static Scanner kbd = new Scanner(System.in);
    public static Registry registry;
    public static MMADorm stub;
    public static String dormName = "";
    
    public static void main(String[] args) {
    	try{
    		reg();
	        dormName = stub.getBoardingHouseName();
	        if(dormName.equals("")){
	        	System.out.println("\n\n**********New Boarding House**********:");
	        	System.out.print("Enter Boarding House Name: ");
	        	String bHName = kbd.nextLine();
	        	System.out.println("What is the Capacity of the Boarding House ");
	        	int capacity = Integer.parseInt(kbd.nextLine());
	        	System.out.println(stub.createBoardingHouse(bHName, capacity));
	        	
	        	dormName = stub.getBoardingHouseName();
	        }
			landlordChoice();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        
		
    }
    
    public static void menu(){
        System.out.println("\n\n**********Welcome to "+ dormName +"**********:");
        System.out.println("1: Send notification to all tenants");
        System.out.println("2: Show Current Status of Dormitory");
        System.out.println("3: Show All Tenants");
        System.out.println("4: Exit");
    }
    
    public static void reg(){
    	try{
    		registry = LocateRegistry.getRegistry();
    		stub = (MMADorm)registry.lookup("dorm");
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void landlordChoice(){
        try {
        	while(true){
        		menu();
        		System.out.print("Enter your choice: ");
				int choice = Integer.parseInt(kbd.nextLine());
                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid input!");
                    System.out.println("Press enter to continue...");
                } else {
                    switch (choice) {
                        case 1 :  sendNotif(); break;
                        case 2 :  status(); break;
                        case 3 : showTenants(); break;
                        case 4 : System.exit(0); break;
                        default : break;
                    }
                }
                
        	}
			
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void status(){
    	try{
    		
    		System.out.println("\n\nThere are currently "+ stub.getNumberOfTenants() + " in the dormitory");
    		System.out.println("And there are still "+ (stub.getCapacity() - stub.getNumberOfTenants()) + " available bed space/s");
    		
    		System.out.println("");
    		
    		System.out.println("Press Enter to Continue...");
			String cont = kbd.nextLine();
    		
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    
    }
    
    public static void sendNotif(){
    	try{
    		
    		System.out.println("\n\nNotification to all tenants");
    		String notif = kbd.nextLine();
    		
    		System.out.println(stub.setNotif(notif));
    		
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static void showTenants(){
    	try{
    		
    		System.out.println("\n\nFirst Name \t Last Name \t Username \t Password");
    		System.out.println(stub.seeAllTenants());
    	} catch(Exception e) {
        	e.printStackTrace();
        }
    }
    

    

}
