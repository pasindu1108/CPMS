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
class Userpro {
    private String PID,CID,ProjName,Location,StartDate,EndDate;
    private int Pstatus;
    
    public Userpro(String PID,String CID,String ProjName,String Location,int Pstatus){
        this.CID=CID;
        this.PID=PID;
        this.Location=Location;
        this.Pstatus=Pstatus;
        this.ProjName=ProjName;
    }

    public String getPID() {
        return PID;
    }

    public String getCID() {
        return CID;
    }

    public String getProjName() {
        return ProjName;
    }

    public String getLocation() {
        return Location;
    }

    public int getPstatus() {
        return Pstatus;
    }
    
}
