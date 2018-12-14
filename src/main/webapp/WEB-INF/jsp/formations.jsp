<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formations</title>
        <style>
            table {
              font-family: arial, sans-serif;
              border-collapse: collapse;
              width: 100%;
            }

            td, th {
              border: 1px solid #dddddd;
              text-align: left;
              padding: 8px;
            }

            tr:nth-child(even) {
              background-color: #dddddd;
            }
        </style>
    </head>
    <body>
        <h1>Liste des formations disponibles</h1>
        <table>
            <tr>
                <th>Intitulé</th>
            </tr>
            <c:forEach var="uneFormation" items="${sessionScope.listeFormations}">
                <tr>
                    <td>
                        <c:out value="${uneFormation.intitule}"/>
                    </td>
                    <td>
                        <form action="/sessionsformation" method="post">
                            <input type="submit" value="Sessions disponibles"
                                   name="Submit" id="sessions_button" />
                            <input type="hidden" value="${uneFormation}"
                                   name="formation" id="formation_bean" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
