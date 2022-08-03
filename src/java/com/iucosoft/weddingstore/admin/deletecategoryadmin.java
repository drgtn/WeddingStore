package com.iucosoft.weddingstore.admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.iucosoft.weddingstore.dao.CategoriiDaoIntf;
import com.iucosoft.weddingstore.dao.impl.CategoriiDaoImpl;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Categorii;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class deletecategoryadmin extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        RequestDispatcher rd=null;             
        MyDataSource myDataSource =(MyDataSource)getServletContext().getAttribute("myDataSource");
        CategoriiDaoIntf categoriiDao = CategoriiDaoImpl.getInstance(myDataSource);
       
        
        String idProdusStr=request.getParameter("idProduct");
        
        try{ 
          int idProduct = Integer.parseInt(idProdusStr);
            Categorii categorie =categoriiDao.findOneCategorieById(idProduct);
            if(categorie != null){
                
          
               categoriiDao.deleteCategorie(categorie);  
            }   
            request.setAttribute("successcategorie", "Procedura de stergere a categoriei este finisată !");
           rd = request.getRequestDispatcher("/admin/homeadmin");
            
        }catch(NumberFormatException nfe){
            
            request.setAttribute("error", nfe.toString() );
            request.setAttribute("linkinapoi", "cmsafiseazalistaproduse.html" );
            rd=request.getRequestDispatcher("/error.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(deletecategoryadmin.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}
