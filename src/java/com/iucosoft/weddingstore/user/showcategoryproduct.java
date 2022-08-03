/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.user;

import com.iucosoft.weddingstore.dao.CategoriiDaoIntf;
import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Categorii;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.exeptions.NoProdusBDExeption;
import com.iucosoft.weddingstore.dao.impl.CategoriiDaoImpl;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrei
 */
public class showcategoryproduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 50;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        RequestDispatcher rd = null;
        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");

        //afisarea tutturor produselor
        ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);

        String idCatStr = request.getParameter("idCat");
        int idCat = Integer.parseInt(idCatStr);

        try {

            List<Produse> listaProduse = new ArrayList<>();

            listaProduse = produseDao.findAllByCategorieId(idCat, (page - 1) * recordsPerPage, recordsPerPage);
            if (produseDao.nrRinduriInBD() < idCat) {
                throw new NoProdusBDExeption("Nu exista produse cu  idCat=" + idCat);

            }

            int noOfRecords = produseDao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("listProduse", listaProduse);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);

            rd = request.getRequestDispatcher("/WEB-INF/pages/user/showcategoryproducts.jsp");
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            rd = request.getRequestDispatcher("/error.jsp");
        }

        //afisarea denumirei categoriei la fiecare categorie deschisa
        CategoriiDaoIntf cat = CategoriiDaoImpl.getInstance(myDataSource);

        try {

            Categorii categorie = new Categorii();

            categorie = cat.findOneCategorieById(idCat);

            request.setAttribute("categorie", categorie);

            rd = request.getRequestDispatcher("/WEB-INF/pages/user/showcategoryproduct.jsp");
        } catch (Exception nfe) {

            request.setAttribute("error", nfe.toString());
            rd = request.getRequestDispatcher("/error.jsp");
        }

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
    }

}
