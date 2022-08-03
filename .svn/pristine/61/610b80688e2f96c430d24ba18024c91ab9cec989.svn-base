/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iucosoft.weddingstore.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Andrei
 */
public class MyDataSource {

    private static MyDataSource instance;
    private static Connection connection;
    private static DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(MyDataSource.class.getName());

    public static MyDataSource getInstance() {
        if (instance == null) {
            instance = new MyDataSource();
        }
        return instance;
    }

    private MyDataSource() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");

            dataSource = (DataSource) envContext.lookup("jdbc/weddingdb");
            Connection conn = dataSource.getConnection();

            if (conn == null) {
                throw new SQLException("NU pot obtine conexiunea din " + "jdbc/weddingdb");
            }
            

        } catch (NamingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, "AICI!", ex);
        }
    }

    private void createConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            createConnection();
        }
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();

        }

        connection = null;
    }

}
