/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.sql.ResultSet;


/**
 *
 * @author Kin Tu
 */
public class ConnectionDatabase {

  private static final Map<String, String> serverUrls = new HashMap<>();

    static {
        // Thông tin kết nối tới các server central và branch
        serverUrls.put("central", "jdbc:sqlserver://DESKTOP-R1E03RC\\\\SERVER:1433;databaseName=MIRAE_ASSET_COMPANY;encrypt=true;trustServerCertificate=true");
        serverUrls.put("branch1", "jdbc:sqlserver://DESKTOP-R1E03RC\\\\BRANCH1:1435;databaseName=MIRAE_ASSET_CNHC;encrypt=true;trustServerCertificate=true");
        serverUrls.put("branch2", "jdbc:sqlserver://DESKTOP-R1E03RC\\\\BRANCH2:1436;databaseName=MIRAE_ASSET_CNCL;encrypt=true;trustServerCertificate=true");
        serverUrls.put("branch3", "jdbc:sqlserver://DESKTOP-R1E03RC\\\\BRANCH3:1437;databaseName=MIRAE_ASSET_CNTK;encrypt=true;trustServerCertificate=true");
        serverUrls.put("branch4", "jdbc:sqlserver://DESKTOP-R1E03RC\\\\BRANCH4:1434;databaseName=MIRAE_ASSET_CNNHS;encrypt=true;trustServerCertificate=true");
    }

    public static Connection getConnection(String serverKey, String user, String password) throws SQLException {
        String url = serverUrls.get(serverKey);
        if (url != null) {
            return DriverManager.getConnection(url, user, password);
        } else {
            throw new IllegalArgumentException("Server key không hợp lệ: " + serverKey);
        }
    }

    public static void main(String[] args) {
        try {
            // Kết nối tới central server
            Connection centralConnection = getConnection("central", "sa", "170801");
            System.out.println("Kết nối tới Central Server thành công!");

            // Kết nối tới branch1 server
            Connection branch1Connection = getConnection("branch1", "sa", "170801");
            System.out.println("Kết nối tới Branch 1 Server thành công!");
            
            // Kết nối tới branch4 server
            Connection branch2Connection = getConnection("branch2", "sa", "170801");
            System.out.println("Kết nối tới Branch 2 Server thành công!");
            
            // Kết nối tới branch4 server
            Connection branch3Connection = getConnection("branch3", "sa", "170801");
            System.out.println("Kết nối tới Branch 3 Server thành công!");
            
            // Kết nối tới branch4 server
            Connection branch4Connection = getConnection("branch4", "sa", "170801");
            System.out.println("Kết nối tới Branch 4 Server thành công!");

            // Đóng kết nối (nếu cần thiết)
            centralConnection.close();
            branch1Connection.close();
            branch2Connection.close();
            branch3Connection.close();
            branch4Connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
