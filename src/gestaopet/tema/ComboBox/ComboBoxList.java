
package gestaopet.tema.ComboBox;

import gestaopet.tema.GlobalItem;
import java.awt.Dimension;
import java.util.List;

public class ComboBoxList extends GlobalItem {
    private ComboBox cb;

    public ComboBoxList(List<Object[]> list, ComboBox cb, int width, int height) {
        initComponents();
        this.list = list;
        this.cb = cb;
        Dimension d = new Dimension(width, height * list.size()+1);
        setSize(d);
        setPreferredSize(d);
        loadList(width, height);
    }
    
    public ComboBox getComboBox(){
        return this.cb;
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(15, 59, 146), 1, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
