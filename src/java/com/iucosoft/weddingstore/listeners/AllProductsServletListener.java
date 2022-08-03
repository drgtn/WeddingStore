/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.listeners;

import com.iucosoft.weddingstore.dao.ProduseDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Produse;
import com.iucosoft.weddingstore.dao.impl.ProduseDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener.
 *
 * @author Andrei
 */
public class AllProductsServletListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
         MyDataSource myDataSource = (MyDataSource) sre.getServletContext().getAttribute("myDataSource");

        ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);

        List<Produse> listaProduse = new ArrayList<>();
       // listaProduse = produseDao.findAllProduse();
        sre.getServletContext().setAttribute("listaProduse", listaProduse);
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        MyDataSource myDataSource = (MyDataSource) sre.getServletContext().getAttribute("myDataSource");

        ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);

        List<Produse> listaProduse = new ArrayList<>();
       // listaProduse = produseDao.findAllProduse();
        sre.getServletContext().setAttribute("listaProduse", listaProduse);
    }

//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        //afisarea categoriilor pentru sidebar
//
//        MyDataSource myDataSource = (MyDataSource) sce.getServletContext().getAttribute("myDataSource");
//
//        ProduseDaoIntf produseDao = ProduseDaoImpl.getInstance(myDataSource);
//
//        List<Produse> listaProduse = new ArrayList<>();
//        listaProduse = produseDao.findAllProduse();
//        sce.getServletContext().setAttribute("listaProduse", listaProduse);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContext sc = sce.getServletContext();
//        List<Produse> listaProduse = (List<Produse>) sc.getAttribute("listaProduse");
//        sc.removeAttribute("listaProduse");
//    }
}
