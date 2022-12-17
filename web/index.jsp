<%-- 
    Document   : index
    Created on : Jun 20, 2022, 2:26:38 PM
    Author     : lthut
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.io.File"%>
<%@page import="dbaccess.plantDao"%>
<%@page import="basicObj.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <% String msg = (String) request.getAttribute("msg");
                if (msg != null) {%>
            <script> alert("<%= msg%>");</script>
            <%}%>
            <%

                String keyword = request.getParameter("txtsearch");
                String searchBy = request.getParameter("searchBy");
                ArrayList<Plant> list = null;
                if (keyword == null || searchBy == null || keyword.equals("null") || searchBy.equals("null")) {
                    list = plantDao.getAllPlants();
                } else {
                    list = plantDao.getPlant(keyword, searchBy);
                }
                String[] tmp = {"out of stock", "available"};
                if (list != null && !list.isEmpty()) {

            %> 

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Image</th>
                        <th scope="col">Plant ID</th>
                        <th scope="col">Name</th>
                        <th scope="col">Price</th>

                        <th scope="col">Status</th>
                        <th scope="col">Category name</th>
                        <th scope="col">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%  for (Plant plant : list) {%>
                    <tr>

                        <td><img class=" d-block product" src='<%= plant.getImagePath()%>'/></td>
                        <th scope="row"><%= plant.getPID()%></th>

                        <td><%= plant.getPName()%></td>
                        <td><%= plant.getPrice()%></td>

                        <td><%= tmp[plant.getStatus()]%></td>
                        <td><%= plant.getCatename()%></td>
                        <td><a class="link-danger" href='mainController?txtsearch=<%=keyword%>&searchBy=<%=searchBy%>&action=addToCart&pid=<%= plant.getPID()%>'>Add to your cart</a></td>
                    </tr>
                </tbody>
                <%}%>
            </table>
            <%} else {%>
            <h2>Not found plant.</h2>
            <%}%>

        </section>

        <footer>
            <c:import url="footer.jsp"></c:import>
        </footer>
    </body>
</html>
<%-- 
    Document   : index
    Created on : Jun 14, 2022, 4:44:00 PM
    Author     : lthut
--%>

