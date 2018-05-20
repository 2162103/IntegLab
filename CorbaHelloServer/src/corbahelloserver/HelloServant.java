/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corbahelloserver;

/**
 *
 * @author Casey Steven
 */
public class HelloServant extends HelloPOA{
    
    private String massage="\nHello World!\n";
    private ORB orb;
    
    public void setORB(ORB orb_val){
        orb = orb_val;
    }
 
    
    public String hellomaessage(){
        return massage;
    }
    
    public void hellomessage(String newHellomaessage){
        massage = newHellomaessage;
    }
}
