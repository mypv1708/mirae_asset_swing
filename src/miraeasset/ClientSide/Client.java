/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.ClientSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Kin Tu
 */
public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public boolean authenticateUser(String user_account, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); 
                PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true); 
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Gửi thông tin đăng nhập tới server
            out.println(user_account);
            out.println(password);

            // Nhận kết quả xác thực từ server
            String response = in.readLine();
            return "Đăng nhập thành công".equals(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
