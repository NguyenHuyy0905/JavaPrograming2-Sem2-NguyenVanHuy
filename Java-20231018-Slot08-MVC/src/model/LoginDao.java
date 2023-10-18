package model;

import entity.Users;

public interface LoginDao {
    public String checkLoginStatement(Users user);
    public String checkLoginPreparedStatement(Users user);
}
