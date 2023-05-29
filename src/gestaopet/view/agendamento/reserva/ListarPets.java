package gestaopet.view.agendamento.reserva;

import gestaopet.DB.PetDB;
import gestaopet.V;
import gestaopet.classes.ModalPanel;
import gestaopet.classes.Pet;
import gestaopet.components.modal.Modal;
import gestaopet.tema.scroll.SpecialScroll;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ScrollPaneConstants;

public class ListarPets extends /*javax.swing.JPanel*/ ModalPanel {
    private java.util.List<Pet> localList;
    private Modal parent;
    private List<ItemListPet> select = new ArrayList<>();
    private Pet pet;
    private SpecialScroll ss;
    
    
    public ListarPets(Pet pet) {
        initComponents();
        ss = new SpecialScroll(scroll);
        scrollArea.add(ss);
        ss.resetPosition();
        this.pet = pet;
        this.localList = PetDB.getByOwner(pet.getDonoId());
        removeOriginalPet();
        setPreferredSize(new Dimension(400, 400));
        setSize(new Dimension(400, 400));
        scroll.setLocation(0, 0);
        scroll.setPreferredSize(new Dimension(380, 300));
        scroll.setSize(new Dimension(380, 300));
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //parent = V.newPopUp(this);
        loadList("");
        start();
    }
    
    public void loadList(String filter){
        panel.removeAll();
        int y = 5;
        int j = 0;
        for(int k = 0; k < 10; k ++){
            for(int i = 0; i < localList.size(); i++){
            String v = localList.get(i).getNome();
            if(v.toLowerCase().indexOf(filter.toLowerCase()) > -1 || filter.equals("")){
                j++;
                ItemListPet f = new ItemListPet(localList.get(i));
                select.add(f);
                panel.add(f);
                f.setVisible(true);
                f.setLocation(20, y);
                f.setPreferredSize(new Dimension(325,35));
                f.setSize(new Dimension(325,35));
                y = y + 35;
                Dimension d = new Dimension(350, y);
                panel.setPreferredSize(d);
                panel.setSize(d);
            }
        }
        
            panel.repaint();
            panel.revalidate();
        }   
        if( j == 0){
            setAlert("Nenhum pet encontrado para o termo : " + filter);
        } else {
            setAlert("");
        } 
        ss.updateSize();
        ss.resetPosition();
    }
    
    public void removeOriginalPet(){
        for(int i = 0; i < localList.size(); i++){
            if(localList.get(i).getId() == pet.getId() ){
                localList.remove(localList.get(i));
            }
        }
    }
    
    public void setAlert(String msg){
        alert.setText(msg);
    }

    public void confirm(){
        V.bkw.recalculate();
        close();
    }
    
    public void show(){
        show(true);
    }
    public void cancel(){
        close();
    }
    
    public List<Pet> getSelected(){
        List<Pet> output = new ArrayList<>();
        for(int i = 0; i < select.size(); i++){
            if(select.get(i).isSelected()){
                output.add(select.get(i).petSelected());
            }
        }
        return output;
    }
    
    public boolean isDailyIncluded(){
        return dailyInclusion.isSelected();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        search = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        buttonsPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        alert = new javax.swing.JLabel();
        dailyInclusion = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        scrollArea = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setAutoscrolls(true);
        scroll.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                scrollMouseWheelMoved(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel);

        add(scroll, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 350, 240));

        search.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        search.setBorder(null);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, 310, 30));

        searchButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/buscar0.png"))); // NOI18N
        searchButton.setToolTipText("");
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        add(searchButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 50, 40));

        buttonsPanel.setBackground(new java.awt.Color(255, 255, 255));
        buttonsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarWB1.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, 45));

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/confirmarWB1.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        buttonsPanel.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 130, 45));
        buttonsPanel.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(389, 31, -1, -1));

        add(buttonsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 390, 50));

        alert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(204, 0, 0));
        alert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 370, 20));

        dailyInclusion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        dailyInclusion.setText("Incluir no valor da diária");
        add(dailyInclusion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, 40));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 210, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 140, -1));

        scrollArea.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout scrollAreaLayout = new javax.swing.GroupLayout(scrollArea);
        scrollArea.setLayout(scrollAreaLayout);
        scrollAreaLayout.setHorizontalGroup(
            scrollAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        scrollAreaLayout.setVerticalGroup(
            scrollAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        add(scrollArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 40, 240));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/listaAcabamento.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 350, 110));
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        loadList(search.getText());
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        confirm();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cancel();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void scrollMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_scrollMouseWheelMoved
        ss.scroolAction(scroll.getVerticalScrollBar().getValue());
    }//GEN-LAST:event_scrollMouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JCheckBox dailyInclusion;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JPanel scrollArea;
    private javax.swing.JTextField search;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}
