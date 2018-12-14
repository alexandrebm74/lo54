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
import java.util.Date;
import java.util.ArrayList;
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
        
        
        ArrayList<Formation> listeFormations = new ArrayList<>();
        
        HttpSession session = request.getSession();
        session.setAttribute("listeFormations", listeFormations);
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
