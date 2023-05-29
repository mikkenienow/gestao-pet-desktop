package gestaopet.view.pet;

import gestaopet.V;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.Pet;

public class ItemList extends GlobalPanel {
        private Pet pet;
        
    public ItemList(Pet pet) {
        initComponents();
        this.pet = pet;
        petNome.setText(this.pet.getNome());
        donoNome.setText(this.pet.getDonoNome());
        petPorte.setText(this.pet.getPorte()+"");       
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        schedule = new javax.swing.JButton();
        petPorte = new javax.swing.JLabel();
        petNome = new javax.swing.JLabel();
        donoNome = new javax.swing.JLabel();
        edit = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
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

        schedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/agendar0.png"))); // NOI18N
        schedule.setBorderPainted(false);
        schedule.setContentAreaFilled(false);
        schedule.setMargin(new java.awt.Insets(0, 0, 0, 0));
        schedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleActionPerformed(evt);
            }
        });
        add(schedule, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 101, 40));

        petPorte.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        petPorte.setText("pet_porte");
        add(petPorte, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 4, 130, 35));

        petNome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        petNome.setText("pet_nome");
        add(petNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, 170, 35));

        donoNome.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        donoNome.setText("dono_nome");
        add(donoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 4, 170, 35));

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/editar0.png"))); // NOI18N
        edit.setToolTipText("");
        edit.setBorderPainted(false);
        edit.setContentAreaFilled(false);
        edit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 101, 40));
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
        V.pets.petPreview(pet);
        mouseEvent(evt);
    }//GEN-LAST:event_formMouseReleased

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        V.pets.loadPet(pet);
    }//GEN-LAST:event_editActionPerformed

    private void scheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleActionPerformed
        V.petReg.newService(this.pet);
    }//GEN-LAST:event_scheduleActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel donoNome;
    private javax.swing.JButton edit;
    private javax.swing.JLabel petNome;
    private javax.swing.JLabel petPorte;
    private javax.swing.JButton schedule;
    // End of variables declaration//GEN-END:variables
}
