package gestaopet.tema.scroll;

import gestaopet.tema.checkbox.CheckBox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;

public class TesteScroll extends javax.swing.JFrame {
    ButtonGroup bg = new ButtonGroup();
    public TesteScroll() {
        initComponents();
        start();
    }
    public void start(){
        SpecialScroll ss = new SpecialScroll(scrollPanel);
        getContentPane().add(ss);
        ss.setVisible(true);
        Dimension d2 = new Dimension(500, 500-10);
        
        setSize(d2);
        setPreferredSize(d2);

        /*CheckBox s1 = new CheckBox("Testando");
        CheckBox s2 = new CheckBox("Testand22222");
        CheckBox s3 = new CheckBox("Testand22233");*/
        /*
        createCheckbox(
                new CheckBox("Testando"),
                new CheckBox("Mais uma"),
                new CheckBox("Mais duas"),
                new CheckBox("Mais tres"),
                new CheckBox("Mais quatro")
                );
        
        
        
        scrollPanel.setVisible(false);
        scrollPanel.getVerticalScrollBar().setVisible(false);
        scrollPanel.setVerticalScrollBarPolicy(scrollPanel.VERTICAL_SCROLLBAR_NEVER);*/
        
        popular();
        
        
        
    }
    
    public void addItem(CheckBox s1, int pos){
        this.add(s1);
        s1.setVisible(true);
        Dimension d =  new Dimension(200, 25);
        s1.setSize(d);
        s1.setPreferredSize(d);
        s1.setLocation(200, (25 * pos) + 30);
        bg.add(s1.getComponent());
    }
    
    public void createCheckbox(CheckBox... item){
        for(int i = 0; i < item.length; i++){
            addItem(item[i], i);
        }
    }
    
    public void scroll(int pos){
        scrollPanel.getVerticalScrollBar().setValue(pos);
    }
    
    public int getScrool(){
        
        
        return scrollPanel.getVerticalScrollBar().getValue();
    }
    
    
    public void popular(){
        Dimension a = new Dimension(150,15 * 50 );
        area.setSize(a);
        area.setPreferredSize(a);
        for(int i = 0; i < 50; i++){
            JLabel l = new JLabel("Teste " + i);
            Dimension d = new Dimension(50,15 );
            area.add(l);
            l.setVisible(true);
            l.setSize(d);
            l.setPreferredSize(d);
            l.setLocation(5, i * 15);
        }
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        area.setBackground(new java.awt.Color(204, 204, 255));
        area.setMaximumSize(new java.awt.Dimension(32767, 800));
        area.setPreferredSize(new java.awt.Dimension(165, 100));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 397, Short.MAX_VALUE)
        );

        scrollPanel.setViewportView(area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(564, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TesteScroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TesteScroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TesteScroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TesteScroll.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TesteScroll().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}