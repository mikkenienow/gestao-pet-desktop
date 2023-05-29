package gestaopet.components;

import gestaopet.V;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.ModalPanel;
import java.awt.Dimension;

public class DeleteEntity extends ModalPanel {
    private GlobalPanel entity;
    public boolean back = true;
    
    public DeleteEntity(String msg, GlobalPanel entity) {
        initComponents();
        Dimension d = new Dimension(285,170);
        setPreferredSize(d);
        setSize(d);
        alertMsg.setText(msg);
        this.entity = entity;
        start();
    }
    
    public DeleteEntity(String msg, GlobalPanel entity, boolean back) {
        initComponents();
        this.back = back;
        Dimension d = new Dimension(285,170);
        setPreferredSize(d);
        setSize(d);
        alertMsg.setText(msg);
        this.entity = entity;
        start();
    }
    
    
    public void confirm(){
        this.entity.delete();
        if(this.back){
            this.entity.back();
            this.entity.back();
        }
        V.reloadAllEntityList();
        close();
    }
    
    public void cancel(){
        close();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancel = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        alertMsg = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(280, 120));
        setMinimumSize(new java.awt.Dimension(280, 120));

        cancel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarP0.png"))); // NOI18N
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        confirm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/confirmarP1.png"))); // NOI18N
        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.setMargin(new java.awt.Insets(0, 0, 0, 0));
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        alertMsg.setEditable(false);
        alertMsg.setColumns(20);
        alertMsg.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        alertMsg.setLineWrap(true);
        alertMsg.setRows(5);
        alertMsg.setText("SADADADADADADADADADADADADADADADADADADADADADADADADADADAD");
        alertMsg.setWrapStyleWord(true);
        alertMsg.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(alertMsg)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(alertMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        confirm();
    }//GEN-LAST:event_confirmActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        cancel();
    }//GEN-LAST:event_cancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alertMsg;
    private javax.swing.JButton cancel;
    private javax.swing.JButton confirm;
    // End of variables declaration//GEN-END:variables
}
