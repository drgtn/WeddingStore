
package com.iucosoft.weddingstore.listeners;

import com.iucosoft.weddingstore.db.MyDataSource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Andrei
 */
public class DataSourceServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       
        ServletContext sc=sce.getServletContext();
        MyDataSource myDataSource=MyDataSource.getInstance();
        sc.setAttribute("myDataSource", myDataSource);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc=sce.getServletContext();
        MyDataSource myDataSource= (MyDataSource) sc.getAttribute("myDataSource");
        try {
            myDataSource.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DataSourceServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        sc.removeAttribute("myDataSource");
                
    }
}
