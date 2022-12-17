<%-- 
    Document   : register
    Created on : Jun 20, 2022, 2:34:29 PM
    Author     : lthut
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <header>
            <%@include file="header.jsp" %>
        </header>
         <section>
             <p class="error">${requestScope.error}</p>
       
              <div class="container d-flex justify-content-center align-items-center">
                <div >
                    <div >
                        <div class="card">
                            <img src="images/loginImg.jpg"
                                 alt="" class="card-img mb-3" />
                            <div class="card-body">
                                <h4 class="card-title">Register</h4>
                                <form action="mainController" method="POST" >
                                    <div class="mb-3">
                                        <label for="username" >Email</label>
                                        <input class="form-control"  type="text" name="txtemail" required="" value="${requestScope.txtemail eq null?"":requestScope.txtemail}" title="Mail is invalid" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="password">Password</label>
                                       <input class="form-control" type="password" name="txtpassword" required="" value="${requestScope.txtpassword eq null?"":requestScope.txtpassword}"/>
                                    </div>
                                        <div class="mb-3">
                                        <label for="password">Fullname</label>
                                       <input class="form-control" type="text" name="txtfullname" required="" value="${requestScope.txtfullname eq null?"":requestScope.txtfullname}" pattern="^[a-zA-Z\s,.'-]+$"/>
                                    </div>
                                        <div class="mb-3">
                                        <label for="password">Phone</label>
                                       <input class="form-control" type="text" name="txtphone" required="" value="${requestScope.txtphone eq null?"":requestScope.txtphone}" title="Phone is invalid" pattern="[0]\d{9}" />
                                    </div>
                                    <div>
                                        <input type="submit" value="Register" name="action"/>
                                    </div>
                                </form>
                                    <div>
                                        <p>Do you have an account already?  <a href="mainController?action=Login"> Login</a></p>
                                    </div>
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
