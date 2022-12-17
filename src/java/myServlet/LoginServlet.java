/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import basicObj.Account;
import dbaccess.accountDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lthut
 */
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            String a = request.getContextPath();
//             String b = request.getContentType();
//              String c = request.getServletPath();
//              out.println("<html><body>");
//            out.println("<h2>"+a+"</h2>");
//             out.println("<h2>"+b+"</h2>"); 
//             out.println("<h2>"+c+"</h2>");
//               out.println("</body></html>");

            String email = request.getParameter("txtemail");
            String password = request.getParameter("txtpassword");
            String save = request.getParameter("saveLogin");
            Account account = null;
            try {
                if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if (c != null) {
                        for (Cookie aCookie : c) {
                            if (aCookie.getName().equals("selector")) {
                                token = aCookie.getValue();
                            }
                        }
                    }
                    if (!token.equals("")) {
                        Account acc = accountDao.getAccountByToken(token);
                        if (acc != null) {
                            HttpSession session = request.getSession(true);
                            session.setAttribute("password", acc.getPassword());
                            session.setAttribute("name", acc.getFullname());
                            session.setAttribute("email", acc.getEmail());
                        }
                        if (acc.getRole() == 1) {
                                response.sendRedirect("adminIndex.jsp");
                            }else {
                                response.sendRedirect("mainController?action=viewOrders");
                            }
                    } else {
                        request.setAttribute("error", "You have to login.");
                        request.getRequestDispatcher("loginpage.jsp").forward(request, response);
                    }
                } else {
                    account = accountDao.getAccount(email, password);
                    if (account != null) {
                        HttpSession session = request.getSession(true);
                            if (session != null) {
                                session.setAttribute("name", account.getFullname());
                                session.setAttribute("email", email);
                                session.setAttribute("password", account.getPassword());
                            }
                            if (save != null) {
                                    String token = account.getEmail() + account.getAccid();
                                    accountDao.updateAccountToken(email, token);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 5);
                                    response.addCookie(cookie);
                            }
                        if (account.getRole() == 1) {
                                response.sendRedirect("adminIndex.jsp");
                            }else {
                                response.sendRedirect("mainController?action=viewOrders");
                            }
                    } else {
                        request.setAttribute("error", "Email or password is wrong!");
//                response.sendRedirect("loginpage.html");
                        request.getRequestDispatcher("loginpage.jsp").forward(request, response);
                    }
//            } else {
//               request.setAttribute("error", "email or password is invalid!");
////                response.sendRedirect("loginpage.html");
//            request.getRequestDispatcher("loginpage.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
