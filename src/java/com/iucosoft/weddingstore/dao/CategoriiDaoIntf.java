
package com.iucosoft.weddingstore.dao;

import com.iucosoft.weddingstore.domain.Categorii;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Andrei
 */
public interface CategoriiDaoIntf {
    void saveCategorie(Categorii categorie);
    void updateCategorie(Categorii categorie)  throws SQLException;
    void deleteCategorie(Categorii categorie)throws SQLException;
    
    Categorii findOneCategorieById(int id);
    List<Categorii> findAllCategorii();
}
