/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.admin;

import com.iucosoft.weddingstore.dao.CategoriiDaoIntf;
import com.iucosoft.weddingstore.dao.impl.CategoriiDaoImpl;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Categorii;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrei
 */
public class showupdatecategoryadmin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     RequestDispatcher rd = null;

        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        CategoriiDaoIntf categoriiDao = CategoriiDaoImpl.getInstance(myDataSource);
      

        String idProductStr = request.getParameter("idProduct");
        int idProduct = Integer.parseInt(idProductStr);
      

        Categorii categorie = categoriiDao.findOneCategorieById(idProduct);

       
       
        

      
   
            request.setAttribute("categorie", categorie);
           
            rd = request.getRequestDispatcher("/WEB-INF/pages/admin/showupdatecategoryadmin.jsp");

        

        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
