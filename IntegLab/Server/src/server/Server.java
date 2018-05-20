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
    public static PreparedStatement st;
    
    public static void main(String[] args) {
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/integprojv1?autoReconnect=true&useSSL=false", "root", ""); 
            
            addDorm();
            
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
            
            rs.close();
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
            st = con.prepareStatement(query); 
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
    
    public static void addDorm(){
                try{
                query = "INSERT INTO users (dorrm_name, dorm_Address, rooms_available, monthly_rent, male_dorm, female dorm) value (?, ?, ?, ?, ?, ?)";
                String dormName, address, males, females;
                int roomsAvailable, monthlyRent;
                System.out.println("******Welcome to the Dorm Finder******");
                System.out.println("ADD DORMITORY");
                System.out.println("Dorm Name:");
                dormName = scn.nextLine();
                System.out.println("Address:");
                address = scn.nextLine();
                System.out.println("Rooms Available:");
                roomsAvailable = Integer.parseInt(scn.nextLine());
                System.out.println("Monthly Rent:");
                monthlyRent = Integer.parseInt(scn.nextLine());
                System.out.println("Can Males Apply? (T/F):");
                males = scn.nextLine();
                System.out.println("Can Females Apply? (T/F):");
                females = scn.nextLine();
                
                st = con.prepareStatement(query); 
                st.setString(1, dormName);
                st.setString(2, address);
                st.setInt(3, roomsAvailable);
                st.setInt(4, monthlyRent);
                st.setString(5, males);
                st.setString(6, females);
                    
                } catch (Exception e){
                    
                }
                
    }
    
}
