package gestaopet.classes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class HoverEvent {
    public static void mouseEvent(MouseEvent evt, JPanel obj){
        switch(evt.getID()){
            case 504:
                obj.setBackground(new Color(204,204,255));
                break;
        }
    }
}
