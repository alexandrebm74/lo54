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
        <h1>Liste des sessions disponibles</h1>
        <table>
            <tr>
                <th>Début</th>
                <th>Fin</th>
                <th>Capacité</th>
                <th>Emplacement</th>
            </tr>
            <tr>
                <td>
                    <c:out value="${formation.debut}"/>
                </td>
                <td>
                    <c:out value="${formation.fin}"/>
                </td>
                <td>
                    <c:out value="${formation.capacite}"/>
                </td>
                <td>
                    <c:out value="${formation.emplacement}"/>
                </td>
            </tr>
        </table>
        <form action="/inscription" method="post">
            <input type="submit" value="Sessions disponibles"
                   name="Submit" id="sessions_button" />
        </form>
    </body>
</html>
