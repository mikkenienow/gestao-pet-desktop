package gestaopet.view.New.financeiro;

import gestaopet.DB.FinanceiroDB;
import gestaopet.V;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.FinanceiroItem;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;

public class PendencyModal extends ModalPanel {
    private List<FinanceiroItem> financeiroList = new ArrayList<>();
    private List<JCheckBox> selection = new ArrayList<>();
    public PendencyModal() {
        initComponents();        
        Dimension d = new Dimension(895, 650);
        setPreferredSize(d);
        setSize(d);
        start();
        financeiroList = null;
        loadList();
        jScrollPane1.setHorizontalScrollBarPolicy(jScrollPane1.HORIZONTAL_SCROLLBAR_NEVER);
        //parent = V.newSpecialModal(this);
    }
    
    public void selectAll(boolean select){
        for(int i = 0; i < selection.size(); i++){
            selection.get(i).setSelected(select);
        }
    }
    
    public void confirmAll() {
        for (int i = 0; i < financeiroList.size(); i++) {
            if (selection.get(i).isSelected()) {
                FinanceiroDB.addFInanceiroItem(financeiroList.get(i));
            }
        }
        V.fin.np.loadList();
    }
    
    
    public void loadList() {
        int y = 0;
        financeiroList = FinanceiroDB.getPendency();
        
        area.removeAll();
        area.setPreferredSize(new Dimension(880, 42 * financeiroList.size()));
        area.setSize(new Dimension(880, y * financeiroList.size()));
        area.setBackground(Color.white);
        
        for (int i = 0; i < financeiroList.size(); i++) {
            PendencyItemList financeiroItem = new PendencyItemList(financeiroList.get(i));
            area.add(financeiroItem);
            selection.add(financeiroItem.select);
            financeiroItem.setLocation(0, y);
            financeiroItem.setPreferredSize(new Dimension(1099, 42));
            financeiroItem.setSize(new Dimension(1099, 42));
            
            y = y + 42;
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        dataLabel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        valorLabel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        taxaLabel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        porteLabel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        donoLabel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nomeLabel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Lançar pendências");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 245, -1));

        jButton1.setBackground(new java.awt.Color(255, 0, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(865, 0, 30, 20));

        jScrollPane1.setBorder(null);

        area.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 834, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(area);

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataLabel.setBackground(new java.awt.Color(255, 255, 255));
        dataLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dataLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dataLabelMouseExited(evt);
            }
        });
        dataLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel19.setText("jLabel1");
        dataLabel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 59, 146));
        jLabel20.setText("Pessoa");
        dataLabel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel3.add(dataLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 210, 30));

        valorLabel.setBackground(new java.awt.Color(255, 255, 255));
        valorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valorLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valorLabelMouseExited(evt);
            }
        });
        valorLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel17.setText("jLabel1");
        valorLabel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(15, 59, 146));
        jLabel18.setText("Data");
        valorLabel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel3.add(valorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 0, 90, 30));

        taxaLabel.setBackground(new java.awt.Color(255, 255, 255));
        taxaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taxaLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taxaLabelMouseExited(evt);
            }
        });
        taxaLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel15.setText("jLabel1");
        taxaLabel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 59, 146));
        jLabel16.setText("Valor");
        taxaLabel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel3.add(taxaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 80, 30));

        porteLabel.setBackground(new java.awt.Color(255, 255, 255));
        porteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                porteLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                porteLabelMouseExited(evt);
            }
        });
        porteLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(15, 59, 146));
        jLabel13.setText("Quantidade");
        porteLabel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel14.setText("jLabel1");
        porteLabel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jPanel3.add(porteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 0, 115, 30));

        donoLabel.setBackground(new java.awt.Color(255, 255, 255));
        donoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                donoLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                donoLabelMouseExited(evt);
            }
        });
        donoLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 59, 146));
        jLabel9.setText("Descrição");
        donoLabel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel10.setText("jLabel1");
        donoLabel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jPanel3.add(donoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(165, 0, 190, 30));

        nomeLabel.setBackground(new java.awt.Color(255, 255, 255));
        nomeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nomeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nomeLabelMouseExited(evt);
            }
        });
        nomeLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel11.setText("jLabel1");
        nomeLabel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 2, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(15, 59, 146));
        jLabel12.setText("Categoria");
        nomeLabel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel3.add(nomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 135, 30));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setMargin(new java.awt.Insets(5, 5, 5, 5));
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/lancar0.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        close();
        V.fin.loadList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dataLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataLabelMouseEntered
        dataLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_dataLabelMouseEntered

    private void dataLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataLabelMouseExited
        dataLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_dataLabelMouseExited

    private void valorLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valorLabelMouseEntered
        valorLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_valorLabelMouseEntered

    private void valorLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valorLabelMouseExited
        valorLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_valorLabelMouseExited

    private void taxaLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxaLabelMouseEntered
        taxaLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_taxaLabelMouseEntered

    private void taxaLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxaLabelMouseExited
        taxaLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_taxaLabelMouseExited

    private void porteLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_porteLabelMouseEntered
        porteLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_porteLabelMouseEntered

    private void porteLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_porteLabelMouseExited
        porteLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_porteLabelMouseExited

    private void donoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoLabelMouseEntered
        donoLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_donoLabelMouseEntered

    private void donoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoLabelMouseExited
        donoLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_donoLabelMouseExited

    private void nomeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeLabelMouseEntered
        nomeLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_nomeLabelMouseEntered

    private void nomeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeLabelMouseExited
        nomeLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_nomeLabelMouseExited

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        selectAll(jCheckBox1.isSelected());
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        confirmAll();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area;
    private javax.swing.JPanel dataLabel;
    private javax.swing.JPanel donoLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel nomeLabel;
    private javax.swing.JPanel porteLabel;
    private javax.swing.JPanel taxaLabel;
    private javax.swing.JPanel valorLabel;
    // End of variables declaration//GEN-END:variables
}
