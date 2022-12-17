<%-- 
    Document   : viewSearchResult
    Created on : May 31, 2022, 11:06:35 PM
    Author     : lthut
--%>

<%@page import="dbaccess.plantDao"%>
<%@page import="basicObj.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plants Searching Result</title>
        <link href='style.css' rel='stylesheet' type='text/css'/>
    </head>
    <body>
           <% ArrayList<Plant> list = (ArrayList)request.getAttribute("ketqua");%>      
        <table class='producttable'>
            <tr><td>Plant ID</td><td>Name</td><td>Price</td><td>Image</td><td>Detail</td><td>Action</td></tr>
            <%  for (Plant plant : list) {%>
            <tr>
                <td><%= plant.getPID()%></td>
                <td><%= plant.getPName()%></td>
                <td><%= plant.getPrice()%></td>
                <td><img src='<%= plant.getImagePath()%>' class='product'/></td>
                <td><a href='#'>View detail</a></td>
                <td><a href='#'>Add to your cart</a></td>
            </tr>
            <%}%>
        </table>
        
</body>
</html>
