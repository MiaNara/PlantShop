/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicObj;

/**
 *
 * @author lthut
 */
public class Category {
    private int CateID;
    private String Catename;

    public Category(int CateID, String Catename) {
        this.CateID = CateID;
        this.Catename = Catename;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int CateID) {
        this.CateID = CateID;
    }

    public String getCatename() {
        return Catename;
    }

    public void setCatename(String Catename) {
        this.Catename = Catename;
    }
    
}
