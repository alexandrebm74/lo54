<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <h1>Inscription à la formation</h1>
        
        <form action="/inscription" method="post">
            <input type="submit" value="Sessions disponibles"
                   name="Submit" id="sessions_button" />
        </form>
    </body>
</html>
