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
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import com.iucosoft.weddingstore.domain.ImaginiProduse;
import com.iucosoft.weddingstore.util.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Andrei
 */
@MultipartConfig
public class updateproduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("anuntadaugat", "");
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd = null;

        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
        ProduseDaoIntf produsDao = ProduseDaoImpl.getInstance(myDataSource);
        ImaginiProduseDaoIntf imaginiProduseDao = ImaginiProduseDaoImpl.getInstance(myDataSource);
        ImaginiProduse imaginiProduse = null;

        try {
            String denumire = request.getParameter("denumire");
            String descriere = request.getParameter("descriere");
            String pretStr = request.getParameter("pret");
            String regiunea = request.getParameter("regiunea");
            String telefonStr = request.getParameter("telefon");

            int pret = Integer.parseInt(pretStr);
            int telefon = Integer.parseInt(telefonStr);

            String idProductStr = request.getParameter("id");
            int idProduct = Integer.parseInt(idProductStr);

            //lucrul cu files
            String directoriu = "imaginiproduse";
            final String path = getServletContext().getRealPath("/") + File.separator + directoriu;
            final Part filePart = request.getPart("file");
            final String fileName = getFileName(filePart);
            byte[] imageData = null;
            InputStream filecontent = null;

            if (fileName != null && fileName.length() >= "*.jpg".length()) {
                String fullPath = path + File.separator + fileName;
                filecontent = filePart.getInputStream();
                ImageUtil.saveImageToFile(fullPath, filecontent);
                imageData = ImageUtil.readImageFileToBytes(fullPath);
            }

            if (imaginiProduseDao != null) {
                imaginiProduse = new ImaginiProduse();
                imaginiProduse.setNumeDir(directoriu);
                imaginiProduse.setImageData(imageData);
                imaginiProduse.setNumeFile(fileName);
                imaginiProduse.setIdProdus(idProduct);
            }

            Produse produs = produsDao.findOneProdusById(idProduct);
            produs.setDenumire(denumire);
            produs.setDescriere(descriere);
            produs.setPret(pret);
            produs.setRegiunea(regiunea);
            produs.setTelefon(telefon);

            if (fileName == null || fileName.isEmpty()) {

                if (produs != null) {

                    produsDao.updateProdus(produs);
                    request.setAttribute("produs", produs);
                  
                    request.setAttribute("anuntupdatat", "Procedura de editare a anunțului este finisată !");
                    request.setAttribute("anuntupdatatsucess", "Aţi editat cu succes  <a href=\"showproducts.htm?idProduct="+produs.getId()+"\">anuntul</a> pe Weddingdresses.md!");

                    rd = request.getRequestDispatcher("/WEB-INF/pages/user/success.jsp");

                }
            } else {

                produsDao.updateProdus(produs);
                imaginiProduseDao.save(imaginiProduse);

              
                rd = request.getRequestDispatcher("/cabinet/showupdateproduct?idProduct=" + idProduct);

            }

        } catch (Exception nfe) {

            request.setAttribute("error", "Nu a fost indicat corect ID-ul " + nfe.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        rd.forward(request, response);

    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
//        LOG.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
