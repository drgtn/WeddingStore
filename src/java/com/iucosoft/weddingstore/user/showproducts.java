/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.user;

import com.iucosoft.weddingstore.dao.ImaginiProduseDaoIntf;
import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.dao.impl.ImaginiProduseDaoImpl;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.exeptions.NoProdusBDExeption;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import com.iucosoft.weddingstore.domain.ImaginiProduse;
import java.io.IOException;
import java.io.PrintWriter;
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
public class showproducts extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         RequestDispatcher rd = null;
        
        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");

        ProduseDaoIntf produsDao = ProduseDaoImpl.getInstance(myDataSource);
         ImaginiProduseDaoIntf imaginiProduseDao=ImaginiProduseDaoImpl.getInstance(myDataSource);

        //produse detalii
        String IdProductStr = request.getParameter("idProduct");
        int idProduct = Integer.parseInt(IdProductStr);

        try {
           

            Produse produs = new Produse();

            produs = produsDao.findOneProdusById(idProduct);
            List<ImaginiProduse> listaImaginiProduse=imaginiProduseDao.findAllByProduct(produs);
            if (produs == null) {
                throw new NoProdusBDExeption("Nu exista produs cu  id=" + idProduct);

            }
             request.setAttribute("listaImaginiProduse", listaImaginiProduse);
            request.setAttribute("produs", produs);

            rd = request.getRequestDispatcher("/WEB-INF/pages/user/showproduct.jsp");
        } catch (NoProdusBDExeption e) {
            request.setAttribute("error", e.toString());
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
