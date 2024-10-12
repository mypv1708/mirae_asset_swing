/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.Service;

import miraeasset.database.ConnectionDatabase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Kin Tu
 */
public class AuthenticationService {
    
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM Account WHERE user_account = ? AND password = ? AND is_admin = 1";
        try (Connection connection = ConnectionDatabase.getConnection("central", "sa", "170801"); PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
