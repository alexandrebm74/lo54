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
import java.text.ParseException;
import java.util.ArrayList;
import org.hibernate.Session;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import java.text.SimpleDateFormat;  
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abourdillas
 */
@WebServlet(name = "Formations", urlPatterns = {"/formations"})
public class Formations extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
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
        
        System.out.println("\n\n\n\nCOUCOU\n\n\n\n");
        System.out.println(request.getParameter("debutmin"));
        System.out.println(request.getParameter("finmax"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        if (request.getParameter("debutmin")!=null && !request.getParameter("debutmin").equals(""))
        {
            String debutminString = request.getParameter("debutmin");
            Date debutmin = dateFormat.parse(debutminString);
            List<Formation> listeFormationsTriee = new ArrayList<>();
            for (Formation formation : listeFormations)
            {
                boolean conserver = false;
                for (SessionDeFormation sessionDeFormation : formation.getListeSessions())
                {
                    if (sessionDeFormation.getDebut().after(debutmin))
                        conserver = true;
                }
                
                if (conserver)
                    listeFormationsTriee.add(formation);
            }
            listeFormations = listeFormationsTriee;
        }
        
        if (request.getParameter("finmax")!=null && !request.getParameter("finmax").equals(""))
        {
            String finmaxString = request.getParameter("debutmin");
            Date finmax = dateFormat.parse(finmaxString);
            List<Formation> listeFormationsTriee = new ArrayList<>();
            for (Formation formation : listeFormations)
            {
                boolean conserver = false;
                for (SessionDeFormation sessionDeFormation : formation.getListeSessions())
                {
                    if (sessionDeFormation.getFin().before(finmax))
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Formations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(Formations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Servlet formations";
    }

}
