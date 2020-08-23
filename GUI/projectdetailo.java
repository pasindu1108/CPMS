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
class projectdetailo {
   
    
    String PID,CID,ProjectName,Location;
    int Projectstatus;

    public projectdetailo(String PID, String CID, String ProjectName, String Location, int Projectstatus) {
        this.PID = PID;
        this.CID = CID;
        this.ProjectName = ProjectName;
        this.Location = Location;
        this.Projectstatus = Projectstatus;
    }

    public String getPID() {
        return PID;
    }

    public String getCID() {
        return CID;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public String getLocation() {
        return Location;
    }

    public int getProjectstatus() {
        return Projectstatus;
    }
    
    
    
    
}


