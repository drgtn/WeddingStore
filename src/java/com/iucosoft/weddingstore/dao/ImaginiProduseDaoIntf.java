package com.iucosoft.weddingstore.dao;

import com.iucosoft.weddingstore.domain.ImaginiProduse;
import com.iucosoft.weddingstore.domain.Produse;
import java.util.ArrayList;
import java.util.List;

public interface ImaginiProduseDaoIntf {

    void save(ImaginiProduse img);

    List<ImaginiProduse> findAllByProduct(Produse produs);

    void delete(int imgageId);

    ImaginiProduse findById(int imgageId);

    void deleteByProdus(Produse produs);
}
