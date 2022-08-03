package com.iucosoft.weddingstore.dao.impl;

import com.iucosoft.weddingstore.dao.UserBeanDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
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
public class UserBeanDaoImpl implements UserBeanDaoIntf {

    private static final Logger LOG = Logger.getLogger(UserBeanDaoImpl.class.getName());

    private static UserBeanDaoImpl instance;
    private MyDataSource myDataSource;

    public UserBeanDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    public static UserBeanDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new UserBeanDaoImpl(myDataSource);
        }
        return instance;
    }

    @Override
    public UserBean emailExists(UserBean userBean) {

        String email = userBean.getEmail();

        String sql = "select * from users where email='" + email + "'";

        try (
                Connection conn = myDataSource.getConnection();
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery(sql);) {

            boolean more = rs.next();
            if (!more) {
                userBean.setValid(false);

            } else if (more) {
                userBean.setValid(true);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userBean;
    }

    @Override
    public UserBean login(UserBean userBean) {

        String username = userBean.getEmail();
        String password = userBean.getPassword();

        String sql = "select * from users where email='"
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
                userBean.setValid(false);

            } else if (more) {
                int id=rs.getInt(1);
                String firstName = rs.getString(4);
                String lastName = rs.getString(5);
                
                userBean.setId(id);
                userBean.setFirstName(firstName);
                userBean.setLastName(lastName);
                userBean.setValid(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userBean;

    }

    @Override
    public void createAccount(UserBean userBean) {

        String sql = "INSERT INTO users VALUES(null,?,?,?,?)";

        try (
                Connection conn = myDataSource.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql);)
        {
            prepstat.setString(1, userBean.getEmail());
            prepstat.setString(2, userBean.getPassword());
            prepstat.setString(3, userBean.getFirstName());
            prepstat.setString(4, userBean.getLastName());
            prepstat.executeUpdate();

        } catch (SQLException ex) {
                Logger.getLogger(UserBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

    }
}
