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
            
            #barre {
                width: 100%;
                background-color: #00ff00;
                text-align: center;
                color: black;
                display: inlineblock;
                line-height: 30px;
                color: white;
            }
                
            #remplissage {
                height: 30px;
                background-color: #ff0000;
                display: inline;
            }
        </style>
    </head>
    <body>
        <form action="/formations" method="get">
            <input type="submit" value="Accueil" 
                   name="Submit" id="formations_button" />
        </form>
        <br><br><br>
        <h1>Liste des sessions disponibles</h1>
        <table>
            <tr>
                <th>Début</th>
                <th>Fin</th>
                <th>Remplissage</th>
                <th>Lieu</th>
            </tr>
            <c:forEach var="session" items="${listesessions}">
                <tr>
                    <td>
                        <c:out value="${session.debut.toString().substring(0,10)}"/>
                    </td>
                    <td>
                        <c:out value="${session.fin.toString().substring(0,10)}"/>
                    </td>
                    <td style="text-align: center;
                                line-height: 30px;
                                color: black;">
                        ${session.nbInscrits}/${session.capacite} inscrits
                        <div id="barre">
                            <div id="remplissage" style="width: ${session.tauxRemplissage}%"></div>
                            ${session.tauxRemplissage} %
                            </div>
                    </td>
                    <td>
                        <c:out value="${session.emplacement.ville}"/>
                    </td>
                    <td>
                        <form action="/inscription" method="post">
                            <input type="submit" value="S'inscrire"
                                   name="Submit" id="sessions_button" />
                            <input type="hidden" value="${session.cours}"
                                   name="formation_code" id="formation_code" />
                            <input type="hidden" value="${session.id}"
                                   name="session_id" id="session_id" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
