package gestaopet.view.New.financeiro;

import gestaopet.DB.FinanceiroDB;
import gestaopet.V;
import gestaopet.classes.DateTools;
import gestaopet.classes.Estoque;
import gestaopet.classes.Financeiro;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.InputTools;
import java.awt.Color;
import javax.swing.BorderFactory;

public class FinanceiroItemList extends GlobalPanel {
    private Financeiro financeiro;
    private EditRateModal np;
    
    public FinanceiroItemList(Financeiro financeiro) {
        initComponents();
        this.financeiro = financeiro; 
        
        Color borderColor = new Color(215, 215, 215);;
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, borderColor));
        
        this.setBackground(Color.white);
        editButton.setBackground(Color.white);
        payedButton.setBackground(Color.white);
        setAll();
    }
    
    public void setAll(){
        categoriaLabel.setText(financeiro.getCategoria());
        descricaoLabel.setText(financeiro.getDescricao());
        tipoLabel.setText(InputTools.getStringValor(financeiro.getTaxa()).replace("-", ""));
        cifra.setText((financeiro.getTaxa() >=
                0)? "R$":"-R$");
        valorTotalLabel.setText(InputTools.getStringValor((financeiro.getValor() + (financeiro.getTaxa()))));
        valorLabel.setText(InputTools.getStringValor(financeiro.getValor()));
        dataLabel.setText(DateTools.dateToString(financeiro.getDataLancamento())); 
        if(financeiro.getStatusPagamento() == 1){
            jButton1.setVisible(false);
            payedButton.setVisible(false);
            editButton.setVisible(false);
        }else{
            desfazerButton.setVisible(false);
            quitadoButton.setVisible(false);
        }
        if(financeiro.getIshide() == 1){
            desfazerButton.setEnabled(false);
            quitadoButton.setEnabled(false);
            payedButton.setEnabled(false);
            editButton.setEnabled(false);
            jButton1.setBackground(Color.GREEN);
            jButton1.setForeground(Color.BLACK);
            jButton1.setText("<");
        }
    }
    
    public void openDialog() {
        try {
            np.show(true);
        } catch (Exception e) {
            EditRateModal np = new EditRateModal(this.financeiro);
        }
    }
    
    public void excluir(){
        if(financeiro.getIshide() == 1){
            ReexibirModal rm = new ReexibirModal(financeiro);
        } else {
            ExcluirOcultarModal eom = new ExcluirOcultarModal(this.financeiro);
        }
        
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        tipoLabel = new javax.swing.JLabel();
        categoriaLabel = new javax.swing.JLabel();
        descricaoLabel = new javax.swing.JLabel();
        dataLabel = new javax.swing.JLabel();
        payedButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        valorLabel = new javax.swing.JLabel();
        valorTotalLabel = new javax.swing.JLabel();
        desfazerButton = new javax.swing.JButton();
        quitadoButton = new javax.swing.JButton();
        valorLabel1 = new javax.swing.JLabel();
        cifra = new javax.swing.JLabel();
        valorLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel4.setText("jLabel1");

        jButton2.setText("VER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setPreferredSize(new java.awt.Dimension(1300, 30));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tipoLabel.setBackground(new java.awt.Color(204, 255, 255));
        tipoLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tipoLabel.setText("Taxa");
        add(tipoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 10, 75, 20));

        categoriaLabel.setBackground(new java.awt.Color(204, 255, 255));
        categoriaLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        categoriaLabel.setText("Categoria");
        add(categoriaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 20));

        descricaoLabel.setBackground(new java.awt.Color(204, 255, 255));
        descricaoLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        descricaoLabel.setText("Descrição");
        add(descricaoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 190, 20));

        dataLabel.setBackground(new java.awt.Color(204, 255, 255));
        dataLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        dataLabel.setText("Data");
        add(dataLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 110, 20));

        payedButton.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        payedButton.setForeground(new java.awt.Color(0, 102, 204));
        payedButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/quitar0.png"))); // NOI18N
        payedButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        payedButton.setBorderPainted(false);
        payedButton.setContentAreaFilled(false);
        payedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payedButtonActionPerformed(evt);
            }
        });
        add(payedButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 100, 40));

        editButton.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 14)); // NOI18N
        editButton.setForeground(new java.awt.Color(0, 153, 0));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/editar0.png"))); // NOI18N
        editButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true));
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        add(editButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 100, 40));

        valorLabel.setBackground(new java.awt.Color(204, 255, 255));
        valorLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        valorLabel.setText("00,00");
        add(valorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 60, 20));

        valorTotalLabel.setBackground(new java.awt.Color(204, 255, 255));
        valorTotalLabel.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        valorTotalLabel.setText("Valor");
        add(valorTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 80, 20));

        desfazerButton.setFont(new java.awt.Font("Microsoft JhengHei Light", 1, 14)); // NOI18N
        desfazerButton.setForeground(new java.awt.Color(0, 153, 0));
        desfazerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/desfazer0.png"))); // NOI18N
        desfazerButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true));
        desfazerButton.setBorderPainted(false);
        desfazerButton.setContentAreaFilled(false);
        desfazerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desfazerButtonActionPerformed(evt);
            }
        });
        add(desfazerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 0, 100, 40));

        quitadoButton.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 1, 14)); // NOI18N
        quitadoButton.setForeground(new java.awt.Color(0, 102, 204));
        quitadoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/quitado0.png"))); // NOI18N
        quitadoButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        quitadoButton.setBorderPainted(false);
        quitadoButton.setContentAreaFilled(false);
        quitadoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitadoButtonActionPerformed(evt);
            }
        });
        add(quitadoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 100, 40));

        valorLabel1.setBackground(new java.awt.Color(204, 255, 255));
        valorLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        valorLabel1.setText("R$");
        add(valorLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 20, 20));

        cifra.setBackground(new java.awt.Color(204, 255, 255));
        cifra.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        cifra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cifra.setText("R$");
        add(cifra, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 30, 20));

        valorLabel3.setBackground(new java.awt.Color(204, 255, 255));
        valorLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        valorLabel3.setText("R$");
        add(valorLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 20, 20));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorderPainted(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 5, 30, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void payedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payedButtonActionPerformed
        FinanceiroDB.setPayed(this.financeiro, true);
        V.fin.loadList();
    }//GEN-LAST:event_payedButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        openDialog();
    }//GEN-LAST:event_editButtonActionPerformed

    private void desfazerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desfazerButtonActionPerformed
        FinanceiroDB.setPayed(this.financeiro, false);
        V.fin.loadList();
    }//GEN-LAST:event_desfazerButtonActionPerformed

    private void quitadoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitadoButtonActionPerformed
        // TODO add your handling code here:
        // Show modal
    }//GEN-LAST:event_quitadoButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        excluir();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel categoriaLabel;
    private javax.swing.JLabel cifra;
    private javax.swing.JLabel dataLabel;
    private javax.swing.JLabel descricaoLabel;
    private javax.swing.JButton desfazerButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton payedButton;
    private javax.swing.JButton quitadoButton;
    private javax.swing.JLabel tipoLabel;
    private javax.swing.JLabel valorLabel;
    private javax.swing.JLabel valorLabel1;
    private javax.swing.JLabel valorLabel3;
    private javax.swing.JLabel valorTotalLabel;
    // End of variables declaration//GEN-END:variables
}
