<%-- 
    Document   : header_loginedUser.jsp
    Created on : Jun 22, 2022, 11:58:27 PM
    Author     : lthut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="style.css" type="text/css">
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
                    <div class="col-1">
                        <a class="navbar-brand" href="#">
                            <img src="images/logo2.png " alt="logo" class="d-inline-block" />
                        </a>
                    </div>
                    <div class="navbar-collapse row col-10" >
                        <div class=" d-flex col-7 ">
                            <ul class="navbar-nav navbar-list">
                                <li class="nav-item col-2">
                                    <a class="nav-link active" aria-current="page"href="index.jsp">Home</a>
                                </li>
                                <li class="nav-item col-2">
                                    <a class="nav-link active" aria-current="page"<a href="mainController?action=updateAccount">Change profile</a>
                                </li>
                                <li class="nav-item col-2">
                                    <a class="nav-link active" aria-current="page"href="mainController?action=viewOrders&type=1">Processing orders</a>
                                </li>
                                <li class="nav-item col-2">
                                    <a class="nav-link active" href="mainController?action=viewOrders&type=2">Completed orders</a>
                                </li>
                                <li class="nav-item col-2">
                                    <a class="nav-link active" href="mainController?action=viewOrders&type=3">Canceled orders</a></li>
                                </li>
                            </ul>
                        </div>
                        <div class="col-4">
                            <form class="d-flex dateForm"  action="mainController" method="post"> 
                                From <input type="date" name="from" class="form-control" placeholder="yyyy-mm-dd"> 
                                to  <input type="date" name="to" class="form-control" placeholder="yyyy-mm-dd">
                                <input type="submit" value="searchOrderByDate" name="action">
                            </form>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
