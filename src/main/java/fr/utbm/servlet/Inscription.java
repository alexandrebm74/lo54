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
import fr.utbm.repository.SessionDeFormationDao;
import fr.utbm.tools.HibernateUtil;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import org.hibernate.Session;

/**
 *
 * @author abourdillas
 */
@WebServlet(name = "Inscription", urlPatterns = {"/inscription"})
public class Inscription extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        SessionDeFormation sessionDeFormation = SessionDeFormationDao.chargerSession(Integer.parseInt(request.getParameter("session_id")), session);
        
        if (sessionDeFormation.getNbInscrits() >= sessionDeFormation.getCapacite())
            this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/sessionpleine.jsp" ).forward( request, response );
        
        request.setAttribute("formation_code", request.getParameter("formation_code"));
        request.setAttribute("session_id", request.getParameter("session_id"));
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/inscription.jsp" ).forward( request, response );
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
        return "Servlet Inscription à une formation";
    }

}
