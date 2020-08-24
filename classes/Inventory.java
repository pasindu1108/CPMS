/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates//
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author madub
 */
public class Inventory {
    
     private String Itemcode,Name;
     private int Qty,Unitrice;

    public Inventory(String Itemcode, String Name, int Qty, int Unitrice) {
        this.Itemcode = Itemcode;
        this.Name = Name;
        this.Qty = Qty;
        this.Unitrice = Unitrice;
    }

    public String getItemcode() {
        return Itemcode;
    }

    public String getName() {
        return Name;
    }

    public int getQty() {
        return Qty;
    }

    public int getUnitrice() {
        return Unitrice;
    }
     
    
    
}
