/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author madub
 */
public class username {
    
 public static String loggedusername;

  public void username(String username) {
         loggedusername = username;
    }   
 
 
 public static String getLoggedusername() {
        return loggedusername;
    }

     public String userid(){
        return loggedusername;
    }
	
    public static void main(String[] args) {}
}

