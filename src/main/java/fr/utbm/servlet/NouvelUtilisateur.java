/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.utbm.entity.*;
import fr.utbm.repository.LogRedisDao;
import fr.utbm.repository.SessionDeFormationDao;
import fr.utbm.repository.UtilisateurDao;
import fr.utbm.tools.HibernateUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import org.hibernate.Session;

/**
 *
 * @author abourdillas
 */
@WebServlet(name = "NouvelUtilisateur", urlPatterns = {"/nouvelutilisateur"})
public class NouvelUtilisateur extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        SessionDeFormation sessionDeFormation = SessionDeFormationDao.chargerSession(Integer.parseInt(request.getParameter("session_id")), session);
        if (sessionDeFormation.getNbInscrits() >= sessionDeFormation.getCapacite())
            throw new Exception("Plus de place disponible dans cette session");
        
        System.out.println("\n\n\nAS was here !");
        Utilisateur utilisateur = new Utilisateur(request.getParameter("nom"),request.getParameter("prenom"),request.getParameter("addresse"),
                request.getParameter("telephone"),request.getParameter("email"),sessionDeFormation);
        
        UtilisateurDao.ajouter(utilisateur, session);
        LogRedisDao.tracerInscription(utilisateur);
        
        this.getServletContext().getRequestDispatcher( "/WEB-INF/jsp/nouvelutilisateur.jsp" ).forward( request, response );
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NouvelUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(NouvelUtilisateur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
