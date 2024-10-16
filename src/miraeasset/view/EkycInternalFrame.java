/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package miraeasset.view;

import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import miraeasset.ClientSide.EkycClient;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import miraeasset.model.Ekyc;

/**
 *
 * @author Kin Tu
 */
public class EkycInternalFrame extends javax.swing.JInternalFrame {

    /**
     * Creates new form EkycInternalFrame
     */
    private EkycClient ekycClient;
    private ButtonGroup radioGroup;

    public EkycInternalFrame(String branchInfo) {
        this.ekycClient = new EkycClient(branchInfo);
        initComponents();
        setupRadioButtons();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        loadData("all"); // Load tất cả dữ liệu khi khởi tạo
    }

    private void setupRadioButtons() {
        radioGroup = new ButtonGroup();
        radioGroup.add(jRadioButton1);
        radioGroup.add(jRadioButton2);
    }

    private void loadData(String filter) {
        List<Ekyc> ekycList;
        switch (filter) {
            case "pending":
                ekycList = ekycClient.getEkycData("pending");
                break;
            case "processed":
                ekycList = ekycClient.getEkycData("processed");
                break;
            case "all":
            default:
                ekycList = ekycClient.getEkycData("all");
                break;
        }
        updateTable(ekycList);
    }

    private void updateTable(List<Ekyc> ekycList) {
        DefaultTableModel model = (DefaultTableModel) tblEkyc.getModel();
        model.setRowCount(0);

        for (Ekyc ekyc : ekycList) {
            Object[] row = {
                ekyc.getUserAccount(),
                ekyc.getFullnName(),
                ekyc.getStatus(),
                ekyc.getReview(),
                ekyc.getCreatetAt(),
                ekyc.getCommitTime()
            };
            model.addRow(row);
        }
    }

    private void updateSelectedEkyc() {
        int selectedRow = tblEkyc.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi để cập nhật.");
            return;
        }

        String status = (String) tblEkyc.getValueAt(selectedRow, 2); // Assuming status is in the third column
        if (status != null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng chưa được duyệt!");
            return;
        }

        String userAccount = (String) tblEkyc.getValueAt(selectedRow, 0);
        String review = revewJTextField.getText();
        String newStatus = jRadioButton1.isSelected() ? "Đồng ý" : "Từ chối";

        // Kiểm tra xem có radio button nào được chọn không
        if (!jRadioButton1.isSelected() && !jRadioButton2.isSelected()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn trạng thái (Đồng ý hoặc Từ chối).");
            return;
        }

        // Kiểm tra xem đã nhập đánh giá chưa
        if (review.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đánh giá.");
            return;
        }

        boolean success = ekycClient.updateEkyc(userAccount, review, newStatus);
        if (success) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
             // Xóa nội dung của revewJTextField
            revewJTextField.setText("");

            // Bỏ chọn các jRadioButton
            radioGroup.clearSelection();
            
            loadData("all");
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại. Vui lòng thử lại.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEkyc = new javax.swing.JTable();
        updateButton = new javax.swing.JButton();
        revewJTextField = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        btnPending = new javax.swing.JButton();
        btnGetAll = new javax.swing.JButton();
        btnProcessed = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(202, 114, 40));
        jLabel1.setText("THÔNG TIN EKYC");

        tblEkyc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblEkyc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Trạng thái", "Đánh giá", "Ngày tạo", "Ngày duyệt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEkyc.setRowHeight(25);
        jScrollPane1.setViewportView(tblEkyc);
        if (tblEkyc.getColumnModel().getColumnCount() > 0) {
            tblEkyc.getColumnModel().getColumn(0).setResizable(false);
            tblEkyc.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblEkyc.getColumnModel().getColumn(1).setResizable(false);
            tblEkyc.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblEkyc.getColumnModel().getColumn(2).setResizable(false);
            tblEkyc.getColumnModel().getColumn(2).setPreferredWidth(75);
            tblEkyc.getColumnModel().getColumn(3).setResizable(false);
            tblEkyc.getColumnModel().getColumn(3).setPreferredWidth(75);
            tblEkyc.getColumnModel().getColumn(4).setResizable(false);
            tblEkyc.getColumnModel().getColumn(4).setPreferredWidth(75);
            tblEkyc.getColumnModel().getColumn(5).setResizable(false);
            tblEkyc.getColumnModel().getColumn(5).setPreferredWidth(75);
        }

        updateButton.setText("Xác nhận");
        updateButton.setMaximumSize(new java.awt.Dimension(80, 24));
        updateButton.setMinimumSize(new java.awt.Dimension(80, 24));
        updateButton.setPreferredSize(new java.awt.Dimension(80, 24));
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        revewJTextField.setMinimumSize(new java.awt.Dimension(64, 30));
        revewJTextField.setPreferredSize(new java.awt.Dimension(64, 30));

        jRadioButton1.setText("Đồng ý");

        jRadioButton2.setText("Từ chối");

        btnPending.setText("Chưa duyệt");
        btnPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPendingActionPerformed(evt);
            }
        });

        btnGetAll.setText("Tất cả");
        btnGetAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllActionPerformed(evt);
            }
        });

        btnProcessed.setText("Đã duyệt");
        btnProcessed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(711, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGetAll, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPending)
                                .addGap(18, 18, 18)
                                .addComponent(btnProcessed)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2)
                                .addGap(4, 4, 4)
                                .addComponent(revewJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(revewJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2)
                        .addComponent(btnPending)
                        .addComponent(btnGetAll)
                        .addComponent(btnProcessed)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        updateSelectedEkyc();
    }//GEN-LAST:event_updateButtonActionPerformed

    private void btnGetAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllActionPerformed
        // TODO add your handling code here:
        loadData("all");
    }//GEN-LAST:event_btnGetAllActionPerformed

    private void btnPendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPendingActionPerformed
        // TODO add your handling code here:
        loadData("pending");
    }//GEN-LAST:event_btnPendingActionPerformed

    private void btnProcessedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessedActionPerformed
        // TODO add your handling code here:
        loadData("processed");
    }//GEN-LAST:event_btnProcessedActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGetAll;
    private javax.swing.JButton btnPending;
    private javax.swing.JButton btnProcessed;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField revewJTextField;
    private javax.swing.JTable tblEkyc;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
