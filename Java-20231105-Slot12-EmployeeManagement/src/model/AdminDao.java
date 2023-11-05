package model;

import entity.Admin;

public interface AdminDao {
    void register(Admin admin);
    void login(Admin admin);
    boolean isExistUsername(String username);
}
