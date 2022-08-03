package com.iucosoft.weddingstore.dao.impl;

import com.iucosoft.weddingstore.dao.AdminBeanDaoIntf;

import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.AdminBean;
import com.iucosoft.weddingstore.domain.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrei
 */
public class AdminBeanDaoImpl implements AdminBeanDaoIntf {

    private static final Logger LOG = Logger.getLogger(AdminBeanDaoImpl.class.getName());

    private static AdminBeanDaoImpl instance;
    private MyDataSource myDataSource;

    public AdminBeanDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    public static AdminBeanDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new AdminBeanDaoImpl(myDataSource);
        }
        return instance;
    }

    @Override
    public AdminBean emailExists(AdminBean adminBean) {

        String email = adminBean.getEmail();

        String sql = "select * from admin where email='" + email + "'";

        try (
                Connection conn = myDataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {

            boolean more = rs.next();
            if (!more) {
                adminBean.setValid(false);

            } else if (more) {
                adminBean.setValid(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return adminBean;
    }

    @Override
    public AdminBean login(AdminBean adminBean) {

        String username = adminBean.getEmail();
        String password = adminBean.getPassword();

        String sql = "select * from admin where email='"
                + username
                + "' AND password='"
                + password
                + "'";

        try (
                Connection conn = myDataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {

            boolean more = rs.next();
            if (!more) {
                adminBean.setValid(false);

            } else if (more) {
                int id=rs.getInt(1);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                
                adminBean.setId(id);
                adminBean.setFirstName(firstName);
                adminBean.setLastName(lastName);
                adminBean.setValid(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AdminBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return adminBean;

    }

    @Override
    public void createAccount(AdminBean adminBean) {

        String sql = "INSERT INTO users VALUES(null,?,?,?,?)";

        try (
                Connection conn = myDataSource.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql);)
        {
            prepstat.setString(1, adminBean.getEmail());
            prepstat.setString(2, adminBean.getPassword());
            prepstat.setString(3, adminBean.getFirstName());
            prepstat.setString(4, adminBean.getLastName());
            prepstat.executeUpdate();

        } catch (SQLException ex) {
                Logger.getLogger(AdminBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
