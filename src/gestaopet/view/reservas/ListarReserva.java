package gestaopet.view.reservas;

import gestaopet.DB.ReservaDB;
import gestaopet.classes.DateTools;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.Reserva;
import gestaopet.components.DataPicker;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarReserva extends ModalPanel {
    private List<Reserva> listareserva = new ArrayList<>();
    private int idpet = -1;
    private DataPicker dataInicial; 
    private DataPicker dataFinal;

    public ListarReserva() {
        initComponents();
    }
    
    public ListarReserva(boolean modal) {
        initComponents();
        Dimension d = new Dimension(415,450);
        setPreferredSize(d);
        setSize(d);
        start();
        filter();
    }
    
    public void setPetId(int id){
        idpet = id;
    }
    
    public void dataPickerInicial(){
        try {
            dataInicial.show(true);
        } catch (Exception e) {
            dataInicial  = new DataPicker(di);
        }
    }
    
    public void dataPickerFinal(){
        try {
            dataFinal.show(true);
        } catch (Exception e) {
            dataFinal  = new DataPicker(df);
        }
    }
    
    public void filter(){
        area.removeAll();
        listareserva.clear();
        Date i = DateTools.stringToDate(di.getText(), "00:00");
        Date f = DateTools.stringToDate(df.getText(), "00:00");
        listareserva = ReservaDB.getAll(i, f, idpet, checkinB.isSelected());

        Dimension d1 = new Dimension(380, 40);
        Dimension d2 = new Dimension(380, listareserva.size()*40);
        area.setSize(d2);
        area.setPreferredSize(d2);
        int y = 0;
        for(int j = 0; j < listareserva.size(); j++){
            ItemList il = new ItemList(listareserva.get(j), checkinB.isSelected(), this);
            area.add(il);
            il.setVisible(true);
            il.setSize(d1);
            il.setPreferredSize(d1);
            il.setLocation(0, y);
            y = y + 40;
        }
        area.repaint();
        area.revalidate();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Checks = new javax.swing.ButtonGroup();
        di = new javax.swing.JTextField();
        df = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        checkinB = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        di.setEditable(false);
        di.setBackground(new java.awt.Color(255, 255, 255));
        di.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        di.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        di.setText("01/01/2022");
        di.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diMouseClicked(evt);
            }
        });
        di.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diActionPerformed(evt);
            }
        });

        df.setEditable(false);
        df.setBackground(new java.awt.Color(255, 255, 255));
        df.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        df.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        df.setText("01/05/2023");
        df.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dfMouseClicked(evt);
            }
        });
        df.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dfActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Checks.add(checkinB);
        checkinB.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        checkinB.setSelected(true);
        checkinB.setText("Check In");
        checkinB.setContentAreaFilled(false);
        checkinB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkinB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkinBItemStateChanged(evt);
            }
        });
        jPanel1.add(checkinB, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 20));

        Checks.add(jRadioButton2);
        jRadioButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jRadioButton2.setText("Check Out");
        jRadioButton2.setContentAreaFilled(false);
        jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, 20));

        area.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(area);

        jToggleButton1.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButton1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("x");
        jToggleButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(di, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(df, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(di, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(df, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dfActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        close();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void diActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diActionPerformed

    private void diMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diMouseClicked
        dataPickerInicial();
    }//GEN-LAST:event_diMouseClicked

    private void dfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dfMouseClicked
        dataPickerFinal();
    }//GEN-LAST:event_dfMouseClicked

    private void checkinBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkinBItemStateChanged
        filter();
    }//GEN-LAST:event_checkinBItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Checks;
    private javax.swing.JPanel area;
    private javax.swing.JRadioButton checkinB;
    private javax.swing.JTextField df;
    private javax.swing.JTextField di;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
