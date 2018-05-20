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
    public static void main(String[] args) {
        try {
			Registry registry = LocateRegistry.getRegistry();
			MMADorm stub = (MMADorm)registry.lookup("dorm");
			Scanner kb = new Scanner(System.in);

                        System.out.println("Enter Username: ");
                        String username = kb.nextLine();
                        System.out.println("Enter Password");
                        String password = kb.nextLine();
                        
                        System.out.println(stub.login(username, password));
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}
