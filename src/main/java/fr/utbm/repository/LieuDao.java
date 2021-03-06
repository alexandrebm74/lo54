package fr.utbm.repository;

import fr.utbm.entity.Lieu;
import org.hibernate.Session;

import java.util.List;

public class LieuDao {

    /**
     * Permet d'enregistrer un objet lieu en base de données
     * @param lieu : objet à enregistrer
     * @param session : session courante hibernate
     */
    public static void ajouter(Lieu lieu, Session session) throws Exception {

        if(lieu == null) {
            throw new Exception("Erreur : lieu inexistant, " +
                    "l'élement ne peut pas être ajouté en base de données");
        }

        if(lieu.getId() > 0){
            throw new Exception("Erreur : l'identifiant du lieu est déjà présent en base de données");
        }

        session.beginTransaction();
        session.persist(lieu);
        session.getTransaction().commit();
    }

    /**
     * Permet de mettre à jour un enregistrement en base de données à partir d'un objet lieu.
     * @param lieu : objet lieu à jour
     * @param session : session hibernate courante
     */
    public static void sauvegarder(Lieu lieu, Session session) throws Exception {

        if(lieu == null) {
            throw new Exception("Erreur : lieu inexistant, " +
                    "l'élement ne peut pas être sauvegardé en base de données");
        }

        if(lieu.getId() == 0){
            throw new Exception("Erreur : Id inconnu en base de données");
        }

        session.beginTransaction();
        session.merge(lieu);
        session.getTransaction().commit();
    }

    /**
     * Efface l'enregistrement en base de l'objet lieu passé en paramètre.
     * @param lieu : lieu à effacer
     * @param session : session hibernate courante
     */
    public static void delete(Lieu lieu, Session session) throws Exception {

        if(lieu == null) {
            throw new Exception("Erreur : lieu inexistant, " +
                    "l'élement ne peut pas être supprimé de la base de données");
        }

        if(lieu.getId() == 0){
            throw new Exception("Erreur : Id inconnu en base de données");
        }

        session.beginTransaction();
        session.delete(lieu);
        session.getTransaction().commit();
    }

    /**
     * Récupère l'enregistrement de lieu en base de donnée pour l'id passé en paramètre.
     * @param idLieu : id du lieu à récupérer
     * @param session : session hibernate courante
     * @return l'objet lieu correspondant
     */
    public static Lieu chargerLieu(int idLieu, Session session) {

        Lieu lieu = (Lieu)session.get(Lieu.class, idLieu);
        return lieu;
    }

    /**
     * Récupère l'ensemble des enregistrements de la table lieu sous forme de liste d'objet lieu.
     * @param session : session courante hibernate
     * @return la liste complète des objets lieu
     */
    public static List<Lieu> chargerTousLesLieux(Session session) {

        String s = new String("from Lieu");
        return session.createQuery(s).list();
    }
}
