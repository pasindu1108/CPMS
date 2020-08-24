/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates//
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author madub
 */
class Userattend {
    
     private String EID,PID,attendDate,Intime,Outtime;
  
    
     public Userattend(String EID,String PID,String attendDate,String Intime,String Outtime){
        
        this.EID=EID;
        this.Intime=Intime;
        this.Outtime=Outtime;
        this.attendDate=attendDate;
        this.PID=PID;
    
        
    }

    public String getEID() {
        return EID;
    }

    public String getPID() {
        return PID;
    }

    public String getAttendDate() {
        return attendDate;
    }

    public String getIntime() {
        return Intime;
    }

    public String getOuttime() {
        return Outtime;
    }

    
    
    
}

    

