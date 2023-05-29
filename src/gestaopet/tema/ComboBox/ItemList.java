package gestaopet.tema.ComboBox;

import gestaopet.tema.GlobalItem;
import java.awt.Dimension;
import java.awt.event.MouseEvent;

public class ItemList extends GlobalItem {
    private int id;
    private String nome;
    private GlobalItem cbl;
    
    public ItemList(int id, String nome, GlobalItem cbl, int height) {
        initComponents();
        this.id = id;
        this.nome = nome;
        selecionado.setText(nome );
        this.cbl = cbl;

    }
    
    public void choose(){
        ((ComboBoxList)cbl).getComboBox().setOption(id);
        ((ComboBoxList)cbl).getComboBox().getMenuMode().ib.hideIt();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selecionado = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selecionado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        selecionado.setForeground(new java.awt.Color(15, 59, 146));
        selecionado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        selecionado.setText("item...");
        selecionado.setToolTipText("");
        selecionado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selecionadoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selecionadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selecionadoMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                selecionadoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                selecionadoMouseReleased(evt);
            }
        });
        add(selecionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        mouseEvent(evt);
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        mouseEvent(evt);
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        mouseEvent(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        mouseEvent(evt);
    }//GEN-LAST:event_formMouseReleased

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        choose();
    }//GEN-LAST:event_formMouseClicked

    private void selecionadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionadoMouseClicked
        choose();
    }//GEN-LAST:event_selecionadoMouseClicked

    private void selecionadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionadoMouseEntered
        mouseEvent(evt);
    }//GEN-LAST:event_selecionadoMouseEntered

    private void selecionadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionadoMouseExited
        mouseEvent(evt);
    }//GEN-LAST:event_selecionadoMouseExited

    private void selecionadoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionadoMousePressed
        mouseEvent(evt);
    }//GEN-LAST:event_selecionadoMousePressed

    private void selecionadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selecionadoMouseReleased
        mouseEvent(evt);
    }//GEN-LAST:event_selecionadoMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel selecionado;
    // End of variables declaration//GEN-END:variables
}
