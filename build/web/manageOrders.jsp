<%-- 
    Document   : manageOrders
    Created on : Jul 18, 2022, 3:16:50 PM
    Author     : lthut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <section>
            <c:if test="${requestScope.error != null}"><h1>${requestScope.error}</h1></c:if>
                <li><form action="mainController" method="post">From<input type="date" name="from" placeholder="yyyy-mm-dd"> to <input type="date" name="to" placeholder="yyyy-mm-dd">
                        <input type="submit" value="searchOrderByDate" name="action"></form></li>
                <table class="order order2">
                    <tr>
                        <th>ID</th>
                        <th>Order date</th>   
                        <th>Ship date</th>
                        <th>Status</th>
                        <th>Account ID</th>
                        <th>Action</th>
                    </tr>
                <c:forEach var="ord" items="${requestScope.ordersList}">
                    <tr>
                        <td><c:out value="${ord.getOrderID()}"></c:out></td>
                        <td><c:out value="${ord.getOrderDate()}"></c:out></td>
                        <td><c:out value="${ord.getShipDate() eq null?"null":ord.getShipDate()}"></c:out></td>
                            <td>
                            <c:choose>
                                <c:when test="${ord.status eq 1}">Processing</c:when>
                                <c:when test="${ord.status eq 2}">Completed</c:when>
                                <c:otherwise>Canceled</c:otherwise>
                            </c:choose>
                        </td>
                        <td><c:out value="${ord.accID}"></c:out></td>
                            <td>
                            <c:if test="${ord.status eq 1}">
                                <div>
                                    
                               
                                <form action="mainController">
                                    <input type="hidden" name="ordID" value="${ord.orderID}">
                                    <input type="submit"name="action" value="Delivered">
                                </form>
                                <form action="mainController">
                                    <input type="hidden" name="ordID" value="${ord.orderID}">
                                    <input type="submit"name="action" value="cancel">
                                </form>
                                     </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        <footer>
            <c:import url="footer.jsp"></c:import>
        </footer>
    </body>
</html>
