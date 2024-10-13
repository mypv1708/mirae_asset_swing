/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.controller;

import javax.swing.JOptionPane;
import miraeasset.ClientSide.LoginClient;
import miraeasset.view.LoginFrame;
import miraeasset.view.ManagerMainFrame;

/**
 *
 * @author Kin Tu
 */
public class LoginController {

    private final LoginClient loginClient;
    private final LoginFrame loginView;

    public LoginController(LoginFrame loginView, LoginClient loginClient) {
        this.loginClient = loginClient;
        this.loginView = loginView;

        this.loginView.addLoginButtonListener(e -> handleLogin());
    }

    private void handleLogin() {
        String username = loginView.txtUsername.getText();
        String password = new String(loginView.txtPassword.getPassword());
        String branchInfo = loginClient.authenticateUser(username, password);

        if (branchInfo != null) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
            loginView.dispose();
            showMainFrame(branchInfo);
        } else {
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng!");
        }
    }

    private void showMainFrame(String branchInfo) {
        ManagerMainFrame mainFrame = new ManagerMainFrame(branchInfo);
        mainFrame.setVisible(true);
    }
}
