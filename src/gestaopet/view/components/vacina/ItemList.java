
package gestaopet.view.components.vacina;

import gestaopet.DB.VacinaDB;
import gestaopet.classes.DateTools;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.Vacina;
import java.awt.Color;

public class ItemList extends javax.swing.JPanel {
    int vacinaid;
    String nome;
    String data;
    ModalPanel parent;
    
    public ItemList(Vacina vacina, ModalPanel parent) {
        initComponents();
        this.vacinaid = vacina.getIdvacina();
        this.nome = vacina.getNome();
        this.data = DateTools.SQLToString(vacina.getData(), "00:00");
        this.parent = parent;
        setVacina();
    }
    
    public void setVacina(){
        nomeLabel.setText(this.nome);
        dataLabel.setText(this.data);
    }

    public void excluirVacina(){
        VacinaDB.delete(vacinaid);
        ((AdicionarVacina)parent).listarVacinas();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataLabel = new javax.swing.JLabel();
        nomeLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dataLabel.setText("dd/mm/aaaa");
        add(dataLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 100, 40));

        nomeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        nomeLabel.setText("nome_vacina");
        add(nomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 150, 40));

        jButton1.setText("Excluir");
        jButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 70, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        setBackground(new Color(204,204,255));
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        setBackground(Color.WHITE);
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setBackground(Color.CYAN);
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        setBackground(Color.WHITE);
    }//GEN-LAST:event_formMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        excluirVacina();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dataLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel nomeLabel;
    // End of variables declaration//GEN-END:variables

}
