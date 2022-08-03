/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.frontend;

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
public class dispatcher extends HttpServlet {

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

        String path = request.getServletPath();

        String page = "home";
        if (path.equals("/home.htm")) {
            page = "home";
        } else if (path.equals("/sitemap.htm")) {
            page = "sitemap";
        } else if (path.equals("/login.htm")) {
            page = "showlogin";
        } else if (path.equals("/createaccount.htm")) {
            page = "showcreateaccount";
        } else if (path.equals("/showcategoryproducts.htm")) {

            page = "showcategoryproducts";
        } else if (path.equals("/showproduct.htm")) {

            page = "showproduct";
        } else if (path.equals("/signout.htm")) {

            page = "signout";
        } else if (path.equals("/main.htm")) {

            page = "/cabinet/main";
        }  else if (path.equals("/showcategoryproduct.htm")) {

            page = "/cabinet/showcategoryproduct";
        }else if (path.equals("/showproducts.htm")) {

            page = "/cabinet/showproducts";
        }
        else if (path.equals("/showaddproduct.htm")) {

            page = "/cabinet/showaddproduct";
        }
         else if (path.equals("/showmyproducts.htm")) {

            page = "/cabinet/showmyproducts";
        }
        else if (path.equals("/deleteproduct.htm")) {

            page = "/cabinet/deleteproduct";
        } else if (path.equals("/deleteproductimage.htm")) {

            page = "/cabinet/deleteproductimage";
        }
        else if (path.equals("/showresults.htm")) {

            page = "showresults";
        }else if (path.equals("/showresult.htm")) {

            page = "/cabinet/showresult";
        }else if (path.equals("/showupdateproduct.htm")) {

            page = "/cabinet/showupdateproduct";
        }
        //admin
        else if (path.equals("/loginadmin.htm")) {

            page = "showloginadmin";
        }
         
        else if (path.equals("/signoutadmin.htm")) {

            page = "signoutadmin";
        }
         else if (path.equals("/homeadmin.htm")) {

            page = "/admin/homeadmin";
        }
         else if (path.equals("/showaddcategoryadmin.htm")) {

            page = "/admin/showaddcategoryadmin";
        }
          else if (path.equals("/showupdatecategoryadmin.htm")) {

            page = "/admin/showupdatecategoryadmin";
        }
           else if (path.equals("/deletecategoryadmin.htm")) {

            page = "/admin/deletecategoryadmin";
        }
        else{
            page = "/error.jsp";
        }
        RequestDispatcher rd = request.getRequestDispatcher(page);
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
