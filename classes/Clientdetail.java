/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author madub
 */
class Clientdetail {
    String CID,ClientName;
    int status;

    public Clientdetail(String CID, String ClientName, int status) {
        this.CID = CID;
        this.ClientName = ClientName;
        this.status = status;
    }

    public String getCID() {
        return CID;
    }

    public String getClientName() {
        return ClientName;
    }

    public int getStatus() {
        return status;
    }
    
    
    
    
    
    
}