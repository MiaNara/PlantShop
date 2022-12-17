/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicObj;

import java.io.Serializable;

/**
 *
 * @author lthut
 */
public class Plant implements Serializable {
    private int PID;
    private String PName;
    private int price;
    private String imagePath;
    private String description;
    private int status;
    private int cateID;
    private String catename;
 public Plant() {
    }
    public Plant(int PID, String PName, int price, String imagePath, String description, int status, int CateID, String Catename) {
        this.PID = PID;
        this.PName = PName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.status = status;
        this.cateID = CateID;
        this.catename = Catename;
    }

    public Plant(int PID, String PName, int price, String imagePath, String description, int status, int CateID) {
        this.PID = PID;
        this.PName = PName;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
        this.status = status;
        this.cateID = CateID;
        
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int CateID) {
        this.cateID = CateID;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

  
    
}
