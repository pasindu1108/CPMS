/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates//
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author madub
 */
public class userlevel {
     public static String loggeduserlevel;

    public void userlevel(String userlevel) {
        loggeduserlevel = userlevel;
    }

    public static String getLoggeduserlevel() {
        System.out.println(loggeduserlevel);
        return loggeduserlevel;
    }
    
    public String user(){
        return loggeduserlevel;
    }
	
    public static void main(String[] args) {}
}
