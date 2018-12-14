
package fr.utbm.repository;

import fr.utbm.entity.Formation;
import java.util.ArrayList;

public class FormationDao {
    
    private static FormationDao instance = null;
    private ArrayList<Formation> listeFormations = null;
    
    private FormationDao()
    {
        listeFormations = new ArrayList<>();
    }
    
    public static FormationDao getInstance()
    {
        if (instance == null)
            instance = new FormationDao();
        
        return instance;
    }
    
    public void addFormation(Formation formation)
    {
        listeFormations.add(formation);
    }

    public ArrayList<Formation> getListeFormations() {
        return listeFormations;
    }
}
