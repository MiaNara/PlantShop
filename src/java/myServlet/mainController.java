/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lthut
 */
public class mainController extends HttpServlet {
 private String url = "errorpage.html";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            if(action == null || action.equals("")||action.equals("search")){
                url = "index.jsp";
            }else if(action.equals("Login")){
                url = "LoginServlet";
            }else if(action.equals("Register")){
                url = "RegisterServlet";
            } else if(action.equals("logout"))
                url = "LogOutServlet";
            else if(action.equals("search"))
                url = "SearchPlantServlet";
            else if(action.equals("updateAccount"))
                url ="GetAccountInforServlet";
            else if(action.equals("addToCart"))
                url="AddToCartServlet";
            else if(action.equals("viewCart"))
                url="viewCart.jsp";
            else if(action.equals("updateCart"))
                url="UpdateCartServlet";
            else if(action.equals("deleteCart"))
                url="RemoveCartServlet";
            else if(action.equals("Save Order"))
                url="SaveShoppingCartServlet";
             else if(action.equals("searchOrderByDate"))
                url="SearchOrderByDate";
             else if(action.equals("SearchAccount"))
                 url="SearchAccountServlet";
             else if(action.equals("manageAccounts"))
                 url="ManageAccountsServlet";
             else if(action.equals("updateStatusAccount"))
                 url="UpdateStatusAccountServlet";
            else if(action.equals("manageOrders"))
                 url="ManageOrdersServlet";
            else if(action.equals("Delivered"))
                 url="UpdateShipDateServlet";
            else if(action.equals("viewOrders"))
                 url="ViewOrdersServlet";
            else if(action.equals("cancel")|| action.equals("orderAgain"))
                url= "ActionWithOrderServlet";
            RequestDispatcher rd =  request.getRequestDispatcher(url);
            rd.forward(request, response);
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
