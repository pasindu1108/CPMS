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
class Usersammary {
    
    String PID,CID,StartDate,EndDate;

    public Usersammary(String PID, String CID, String StartDate, String EndDate) {
        this.PID = PID;
        this.CID = CID;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
    }

    public String getPID() {
        return PID;
    }

    public String getCID() {
        return CID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }
    
}
