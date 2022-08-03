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
import com.iucosoft.weddingstore.domain.UserBean;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import com.iucosoft.weddingstore.domain.ImaginiProduse;
import com.iucosoft.weddingstore.util.ImageUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrei
 */
@WebServlet(name = "delete", urlPatterns = {"/delete"})
public class deleteproduct extends HttpServlet {

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
        request.setAttribute("anuntadaugat", "");
        RequestDispatcher rd = null;

        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");

        try {
            String idProductStr = request.getParameter("idProduct");
            ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);
            ImaginiProduseDaoIntf imaginiProduseDao = ImaginiProduseDaoImpl.getInstance(myDataSource);

            String numeDir = null;
            String numeFile = null;
            int idProduct = Integer.parseInt(idProductStr);

            Produse produs = produseDao.findOneProdusById(idProduct);
            List<ImaginiProduse> listaImaginiProduse = imaginiProduseDao.findAllByProduct(produs);

            if (produs != null) {
                UserBean user = (UserBean) request.getSession().getAttribute("user");
                produs = produseDao.findOneProdusById(idProduct);

                List<Produse> listaProduse = produseDao.findAllByUser(user);

                if (produseDao.checkUserProduct(idProduct, (ArrayList<Produse>) listaProduse)) {

                    Iterator<ImaginiProduse> iterator = listaImaginiProduse.iterator();

                    while (iterator.hasNext()) {
                        ImaginiProduse imagini= iterator.next();
                        numeDir = imagini.getNumeDir();
                        numeFile = imagini.getNumeFile();
                        ImageUtil.deleteImage(getServletContext().getRealPath("/"), numeFile, numeDir);
                       
                    }
//                    for (ImaginiProduse imagini : listaImaginiProduse) {
//                        numeDir = imagini.getNumeDir();
//                        numeFile = imagini.getNumeFile();
//                        ImageUtil.deleteImage(getServletContext().getRealPath("/"), numeFile, numeDir);
//                    }
                    imaginiProduseDao.deleteByProdus(produs);
                    produseDao.deleteProdus(produs);
                    request.setAttribute("anuntsters", "Anunt sters!");
                    request.getRequestDispatcher("/cabinet/showmyproducts").forward(request, response);

                } else {
                    request.getRequestDispatcher("/cabinet/showproducts?idProduct=" + idProduct).forward(request, response);

                }

            }

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e.toString());
            request.setAttribute("error", e.toString());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }

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
