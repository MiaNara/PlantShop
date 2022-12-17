<%-- 
    Document   : header
    Created on : Jun 20, 2022, 2:22:36 PM
    Author     : lthut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='style.css' type='text/css'>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;400&display=swap" rel="stylesheet">
    </head>
    <body>
        <header>

            <nav class="navbar navbar-expand ">
                <div class="container-fluid">
                    <div class="col-2">
                        <a class="navbar-brand" href="#">
                            <img src="images/logo2.png " alt="logo" class="d-inline-block" />
                        </a>
                    </div>

                    <div class="navbar-collapse row col-10" >
                        <div class=" d-flex col-8  ">
                            <ul class="navbar-nav navbar-list">
                                <li class="nav-item col-2">
                                    <a class="nav-link active" aria-current="page"href="index.jsp">Home</a>
                                </li>
                                <c:choose>
                                    <c:when test="${sessionScope.name ne null}">
                                        <li class="nav-item col-2">
                                            <a class="nav-link active" href="mainController?action=viewOrders">Personal page</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav-item col-2">
                                            <a class="nav-link active" aria-current="page"href="loginpage.jsp">Login</a>
                                        </li>
                                        <li class="nav-item col-2">
                                            <a class="nav-link active" href="registerpage.jsp">Register</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>


                                <li class="nav-item col-2">
                                    <a class="nav-link active" href="mainController?action=viewCart">View Cart</a>
                                </li>
                            </ul>
                        </div>

                        <div class="col-4">
                            <form  role="search"class="d-inline-block" action="mainController" method="post">
                                <input class="form-control" type="text" name="txtsearch" placeholder="Search plant" value="${param.txtsearch}">
                                <select name="searchBy" >
                                    <option value="byname">by name</option>
                                    <option value="bycate">by category </option>
                                </select>
                                <input type="submit" value="search" name="action">
                            </form>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
