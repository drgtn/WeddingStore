package com.iucosoft.weddingstore.user;

import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.domain.UserBean;
import com.iucosoft.weddingstore.exeptions.NoProdusBDExeption;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class showmyproducts extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
         RequestDispatcher rd = null;
        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");
         UserBean user = (UserBean)request.getSession().getAttribute("user");   

        //afisarea tutturor produselor
        ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);

      

        try {

            List<Produse> listaProduse = new ArrayList<>();

            listaProduse = produseDao.findAllByUser(user);
          
            request.setAttribute("listProduse", listaProduse);

           request.getRequestDispatcher("/WEB-INF/pages/user/myproducts.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", e.toString());
            rd = request.getRequestDispatcher("/error.jsp");
        }
        
        
        
        
        
        
     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
      
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
