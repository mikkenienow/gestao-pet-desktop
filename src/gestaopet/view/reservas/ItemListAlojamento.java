package gestaopet.view.reservas;

public class ItemListAlojamento extends javax.swing.JPanel {

    public ItemListAlojamento(String al, String ci, String co) {
        initComponents();
        alojamento.setText(al);
        checkin.setText(ci);
        checkout.setText(co);
    }

    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkout = new javax.swing.JLabel();
        alojamento = new javax.swing.JLabel();
        checkin = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        checkout.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        checkout.setText("dd/mm/aaaa");
        add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 90, 30));

        alojamento.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        alojamento.setText("jLabel1");
        add(alojamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 70, 30));

        checkin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        checkin.setText("dd/mm/aaaa");
        add(checkin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 80, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alojamento;
    private javax.swing.JLabel checkin;
    private javax.swing.JLabel checkout;
    // End of variables declaration//GEN-END:variables
}
