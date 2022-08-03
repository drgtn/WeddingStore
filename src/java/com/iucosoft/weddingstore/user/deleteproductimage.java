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
import com.iucosoft.weddingstore.util.ImageUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
public class deleteproductimage extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        ImaginiProduseDaoIntf imaginiProduseDao = ImaginiProduseDaoImpl.getInstance(myDataSource);
        ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        RequestDispatcher rd = null;

        int imageId = Integer.parseInt(request.getParameter("imgId"));

        ImaginiProduse imaginiProduse = imaginiProduseDao.findById(imageId);
        String numeDir = imaginiProduse.getNumeDir();
        String numeFile = imaginiProduse.getNumeFile();
       

        int idProdus = imaginiProduse.getIdProdus();

        List<Produse> listaProduse = produseDao.findAllByUser(user);

        if (produseDao.checkUserProduct(idProdus, (ArrayList<Produse>) listaProduse)) {
            
            ImageUtil.deleteImage(getServletContext().getRealPath("/"),numeFile, numeDir);
            imaginiProduseDao.delete(imageId);      
              request.getRequestDispatcher("/cabinet/showupdateproduct?idProduct=" + idProdus).forward(request, response);

        } else {
           
           request.getRequestDispatcher("/cabinet/showproducts?idProduct=" + idProdus).forward(request, response);

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
    }// </editor-fold>

}
