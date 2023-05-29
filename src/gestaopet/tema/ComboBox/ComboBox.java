package gestaopet.tema.ComboBox;

import gestaopet.tema.GlobalItem;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public final class ComboBox extends GlobalItem {
    private ComboBoxList cbl;
    private MenuMode mm;
    private List<Object[]> list;
    private int option = -1;
    private JComboBox jcb;
    private int width;
    
    public ComboBox(List<Object[]> list, JComboBox jcb, int width, int height, int startItem) {
        initComponents();
        this.list = list;
        this.jcb = jcb;
        resize(width);
        this.width = width;
        cbl = new ComboBoxList(list, this, width, height);
        mm = new MenuMode(cbl);
        selecionado.setText((String)list.get(startItem)[1]);
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public ComboBox(List<Object[]> list, JComboBox jcb,int height, int startItem) {
        initComponents();
        this.list = list;
        this.jcb = jcb;
        this.width = autoSize();
        resize(autoSize());
        cbl = new ComboBoxList(list, this, autoSize(), height);
        mm = new MenuMode(cbl);
        selecionado.setText((String)list.get(startItem)[1]);
    }
    
    public MenuMode getMenuMode(){
        return this.mm;
    }
    
    public int autoSize(){
        int output = 30;
        for(int i = 0; i < list.size(); i++){
            
            int c = (int)(((String)list.get(i)[1]).length() * 12);
            if(c > output){
                output = c;
            }
        }
        output = (output<100)? 100: output; 
        return output;
    }
    
    public static int autoSize(List<Object[]> list){
        int output = 30;
        for(int i = 0; i < list.size(); i++){
            
            int c = (int)(((String)list.get(i)[1]).length() * 12);
            if(c > output){
                output = c;
            }
        }
        output = (output<100)? 100: output;
        return output;
    }

    public void resize(int width){
        // 180
        // start 0   | 10
        // mid   10  | 160
        // end   170 | 10

        
        this.setPreferredSize(new Dimension(width, 40));
        this.setSize(new Dimension(width, 40));
        
        int nWidth = width - 20;
        midbox.setSize(nWidth, 40);
        midbox.setPreferredSize(new Dimension(nWidth, 40));
        midbox.revalidate();
        
        end.setSize(nWidth , 40);
        end.setPreferredSize(new Dimension(nWidth, 40));
        
        open.setSize(width, 30);
        open.setPreferredSize(new Dimension(width, 30));
        open.setLocation(0, 5);
    }

    public void setOption(int option){
        this.option = option;
        jcb.setSelectedIndex(option);
        selecionado.setText((String)list.get(option)[1]);
    }
    
    public int getSelection(){
        return option;
    }
    
    public void open(){
        mm.show(new int[]{this.getLocationOnScreen().x, this.getLocationOnScreen().y + this.getHeight()});
        mm.setAlwaysOnTop(true);
        cbl.setVisible(true);
        
    }
    
    
    public static ComboBox setTheme(ComboBox cb, JComboBox jc, int[] xy, int height, int startItem, JPanel target){
        List<Object[]> list  = new ArrayList<>();
        for(int i = 0; i < jc.getItemCount(); i++){
            list.add(new Object[] {i, jc.getItemAt(i)});
        }
        cb = new ComboBox(list, jc,height, startItem);
        
        jc.setVisible(false);
        //.setLocation(150,0);
        
        //Dimension d = new Dimension(180, 40);
        target.add(cb);
        cb.setVisible(true);
        cb.setLocation(xy[0], xy[1]);
        cb.resize(autoSize(list));
        target.setPreferredSize(cb.getSize());
        target.setSize(cb.getSize());
        
        return cb;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        open = new javax.swing.JButton();
        end = new javax.swing.JLabel();
        selecionado = new javax.swing.JLabel();
        start = new javax.swing.JLabel();
        midbox = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        open.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        open.setForeground(new java.awt.Color(15, 59, 146));
        open.setText("V  ");
        open.setToolTipText("");
        open.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        open.setBorderPainted(false);
        open.setContentAreaFilled(false);
        open.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        open.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                openMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                openMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                openMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                openMouseReleased(evt);
            }
        });
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        add(open, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, -1, -1));

        end.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        end.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(end, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        selecionado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        selecionado.setForeground(new java.awt.Color(15, 59, 146));
        selecionado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        selecionado.setText("item...");
        add(selecionado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 140, 30));

        start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, -1));

        midbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        midbox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        midbox.setFocusable(false);
        add(midbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        open();
    }//GEN-LAST:event_openActionPerformed

    private void openMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMouseEntered
        mouseEvent(evt);
    }//GEN-LAST:event_openMouseEntered

    private void openMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMouseExited
        mouseEvent(evt);
    }//GEN-LAST:event_openMouseExited

    private void openMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMousePressed
        mouseEvent(evt);
    }//GEN-LAST:event_openMousePressed

    private void openMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_openMouseReleased
        mouseEvent(evt);
    }//GEN-LAST:event_openMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel end;
    private javax.swing.JLabel midbox;
    private javax.swing.JButton open;
    private javax.swing.JLabel selecionado;
    private javax.swing.JLabel start;
    // End of variables declaration//GEN-END:variables
}
