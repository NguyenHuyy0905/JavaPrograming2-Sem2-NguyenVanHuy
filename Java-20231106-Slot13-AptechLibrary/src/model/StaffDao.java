package model;

import entity.Staff;

public interface StaffDao {
    void register(Staff staff);
    void login(Staff staff);
    boolean isExistUsername(String username);
}
