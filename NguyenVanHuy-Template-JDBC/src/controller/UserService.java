package controller;

import entity.User;
import model.UserDaoImpl;

public class UserService {
    UserDaoImpl userDao = new UserDaoImpl();
    public boolean checkExistUser(String username) {
        return userDao.checkExistUser(username);
    }
    public boolean register(User user) {
        return userDao.register(user);
    }
    public boolean checkLogin(User user) {
        return userDao.checkLogin(user);
    }
}
