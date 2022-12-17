/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import basicObj.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUTils;

/**
 *
 * @author lthut
 */
public class accountDao {

    public static ArrayList<Account> getAllAcounts() {
        ArrayList<Account> list = new ArrayList<>();
        //Step1: make connections
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n" + "from dbo.Accounts\n";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                //step 3 : xu li ket qua step 2
                if (table != null) {
                    while (table.next()) {
                        int accid = table.getInt("accID");
                        String email = table.getString("email");
                        String password = table.getString("password");
                        String fullname = table.getString("fullname");
                        String phone = table.getString("phone");
                        int status = table.getInt("status");
                        int role = table.getInt("role");
                        Account acc = new Account(accid, email, password, fullname, phone, status, role);
                        list.add(acc);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n" + "from dbo.Accounts\n" + "where status=1 and email= ? and password= ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    int accid = table.getInt("accID");
                    String Email = table.getString("email");
                    String Password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    acc = new Account(accid, Email, Password, fullname, phone, status, role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
     public static Account getAccountByToken(String token) throws Exception {
        Account acc = null;
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role, token\n" + "from dbo.Accounts\n" + "where token= ? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    int accid = table.getInt("accID");
                    String Email = table.getString("email");
                    String Password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    acc = new Account(accid, Email, Password, fullname, phone, status, role,token);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return acc;
    }
      public static boolean updateAccountToken(String email, String token) throws Exception {
        Connection cn = null;
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.Accounts\n" + "SET token = ?\n" + "WHERE email LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2,email);
                pst.executeUpdate();
              return true;
            }
            else{
                return false;
            }
    }
public static boolean getExistedAccount(String email) throws Exception {
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select email\n" + "from dbo.Accounts\n" + "where status=1 and email= ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet table = pst.executeQuery();
                if (table != null && table.next()) {
                    return true;
                }
                else{
                    return false;
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
    public static ArrayList<Account> searchAccountByName(String name) throws Exception {
        Account acc = null;
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n" + "from dbo.Accounts\n" + "where fullname LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1,"%"+name+"%");
                ResultSet table = pst.executeQuery();
                if (table != null) {
                       while (table.next()) {
                    int accid = table.getInt("accID");
                    String Email = table.getString("email");
                    String Password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    acc = new Account(accid, Email, Password, fullname, phone, status, role);
                    list.add(acc); 
                       }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
    public static int insertAccount(String email, String password, String fullname, String phone, int status, int role) throws Exception {
        int result = 0;
        Connection cn = DBUTils.makeConnection();
        if (cn != null) {
            String sql = "insert dbo.Accounts(email,password,fullname,phone,status,role)"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            //gan input vao cac dau cham hoi.
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, fullname);
            pst.setString(4, phone);
            pst.setInt(5, status);
            pst.setInt(6, role);
            pst.executeUpdate();
        }
        else{
            result = -1;
        }
        return result;
    }

    public static boolean updateAccountStatus(String email, int status) throws Exception {
        Connection cn = null;
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.Accounts\n" + "SET status = ?\n" + "WHERE email LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1,status);
                pst.setString(2,email);
                pst.executeUpdate();
              return true;
            }
            else{
                return false;
            }
    }
    public static boolean updateProfile(String email, String newName, String newPhone) throws Exception{
         Connection cn = null;
         try{
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "UPDATE dbo.Accounts\n" + "SET fullname = ?, phone = ?\n" + "WHERE email LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newName);
                pst.setString(2,newPhone);
                pst.setString(3,email );
                pst.executeUpdate();
              return true;
            }
            else{
                return false;
            }
             } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
      
}
