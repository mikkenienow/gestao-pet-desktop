package gestaopet.components;

import gestaopet.classes.InputTools;
import gestaopet.classes.ModalPanel;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InputField extends ModalPanel {
    private String tipo;
    private JLabel obj;
    private ModalPanel origin;
    private String inputValue;
    public InputField(String tipo, JLabel obj, ModalPanel origin, String input) {
        initComponents();
        Dimension d = new Dimension(210,110);
        setPreferredSize(d);
        setSize(d);
        this.tipo = tipo;
        this.obj = obj;
        this.origin = origin;
        if(tipo.equals("valor")){
            this.input.setText(obj.getText().replace(".", ","));
        }
        start();
        this.inputValue = input;
    }
    

    
    public void confirmar(){
        obj.setText( input.getText());
        origin.setInput(this.inputValue);
        close();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        input = new javax.swing.JTextField();
        confirmar = new javax.swing.JButton();
        cancelar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        input.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        input.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        input.setText("00");
        input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputKeyTyped(evt);
            }
        });
        add(input, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 12, 190, 40));

        confirmar.setText("Confirmar");
        confirmar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarActionPerformed(evt);
            }
        });
        add(confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 80, 40));

        cancelar.setText("Cancelar");
        cancelar.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputActionPerformed

    private void inputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputKeyTyped
        if(tipo.equals("valor")){
            input.setText(InputTools.makeDecimal(input.getText(), evt, evt.getKeyChar()+""));
            evt.consume();
        }
        
    }//GEN-LAST:event_inputKeyTyped

    private void confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_confirmarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        close();
    }//GEN-LAST:event_cancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JButton confirmar;
    private javax.swing.JTextField input;
    // End of variables declaration//GEN-END:variables
}
