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
import fr.utbm.repository.SessionDeFormationDao;
import fr.utbm.repository.UtilisateurDao;
import fr.utbm.tools.HibernateUtil;
import org.hibernate.Session;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author abourdillas
 */
@WebServlet(name = "SessionsFormation", urlPatterns = {"/sessionsformation"})
public class SessionsFormation extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Formation formation = FormationDao.chargerFormation(request.getParameter("formation_code"), session);
        //SessionDeFormation sessionFormation = SessionDeFormationDao.chargerToutesLesFormations(session).stream().filter(f -> f.getCours().getCode().equals(request.getParameter("formation_code"))).collect(Collectors.toList());
        request.setAttribute("listesessions", formation.getListeSessions());
        
        System.out.println(formation.getListeSessions().toString());
        
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/sessionsformation.jsp" ).forward( request, response );
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
        return "Servlet sessions d'une formation";
    }

}
