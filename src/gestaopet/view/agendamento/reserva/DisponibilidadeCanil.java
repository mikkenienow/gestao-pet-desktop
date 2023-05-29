package gestaopet.view.agendamento.reserva;


import gestaopet.V;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.Pet;
import gestaopet.reservas.AgendamentoFracionado;
import java.awt.Dimension;

public class DisponibilidadeCanil extends ModalPanel{
    //private SpecialModal parent;
    private Object[] list;
    public DisponibilidadeCanil(Object[] list) {
        //bo = new Blackout(this);
        initComponents();
        this.list = list;
        Dimension d = new Dimension(265,150);
        setPreferredSize(d);
        setSize(d);
        start();
    }
    
    public void confirm(){
        //V.petReg.delete();
        
        AgendamentoFracionado af = new AgendamentoFracionado();
        //af.reservando((Pet)list[0], (int)list[1], (String)list[2], (String)list[3]);
        if(af.reservando((Pet)list[0], (int)list[1], (String)list[2], (String)list[3]).size() == 0){
            V.bkw.reservaFracionada(false);
            close();
            V.showMessage("Não foi possível realizar o agendamento para o canil selecionado por indisponibilidade no período escolhido, escolha outro canil e tente novamente.");
            V.bkw.back();
        } else {
            V.bkw.reservaFracionada(true);
            V.bkw.setReserva(af.getReserva());
            close();
        }

        
        
    }

    public void cancel(){
        close();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancel = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        jTextArea1 = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 2));
        setForeground(new java.awt.Color(153, 153, 153));
        setMaximumSize(new java.awt.Dimension(280, 120));
        setMinimumSize(new java.awt.Dimension(280, 120));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarP0.png"))); // NOI18N
        cancel.setToolTipText("");
        cancel.setBorderPainted(false);
        cancel.setContentAreaFilled(false);
        cancel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 102, 40));

        confirm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        confirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/confirmarP0.png"))); // NOI18N
        confirm.setBorderPainted(false);
        confirm.setContentAreaFilled(false);
        confirm.setMargin(new java.awt.Insets(0, 0, 0, 0));
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 101, 40));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Não há um único alojamento disponível para o período escolhido, deseja que sistema combine em mais de um alojamento?");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 230, 88));
    }// </editor-fold>//GEN-END:initComponents

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        confirm();
    }//GEN-LAST:event_confirmActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        cancel();
    }//GEN-LAST:event_cancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JButton confirm;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}