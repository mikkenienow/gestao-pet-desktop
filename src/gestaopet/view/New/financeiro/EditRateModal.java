package gestaopet.view.New.financeiro;

import gestaopet.DB.FinanceiroDB;
import gestaopet.V;
import gestaopet.classes.Financeiro;
import gestaopet.classes.InputTools;
import gestaopet.classes.ModalPanel;
import gestaopet.tema.ComboBox.ComboBox;
import java.awt.Dimension;

public class EditRateModal extends ModalPanel {
    //private SpecialModal parent;
    private Financeiro financeiro;
    ComboBox cb1;
    
    public EditRateModal(Financeiro financeiro) {
        initComponents();
        this.financeiro = financeiro;
        
        
        Dimension d = new Dimension(290, 170);
        setPreferredSize(d);
        setSize(d);
        start();
        int start = 0;
        System.out.println("Financeiro gettaxa: " + financeiro.getTaxa());
        if(financeiro.getTaxa() < 0.0){
            start = 0;
        }else if(financeiro.getTaxa() >= 0.0){
            start = 1;
        }
        
        ComboBox.setTheme(cb1, choice1, new int[] {0,0}, 40,start, target);
        choice1.setSelectedItem(start);
        rateInput.setText(InputTools.getStringValor(financeiro.getTaxa()).replace("-",""));

    }
    
    public void check(){
        double value = Double.valueOf(rateInput.getText().replace(",", "."));
        double itemValue = financeiro.getValor();
        int operationIndex = choice1.getSelectedIndex();
        String operation = null;
        try {
            operation = choice1.getSelectedItem().toString();
        } catch (Exception e) {
        }
        if (operationIndex == 0 && value > itemValue) { //Desconto
            msg.setText("O desconto não pode ser superior a R$ " + InputTools.getStringValor(itemValue));
        } else if (operation == null) {
            msg.setText("Selecione uma operação!");
        } else {
            FinanceiroDB.editRate(financeiro.getIdFinanceiro(), value + "", operation);
            V.nav.setView(13);
            V.fin.loadList();
            close();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rateInput = new javax.swing.JTextField();
        msg = new javax.swing.JLabel();
        target = new javax.swing.JPanel();
        choice1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarP0.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 100, 40));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/confirmarP0.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 100, 40));

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ajustar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(264, 74, -1, -1));

        rateInput.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        rateInput.setBorder(null);
        rateInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateInputActionPerformed(evt);
            }
        });
        rateInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                rateInputKeyTyped(evt);
            }
        });
        add(rateInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 53, 100, 34));

        msg.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        msg.setForeground(new java.awt.Color(255, 0, 0));
        msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 208, 21));

        target.setOpaque(false);

        choice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Desconto", "Acréscimo" }));
        choice1.setSelectedIndex(-1);

        javax.swing.GroupLayout targetLayout = new javax.swing.GroupLayout(target);
        target.setLayout(targetLayout);
        targetLayout.setHorizontalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(targetLayout.createSequentialGroup()
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        targetLayout.setVerticalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(targetLayout.createSequentialGroup()
                .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(target, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 120, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 50, 90, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 90, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void rateInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateInputActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        check();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        V.nav.setView(13);
        V.fin.loadList();
        close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void rateInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rateInputKeyTyped
        rateInput.setText(InputTools.makeDecimal(rateInput.getText(), evt, evt.getKeyChar()+""));
        evt.consume();
    }//GEN-LAST:event_rateInputKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> choice1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel msg;
    private javax.swing.JTextField rateInput;
    private javax.swing.JPanel target;
    // End of variables declaration//GEN-END:variables
}
