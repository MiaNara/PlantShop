/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lthut
 */
public class AddToCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String pid = request.getParameter("pid");
            HttpSession session = request.getSession(true);
            if(session != null){
                HashMap<String,Integer> cart = (HashMap<String,Integer>) session.getAttribute("cart");
                if(cart == null){
                    cart = new HashMap<>();
                    cart.put(pid, 1);
            }
                else{
                    Integer tmp = cart.get(pid);
                    if(tmp==null){
                        cart.put(pid, 1);
                    }
                    else{
                        tmp++;
                        cart.put(pid,tmp);
                    }
                }
                session.setAttribute("cart", cart);
                request.setAttribute("msg","Plant is added to cart.");
//                request.setAttribute("txtsearch", request.getParameter("txtsearch"));
//                request.setAttribute("searchBy", request.getParameter("searchBy"));
                request.getRequestDispatcher("index.jsp").forward(request, response);
//                response.sendRedirect("index.jsp");
            
        }
    }
        return null;
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   

   
    @Override
     public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
