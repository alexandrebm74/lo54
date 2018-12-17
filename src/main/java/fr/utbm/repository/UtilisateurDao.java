package fr.utbm.repository;

import fr.utbm.entity.Utilisateur;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class UtilisateurDao {

    /**
     * Permet d'enregistrer une nouvelle inscription à une formation (sous forme de bean Utilisateur) en base de données
     * @param utilisateur : l'utilisateur à ajouter
     * @param session : la session courante Hibernate
     */
    public static void ajouter(Utilisateur utilisateur, Session session) throws Exception {

        if(utilisateur == null
                || utilisateur.getSession() == null
                || utilisateur.getSession().getCours() == null
                || utilisateur.getSession().getEmplacement() == null) {
            throw new Exception("Erreur : utilisateur incomplet, " +
                    "l'élement ne peut pas être ajouté en base de données");
        }

        if(utilisateur.getId() > 0) {
            throw new Exception("Erreur : l'identifiant de l'utilisateur est déjà présent en base de données");
        }

        session.beginTransaction();
        session.persist(utilisateur);
        session.getTransaction().commit();
    }

    /**
     * Permet de mettre à jour un utilisateur en base de données à partir d'un objet de type Utilisateur.
     * @param utilisateur : l'objet java Utilisateur à jour
     * @param session : session courante hibernate
     */
    public static void sauvegarder(Utilisateur utilisateur, Session session) throws Exception {

        if(utilisateur == null
                || utilisateur.getSession() == null
                || utilisateur.getSession().getCours() == null
                || utilisateur.getSession().getEmplacement() == null) {
            throw new Exception("Erreur : utilisateur incomplet, " +
                    "l'élement ne peut pas être sauvegardé en base de données");
        }

        if(utilisateur.getId() == 0) {
            throw new Exception("Erreur : Id inconnu en base de données");
        }

        session.beginTransaction();
        session.merge(utilisateur);
        session.getTransaction().commit();
    }

    /**
     * Efface l'enregistrement en base de l'objet java Utilisateur passé en paramètre.
     * @param utilisateur : Utilisateur à effacer
     * @param session : session hibernate courante
     */
    public static void delete(Utilisateur utilisateur, Session session) throws Exception {

        if(utilisateur == null) {
            throw new Exception("Erreur : utilisateur incomplet, " +
                    "l'élement ne peut pas être supprimé de la base de données");
        }

        if(utilisateur.getId() == 0) {
            throw new Exception("Erreur : Id inconnu en base de données");
        }

        session.beginTransaction();
        session.delete(utilisateur);
        session.getTransaction().commit();
    }

    /**
     * Permet de charger un objet Utilisateur depuis la base à l'aide de son id.
     * @param utilisateurId : id de l'utilisateur que l'on veut charger
     * @param session : session courante d'hibernate
     * @return l'objet java Utilisateur correspondant
     */
    public static Utilisateur chargerUtilisateur(int utilisateurId, Session session) {

        return session.get(Utilisateur.class, utilisateurId);
    }

    /**
     * Permet de récupérer une liste d'objets java Utilisateur à partir de toutes les
     * utilisateur présents en base.
     * @param session : session courante d'hibernate
     * @return une liste complète de toutes les utilisateur présents en base
     */
    public static List<Utilisateur> chargerTousLesUtilisateurs(Session session) {

        String s = new String("from Utilisateur");
        Query query = session.createQuery(s);
        return query.list();
    }
}
