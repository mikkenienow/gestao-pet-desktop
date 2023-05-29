package gestaopet.view.agendamento.reserva;

import gestaopet.classes.Alojamento;
import gestaopet.classes.Canil;
import gestaopet.enums.StatusAlojamento;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ReservaAlojamento extends javax.swing.JPanel {
    private int count = 0;
    private int lastX = 10;
    private int lastY = 10;
    private Canil dk;
    private int alojamentoId = 999;
    private String titulo = "";

    public ReservaAlojamento(){
        
    }

    public class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            alojamentoId = Integer.parseInt(((JButton)e.getSource()).getName());
            titulo = dk.getAlojamentoById(alojamentoId).getTitulo();
            updateButtonStatus();
        }
    }
    
    public int getAlojamento(){
        return this.alojamentoId;
    }    
    
    public String getAlojamentoTitulo(){
        return this.titulo;
    }
    public void resetAlojamento(){
        this.alojamentoId = 999;
    }    
    
    public void updateAll(Canil dk){
        try {
            panel.removeAll();
        } catch (Exception e) {
            initComponents();
        }
        button.setVisible(false);
        this.dk = dk;
        count = 0;
        lastX = 10;
        lastY = 10;
        alojamentoId = 999;
        dogKennelTittle.setText(dk.getTitulo());
        createButtons(this.dk.getTotalSections());
        setButtonsActions();
    }
    
    private void updateButtonStatus(){
        for(int i = 0; i < getButtons().length; i++){
            int j = Integer.parseInt(getButtons()[i].getName())-1;

            if(j == alojamentoId-1){
                dk.getAlojamentoList().get(i).setStatus(StatusAlojamento.HOSPEDADO);
            } else {
                if(dk.getAlojamentoList().get(i).getStatus() == StatusAlojamento.HOSPEDADO){
                    dk.getAlojamentoList().get(i).setStatus(StatusAlojamento.LIVRE);
                }
            }
            if(dk.getAlojamentoList().get(i).getStatus() != StatusAlojamento.LIVRE){
                if(dk.getAlojamentoList().get(i).getStatus() == StatusAlojamento.HOSPEDADO){
                    getButtons()[i].setEnabled(false);
                    getButtons()[i].setBackground(new java.awt.Color(153, 255, 153));
                } else {
                    getButtons()[i].setEnabled(false);
                    getButtons()[i].setBackground(new java.awt.Color(204, 204, 204));
                }
            } else {
                getButtons()[i].setEnabled(true);
                getButtons()[i].setBackground(new java.awt.Color(255, 255, 255));
            }
        }
    }
    
    public void setSectionId(int sectionId){
        this.alojamentoId = sectionId;
    }
    
    public void createButtons(int amount){
        for(int i =0; i < amount; i++){
            setButtons(dk.getAlojamentoList().get(i));
        }
    }

    public void setButtons(Alojamento a){
        JButton b = new JButton();
        panel.add(b);
        b.setVisible(true);
        count++;
        b.setName(a.getId()+"");
        b.setText(a.getTitulo());
        b.setSize(50,50);
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setLocation(lastX, lastY);
        lastX = lastX + 70;
        if(count % 4 == 0){
            lastX = 10;
            lastY = lastY + 70;
        }

        panel.setPreferredSize(new Dimension(280, lastY + 20));
    }
    
    private Component[] getButtons(){
        return panel.getComponents();
    }
    
    
    private void setButtonsActions(){
        ButtonHandler handler = new ButtonHandler();
        updateButtonStatus();
        for (Component sectionbutton : getButtons()) {
            ((JButton) sectionbutton).addActionListener(handler);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button = new javax.swing.JButton();
        dogKennelTittle = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(214, 230, 255));
        setName("10"); // NOI18N
        setOpaque(false);

        button.setBackground(new java.awt.Color(153, 255, 153));
        button.setText("jButton1");
        button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        button.setIconTextGap(1);
        button.setMargin(null);

        dogKennelTittle.setText("titulo");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setAutoscrolls(true);
        scroll.setDoubleBuffered(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dogKennelTittle)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dogKennelTittle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(451, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button;
    private javax.swing.JLabel dogKennelTittle;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
