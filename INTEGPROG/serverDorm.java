import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.util.Scanner;
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
    
    public String login(String username, String password){
                        
        if (username.equals("user") && password.equals("user")){
            return "You have Login";
        } else {
            return "Login dennied ";
        }
    }
    
    public void register (){
        System.out.println("Enter Your First Name");
        String fName = kbd.nextLine();
        System.out.println("Enter Your Last Name");
        String lName = kbd.nextLine();
        System.out.println("Enter Your Last Name");
        String userName = kbd.nextLine();
        System.out.println("Enter a Password");
        String pswd = kbd.nextLine();
        String resp = "\nFirst Name: " + fName + "\nLast Name: " + lName + "\nUser Name: " + userName + "\nPassword: " + pswd;
        System.out.println(resp);
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
                
                

        } catch(Exception e) {
        	e.printStackTrace();
        	System.exit(1);
        }
    }
}
