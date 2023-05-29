package gestaopet.view.reservas;

import gestaopet.DB.PetDB;
import gestaopet.DB.ReservaDB;
import gestaopet.V;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.Pet;
import gestaopet.classes.Reserva;
import gestaopet.components.InputField;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VisualizarReserva extends ModalPanel {
    private Reserva reserva;
    private JLabel valor = new JLabel();
    private JPanel origin;
    private boolean included;
    public VisualizarReserva(Reserva reserva, JPanel origin, boolean included) {
        initComponents();
        Dimension d = new Dimension(400,500);
        setPreferredSize(d);
        setSize(d);
        start();
        this.reserva = reserva;
        this.origin = origin;
        this.included = included;
        alert.setVisible(included);
        load();
    }
    
    public void load(){
        Pet p = PetDB.getById(reserva.getPet());
        pet.setText(p.getNome());
        status.setText(reserva.getStatus());
        checkin.setText(reserva.getCheckinString());
        checkout.setText(reserva.getCheckoutString());
        diaria.setText("R$ " + reserva.getValorString() + "  -  " + reserva.getDiasTotais() + " dia(s)");
        total.setText("R$ " + reserva.getTotal());
        situacao.setText("A fazer");
        canil.setText(reserva.getCanil());
        token.setText("#" + reserva.getToken());
        valor.setText(reserva.getValorString());
        save.setEnabled(!included);
        status.setEnabled(!included);
        diaria.setEnabled(!included);
        if(included) jLabel3.setText("Pet principal");
        list();
    }
    
    public void list(){
        List<Reserva> listareserva = ReservaDB.getByToken(reserva.getToken());
        Dimension d1 = new Dimension(380, 30);
        Dimension d2 = new Dimension(380, listareserva.size()*30);
        area.setSize(d2);
        area.setPreferredSize(d2);
        int y = 0;
        for(int j = 0; j < listareserva.size(); j++){
            Reserva r = listareserva.get(j);
            ItemListAlojamento il = new ItemListAlojamento(r.getAlojamentoTitle(), r.getCheckinString(), r.getCheckoutString());
            area.add(il);
            il.setVisible(true);
            il.setSize(d1);
            il.setPreferredSize(d1);
            il.setLocation(0, y);
            y = y + 31;
        }
        area.repaint();
        area.revalidate();
    }
    
    public void editValor(){
        InputField ifd = new InputField("valor", this.valor, this, "valor");
    }
    
    public void salvar(){
        ReservaDB.update(reserva);
        ((ListarReserva)origin).filter();
        close();
    }
    
    @Override
    public void setInput(String input){
        switch(input){
            case "valor" :
                setValor();
                break;
        }
    }
    
    public void setValor(){
        reserva.setValor(Double.parseDouble(valor.getText().replaceAll(",", ".")));
        diaria.setText("R$ " + reserva.getValorString() + "  -  " + reserva.getDiasTotais() + " dia(s)");
        total.setText("R$ " + reserva.getTotal());
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        save = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        token = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        checkin = new javax.swing.JLabel();
        checkout = new javax.swing.JLabel();
        situacao = new javax.swing.JLabel();
        canil = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        diaria = new javax.swing.JButton();
        pet = new javax.swing.JButton();
        status = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        alert = new javax.swing.JLabel();

        jScrollPane2.setViewportView(jTextPane1);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        save.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/salvarP0.png"))); // NOI18N
        save.setBorderPainted(false);
        save.setContentAreaFilled(false);
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 455, 102, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(15, 59, 146));
        jLabel2.setText("Reserva");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        token.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        token.setForeground(new java.awt.Color(80, 176, 230));
        token.setText("#Token");
        add(token, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 35, 270, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(15, 59, 146));
        jLabel3.setText("Pet");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(15, 59, 146));
        jLabel4.setText("Status");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(15, 59, 146));
        jLabel5.setText("Checkin");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(15, 59, 146));
        jLabel6.setText("Checkout");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 145, -1, 20));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(15, 59, 146));
        jLabel7.setText("Diária");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, -1, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(15, 59, 146));
        jLabel8.setText("Situação");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, -1, 20));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 59, 146));
        jLabel9.setText("Canil");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 20));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alojamentos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(15, 59, 146))); // NOI18N
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        area.setBackground(new java.awt.Color(255, 255, 255));
        area.setForeground(new java.awt.Color(15, 59, 146));
        area.setPreferredSize(new java.awt.Dimension(377, 5));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 128, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(area);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 380, 150));

        checkin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        checkin.setForeground(new java.awt.Color(15, 59, 146));
        checkin.setText("Checkin");
        add(checkin, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 230, 20));

        checkout.setBackground(new java.awt.Color(255, 255, 255));
        checkout.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        checkout.setForeground(new java.awt.Color(15, 59, 146));
        checkout.setText("Checkout");
        checkout.setOpaque(true);
        add(checkout, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 145, 230, 20));

        situacao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        situacao.setForeground(new java.awt.Color(15, 59, 146));
        situacao.setText("Situação");
        add(situacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 220, 230, 20));

        canil.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        canil.setForeground(new java.awt.Color(15, 59, 146));
        canil.setText("Canil");
        add(canil, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 230, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(15, 59, 146));
        jLabel11.setText("Total");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 195, 70, 20));

        total.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(15, 59, 146));
        total.setText("total");
        add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 195, 230, 20));

        diaria.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        diaria.setForeground(new java.awt.Color(15, 59, 146));
        diaria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/icons8_edit_20px.png"))); // NOI18N
        diaria.setText("Diaria");
        diaria.setAlignmentY(0.0F);
        diaria.setBorderPainted(false);
        diaria.setContentAreaFilled(false);
        diaria.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        diaria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        diaria.setIconTextGap(5);
        diaria.setMargin(new java.awt.Insets(1, 1, 1, 1));
        diaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diariaActionPerformed(evt);
            }
        });
        add(diaria, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 260, 20));

        pet.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pet.setForeground(new java.awt.Color(15, 59, 146));
        pet.setText("Pet");
        pet.setAlignmentY(0.0F);
        pet.setBorderPainted(false);
        pet.setContentAreaFilled(false);
        pet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        pet.setIconTextGap(0);
        pet.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pet.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/icons8_no_edit_20px_1.png"))); // NOI18N
        pet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petActionPerformed(evt);
            }
        });
        add(pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 70, 230, 20));

        status.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        status.setForeground(new java.awt.Color(15, 59, 146));
        status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/icons8_edit_20px.png"))); // NOI18N
        status.setText("Status");
        status.setAlignmentY(0.0F);
        status.setBorderPainted(false);
        status.setContentAreaFilled(false);
        status.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        status.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        status.setIconTextGap(5);
        status.setMargin(new java.awt.Insets(1, 1, 1, 1));
        status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusActionPerformed(evt);
            }
        });
        add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 95, 260, 20));

        jButton2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarP0.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 455, 102, 40));

        alert.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(255, 51, 51));
        alert.setText("(Para alterar esta reserva, abra pelo cadastro do pet principal)");
        add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 360, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        salvar();
    }//GEN-LAST:event_saveActionPerformed

    private void diariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diariaActionPerformed
        editValor();
    }//GEN-LAST:event_diariaActionPerformed

    private void statusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_petActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private javax.swing.JPanel area;
    private javax.swing.JLabel canil;
    private javax.swing.JLabel checkin;
    private javax.swing.JLabel checkout;
    private javax.swing.JButton diaria;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JButton pet;
    private javax.swing.JButton save;
    private javax.swing.JLabel situacao;
    private javax.swing.JButton status;
    private javax.swing.JLabel token;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
