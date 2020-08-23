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
class Userunder2 {

   
    private String PID,EID,startdate,Enddate,Position;
    private int status;
    
    public Userunder2(String EID,String PID,String startdate,String Enddate,String Position,int status){
        
       
        this.EID=EID;
        this.PID=PID;
        this.Enddate=Enddate;
        this.startdate=startdate;
        this.Position=Position;
        this.status=status;

    }

    public String getPID() {
        return PID;
    }

    public String getStartdate() {
        return startdate;
    }

    public String getEnddate() {
        return Enddate;
    }

    public String getPosition() {
        return Position;
    }

  
     public String getEID() {
        return EID;
    }

    public int getStatus() {
        return status;
    }

  
 
}
