<%-- 
    Document   : searchpage.jsp
    Created on : May 31, 2022, 5:01:53 PM
    Author     : lthut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <form action="searchServlet" method="post">
                <input type="text" name="txtsearch">
                <!--                            <select>
                                                <option value="byname">by name</option>
                                                <option value="bycate">by category </option>
                                            </select>-->
                <input type="submit" value="search" name="action">
            </form>
          
        </div>
    </body>
</html>
