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
class Usersalary {
    String EID,FirstName,LastaName;
    float basicsalary;

    public Usersalary(String EID, String FirstName, String LastaName, float basicsalary) {
        this.EID = EID;
        this.FirstName = FirstName;
        this.LastaName = LastaName;
        this.basicsalary = basicsalary;
    }

    public String getEID() {
        return EID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastaName() {
        return LastaName;
    }

    public float getBasicsalary() {
        return basicsalary;
    }
    
    
}
