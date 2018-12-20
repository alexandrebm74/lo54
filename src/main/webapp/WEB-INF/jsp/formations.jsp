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
                <th>Code</th>
                <th>Intitulé</th>
            </tr>
            <c:forEach var="uneFormation" items="${listeFormations}">
                <tr>
                    <td>
                        <c:out value="${uneFormation.code}"/>
                    </td>
                    <td>
                        <c:out value="${uneFormation.intitule}"/>
                    </td>
                    <td>
                        <form action="/sessionsformation" method="post">
                            <input type="submit" value="Sessions disponibles"
                                   name="Submit" id="sessions_button" />
                            <input type="hidden" value="${uneFormation.code}"
                                   name="formation_code" id="formation_code" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><br><br>
        <form action="/formations" method="post">
            Rechercher une formation : <input type="text" name="recherche" id="recherche" /><br>
            Session disponible à : <input list="lieus" name="lieu">
            <datalist id="lieus">
                <c:forEach var="unLieu" items="${listeLieus}">
                    <option value="${unLieu.ville}">
                </c:forEach>
            </datalist><br>
            Session disponible après : <input type="date" id="debutmin" name="debutmin" /><br>
            Session disponible avant : <input type="date" id="finmax" name="finmax" />
            <br><br><br>
            <input type="submit">
        </form>
    </body>
</html>
