/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import basicObj.Account;
import basicObj.Order;
import dbaccess.accountDao;
import dbaccess.orderDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lthut
 */
public class ActionWithOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<Order> list = new ArrayList();
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email");
            String password = (String) session.getAttribute("password");
            Account acc = accountDao.getAccount(email, password);
            if (action.equals("cancel")) {
                String orderid = request.getParameter("ordID");
                int orderID = Integer.parseInt(orderid.trim());
                boolean result = orderDao.cancelOrder(orderID);
                if (result) {
                    request.setAttribute("updateStatus", "Cancel order successfull.");
                } else {
                    request.setAttribute("updateStatus", "Cancel order fail.");
                }
                if(acc.getRole()==1){
                    request.getRequestDispatcher("mainController?action=manageOrders").forward(request, response);
                    return;
                }
                request.getRequestDispatcher("ViewOrdersServlet").forward(request, response);
            } else if (action.equals("orderAgain")) {
                String orderid = request.getParameter("ordID");
                int orderID = Integer.parseInt(orderid.trim());
                boolean result = orderDao.orderAgain(orderID);
                if (result) {
                    request.setAttribute("updateStatus", "Order again successfull.");
                } else {
                    request.setAttribute("updateStatus", "Order again fail.");
                }
                request.getRequestDispatcher("ViewOrdersServlet").forward(request, response);

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ActionWithOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ActionWithOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
