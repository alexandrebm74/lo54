/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.utbm.entity.*;
import fr.utbm.repository.FormationDao;
import fr.utbm.repository.LieuDao;
import fr.utbm.tools.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author abourdillas
 */
@WebServlet(name = "Formations", urlPatterns = {"/formations"})
public class Formations extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Formation> listeFormations = FormationDao.chargerToutesLesFormations(session);
        
        if (request.getParameter("recherche")!=null && !request.getParameter("recherche").equals(""))
        {
            List<Formation> listeFormationsTriee = new ArrayList<>();
            for (Formation formation : listeFormations)
            {
                if (formation.getIntitule().contains(request.getParameter("recherche")))
                        listeFormationsTriee.add(formation);
            }
            
            listeFormations = listeFormationsTriee;
        }
        
        if (request.getParameter("lieu")!=null && !request.getParameter("lieu").equals(""))
        {
            List<Formation> listeFormationsTriee = new ArrayList<>();
            for (Formation formation : listeFormations)
            {
                boolean conserver = false;
                for (SessionDeFormation sessionDeFormation : formation.getListeSessions())
                {
                    if (sessionDeFormation.getEmplacement().getVille().equals(request.getParameter("lieu")))
                        conserver = true;
                }
                
                if (conserver)
                    listeFormationsTriee.add(formation);
            }
            listeFormations = listeFormationsTriee;
        }
        
        System.out.println(listeFormations.toString());
        
        request.setAttribute("listeFormations", listeFormations);
        
        List<Lieu> listeLieus = LieuDao.chargerTousLesLieux(session);
        request.setAttribute("listeLieus", listeLieus);
                
        this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/formations.jsp" ).forward( request, response );
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Servlet formations";
    }

}
