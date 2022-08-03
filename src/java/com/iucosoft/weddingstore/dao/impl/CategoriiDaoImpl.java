package com.iucosoft.weddingstore.dao.impl;

import com.iucosoft.weddingstore.dao.CategoriiDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.Categorii;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriiDaoImpl implements CategoriiDaoIntf {

    private static final Logger LOG = Logger.getLogger(CategoriiDaoImpl.class.getName());

    private static CategoriiDaoImpl instance;
    private MyDataSource myDataSource;

    public static CategoriiDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new CategoriiDaoImpl(myDataSource);
        }
        return instance;
    }

    private CategoriiDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override
    public void saveCategorie(Categorii categorie) {
        String sql = "INSERT INTO categorii VALUE(null,?)";

        try (
                Connection conn = myDataSource.getConnection();
                PreparedStatement prepstat = conn.prepareStatement(sql);)
        {
            prepstat.setString(1, categorie.getDenumire());
          
            prepstat.executeUpdate();

        } catch (SQLException ex) {
                Logger.getLogger(AdminBeanDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @Override
    public void updateCategorie(Categorii categorie) throws SQLException {
           String sql = "UPDATE categorii SET denumire=? WHERE id=?";
        try (
                Connection conn = myDataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, categorie.getDenumire());      
            ps.setInt(2, categorie.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteCategorie(Categorii categorie) throws SQLException {
          String sql = "DELETE FROM categorii WHERE id=" + categorie.getId();
        try (
                Connection conn = myDataSource.getConnection();
                Statement stat = conn.createStatement();) {

            stat.executeUpdate(sql);

        } catch (Exception e) {
            LOG.severe("delete produs: " + e);
            throw new SQLException("nu pot sterge ");
        }
    }

    @Override
    public Categorii findOneCategorieById(int id) {
        Categorii cat = null;
        String sql = "SELECT * FROM categorii WHERE id=?";

        try {
            try (
                    Connection conn = myDataSource.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery();) {
                    if (rs.next()) {
                        cat = new Categorii(rs.getInt(1), rs.getString(2));

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cat;

    }

    @Override
    public List<Categorii> findAllCategorii() {
        String sql = "SELECT * FROM categorii";
        List<Categorii> lista = new ArrayList<>();

        try (
                Connection conn = myDataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {

                Categorii cat = new Categorii(rs.getInt(1), rs.getString(2));
                lista.add(cat);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;

    }

}
