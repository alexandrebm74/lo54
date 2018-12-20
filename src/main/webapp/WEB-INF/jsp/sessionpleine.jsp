<%-- 
    Document   : sessionpleine
    Created on : 20 déc. 2018, 17:14:12
    Author     : Anne-Sophie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Session</title>
    </head>
    <body>
        <h1>Il n'y a plus de place disponible pour cette session !</h1>
        <br><br><br>
        <form action="/formations" method="get">
            <input type="submit" value="Accueil" 
                   name="Submit" id="formations_button" />
        </form>
        
    </body>
</html>
