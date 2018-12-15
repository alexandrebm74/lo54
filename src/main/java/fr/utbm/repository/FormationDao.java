package fr.utbm.repository;

import fr.utbm.entity.Formation;
import fr.utbm.entity.Lieu;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FormationDao {

    /**
     * Permet d'ajouter une nouvelle formation en base de données
     * @param nouvelleFormation : la formation à ajouter
     * @param session : le session courante Hibernate
     * @throws Exception
     */
    public static void ajouter(Formation nouvelleFormation, Session session) throws Exception {

        if(nouvelleFormation.getCode().length() > 4) {
            throw new Exception("Le code de la formation doit comporter au maximum 4 characters");
        }

        session.beginTransaction();
        session.persist(nouvelleFormation);
        session.getTransaction().commit();
    }

    /**
     * Permet de mettre à jour une formation en base de données à partir d'un objet de type formation.
     * @param formation : l'objet formation à jour
     * @param session : session courante hibernate
     */
    public static void sauvegarder(Formation formation, Session session) {

        session.beginTransaction();
        session.merge(formation);
        session.getTransaction().commit();
    }

    /**
     * Efface l'enregistrement en base de l'objet formation passé en paramètre.
     * @param formation : formation à effacer
     * @param session : session hibernate courante
     */
    public static void delete(Formation formation, Session session) {

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

        return session.get(Formation.class, formationCode);
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
        return query.list();
    }
}
