/**
 *
 * This program is used as a constructor for the registration of the tenants
 */
public class CheckTenants{
	
	private String fname = "";
	private String lname = "";
	private String username = "";
	private String password = "";
	
	public CheckTenants(String fname, String lname, String username, String password){
	 	this.fname = fname;
	 	this.lname = lname;
	 	this.username = username;
	 	this.password = password;
	}
	
	
	//setters
	public void setFirstName(String fname){
		this.fname = fname;
	}
	
	public void setLastName(String lname){
		this.lname = lname;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	//getters
	public String getFirstName(){
		return fname;
	}
	public String getLastName(){
		return lname;
	}
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
}