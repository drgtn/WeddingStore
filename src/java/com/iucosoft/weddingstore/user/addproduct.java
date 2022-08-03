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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class addproduct extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(addproduct.class.getName());

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
        ProduseDaoIntf produsDao = ProduseDaoImpl.getInstance(myDataSource);
        ImaginiProduseDaoIntf imaginiProduseDao = ImaginiProduseDaoImpl.getInstance(myDataSource);
        ImaginiProduse imaginiProduse = null;
        RequestDispatcher rd = null;

        try {

            String idCatStr = request.getParameter("idCat");
            String regiunea = request.getParameter("regiunea");
            String denumire = request.getParameter("denumire");
            String descriere = request.getParameter("descriere");
            String pretStr = request.getParameter("pret");
            String telefonStr = request.getParameter("telefon");

            int idCat = Integer.parseInt(idCatStr);
            int telefon = Integer.parseInt(telefonStr);
            int pret = Integer.parseInt(pretStr);
             int idProdus=0;

            UserBean user = (UserBean) request.getSession().getAttribute("user");
           System.out.println("idUser "+  user.getId());
            
                       
         

            // lucrul cu files
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
                 System.out.println("image data "+  imageData);
            }
              
            if (imaginiProduseDao != null) {
                imaginiProduse = new ImaginiProduse();
                imaginiProduse.setNumeDir(directoriu);
                imaginiProduse.setImageData(imageData);
                imaginiProduse.setNumeFile(fileName);
            }
             Produse produs = new Produse(denumire, descriere, pret, telefon, regiunea, idCat,imageData,directoriu,fileName);
            
                if (fileName == null || fileName.isEmpty()) {
//                    produsDao.saveProdus(produs, user);
                    
                    
                    
                    
                     request.setAttribute("anuntadaugat", "Procedura de adaugare/editare a anunțului este finisată !");
                     request.setAttribute("anuntadaugatsuccess", "Aţi introdus/editat cu succes  <a href=\"showproducts.htm?idProduct="+produsDao.saveProdus(produs, user)+"\">anuntul</a> pe Weddingdresses.md!");
                     rd = request.getRequestDispatcher("/WEB-INF/pages/user/success.jsp");
                    
                } else {
                    
                    idProdus = produsDao.saveProdus(produs, user);
                    imaginiProduse.setIdProdus(idProdus);                
                    imaginiProduseDao.save(imaginiProduse);
                    
                   
                   rd = request.getRequestDispatcher("/cabinet/showupdateproduct?idProduct="+idProdus);
                   
                }
            
          
             
        } catch (Exception nfe) {
            request.setAttribute("error", "Nu a fost indicat corect ID-ul " + nfe.getMessage());
            rd = request.getRequestDispatcher("/error.jsp");
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
