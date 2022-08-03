/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.admin;

import com.iucosoft.weddingstore.dao.AdminBeanDaoIntf;
import com.iucosoft.weddingstore.dao.impl.AdminBeanDaoImpl;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.AdminBean;
import com.iucosoft.weddingstore.domain.UserBean;
import com.iucosoft.weddingstore.util.MD5Util;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class loginadmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            int page = 1;
        int recordsPerPage = 8;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        String email = request.getParameter("email");
        
        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        RequestDispatcher rd = null;

        AdminBeanDaoIntf adminDao = AdminBeanDaoImpl.getInstance(myDataSource);
    

        //produse
       
      

        //user
        AdminBean user = new AdminBean();

        
        String password = MD5Util.getHash(request.getParameter("password"));

        user.setEmail(email);
        user.setPassword(password);
        user = adminDao.login(user);
        request.setAttribute("email", email);        //pentru a memora email-ul cind dam back
        request.setAttribute("password", "");

        try {

            if (user.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", user);

             
           
          
              
             
                rd = request.getRequestDispatcher("/WEB-INF/pages/admin/homeadmin.jsp");

                rd.forward(request, response);

            } else {

                request.setAttribute("validationmessage", "* email sau parola gresite!");
                rd = request.getRequestDispatcher("/WEB-INF/pages/admin/loginadmin.jsp");
                rd.forward(request, response);

            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
