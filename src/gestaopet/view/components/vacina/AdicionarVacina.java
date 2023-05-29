
package gestaopet.view.components.vacina;

import gestaopet.components.DataPicker;
import gestaopet.DB.VacinaDB;
import gestaopet.classes.DateTools;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.Vacina;
import gestaopet.enums.DateMethods;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;


public class AdicionarVacina extends ModalPanel {
    int idpet;
    DataPicker dp;
    List<Vacina> vacinas = new ArrayList<>();
    
    public AdicionarVacina(int idpet) {
        initComponents();
        Dimension d = new Dimension(400,450);
        setPreferredSize(d);
        setSize(d);
        dataField.setText(DateTools.dateToString(DateTools.today));
        this.idpet = idpet;
        dataField.setVisible(false);
        start();
        listarVacinas();
        
        
    }
    
    public void listarVacinas(){
        area.removeAll();
        this.vacinas = VacinaDB.getAllByID(idpet);
        int altura = (this.vacinas.size() * 40) + 20;
        Dimension d = new Dimension(area.getSize().width-50, altura);
        area.setPreferredSize(d);
        area.setSize(d);
        int pos = 0;
        for(int i = 0; i < this.vacinas.size(); i ++){
            ItemList l = new ItemList(this.vacinas.get(i), this);
            l.setFont(new Font("Segoe UI", 0, 18));
            area.add(l);
            l.setVisible(true);
            l.setSize(400, 40);
            l.setPreferredSize( new Dimension(400, 40));
            l.setLocation(0, pos);
            pos = pos + 40;
        }

    }
    
    public void adicionarVacina(){
        String data = "";
        try {
            data = dataField.getText();
        } catch (Exception e) {
        }
        
        VacinaDB.insert(new Vacina(
                nomeVacina.getText(),
                idpet,
                dataField.getText()
        ));
        listarVacinas();
        clearAll();
    }

    public void clearAll(){
        nomeVacina.setText("");
        dp = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeVacina = new javax.swing.JTextField();
        adicionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        dataField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nomeVacina.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        add(nomeVacina, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 240, 40));

        adicionar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        adicionar.setText("Adicionar");
        adicionar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarActionPerformed(evt);
            }
        });
        add(adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 80, 40));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Vacinação do pet");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 380, -1));

        area.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 318, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(area);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 380, 320));

        jButton2.setBackground(new java.awt.Color(255, 51, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("X");
        jButton2.setAlignmentY(0.0F);
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(355, 10, 30, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/icons8_calendar_40px.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 40, 40));
        add(dataField, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 50, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            dp.show(true);
        } catch (Exception e) {
            dp = new DataPicker(dataField);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void adicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarActionPerformed
        adicionarVacina();
    }//GEN-LAST:event_adicionarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionar;
    private javax.swing.JPanel area;
    private javax.swing.JTextField dataField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nomeVacina;
    // End of variables declaration//GEN-END:variables
}
