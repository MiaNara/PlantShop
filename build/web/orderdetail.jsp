<%-- 
    Document   : orderdetail
    Created on : Jun 24, 2022, 4:34:41 PM
    Author     : lthut
--%>

<%@page import="basicObj.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbaccess.orderDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <c:import url="header_loginedUser.jsp"></c:import>
            </header>
            <section>
                <h3><a href="mainController?action=logout">Logout</a></h3>
                <a href="mainController?action=viewOrders">View all orders</a>
                <link rel="stylesheet" href="style.css" type="text/css">
            </section>
            <section>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Order ID</th>
                            <th scope="col">Plant ID</th>
                            <th scope="col">Plant Name</th>
                            <th scope="col1">Image</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%String temp = request.getParameter("orderID");
                        if (temp != null) {
                            int orderID = Integer.parseInt(temp.trim());
                            ArrayList<OrderDetail> list = orderDao.getOrderDetail(orderID);
                            if (list != null || !list.isEmpty()) {
                                int money = 0;
                                for (OrderDetail detail : list) {%>    
                    <tr>

                        <td><%=detail.getOrdderID()%></td>
                        <td><%=detail.getPlantID()%></td>
                        <td><%=detail.getPlantName()%></td>
                        <td><img class=" d-block product" src='<%=detail.getImgPath()%>'/></td>
                        <td><%=detail.getPrice()%></td>
                        <td><%=detail.getQuantity()%></td>
                        <% money = money + detail.getPrice() * detail.getQuantity(); %>
                    </tr>

                    <%}%>
                </tbody>
            </table>
            <h3> Total money: <%= money%></h3>
            <% } else {%>
            <p>You don't have any order </p>
            <%   }
                }%>
        </section>
        <footer>
            <c:import url="footer.jsp"></c:import>
        </footer>
    </body>
</html>
