<%-- 
    Document   : changeprofilepage
    Created on : Jun 27, 2022, 9:47:56 PM
    Author     : lthut
--%>

<%@page import="basicObj.Account"%>
<%@page import="dbaccess.accountDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <h3>Hello ${sessionScope.email}! Update your profile</h3>
                    <form action="UpdateServlet" method="post">
                        <table>
                            <tr><td>Full name</td><td>Phone</td></tr>
                            <tr><td><input value="${requestScope.acc.getFullname()}" type="text" name="newName" title="Name only contains letters" pattern="^[a-zA-Z\s,.'-]+$"></td>
                                <td><input value="${requestScope.acc.getPhone()}" type="number" name="newPhone" title="Phone must start with 0 and have 9 numbers" pattern="[0]\\d{9}"></td>
                            </tr>
                            <tr><td><input type="submit" value="Update information"></td></tr>
                        </table>
                    </form>
                </section>
                <footer>
                    <c:import url="footer.jsp"/>
                </footer>
            </c:when>
            <c:otherwise>
                <p><font color="red">You must login to view personal page</font></p>
                </c:otherwise>
            </c:choose>    

    </body>
</html>
