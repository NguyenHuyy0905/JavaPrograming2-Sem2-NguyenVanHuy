package controller;

import entity.Users;
import model.LoginDaoImpl;

public class LoginController {
    LoginDaoImpl loginDao = new LoginDaoImpl();
    public String loginStatementController(Users user) {
        return loginDao.checkLoginStatement(user);
    }
    public String loginPreparedStatementController(Users user){
        return loginDao.checkLoginPreparedStatement(user);
    }
}
