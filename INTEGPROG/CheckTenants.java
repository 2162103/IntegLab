/**
 * @(#)CheckTenant.java
 * This program is used as a constructor for the registration of the tenants
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
 * @version 1.00 2018/5/22
 */
public class CheckTenants{
	
	private String fname = "";
	private String lname = "";
	private String email = "";
	private String phoneNum = "";
	private String username = "";
	private String password = "";
	
	public CheckTenants(String fname, String lname, String email, String phoneNum, String username, String password){
	 	this.fname = fname;
	 	this.lname = lname;
	 	this.email = email;
	 	this.phoneNum = phoneNum;
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
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setPhoneNum(String phoneNum){
		this.phoneNum = phoneNum;
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
	public String getEmail(){
		return email;
	}
	public String getPhoneNum(){
		return phoneNum;
	}
	
	public String getUsername(){
		return username;
	}
	public String getPassword(){
		return password;
	}
}