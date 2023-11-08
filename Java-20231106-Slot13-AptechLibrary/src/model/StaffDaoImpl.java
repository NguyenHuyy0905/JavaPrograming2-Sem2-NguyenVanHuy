package model;

import common.PasswordHashing;
import dao.DBConnection;
import entity.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StaffDaoImpl implements StaffDao{
    private static final Connection conn = DBConnection.createConnection();
    private static final String SQL_REGISTER = "INSERT INTO staffs (username, password) VALUES (?, ?);";
    private static final String SQL_LOGIN = "SELECT password FROM staffs WHERE username = ?;";
    private static final String SQL_CHECK_EXIST_USERNAME = "SELECT username FROM staffs WHERE username = ?;";
    @Override
    public void register(Staff staff) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_REGISTER)) {
            pstm.setString(1, staff.getUsername());
            String passwordHashing = PasswordHashing.hashingPassword(staff.getPassword());
            pstm.setString(2, passwordHashing);
            pstm.executeUpdate();
            System.out.println("Đăng ký thành công !");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void login(Staff staff) {
        boolean isValidPassword;
        try (PreparedStatement pstm = conn.prepareStatement(SQL_LOGIN)) {
            pstm.setString(1, staff.getUsername());
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    isValidPassword = PasswordHashing.checkPassword(staff.getPassword(), rs.getString(1));
                    if (isValidPassword) {
                        System.out.println("Đăng nhập thành công !");
                        break;
                    }
                    System.out.println("Mật khẩu không hợp lệ !");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Có thể username không hợp lệ !");
        }
    }
    @Override
    public boolean isExistUsername(String username) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CHECK_EXIST_USERNAME)) {
            pstm.setString(1, username);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
