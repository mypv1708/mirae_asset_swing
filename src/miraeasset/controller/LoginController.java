/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.controller;

import javax.swing.JOptionPane;
import miraeasset.ClientSide.Client;
import miraeasset.view.LoginFrame;
import miraeasset.view.ManagerMainFrame;

/**
 *
 * @author Kin Tu
 */
public class LoginController {

    private final Client loginClient;
    private final LoginFrame loginView;

    public LoginController(LoginFrame loginView, Client loginClient) {
        this.loginClient = loginClient;
        this.loginView = loginView;

        this.loginView.addLoginButtonListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = loginView.txtUsername.getText();
        String password = new String(loginView.txtPassword.getPassword());


        if (loginClient.authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            loginView.dispose();
            showMainFrame();

        } else {
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
        }
    }

    private void showMainFrame() {
        ManagerMainFrame mainFrame = new ManagerMainFrame();
        mainFrame.setVisible(true);
    }
}
