/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.main;

import miraeasset.controller.LoginController;
import miraeasset.ClientSide.Client;
import miraeasset.view.LoginFrame;

/**
 *
 * @author Kin Tu
 */
public class Main {
     public static void main(String[] args) {
        LoginFrame loginView = new LoginFrame();
        Client loginClient = new Client();
        LoginController loginController = new LoginController(loginView, loginClient);

        loginView.setVisible(true);
    }
}
