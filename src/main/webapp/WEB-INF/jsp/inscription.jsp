<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <form action="/formations" method="get">
            <input type="submit" value="Accueil" 
                   name="Submit" id="formations_button" />
        </form>
        <br><br><br>
        <h1>Inscription à la formation</h1>
        
        <form action="/nouvelutilisateur" method="post">
            Nom: <input type="text" name="nom"><br>
            Prénom: <input type="text" name="prenom"><br>
            Addresse: <textarea rows="4" cols="50" name="addresse"></textarea><br>
            Téléphone: <input type="text" name="telephone"><br>
            Email: <input type="text" name="email"><br>
            <input type="hidden" value="${session_id}"
                                   name="session_id" id="session_id" />
            <input type="submit" value="Valider inscription"
                   name="bouton_validation" id="bouton_validation" />
        </form>
    </body>
</html>
