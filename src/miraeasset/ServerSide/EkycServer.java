/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.ServerSide;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import miraeasset.ServerSide.handle.EkycHandler;


/**
 *
 * @author Kin Tu
 */
public class EkycServer {

    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Ekyc server is running on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new EkycHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
