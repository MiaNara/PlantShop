<%-- 
    Document   : plantDetails
    Created on : Jul 12, 2022, 2:27:17 AM
    Author     : lthut
--%>

<%@page import="dbaccess.plantDao"%>
<%@page import="basicObj.Plant"%>
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
            <c:import url="header.jsp"></c:import>
            </header>
            <section>
                <a href="mainController?action=viewCart">Back to cart</a>
            <jsp:useBean id="plant" class="basicObj.Plant" scope="request"/>
            <table class="plantDetails">
                <tr><td rowspan="7"><img src="<jsp:getProperty name="plant" property="imagePath"></jsp:getProperty>"/></td></tr>
                <tr><td>Plant ID:</td><td><jsp:getProperty name="plant" property="PID"></jsp:getProperty></td></tr>
                <tr><td>Name:</td><td><jsp:getProperty name="plant" property="PName"></jsp:getProperty></td></tr>
                <tr><td>Price:</td><td><jsp:getProperty name="plant" property="price"></jsp:getProperty></td></tr>
                <tr><td>Status:</td><td><jsp:getProperty name="plant" property="status"></jsp:getProperty></td></tr>
                <tr><td>Description:</td><td><jsp:getProperty name="plant" property="description"></jsp:getProperty></td></tr>
                <tr><td>Category name:</td><td><jsp:getProperty name="plant" property="catename"></jsp:getProperty></td></tr>
                </table>
            </section>

            <footer>
            <c:import url="footer.jsp"></c:import>
        </footer>
    </body>
</html>
