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
class Userassign {
     private String EID,PID,startdate,Enddate,Position;
    private int status;
    
    public Userassign(String EID,String startdate,String Enddate,String Position,int status){
        this.EID=EID;
        this.Position=Position;
        this.startdate=startdate;
        this.Enddate=Enddate;
        this.status=status;
        
    }

    public String getEID() {
        return EID;
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

    public int getStatus() {
        return status;
    }
    
}

    

