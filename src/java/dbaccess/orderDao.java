/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaccess;

import basicObj.Order;
import basicObj.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Set;
import mylib.DBUTils;

/**
 *
 * @author lthut
 */
public class orderDao {

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipDate, Orders.status as OrdStatus, Orders.AccID\n"
                        + "from dbo.Orders join dbo.Accounts on Accounts.accID = Orders.AccID\n"
                        + "WHERE email Like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + email + "%");
                ResultSet table = pst.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipDate");
                        int status = table.getInt("OrdStatus");
                        int accID = table.getInt("AccID");

                        Order ord = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(ord);
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
      public static ArrayList<Order> getAllOrders() {
        ArrayList<Order> list = new ArrayList<>();
        //Step1: make connections
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipDate, Orders.status as OrdStatus, Orders.AccID\n"
                        + "from dbo.Orders join dbo.Accounts on Accounts.accID = Orders.AccID\n";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                //step 3 : xu li ket qua step 2
                if (table != null) {
                    while (table.next()) {
                         int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipDate");
                        int status = table.getInt("OrdStatus");
                        int accID = table.getInt("AccID");

                        Order ord = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(ord);
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
    public static ArrayList<Order> getOrdersByType(String email, int status) throws Exception {
        ArrayList<Order> list = new ArrayList();
        Connection cn = null;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipDate, Orders.status as OrdStatus, Orders.AccID\n"
                        + "from dbo.Orders join dbo.Accounts on Accounts.accID = Orders.AccID\n"
                        + "WHERE email Like ? AND Orders.status= ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + email + "%");
                pst.setInt(2, status);
                ResultSet table = pst.executeQuery();

                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipDate");
                        int accID = table.getInt("AccID");
                        Order ord = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(ord);
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

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        ArrayList list = new ArrayList();
        Connection cn = null;

        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderDetails.OrderID, FID, PName as PlantName,price, imgPath, quantity\n"
                        + "from  dbo.OrderDetails join dbo.Plants on OrderDetails.FID = Plants.PID\n"
                        + "Where OrderID = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, orderID);
                ResultSet table = ps.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderDetailID = table.getInt("DetailId");
                        int plantID = table.getInt("FID");
                        String plantName = table.getString("PlantName");
                        int price = table.getInt("price");
                        String imgPath = table.getString("imgPath");
                        int quantity = table.getInt("quantity");
                        OrderDetail ordetail = new OrderDetail(orderDetailID, orderID, plantID, plantName, price, imgPath, quantity);
                        list.add(ordetail);
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

    public static boolean cancelOrder(int orderID) {
        Connection cn = null;
        try {

            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders\n"
                        + "set status = 3\n"
                        + "WHERE orderID Like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                pst.executeUpdate();
                return true;
            } else {
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

    public static boolean orderAgain(int orderID) {
        Connection cn = null;
         Date d = new Date(System.currentTimeMillis());
        try {

            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders\n"
                        + "set status = 1, OrdDate=?\n"
                        + "WHERE orderID Like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, orderID);
                pst.executeUpdate();
                return true;
            } else {
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

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                String sql = "select accID\n from Accounts\n where Accounts.email Like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }

                System.out.println("Accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("Order date: " + d);
                sql = "insert Orders(OrdDate, status,AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                sql = "select top 1 OrderID\n from Orders\n order by orderID DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                System.out.println("OrderID: " + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);

                }
                return true;
            } else {
                System.out.println("Can insert order");

            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
       public static ArrayList<Order> searchOrderByDate(String dateFrom, String dateTo, int accID) {
        ArrayList list = new ArrayList();
        Connection cn = null;

        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate,shipDate,status\n"+
                                "from Orders\n"+"where accID LIKE ? and OrdDate between ? and ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, accID);
                ps.setString(2, dateFrom);
                ps.setString(3, dateTo);
                ResultSet table = ps.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipDate");
                        int status = table.getInt("status");
                        Order ord = new Order(orderID, orderDate, shipDate, status, accID);
                        list.add(ord);
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
        public static ArrayList<Order> searchOrderByDate(String dateFrom, String dateTo) {
        ArrayList list = new ArrayList();
        Connection cn = null;

        try {
            cn = DBUTils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate,shipDate,status,AccID\n"+
                                "from Orders\n"+"where OrdDate between ? and ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, dateFrom);
                ps.setString(2, dateTo);
                ResultSet table = ps.executeQuery();
                if (table != null) {
                    while (table.next()) {
                        int orderID = table.getInt("OrderID");
                        String orderDate = table.getString("OrdDate");
                        String shipDate = table.getString("shipDate");
                        int status = table.getInt("status");
                        int accID = table.getInt("AccID");
                        Order ord = new Order(orderID, orderDate, shipDate, status,accID);
                        list.add(ord);
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
       public static boolean updateShipDate(int ordID) throws SQLException{
           boolean result = false;
           Connection cn = null;
            Date d = new Date(System.currentTimeMillis());
           try{
               cn = DBUTils.makeConnection();
               if(cn!= null){
                   String sql = "Update [dbo].[Orders]\n" +
                                    "set [shipdate] = ?, status = 2\n" +
                                    "where OrderID = ?";
                   PreparedStatement pst = cn.prepareStatement(sql);
                   pst.setDate(1,d);
                   pst.setInt(2, ordID);
                   pst.executeUpdate();
                   result = true;
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
        return result;
    }
}
