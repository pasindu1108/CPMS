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
public class Suppliers {
    
     private String SupplierID,Name,Type,Contact,Address,Email;

  
    public Suppliers(String SupplierID,String Name,String Type,String Contact,String Address,String Email){
        
      this.SupplierID=SupplierID;
      this.Name=Name;
      this.Type=Type;
      this.Contact=Contact;
      this.Address=Address;
      this.Email=Email;
    }

    public String getAddress() {
        return Address;
    }

    public String getContact() {
        return Contact;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }

    public String getSupplierID() {
        return SupplierID;
    }

    public String getType() {
        return Type;
    }
   
}
