package gestaopet.tema.checkbox;

import gestaopet.classes.GlobalPanel;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class CheckBox extends javax.swing.JPanel {
    JRadioButton main;
    ButtonGroup bg;
    GlobalPanel parent;
    public CheckBox(JRadioButton main, ButtonGroup bg, GlobalPanel parent) {
        initComponents();
        setText(main.getText());
        //main.setVisible(false);
        setVisible(true);
        Dimension d = new Dimension(text.getText().length()*15, 25);
        setSize(d);
        setPreferredSize(d);
        this.parent = parent;
        this.main = main;
        this.bg = bg;
        bg.add(main);
        getComponent().setVisible(false);
        updateIcon();
    }
    
    public CheckBox(JRadioButton main, ButtonGroup bg) {
        initComponents();
        setText(main.getText());
        //main.setVisible(false);
        setVisible(true);
        Dimension d = new Dimension(text.getText().length()*15, 25);
        setSize(d);
        setPreferredSize(d);
        this.main = main;
        this.bg = bg;
        bg.add(main);
        getComponent().setVisible(false);
        updateIcon();
    }
    
    public void updateIcon(){
        if(main.isSelected()){
            if(main.isEnabled()){setIcon(icon, 1);} else {setIcon(icon, 3);}
        } else {
            if(main.isEnabled()){setIcon(icon, 0);} else {setIcon(icon, 2);}
        }
    }
    

    
    private void setIcon(JLabel label, int icon){
        switch(icon){
            case 0:
                label.setIcon(new ImageIcon(getClass().getResource("/gestaopet/tema/icons/falseEnable.png")));
                break;
            case 1:
                label.setIcon(new ImageIcon(getClass().getResource("/gestaopet/tema/icons/trueEnable.png")));
                break;
            case 2:
                label.setIcon(new ImageIcon(getClass().getResource("/gestaopet/tema/icons/falseDisable.png")));
                break;
            case 3:
                label.setIcon(new ImageIcon(getClass().getResource("/gestaopet/tema/icons/trueDisable.png")));
                break;
        }
    }
    
    public void setSelection(){
        if(main.isEnabled()){
            main.setSelected(!main.isSelected());
        } 
        System.out.println("main is selected = " + main.isSelected());
        updateIcon();
    }
    
    public void setCheckboxEnabled(){
        main.setEnabled(!main.isEnabled());
    }
    
    public void setText(String text){
        this.text.setText(text);
    }
    
    public JRadioButton getComponent(){
        return main;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        icon = new javax.swing.JLabel();
        text = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        icon.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/falseEnable.png"))); // NOI18N
        icon.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        icon.setAlignmentY(0.0F);
        icon.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        icon.setIconTextGap(0);
        icon.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconMouseClicked(evt);
            }
        });

        text.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        text.setForeground(new java.awt.Color(15, 59, 146));
        text.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        text.setText("text");
        text.setAlignmentY(0.0F);
        text.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconMouseClicked
        setSelection();
    }//GEN-LAST:event_iconMouseClicked

    private void textMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textMouseClicked
        setSelection();
    }//GEN-LAST:event_textMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        setSelection();
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel icon;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}
