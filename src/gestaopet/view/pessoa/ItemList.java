package gestaopet.view.pessoa;

import gestaopet.V;
import gestaopet.classes.Pessoa;
import java.awt.Color;
import java.awt.Desktop;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class ItemList extends javax.swing.JPanel {
        private Pessoa pessoa;
        private String whatsapp;
        private String link = "https://web.whatsapp.com/send?phone=CELULAR&text=MENSAGEM";
        
        
    public ItemList(Pessoa pessoa) {
        initComponents();
        this.pessoa = pessoa;
        pessoaNome.setText(this.pessoa.getNomeCompleto());
        pessoaCidade.setText(this.pessoa.getCidade());
        this.whatsapp = this.pessoa.getTel1();
        onMouseHover(false,"");
        boolean visible = (pessoa.getTipo().equals("Cliente"))? true: false;
        verPets.setVisible(visible);
    }
    
    public void sendMessage(){
        String url = "https://web.whatsapp.com/send?phone=" + whatsapp + "&text=Ola";
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void onMouseHover(boolean hover, String bt){
        Color c = (hover)? new Color(204,204,255): Color.WHITE;
        setBackground(c);
        int a = (hover)? 255: 120;
        int t = (bt.equals("verPets"))? 3:1;
        updateColorButton(verPets, a,t);
        t = (bt.equals("sendWhatsApp"))? 3:1;
        updateColorButton(sendWhatsApp, a,t);
        t = (bt.equals("edit"))? 3:1;
        updateColorButton(edit, a,t);
    }
    
    public void updateColorButton(JButton bt, int alpha, int thickness){
        Color m1 = bt.getBackground();
        Color m2 = bt.getForeground();
        Color b = new Color(m1.getRed(), m1.getGreen(), m1.getBlue(), alpha);
        Color f = new Color(m2.getRed(), m2.getGreen(), m2.getBlue(), alpha);
        
        bt.setBackground(b);
        bt.setForeground(f);
        Border border = BorderFactory.createLineBorder(f, thickness);
        bt.setBorder(border);
        bt.repaint();
        bt.revalidate();
    }
    
    public void verPet(){
        V.nav.setView(1);
        V.pets.onLoad("", "" + pessoa.getId());

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sendWhatsApp = new javax.swing.JButton();
        pessoaNome = new javax.swing.JLabel();
        pessoaCidade = new javax.swing.JLabel();
        edit = new javax.swing.JButton();
        verPets = new javax.swing.JButton();

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

        sendWhatsApp.setBackground(new java.awt.Color(0, 204, 0));
        sendWhatsApp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sendWhatsApp.setForeground(new java.awt.Color(255, 255, 255));
        sendWhatsApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/whatsapp0.png"))); // NOI18N
        sendWhatsApp.setToolTipText("");
        sendWhatsApp.setBorderPainted(false);
        sendWhatsApp.setContentAreaFilled(false);
        sendWhatsApp.setMargin(new java.awt.Insets(0, 0, 0, 0));
        sendWhatsApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendWhatsAppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendWhatsAppMouseExited(evt);
            }
        });
        sendWhatsApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendWhatsAppActionPerformed(evt);
            }
        });
        add(sendWhatsApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 1, 101, 40));

        pessoaNome.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        pessoaNome.setText("pessoa_nome");
        add(pessoaNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1, 210, 40));

        pessoaCidade.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        pessoaCidade.setText("pessoa_cidade");
        add(pessoaCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 1, 150, 40));

        edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/editar0.png"))); // NOI18N
        edit.setBorderPainted(false);
        edit.setContentAreaFilled(false);
        edit.setMargin(new java.awt.Insets(0, 0, 0, 0));
        edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editMouseExited(evt);
            }
        });
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 1, 102, 40));

        verPets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/pets0.png"))); // NOI18N
        verPets.setBorderPainted(false);
        verPets.setContentAreaFilled(false);
        verPets.setMargin(new java.awt.Insets(0, 0, 0, 0));
        verPets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                verPetsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                verPetsMouseExited(evt);
            }
        });
        verPets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verPetsActionPerformed(evt);
            }
        });
        add(verPets, new org.netbeans.lib.awtextra.AbsoluteConstraints(659, 1, 101, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        onMouseHover(true,"");
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        onMouseHover(false,"");
    }//GEN-LAST:event_formMouseExited

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        setBackground(Color.CYAN);
    }//GEN-LAST:event_formMousePressed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        setBackground(new Color(204,204,255));
        //V.pets.petPreview(pet);
    }//GEN-LAST:event_formMouseReleased

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        V.navigate(6);
        V.pessoaReg.loadPessoa(pessoa);
    }//GEN-LAST:event_editActionPerformed

    private void sendWhatsAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendWhatsAppActionPerformed
        //V.pets.newService();
        sendMessage();
    }//GEN-LAST:event_sendWhatsAppActionPerformed

    private void verPetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verPetsActionPerformed
        verPet();
    }//GEN-LAST:event_verPetsActionPerformed

    private void verPetsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPetsMouseEntered
        onMouseHover(true,"verPets");
    }//GEN-LAST:event_verPetsMouseEntered

    private void verPetsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verPetsMouseExited
        onMouseHover(false,"");
    }//GEN-LAST:event_verPetsMouseExited

    private void sendWhatsAppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendWhatsAppMouseEntered
        onMouseHover(true,"sendWhatsApp");
    }//GEN-LAST:event_sendWhatsAppMouseEntered

    private void sendWhatsAppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendWhatsAppMouseExited
        onMouseHover(false,"");
    }//GEN-LAST:event_sendWhatsAppMouseExited

    private void editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseEntered
        onMouseHover(true,"edit");
    }//GEN-LAST:event_editMouseEntered

    private void editMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editMouseExited
        onMouseHover(false,"");
    }//GEN-LAST:event_editMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton edit;
    private javax.swing.JLabel pessoaCidade;
    private javax.swing.JLabel pessoaNome;
    private javax.swing.JButton sendWhatsApp;
    private javax.swing.JButton verPets;
    // End of variables declaration//GEN-END:variables
}
