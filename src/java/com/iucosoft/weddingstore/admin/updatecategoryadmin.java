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
public class updatecategoryadmin extends HttpServlet {

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
        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        CategoriiDaoIntf categoriiDao = CategoriiDaoImpl.getInstance(myDataSource);
        RequestDispatcher rd = null;

        try {

            String denumire = request.getParameter("denumire");
            String idProductStr = request.getParameter("id");
            int idProduct = Integer.parseInt(idProductStr);

            Categorii categorie = categoriiDao.findOneCategorieById(idProduct);
            categorie.setDenumire(denumire);
            categoriiDao.updateCategorie(categorie);

            request.setAttribute("successcategorie", "Procedura de editare a categoriei este finisată !");
            rd = request.getRequestDispatcher("/WEB-INF/pages/admin/homeadmin.jsp");

        } catch (Exception nfe) {

            request.setAttribute("error", "Nu a fost indicat corect ID-ul " + nfe.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
