import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
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
	/****************************************************
	 **** This section implements the Quiz interface ****
	 ****************************************************/
//	Object[][] testItem = {
//		{"1. A RIVER is bigger than a STREAM. TRUE or FALSE?",                       true },
//		{"2. There are one thousand years in a CENTURY. TRUE or FALSE?",             false},
//		{"3. FOUNDED is the past tense of FOUND. TRUE or FALSE?",                    true },
//		{"4. ANSWER can be used as a noun and a verb. TRUE or FALSE?",               true },
//		{"5. SCARLET is a brilliant red colour. TRUE or FALSE?",                     true },
//		{"6. USED TO DOING and USED TO DO mean the same thing. TRUE or FALSE?",	     false},
//		{"7. You can use IMPROVE as a noun and as a verb. TRUE or FALSE?",           false},
//		{"8. DOZEN is equivalent to 20. TRUE or FALSE?",                             false},
//		{"9. The past tense of FIND is FOUND. TRUE or FALSE?",                       true },
//		{"10. EQUIVALENT TO is (more or less) the same as EQUAL TO. TRUE or FALSE?", true }
//	};
//
//	public String getQuestion(int qnNo) {
//		return (String)testItem[qnNo][0];
//	}
//
//	public int submitAnswers(boolean[] ans) {
//		int score = 0;
//
//		for (int qnNo=0; qnNo<10; qnNo++) {
//			if ((boolean)testItem[qnNo][1] == ans[qnNo]) {
//				score ++;
//			}
//		}
//
//		return score;
//	}

    
    public String login (String user, String pass){
        if (user.equals("user") && pass.equals("user")){
            return "You have Login";
        } else {
            return "Login dennied " + user + " " + pass;
        }
    }
    
    public String register (String user, String pass){
        if (user.equals("user") && pass.equals("user")){
            return "You have Login";
        } else {
            return "Login dennied " + user + " " + pass;
        }
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
