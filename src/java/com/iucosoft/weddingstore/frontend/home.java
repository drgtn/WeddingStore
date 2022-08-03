package com.iucosoft.weddingstore.frontend;

import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class home extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage =50;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
       RequestDispatcher rd;
         MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
          ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);
          try {
            
            List<Produse> listProduse ;

            listProduse = produseDao.findAllProduse((page - 1) * recordsPerPage, recordsPerPage);
            
            int noOfRecords = produseDao.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            request.setAttribute("listProduse", listProduse);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            
            rd = request.getRequestDispatcher("/WEB-INF/pages/home.jsp");
        } catch (Exception e) {
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
    }// </editor-fold>

}
