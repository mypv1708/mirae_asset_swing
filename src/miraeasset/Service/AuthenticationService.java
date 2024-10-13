/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.Service;

import miraeasset.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Kin Tu
 */
public class AuthenticationService {

    public String authenticateUser(String username, String password) {
        String sql = "SELECT *, B.branch_code FROM Account join dbo.Staff S on Account.account_id = S.account_id join dbo.Branch B on B.branch_id = S.branch_id WHERE user_account = ? AND password = ? AND is_admin = 1";
        try (Connection connection = DatabaseConnection.getConnection("central", "sa", "170801"); PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("branch_code"); // Trả về branch_code của tài khoản admin
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
