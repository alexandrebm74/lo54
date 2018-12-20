import fr.utbm.entity.Formation;
import fr.utbm.repository.FormationDao;
import fr.utbm.tools.HibernateUtil;
import org.hibernate.Session;

public class maintest {

    public static void main(String args[]) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Formation f = new Formation("LO51", "Administration de BDD");
//        try {
//            FormationDao.ajouter(f, session);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        f.setIntitule("Administration de bases de données");
        FormationDao.sauvegarder(f, session);
        //System.out.println("\n\nFormation : " + FormationDao.chargerFormation("LO54", session).toString());
        //FormationDao.chargerToutesLesFormations(session).stream().forEach(formation -> System.out.println(formation.toString()));
        session.close();
        System.exit(0);
    }
}
