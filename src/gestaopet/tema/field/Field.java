package gestaopet.tema.field;

import gestaopet.classes.GlobalPanel;
import gestaopet.tema.GlobalItem;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JTextField;

public final class Field extends GlobalItem {
    private List<Object[]> list;
    private int width;
    private JTextField origin;
    private String method;
    private GlobalPanel parent;
    
    public Field(int size, String text, JTextField origin, String method, int[] location) {
        initComponents();
        this.list = list;
        resize(size);
        this.width = size;
        this.origin = origin;
        this.text.setText(origin.getText());
        this.method = method;
        this.parent = (GlobalPanel)origin.getParent().getParent();
        this.parent.add(this);
        setVisible(true);
        setLocation(location[0], location[1]);
        origin.setVisible(false);
    }
    
    public int getWidth(){
        return this.width;
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
    }

    public void setText(String t){
        text.setText(t);
    }
    
    public String getText(){
        return text.getText();
    }
    
    public void onClick(String method){
        try {
            ((GlobalPanel)parent).actionTrigger(method);
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        end = new javax.swing.JLabel();
        text = new javax.swing.JLabel();
        start = new javax.swing.JLabel();
        midbox = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        end.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        end.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(end, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        text.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        text.setForeground(new java.awt.Color(15, 59, 146));
        text.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        text.setText("...");
        add(text, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 140, 30));

        start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, -1));

        midbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        midbox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        midbox.setFocusable(false);
        add(midbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        onClick(method);
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel end;
    private javax.swing.JLabel midbox;
    private javax.swing.JLabel start;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}
