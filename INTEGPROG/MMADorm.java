import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @(#)Quiz.java
 *
 *
 * @author
 * @version 1.00 2018/3/27
 */


public interface MMADorm extends Remote {
	public String login(String username, String password) throws RemoteException;
	public String register(String fName, String lName, String username, String pswd) throws RemoteException;
}