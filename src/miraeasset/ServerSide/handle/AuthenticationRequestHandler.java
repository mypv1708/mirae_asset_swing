/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.ServerSide.handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import miraeasset.Service.AuthenticationService;

/**
 *
 * @author Kin Tu
 */
public class AuthenticationRequestHandler extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private AuthenticationService authenticationService;

    public AuthenticationRequestHandler(Socket socket) {
        this.clientSocket = socket;
        this.authenticationService = new AuthenticationService();
    }

    @Override
    public void run() {
        try {
//            objectOut = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            String username = in.readLine();
            String password = in.readLine();

            // Xác thực người dùng với cơ sở dữ liệu
            String branchInfo = authenticationService.authenticateUser(username, password);
            if (branchInfo != null) {
                out.println("Đăng nhập thành công");
                out.println(branchInfo); // Gửi thông tin branch cho client

            } else {
                out.println("Sai tài khoản hoặc mật khẩu!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
