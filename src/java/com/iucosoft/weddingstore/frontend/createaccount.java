package com.iucosoft.weddingstore.frontend;

import com.iucosoft.weddingstore.dao.UserBeanDaoIntf;
import com.iucosoft.weddingstore.db.MyDataSource;
import com.iucosoft.weddingstore.domain.UserBean;
import com.iucosoft.weddingstore.util.MD5Util;
import com.iucosoft.weddingstore.dao.impl.UserBeanDaoImpl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class createaccount extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MyDataSource myDataSource = (MyDataSource) request.getServletContext().getAttribute("myDataSource");

        UserBeanDaoIntf userDao = UserBeanDaoImpl.getInstance(myDataSource);

        UserBean user = new UserBean();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = MD5Util.getHash(request.getParameter("password"));
        String repeatPassword = MD5Util.getHash(request.getParameter("repeatpassword"));

        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("password", "");
        request.setAttribute("repeatpassword", "");
        request.setAttribute("validationmessage", "");

        user.setEmail(email);
        user = userDao.emailExists(user);

        //if passwords match
        if (!password.equals(repeatPassword)) {
            request.setAttribute("validationmessage", "* parolele nu se potrivesc");
            request.getRequestDispatcher("/WEB-INF/pages/createaccount.jsp").forward(request, response);

        }
        try {
            //if email exists already
            if (user.isValid()) {
                request.setAttribute("validationmessage", "* un account cu acest email deja exista !");
               request.getRequestDispatcher("/WEB-INF/pages/createaccount.jsp").forward(request, response);

            } else {
                //create account
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setRepeatPassword(repeatPassword);

                userDao.createAccount(user);
                request.setAttribute("validationmessage", "account creat cu succes, acum poti sa te loghezi !");
                request.getRequestDispatcher("/WEB-INF/pages/createaccount.jsp").forward(request, response);

            }
        } catch (Throwable theException) {
            System.out.println(theException);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
