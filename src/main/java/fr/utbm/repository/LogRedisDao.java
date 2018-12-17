package fr.utbm.repository;

import fr.utbm.entity.Utilisateur;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class LogRedisDao {

    /**
     * Fonction pour l'audit des inscriptions dans la base Redis.
     * DOIT ETRE APPELEE APRES LE DAO PRINCIPAL.
     * @param utilisateur : inscription à sauvegarder dans le base.
     */
    public static void tracerInscription(Utilisateur utilisateur) {

        Jedis jedis = new Jedis();

        //L'utilisation du mode multi de jedis permet de garantir qu'aucun accès concurrent n'aura lieu pendant la
        // transation.
        redis.clients.jedis.Transaction t = jedis.multi();
        t.rpush("logInscription", genererTrace(utilisateur));
        t.exec();
    }

    /**
     * Crée une chaine de character à partir des infos de l'utilisateur
     * pour l'enregistrement de log dans redis.
     * @param utilisateur : inscription que l'on souhaite enregistrer
     * @return une map de type "clé - information" pour redis.
     */
    public static String genererTrace(Utilisateur utilisateur) {

        if(utilisateur == null
                || utilisateur.getSession() == null
                || utilisateur.getSession().getCours() == null
                || utilisateur.getSession().getEmplacement() == null) {
            return new String( new Date().toString() + " : erreur, inscription incomplete");
        }

        String trace = new String("");
        trace = trace.concat(new Date().toString() + " : ");
        trace = trace.concat(utilisateur.getNom() + " ");
        trace = trace.concat(utilisateur.getPrenom() + " ");
        trace = trace.concat("<" + utilisateur.getEmail() + ">");
        trace = trace.concat(" s'est inscrit au cours : "
                + utilisateur.getSession().getCours().getCode());
        trace = trace.concat(" a " + utilisateur.getSession().getEmplacement().getVille()
                + " session du " + utilisateur.getSession().getDebut());
        return trace;
    }
}
