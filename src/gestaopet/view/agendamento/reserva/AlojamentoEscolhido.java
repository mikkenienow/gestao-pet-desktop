package gestaopet.view.agendamento.reserva;

import gestaopet.classes.ModalPanel;
import gestaopet.classes.Reserva;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

public class AlojamentoEscolhido extends ModalPanel {
    java.util.List<Reserva> reserva;

    public AlojamentoEscolhido(java.util.List<Reserva> reserva) {
        initComponents();
        this.reserva = reserva;
        Dimension d = new Dimension(460,470);
        setPreferredSize(d);
        setSize(d);
        start();
        listarReserva();
    }
    
    public void listarReserva(){
        int altura = (reserva.size() * 25) + 20;
        Dimension d = new Dimension(area.getSize().width-50, altura);
        area.setPreferredSize(d);
        area.setSize(d);
        int pos = 11;
        for(int i = 0; i < reserva.size(); i ++){
            String a = reserva.get(i).getAlojamentoTitle();
            String c = reserva.get(i).getCanil();
            String chi = reserva.get(i).getCheckinString();
            String cho = reserva.get(i).getCheckoutString();
            setInfo(c + ": " + a, 10, pos,1);
            setInfo(chi, 140, pos,2);
            setInfo(cho, 270, pos,3);
            pos = pos + 25;
        }
    }
    
    public void setInfo(String info, int x, int y, int id){
        JLabel l = new JLabel(info);
        l.setName(id+"");
        l.setFont(new Font("Segoe UI", 0, 18));
        area.add(l);
        l.setVisible(true);
        l.setSize(400, 20);
        l.setPreferredSize( new Dimension(400, 10));
        l.setLocation(x, y);
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        donoLabel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nomeLabel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        porteLabel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        acoesLabel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Alojamento(s) da reserva");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 440, 30));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        area.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(area);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 440, 370));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("X");
        jButton1.setAlignmentY(0.0F);
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 30, 30));

        jPanel2.setBackground(new java.awt.Color(243, 243, 243));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        donoLabel.setBackground(new java.awt.Color(243, 243, 243));
        donoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                donoLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                donoLabelMouseEntered(evt);
            }
        });
        donoLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Checkin");
        donoLabel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel7.setText("jLabel1");
        donoLabel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jPanel2.add(donoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 0, 130, 30));

        nomeLabel.setBackground(new java.awt.Color(243, 243, 243));
        nomeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nomeLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nomeLabelMouseEntered(evt);
            }
        });
        nomeLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel2.setText("jLabel1");
        nomeLabel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 2, -1));

        jLabel3.setText("Alojamento");
        nomeLabel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jPanel2.add(nomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 135, 30));

        porteLabel.setBackground(new java.awt.Color(243, 243, 243));
        porteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                porteLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                porteLabelMouseEntered(evt);
            }
        });
        porteLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setText("Checkout");
        porteLabel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel8.setText("jLabel1");
        porteLabel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jPanel2.add(porteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 0, 180, 30));

        acoesLabel.setBackground(new java.awt.Color(243, 243, 243));
        acoesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                acoesLabelMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                acoesLabelMouseEntered(evt);
            }
        });
        acoesLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel9.setText("jLabel1");
        acoesLabel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel4.setText("Ações");
        acoesLabel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jPanel2.add(acoesLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 0, 200, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 440, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        close();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void donoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoLabelMouseExited
        donoLabel.setBackground(new Color(243,243,243));
    }//GEN-LAST:event_donoLabelMouseExited

    private void donoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoLabelMouseEntered
        donoLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_donoLabelMouseEntered

    private void nomeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeLabelMouseExited
        nomeLabel.setBackground(new Color(243,243,243));
    }//GEN-LAST:event_nomeLabelMouseExited

    private void nomeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeLabelMouseEntered
        nomeLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_nomeLabelMouseEntered

    private void porteLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_porteLabelMouseExited
        porteLabel.setBackground(new Color(243,243,243));
    }//GEN-LAST:event_porteLabelMouseExited

    private void porteLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_porteLabelMouseEntered
        porteLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_porteLabelMouseEntered

    private void acoesLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acoesLabelMouseExited
        acoesLabel.setBackground(new Color(243,243,243));
    }//GEN-LAST:event_acoesLabelMouseExited

    private void acoesLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acoesLabelMouseEntered
        acoesLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_acoesLabelMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acoesLabel;
    private javax.swing.JPanel area;
    private javax.swing.JPanel donoLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel nomeLabel;
    private javax.swing.JPanel porteLabel;
    // End of variables declaration//GEN-END:variables
}
