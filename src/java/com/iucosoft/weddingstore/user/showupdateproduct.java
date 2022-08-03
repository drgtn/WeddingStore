/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.user;

import com.iucosoft.weddingstore.dao.ImaginiProduseDaoIntf;
import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.dao.impl.ImaginiProduseDaoImpl;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.ImaginiProduse;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.domain.UserBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractList;
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
public class showupdateproduct extends HttpServlet {

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
       RequestDispatcher rd = null;

        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        ProduseDaoIntf produsDao = ProduseDaoImpl.getInstance(myDataSource);
        ImaginiProduseDaoIntf imaginiProduseDao=ImaginiProduseDaoImpl.getInstance(myDataSource);

        String idProductStr = request.getParameter("idProduct");
        int idProduct = Integer.parseInt(idProductStr);
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        Produse produs = produsDao.findOneProdusById(idProduct);

        List<Produse> listaProduse  = produsDao.findAllByUser(user);
        List<ImaginiProduse> listaImaginiProduse=imaginiProduseDao.findAllByProduct(produs);
        

        if (produsDao.checkUserProduct(idProduct, (ArrayList<Produse>) listaProduse)) {
   
            request.setAttribute("produs", produs);
             request.setAttribute("listaImaginiProduse", listaImaginiProduse);
        
            rd = request.getRequestDispatcher("/WEB-INF/pages/user/showupdateproduct.jsp");

        } else {
            rd = request.getRequestDispatcher("/cabinet/showproducts?idProduct="+idProduct);
          
        }

        rd.forward(request, response);
   
        
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
        processRequest(request, response);
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
