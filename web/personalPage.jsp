<%-- 
    Document   : personalPage
    Created on : Jun 23, 2022, 8:40:42 AM
    Author     : lthut
--%>

<%@page import="basicObj.Account"%>
<%@page import="dbaccess.accountDao"%>
<%@page import="basicObj.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dbaccess.orderDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${sessionScope.name != null && !sessionScope.name.isEmpty()}">
                <header>
                    <c:import url="header_loginedUser.jsp"></c:import>
                </header>
                <section>
<!--                    <h3>Welcome ${sessionScope.name} come back</h3>-->
                    <h4><a style="float: right" href="mainController?action=logout">Logout</a></h4>
                </section>
                <section>
                    <% String msg = (String) request.getAttribute("updateStatus");
                        if (msg != null) {%>
                    <h1><%=msg%>
                        <%}%>
                </section>
                <section>
                    <c:choose>
                        <c:when test="${requestScope.list != null && !requestScope.list.isEmpty()}">
                            <% pageContext.setAttribute("status", new String[]{"", "processing", "completed", "canceled"}, pageContext.SESSION_SCOPE);%>
                            <h2>Your orders</h2>
                            <c:forEach var="ord" items="${requestScope.list}">
                                <table class="order">
                                    <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Order's status</td><td>Action</td></tr>
                                    <tr>
                                        <td>${ord.orderID}</td>
                                        <td>${ord.orderDate}</td>
                                        <td>${ord.shipDate eq null?"null": ord.shipDate}</td>
                                        <td>
                                            <p>${status[ord.status]}</p>
                                        <c:if test="${ord.status == 1}">
                                            <a href="mainController?action=cancel&ordID=${ord.orderID}">Cancel Order</a>
                                        </c:if>
                                        <c:if test="${ord.status  == 3}">
                                            <a href="mainController?action=orderAgain&ordID=${ord.orderID}">Order Again</a>
                                        </c:if>
                                            </td>
                                        <td><a href="orderdetail.jsp?orderID=${ord.orderID}">View details</a></td>
                                    </tr>
                                </table>
                            </c:forEach>    
                        </c:when>
                        <c:otherwise>
                            <h2>You don't have any order</h2>
                        </c:otherwise>
                    </c:choose>
                </section>
                <footer>
                    <c:import url="footer.jsp"></c:import>
                </footer>
            </c:when>
            <c:otherwise>
                <p><font color="red">You must login to view personal page</font></p>
                </c:otherwise>
            </c:choose>     

    </body>
</html>
