/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.ServerSide.handle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import miraeasset.Service.EkycService;
import miraeasset.model.Ekyc;

/**
 *
 * @author Kin Tu
 */
public class EkycHandler extends Thread {

    private Socket clientSocket;
    private EkycService ekycService;

    public EkycHandler(Socket socket) {
        this.clientSocket = socket;
        this.ekycService = new EkycService();
    }

    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            String branchInfo = (String) in.readObject();
            List<Ekyc> ekycList = ekycService.getEkycData(branchInfo);
            out.writeObject(ekycList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
