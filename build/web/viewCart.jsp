<%-- 
    Document   : viewCart
    Created on : Jun 30, 2022, 10:29:32 AM
    Author     : lthut
--%>


<%@page import="basicObj.Plant"%>
<%@page import="dbaccess.plantDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <c:import url="header.jsp"/>

        </header>
        <section>
            
           <%
                String name = (String)session.getAttribute("name");
                if(name!=null){
            %>
            <h3>Welcome <%=session.getAttribute("name")%> comeback</h3>
            <h3><a href="mainController?action=logout">Logout</a></h3>
            <h3><a href="mainController?action=viewOrders">Personal page</a></h3>
            <%}%>
            
            <p class="error">${requestScope.WARNING}</p>    
            
             <%
                HashMap <String, Integer> cart = (HashMap)session.getAttribute("cart");
                if (cart != null && cart.size() != 0) {
                    int totalMoney = 0;
                    Set <String> pids = cart.keySet();
            %>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Plant ID</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%for (String pid : pids) {
                            int quantity = cart.get(pid);
                            int PID = Integer.parseInt(pid.trim());
                            Plant plant = plantDao.getPlantByID(PID);

                    %>
                <form action="mainController" method="post">   
                    <tr>
                        <td><img class=" d-block product" src='<%= plant.getImagePath()%>'/></td>
                        <td><input type="hidden" value="<%=pid%>" name="pid"><a href="GetPlantServlet?pid=<%=pid%>"><%=pid%></a> </td>
                        <td><%= plant.getPrice()%></td>
                        <td><input type="number" class="form-control"  max="1000" min="1" pattern="^[1-9]\d*$" value="<%=quantity%>" name="quantity"></td>
                        <td><input  type="submit" value="updateCart" name="action">
                            <input type="submit" value="deleteCart" name="action"></td>
                    </tr>
                </form>
                <%
                                 totalMoney += (plant.getPrice()) * quantity;
                             }%>

                </tbody>           
            </table>
            <p>Total money: <%= totalMoney%> </p>
            <p>Order Date: <%=(new Date()).toString()%></p>
            <p>Ship Date: N/A</p>

            <section>
                <form action="mainController" method="post">
                    <input type="submit" value="Save Order" name="action" class="submitorder">
                </form>
            </section>
            <% } else {   %>
            <h5>Your cart is empty</h5>
            <% }%>

        </section>
         <footer>
                    <c:import url="footer.jsp"></c:import>
                </footer>
    </body>
</html>
