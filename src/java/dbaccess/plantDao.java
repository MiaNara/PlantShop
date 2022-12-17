/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import basicObj.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUTils;

/**
 *
 * @author lthut
 */
public class plantDao {

    public static ArrayList<Plant> getPlant(String keyword, String searchBy) {
        ArrayList<Plant> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select PID,Pname, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n" + "from Plants join Categories on Plants.CateID = Categories.CateID\n";
                if (searchBy.equalsIgnoreCase("byname")) {
                    sql = sql + "where PName LIKE ?";
                } else {
                    sql = sql + "where CateName LIKE ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int PID = table.getInt("PID");
                        String Pname = table.getString("Pname");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        String description = table.getString("description");
                        int status = table.getInt("status");
                        int CateID = table.getInt("CateID");
                        String Catename = table.getString("CateName");
                        Plant plant = new Plant(PID, Pname, price, imgPath, description, status, CateID, Catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }

    public static Plant getPlantByID(int id) {
        Plant plant = null;
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select PID,Pname, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n" + "from Plants join Categories on Plants.CateID = Categories.CateID\n"
                        + "where PID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {

                    int PID = table.getInt("PID");
                    String Pname = table.getString("Pname");
                    int price = table.getInt("price");
                    String imgPath = table.getString("imgPath");
                    String description = table.getString("description");
                    int status = table.getInt("status");
                    int CateID = table.getInt("CateID");
                    String Catename = table.getString("CateName");
                    plant = new Plant(PID, Pname, price, imgPath, description, status, CateID, Catename);
                    return plant;
                }
            }

        } catch (Exception e) {
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (SQLException e) {
                }
            }
        }
        return plant;
    }
    public static ArrayList<Plant> getAllPlants() {
        ArrayList<Plant> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select PID,Pname, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n" + "from Plants join Categories on Plants.CateID = Categories.CateID\n";
               Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if (table != null) {
                    while (table.next()) {
                        int PID = table.getInt("PID");
                        String Pname = table.getString("Pname");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        String description = table.getString("description");
                        int status = table.getInt("status");
                        int CateID = table.getInt("CateID");
                        String Catename = table.getString("CateName");
                        Plant plant = new Plant(PID, Pname, price, imgPath, description, status, CateID, Catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return list;
    }
}
