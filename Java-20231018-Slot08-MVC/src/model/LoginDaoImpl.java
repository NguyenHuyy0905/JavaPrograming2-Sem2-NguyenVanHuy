package model;

import dao.DBConnection;
import entity.Users;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDaoImpl implements LoginDao{
    private static final Connection conn = DBConnection.createConnection();
    private static Statement stmt = null;
    private static PreparedStatement pstm = null;
    @Override
    public String checkLoginStatement(Users user) {
        String dbQuery = "SELECT username FROM users WHERE username ='" + user.getUsername() + "'" + " AND password = '" + user.getPassword() + "' ";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(dbQuery);
            while (rs.next()) {
                System.out.println("Username is: " + rs.getString("username"));
                return rs.getString("username");
            }
            return "Not in the database";
        } catch (SQLException ex) {
            Logger.getLogger(LoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        }
    }

    @Override
    public String checkLoginPreparedStatement(Users user) {
        String dbQuery = "SELECT username FROM users WHERE username like ? AND password like ?";
        try {
            pstm = conn.prepareStatement(dbQuery);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println("Username is: " + rs.getString("username"));
                return rs.getString("username");
            }
            return "Not in the database";
        } catch (SQLException ex) {
            Logger.getLogger(LoginDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        }
    }

}
