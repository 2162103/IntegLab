/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.sql.*;
import java.util.Scanner;
import java.io.Console;
/**
 *
 * @author Casey Steven
 */
public class Server {

    public static Scanner scn = new Scanner(System.in);
    public static Console console = System.console();
    public static Connection con;
    public static String query;
    public static Statement stmt;
    public static ResultSet rs;
    
    public static void main(String[] args) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/integprojv1?autoReconnect=true&useSSL=false", "root", ""); 
            
            register();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public static void login(){
        System.out.println("******Welcome to the Dorm Finder******");
        System.out.println("LOGIN FORM");
        System.out.println("UserName:");
        String username = scn.nextLine();
        System.out.println("Password:");
        String password = scn.nextLine();
        query = "SELECT * from users where username = " + username + " and userpassword = " + password.hashCode();
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.beforeFirst();
            if (rs.next()){
                System.out.println("yey");
            } else {
                System.out.println("no");
            }
        } catch (Exception e){
            
        }
                
    }
    
    public static void register(){
        try {
            query = "INSERT INTO users (username, userpassword, user_first_name, user_last_name, user_email, user_gender) value (?, ?, ?, ?, ?, ?)";
            String userName, password, rePassword, firstName, lastName, email, gender;
            while(true){
                System.out.println("******Welcome to the Dorm Finder******");
                System.out.println("REGISTRATION FORM");
                System.out.println("UserName:");
                userName = scn.nextLine();
                System.out.println("Password:");
                password = scn.nextLine();
                System.out.println("Re Enter Password:");
                rePassword = scn.nextLine();
                System.out.println("First Name:");
                firstName = scn.nextLine();
                System.out.println("Last Name:");
                lastName = scn.nextLine();
                System.out.println("email:");
                email = scn.nextLine();
                System.out.println("Gender (M/F):");
                gender = scn.nextLine();
                
                if(password.equals(rePassword)){
                    break;
                } else {
                    System.out.println("Your Password Did Not Match");
                }
            }
            PreparedStatement st = con.prepareStatement(query); 
            st.setString(1, userName);
            st.setInt(2, password.hashCode());
            st.setString(3, firstName);
            st.setString(4, lastName);
            st.setString(5, email);
            st.setString(6, gender);
            
            st.execute();
            st.close();
        } catch (Exception e){
            System.out.println(e);
        }

    }
    
}
