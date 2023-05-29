package gestaopet.view.pessoa;

import gestaopet.classes.ModalPanel;
import java.awt.Dimension;

public class VisualizarSaldo extends ModalPanel {
    private int idCliente = 9999;

    public VisualizarSaldo(int idCliente) {
        initComponents();
        Dimension d = new Dimension(400,500);
        setPreferredSize(d);
        setSize(d);
        start();
        this.idCliente = idCliente;
        load();
    }
    
    public void load(){

    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jScrollPane2.setViewportView(jTextPane1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 59, 146));
        jLabel2.setText("Saldo do cliente");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(15, 59, 146));
        jLabel11.setText("Total em aberto:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 20));

        total.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(15, 59, 146));
        total.setText("total");
        add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 210, 20));

        jButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/confirmarP0.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 102, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        close();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
