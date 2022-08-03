package com.iucosoft.weddingstore.frontend;

import com.iucosoft.weddingstore.dao.CategoriiDaoIntf;
import com.iucosoft.weddingstore.dao.ImaginiProduseDaoIntf;
import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Categorii;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.exeptions.NoProdusBDExeption;
import com.iucosoft.weddingstore.dao.impl.CategoriiDaoImpl;
import com.iucosoft.weddingstore.dao.impl.ImaginiProduseDaoImpl;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import com.iucosoft.weddingstore.domain.ImaginiProduse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrei
 */
public class showproduct extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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

            rd = request.getRequestDispatcher("/WEB-INF/pages/showproduct.jsp");
        } catch (NoProdusBDExeption e) {
            request.setAttribute("error", e.toString());
            rd = request.getRequestDispatcher("/error.jsp");
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
