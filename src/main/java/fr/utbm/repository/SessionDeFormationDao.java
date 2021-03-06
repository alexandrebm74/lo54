package fr.utbm.repository;

import fr.utbm.entity.SessionDeFormation;
import fr.utbm.repository.UtilisateurDao;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class SessionDeFormationDao {

    /**
     * Permet d'enregistrer une nouvelle session de formation en base de données
     * @param sessionDeFormation : la formation à ajouter
     * @param session : la session courante Hibernate
     */
    public static void ajouter(SessionDeFormation sessionDeFormation, Session session) throws Exception {

        if(sessionDeFormation == null
                || sessionDeFormation.getCours() == null
                || sessionDeFormation.getEmplacement() == null) {
            throw new Exception("Erreur : session de formation incomplète, " +
                    "l'élement ne peut pas être ajouté en base de données");
        }

        if(sessionDeFormation.getId() > 0) {
            throw new Exception("Erreur : l'identifiant de la session de formation est déjà présent en base de données");
        }

        session.beginTransaction();
        session.persist(sessionDeFormation);
        session.getTransaction().commit();
    }

    /**
     * Permet de mettre à jour une session de formation en base de données à partir d'un objet de type SessionDeFormation.
     * @param sessionDeFormation : l'objet java session de formation à jour
     * @param session : session courante hibernate
     */
    public static void sauvegarder(SessionDeFormation sessionDeFormation, Session session) throws Exception {

        if(sessionDeFormation == null
                || sessionDeFormation.getCours() == null
                || sessionDeFormation.getEmplacement() == null) {
            throw new Exception("Erreur : session de formation incomplète, " +
                    "l'élement ne peut pas être sauvegardé en base de données");
        }

        if(sessionDeFormation.getId() == 0) {
            throw new Exception("Erreur : Id inconnu en base de données");
        }

        session.beginTransaction();
        session.merge(sessionDeFormation);
        session.getTransaction().commit();
    }

    /**
     * Efface l'enregistrement en base de l'objet java session de formation passé en paramètre.
     * @param sessionDeFormation : session de formation à effacer
     * @param session : session hibernate courante
     */
    public static void delete(SessionDeFormation sessionDeFormation, Session session) throws Exception {

        if(sessionDeFormation == null
                || sessionDeFormation.getCours() == null
                || sessionDeFormation.getEmplacement() == null) {
            throw new Exception("Erreur : session de formation incomplète, " +
                    "l'élement ne peut pas être supprimé de la base de données");
        }

        if(sessionDeFormation.getId() == 0) {
            throw new Exception("Erreur : Id inconnu en base de données");
        }

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

        SessionDeFormation sessionDeFormation = (SessionDeFormation)session.get(SessionDeFormation.class, sessionId);
        setNbInscrits(session, sessionDeFormation);
        return sessionDeFormation;
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
        List<SessionDeFormation> sessionsDeFormation = query.list();
        
        for (SessionDeFormation sessionDeFormation : sessionsDeFormation)
        {
            setNbInscrits(session, sessionDeFormation);
        }
        
        return sessionsDeFormation;
    }
    
    public static void setNbInscrits(Session session, SessionDeFormation sessionDeFormation)
    {
         sessionDeFormation.setNbInscrits(UtilisateurDao.nbInscritsSessionDeFormation(session, sessionDeFormation.getId()));
    }
}
