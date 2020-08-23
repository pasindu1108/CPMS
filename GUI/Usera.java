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
class Usera {
    String EID,UserName,Position;

    public Usera(String EID, String UserName, String Position) {
        this.EID = EID;
        this.UserName = UserName;
        this.Position = Position;
    }

    public String getEID() {
        return EID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPosition() {
        return Position;
    }
    
}
