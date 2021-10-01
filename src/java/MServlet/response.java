/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MServlet;

import MBeans.QuestionManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import MBeans.*;
import javax.servlet.http.HttpSession;
/**
 *
 * @author __Root0__
 */
public class response extends HttpServlet {

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
        User usr = (User) request.getSession().getAttribute("user");
        if(usr!=null){
            String id = request.getParameter("id-conteneur");
            String categorie = request.getParameter("categories");
            String question = request.getParameter("question");
            String reponse = request.getParameter("reponse");
            String email = request.getParameter("email");
            HttpSession session = request.getSession();
            if(id!=null && categorie!=null && question!=null && reponse!=null && email!=null){
                if(!categorie.equals("Non sélectionner") && !question.equals("")){
                    QuestionManager qm = new QuestionManager();
                    if(qm.modifyQuestionInfo(id, categorie, question) && qm.setQuestionResponse(id, reponse) && qm.modifyQuestionState(id, true, false)){
                        try{
                            Mail.sendMail(email, "Réponse à votre question",reponse);
                            session.setAttribute("ok", 1);
                        }catch(UnsupportedOperationException | MException e){
                            session.setAttribute("error", 5);
                        }
                    }else{
                        session.setAttribute("error", 5);
                    }
                }else{
                    session.setAttribute("error", 1);
                }
            }else{
                session.setAttribute("error", 1);
            }
        }else{
            response.sendRedirect("/ProjetJEE/index.jsp");
            return;
        }
        response.sendRedirect("/ProjetJEE/pages/admin.jsp?pages=messagerie");
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
