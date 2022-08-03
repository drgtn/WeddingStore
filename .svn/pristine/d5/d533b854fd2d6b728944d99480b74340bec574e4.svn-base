package com.iucosoft.weddingstore.frontend;
import com.iucosoft.weddingstore.domain.UserBean;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class signout extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            UserBean user = new UserBean();
            user.removePassword();
            user.removePassword();

            HttpSession session = request.getSession(false);
            session.removeAttribute("currentSessionUser");
            session.invalidate();
            request.getRequestDispatcher("showlogin").forward(request, response);
        } catch (Throwable theException) {
            System.out.println(theException);
        }

    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
