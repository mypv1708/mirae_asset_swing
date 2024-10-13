/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miraeasset.Test;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Kin Tu
 */
public class FirebaseImagePanelDisplay extends JFrame {

    public FirebaseImagePanelDisplay() {
        // Tạo một JPanel để chứa ảnh
        JPanel imagePanel = new JPanel();

        // URL của ảnh trên Firebase
        String imageUrl = "https://firebasestorage.googleapis.com/v0/b/distributejava.appspot.com/o/branch4%2Fmypham-cccd-1.jpg?alt=media&token=5734f291-f143-4358-a681-f5313e04a403";

        try {
            // Tải ảnh từ URL
            URL url = new URL(imageUrl);
            Image originalImage = ImageIO.read(url);

            // Kiểm tra nếu ảnh không null
            if (originalImage != null) {
                // Resize ảnh xuống kích thước nhỏ hơn (giảm 10 lần)
                int width = originalImage.getWidth(null) / 7;
                int height = originalImage.getHeight(null) / 7;
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

                // Chuyển đổi thành ImageIcon và hiển thị trên JLabel
                ImageIcon imageIcon = new ImageIcon(scaledImage);
                JLabel imageLabel = new JLabel(imageIcon);

                // Thêm JLabel vào JPanel
                imagePanel.add(imageLabel);
            } else {
                System.out.println("Không thể tải ảnh từ URL.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Lỗi khi tải ảnh từ URL.");
        }

        // Thêm JPanel vào JFrame
        add(imagePanel);

        // Cấu hình JFrame
        setTitle("Hiển thị ảnh từ Firebase trên JPanel với resize");
        setSize(800, 600); // Thiết lập kích thước cửa sổ JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Hiển thị ở giữa màn hình
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FirebaseImagePanelDisplay());
    }
}
