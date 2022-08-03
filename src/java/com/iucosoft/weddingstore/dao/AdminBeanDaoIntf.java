package com.iucosoft.weddingstore.dao;

import com.iucosoft.weddingstore.domain.AdminBean;
import com.iucosoft.weddingstore.domain.UserBean;

public interface AdminBeanDaoIntf {

    AdminBean login(AdminBean adminBean);

    AdminBean emailExists(AdminBean adminBean);
    
    void createAccount(AdminBean adminBean);
    
}
