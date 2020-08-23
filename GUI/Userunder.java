/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author madub
 */
class Userunder {
     
    private String PID,EID,startdate,Enddate;
    private int status;
    
    public Userunder(String EID,String PID,String startdate,String Enddate,int status){
        
       
        this.EID=EID;
this.status=status;

    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return Enddate;
    }

    public String getPID() {
        return PID;
    }

    public int getStatus() {
        return status;
    }

  

    public String getEID() {
        return EID;
    }
    
    
    
}


    

    

