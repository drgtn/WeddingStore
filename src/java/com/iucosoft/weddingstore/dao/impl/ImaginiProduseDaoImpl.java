package com.iucosoft.weddingstore.dao.impl;

import com.iucosoft.weddingstore.dao.ImaginiProduseDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.ImaginiProduse;
import com.iucosoft.weddingstore.domain.Produse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImaginiProduseDaoImpl implements ImaginiProduseDaoIntf {

    private static final Logger LOG = Logger.getLogger(CategoriiDaoImpl.class.getName());

    private static ImaginiProduseDaoImpl instance;
    private MyDataSource myDataSource;

    public static ImaginiProduseDaoImpl getInstance(MyDataSource myDataSource) {
        if (instance == null) {
            instance = new ImaginiProduseDaoImpl(myDataSource);
        }
        return instance;
    }

    private ImaginiProduseDaoImpl(MyDataSource myDataSource) {
        this.myDataSource = myDataSource;
    }

    @Override
    public void save(ImaginiProduse img) {
        String sql = "INSERT INTO imaginiprodus VALUES (null,?,?,?,?)";

        try (
                Connection conn = myDataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, img.getNumeFile());
            ps.setString(2, img.getNumeDir());
            ps.setInt(3, img.getIdProdus());
            ps.setBytes(4, img.getImageData());
            
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ImaginiProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @Override
    public List<ImaginiProduse> findAllByProduct(Produse produs) {
       List<ImaginiProduse> lista=new ArrayList<ImaginiProduse>();
       Connection conn=null;
         ImaginiProduse imaginiProduse=null;
         String sql= "SELECT * FROM imaginiProdus WHERE idProdus="+produs.getId();
        
         try{
            conn = myDataSource.getConnection();
          //  UPDATE `webmvc`.`texte` SET `pagetext`='Text la HOME din BD   gggg' WHERE `pagename`='contactepage';
           Statement stat = conn.createStatement();
            
           ResultSet rs =stat.executeQuery(sql);
           
          while(rs.next()){
              imaginiProduse = new ImaginiProduse();
              imaginiProduse.setId(rs.getInt(1));
              imaginiProduse.setNumeDir(rs.getString(2));
              imaginiProduse.setNumeFile(rs.getString(3));
              imaginiProduse.setIdProdus(rs.getInt(4));
              
              lista.add(imaginiProduse);
           }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }finally{
            try {
                conn.close();
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
         
         return lista;      
    
    }

    @Override
    public void delete(int imgageId) {
         String sql = "DELETE FROM imaginiProdus WHERE id="+imgageId;
        try (
                Connection conn = myDataSource.getConnection();
                Statement stat = conn.createStatement();) {

            stat.executeUpdate(sql);

        } catch (Exception e) {
             try {
                 LOG.severe("delete produs: " + e);
                 throw new SQLException("nu pot sterge ");
             } catch (SQLException ex) {
                 Logger.getLogger(ImaginiProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
             }
        

    }
    
    
    
    }

    @Override
    public ImaginiProduse findById(int imgageId) {
        ImaginiProduse imaginiProduse = null;
        String sql = "SELECT * FROM imaginiProdus WHERE id=?";

        try {
            try (
                    Connection conn = myDataSource.getConnection();
                    PreparedStatement ps = conn.prepareStatement(sql);) {
                ps.setInt(1, imgageId);
                try (ResultSet rs = ps.executeQuery();) {
                    if (rs.next()) {
                        imaginiProduse = new ImaginiProduse();
                        imaginiProduse.setId(imgageId);
                        imaginiProduse.setNumeDir(rs.getString(2));
                         imaginiProduse.setNumeFile(rs.getString(3));
                        imaginiProduse.setIdProdus(rs.getInt(4));
                       

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imaginiProduse;

    }

    @Override
    public void deleteByProdus(Produse produs) {
         String sql = "DELETE FROM imaginiProdus WHERE idProdus=" + produs.getId();
        try (
                Connection conn = myDataSource.getConnection();
                Statement stat = conn.createStatement();) {

            stat.executeUpdate(sql);

        } catch (Exception e) {
             try {
                 LOG.severe("delete produs: " + e);
                 throw new SQLException("nu pot sterge ");
             } catch (SQLException ex) {
                 Logger.getLogger(ImaginiProduseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
             }
        }

    }
    


}
     