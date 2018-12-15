package fr.utbm.repository;

import fr.utbm.entity.SessionDeFormation;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SessionDeFormationDao {

    /**
     * Permet d'enregistrer une nouvelle session de formation en base de données
     * @param sessionDeFormation : la formation à ajouter
     * @param session : la session courante Hibernate
     */
    public static void ajouter(SessionDeFormation sessionDeFormation, Session session) {

        session.beginTransaction();
        session.persist(sessionDeFormation);
        session.getTransaction().commit();
    }

    /**
     * Permet de mettre à jour une session de formation en base de données à partir d'un objet de type SessionDeFormation.
     * @param sessionDeFormation : l'objet java session de formation à jour
     * @param session : session courante hibernate
     */
    public static void sauvegarder(SessionDeFormation sessionDeFormation, Session session) {

        session.beginTransaction();
        session.merge(sessionDeFormation);
        session.getTransaction().commit();
    }

    /**
     * Efface l'enregistrement en base de l'objet java session de formation passé en paramètre.
     * @param sessionDeFormation : session de formation à effacer
     * @param session : session hibernate courante
     */
    public static void delete(SessionDeFormation sessionDeFormation, Session session) {

        session.beginTransaction();
        session.delete(sessionDeFormation);
        session.getTransaction().commit();
    }

    /**
     * Permet de charger un objet session de formation depuis la base à l'aide de son id.
     * @param sessionId : id de la session de formation que l'on veut charger
     * @param session : session courante d'hibernate
     * @return l'objet java session de formation correspondant
     */
    public static SessionDeFormation chargerSession(int sessionId, Session session) {

        return session.get(SessionDeFormation.class, sessionId);
    }

    /**
     * Permet de récupérer une liste d'objets java session de formation à partir de toutes les
     * sessions présentes en base.
     * @param session : session courante d'hibernate
     * @return une liste complète de toutes les sessions de formations présentes en base
     */
    public static List<SessionDeFormation> chargerToutesLesFormations(Session session) {

        String s = new String("from SessionDeFormation");
        Query query = session.createQuery(s);
        return query.list();
    }
}
