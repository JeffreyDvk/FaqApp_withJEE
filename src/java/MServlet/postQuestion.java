/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MServlet;

import MBeans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author __Root0__
 */
public class postQuestion extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
        processRequest(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        String categorie = request.getParameter("categories");
        String question = request.getParameter("question");
        HttpSession session = request.getSession();
        User usr = (User)session.getAttribute("user");
        System.out.println(categorie);
        System.out.println(question);
        System.out.println(usr);
        if(usr!=null){
            if(categorie!=null && question!=null){
                if(!categorie.equals("Non sélectionner")){
                    if(!question.equals("")){
                        QuestionManager qm = new QuestionManager();
                        if(qm.addQuestion(categorie, question, false, false, usr.getEmail())){
                            session.setAttribute("ok", true);    
                        }else{
                            session.setAttribute("error", 4);
                        }
                    }else{
                        session.setAttribute("error", 3);
                    }
                }else{
                    session.setAttribute("error", 2);
                }
            }else{
                session.setAttribute("error", 1);
            }
        }else{
            session.setAttribute("error", 5);
            response.sendRedirect("/ProjetJEE/index.jsp");
            return;
        }
        session.setAttribute("user", usr);
        if(usr.getStatus()){
            response.sendRedirect("/ProjetJEE/pages/admin.jsp?pages=faq");
        }else{
            response.sendRedirect("/ProjetJEE/pages/main.jsp?pages=faq");
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
