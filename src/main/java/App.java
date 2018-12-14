

import fr.utbm.entity.Movie;
import fr.utbm.tools.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import fr.utbm.repository.*;

import java.util.List;

/**
 */
public class App {

	public static void main(String[] args) {
            
            FormationDao formationdao = FormationDao.getInstance();
            
            
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            
            Query q = session.createQuery("from Movie");
            List<Movie> movies = q.list();
            for (Movie movie : movies) {
                System.err.println(movie.getTitle() + " " + movie.getMainActor().getFirstname());
            }
            session.getTransaction().commit();
            session.close();
            System.exit(0);
	}
}
