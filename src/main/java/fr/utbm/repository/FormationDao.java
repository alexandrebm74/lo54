package fr.utbm.repository;

import fr.utbm.entity.Formation;
import fr.utbm.entity.SessionDeFormation;
import fr.utbm.repository.SessionDeFormationDao;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FormationDao {

    /**
     * Permet d'ajouter une nouvelle formation en base de données
     * @param formation : la formation à ajouter
     * @param session : le session courante Hibernate
     * @throws Exception
     */
    public static void ajouter(Formation formation, Session session) throws Exception {

        if(formation == null) {
            throw new Exception("Erreur : formation inexistante, " +
                    "l'élement ne peut pas être ajouté en base de données");
        }

        if(chargerLesCodesDeFormation(session).contains(formation.getCode())) {
            throw new Exception("Erreur : ce code de formation est déjà présent en base de données");
        }

        session.beginTransaction();
        session.persist(formation);
        session.getTransaction().commit();
    }

    /**
     * Permet de mettre à jour une formation en base de données à partir d'un objet de type formation.
     * @param formation : l'objet formation à jour
     * @param session : session courante hibernate
     */
    public static void sauvegarder(Formation formation, Session session) throws Exception {

        if(formation == null) {
            throw new Exception("Erreur : formation inexistante, " +
                    "l'élement ne peut pas être sauvegardé en base de données");
        }

        if(!chargerLesCodesDeFormation(session).contains(formation.getCode())) {
            throw new Exception("Erreur : code inconnu en base de données");
        }

        session.beginTransaction();
        session.merge(formation);
        session.getTransaction().commit();
    }

    /**
     * Efface l'enregistrement en base de l'objet formation passé en paramètre.
     * @param formation : formation à effacer
     * @param session : session hibernate courante
     */
    public static void delete(Formation formation, Session session) throws Exception {

        if(formation == null) {
            throw new Exception("Erreur : formation inexistante, " +
                    "l'élement ne peut pas être supprimé de la base de données");
        }

        if(!chargerLesCodesDeFormation(session).contains(formation.getCode())) {
            throw new Exception("Erreur : code inconnu en base de données");
        }

        session.beginTransaction();
        session.delete(formation);
        session.getTransaction().commit();
    }

    /**
     * Permet de charger un objet formation à partir depuis la base avec son code.
     * @param formationCode : code de la formation que l'on veut charger
     * @param session : session courante d'hibernate
     * @return l'objet formation correspondant
     */
    public static Formation chargerFormation(String formationCode, Session session) {
        
        Formation formation = (Formation)session.get(Formation.class, formationCode);
        setNbInscrits(session, formation);
        return formation;
    }

    /**
     * Permet de récupérer une liste d'objets formation à partir de toutes les
     * formations présentes en base.
     * @param session : session courante d'hibernate
     * @return une liste complète de toutes les formations présentes en base
     */
    public static List<Formation> chargerToutesLesFormations(Session session) {

        String s = new String("from Formation");
        Query query = session.createQuery(s);
        List<Formation> listeFormations = query.list();
        
        for (Formation formation : listeFormations)
            setNbInscrits(session, formation);
        
        return listeFormations;
    }
    
    public static void setNbInscrits(Session session, Formation formation)
    {
        for (SessionDeFormation sessionDeFormation : formation.getListeSessions())
        {
            SessionDeFormationDao.setNbInscrits(session, sessionDeFormation);
        }
    }

    /**
     * Permet de récupérer sous forme de liste l'ensemble des codes de formations présent en base.
     * @param session : session courante Hibernate
     * @return la liste exhaustive des codes des formations présentes en base de données
     */
    public static List<String> chargerLesCodesDeFormation(Session session) {
        String s = new String("SELECT F.code FROM Formation F");
        Query query = session.createQuery(s);
        return query.list();
    }
}
