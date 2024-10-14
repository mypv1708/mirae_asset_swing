/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.ClientSide;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import miraeasset.model.Ekyc;

/**
 *
 * @author Kin Tu
 */
public class EkycClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;
    private String branchInfo;

    public EkycClient(String branchInfo) {
        this.branchInfo = branchInfo;
    }

    public List<Ekyc> getEkycData(String filter) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject("GET_EKYC_FILTERED");
            out.writeObject(branchInfo);
            out.writeObject(filter);

            return (List<Ekyc>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateEkyc(String userAccount, String review, String status) {
        try (
                Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT); ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            out.writeObject("UPDATE_EKYC");
            out.writeObject(branchInfo);
            out.writeObject(userAccount);
            out.writeObject(review);
            out.writeObject(status);
            return (boolean) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
