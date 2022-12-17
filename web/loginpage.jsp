<%-- 
    Document   : loginpage
    Created on : May 31, 2022, 4:46:39 PM
    Author     : lthut
--%>

<%@page import="java.lang.String"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel='stylesheet' href='style.css' type='text/css'>

    </head>
    <body>
        <header>
            <c:import url="header.jsp"></c:import>
            </header>
            <section>
                <p class="error">${requestScope.error}</p>
            <font style="color:red;">${requestScope.WARNING == NULL ? "" : requestScope.WARNING}</font>
            <div class="container d-flex justify-content-center align-items-center">
                <div >
                    <div >
                        <div class="card">
                            <img src="images/loginImg.jpg"
                                 alt="" class="card-img mb-3" />
                            <div class="card-body">
                                <h4 class="card-title">Login</h4><form action="mainController"  novalidate method="post" class="formlogin">
                                    <div class="mb-3">
                                        <label for="username" >Email</label>
                                        <input class="form-control" type="text" name='txtemail' 
                                               required>

                                    </div>
                                    <div class="mb-3">
                                        <label for="password" >Password</label>
                                        <input type="password" name='txtpassword' class="form-control "  
                                               required>

                                    </div>
                                    <div class="mb-3">
                                        <input type="checkbox" value="saveLogin" name="saveLogin"/> <label for="password" class="form-label">Stayed signed in</label>
                                    </div>
                                    <input type="submit" value="Login" name="action" >
                                    <div>
                                        <p>Do not have an account?  <a href="mainController?action=Register">  Register</a></p>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <footer>
            <c:import url="footer.jsp"></c:import>
        </footer>

    </body>
</html>
