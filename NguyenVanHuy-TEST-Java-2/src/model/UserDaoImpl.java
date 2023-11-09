package model;

import common.PasswordHashing;
import dao.DBConnection;
import entity.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImpl {
    private static final Connection conn = DBConnection.createConnection();
    private static final String SQL_REGISTER = "INSERT INTO users (username, password) VALUES (?, ?);";
    private static final String SQL_CHECK_EXIST_USER = "SELECT username FROM users WHERE username = ?;";
    private static final String SQL_LOGIN = "SELECT username, password FROM users WHERE username = ?;";

    public boolean checkExistUser(String username) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_CHECK_EXIST_USER)) {
            pstm.setString(1, username);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    return true;
                }
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean register(User user) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_REGISTER, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, user.getUsername());
            String hashedPassword = PasswordHashing.hashPassword(user.getPassword());
            pstm.setString(2, hashedPassword);
            pstm.executeUpdate();
            try (ResultSet rs = pstm.getGeneratedKeys()) {
                while (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
            System.out.println("Register successfully !");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in register :(");
            return false;
        }
    }

    public boolean checkLogin(User user) {
        try (PreparedStatement pstm = conn.prepareStatement(SQL_LOGIN)) {
            pstm.setString(1, user.getUsername());
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    boolean isValidPassword = PasswordHashing.checkPassword(user.getPassword(), rs.getString("password"));
                    if (isValidPassword) {
                        System.out.println("Login successfully !");
                        return true;
                    }
                }
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
