/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.Service;

import java.util.ArrayList;
import java.util.List;
import miraeasset.model.Ekyc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import miraeasset.database.DatabaseConnection;

/**
 *
 * @author Kin Tu
 */
public class EkycService {

    private String branchInfo;

    public List<Ekyc> getEkycData(String branchInfo, String filter) {
        List<Ekyc> ekycList = new ArrayList<>();
        String sql = "SELECT A.user_account, C.full_name, RE.status, RE.review, RE.created_at, RE.commit_time FROM CustomerDetail C "
                + "JOIN dbo.Account A on A.account_id = C.account_id "
                + "JOIN dbo.RequestEkyc RE on C.customer_id = RE.customer_id";

        if ("pending".equals(filter)) {
            sql += " AND status IS NULL";
        } else if ("processed".equals(filter)) {
            sql += " AND status IS NOT NULL";
        }
        
        try (Connection connection = DatabaseConnection.getConnection(branchInfo, "sa", "170801"); PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ekyc ekyc = new Ekyc(
                        rs.getString("user_account"),
                        rs.getString("full_name"),
                        rs.getString("status"),
                        rs.getString("review"),
                        rs.getDate("created_at"),
                        rs.getDate("commit_time")
                );
                ekycList.add(ekyc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ekycList;
    }

    public boolean updateEkyc(String branchInfo, String userAccount, String review, String status) {
        String sql = "UPDATE dbo.RequestEkyc SET review = ?, status = ?, commit_time = ? "
                + "WHERE customer_id = (SELECT customer_id FROM dbo.CustomerDetail join dbo.Account A on A.account_id = CustomerDetail.account_id WHERE user_account = ?)";

        try (Connection connection = DatabaseConnection.getConnection(branchInfo, "sa", "170801"); PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, review);
            pstmt.setString(2, status);
            pstmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
            pstmt.setString(4, userAccount);

            int affectedRows = pstmt.executeUpdate();
            System.out.println("Rows affected: " + affectedRows);

            pstmt.close();
            connection.close();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
