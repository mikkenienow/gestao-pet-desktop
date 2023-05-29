package gestaopet.tema;

import gestaopet.tema.ComboBox.ItemList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;

public class GlobalItem extends JPanel {
    public List<Object[]> list;
    
    public void mouseEvent(MouseEvent evt){
        switch(evt.getID()){
            case 504:
                setBackground(new Color(204,204,255));
                break;
            case 505:
                setBackground(Color.WHITE);
                break;
            case 501:
                setBackground(Color.CYAN);
                break;
            case 502:
                setBackground(new Color(204,204,255));
                break;
        }
    }
    
    public void addItem(ItemList il, int pos, int width, int height){
        add(il);
        il.setVisible(true);
        Dimension d =  new Dimension(width - 2, height);
        il.setSize(d);
        il.setPreferredSize(d);
        il.setLocation(1, (height * pos));
    }
    
    public void loadList(int width, int height){
        for(int i = 0; i < list.size(); i++){
            int id = (int)list.get(i)[0];
            String nome = (String)list.get(i)[1];
            ItemList il = new ItemList(id, nome, this, height);
            addItem(il, i, width, height);
        }
    }
}
