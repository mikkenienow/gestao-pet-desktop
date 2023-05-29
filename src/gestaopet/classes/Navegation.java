package gestaopet.classes;

import gestaopet.V;
import gestaopet.view.home.Home;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Navegation {
    private static GlobalPanel[] panelList = null;
    private JPanel target = new JPanel();
    public JFrame defaultFrame;
    public JPanel popup = null;
    public JPanel defaultPanel = null;
    private JButton[] bList;
    private List<String> history = new ArrayList<String>();
    private int pos = -1;
    private boolean back = false;
    private boolean lock = false;
//    public Navegation1(JFrame frame){
//        this.panelList = ((Home)frame).getPanelList();
//        this.bList = ((Home)frame).getButtons();
//        
//        for(int i =0; i < panelList.length; i++){
//            panelList[i].setName(i + "");
//            frame.getContentPane().add(panelList[i]);
//        }
//        changeProperties(((Home)frame).formViewArea, this.target);
//        this.defaultFrame = frame;
//    }
    
    public Navegation(JFrame frame){
        this.panelList = ((Home)frame).getPanelList();
        this.bList = ((Home)frame).getButtons();
        
        for(int i =0; i < panelList.length; i++){
            panelList[i].setName(i + "");
            ((Home)frame).target.add(panelList[i]);
        }
        changeProperties(((Home)frame).target, this.target);
        this.defaultFrame = frame;
    }

    public Navegation(JFrame frame, JPanel target, JPanel popup){
        this.popup = popup;
        this.defaultFrame = frame;
        this.defaultFrame.getContentPane().add(this.popup);
        changeProperties(target, this.target);
    }
    
    public Navegation(JPanel panel, JPanel target, JPanel popup){
        this.popup = popup;
        this.defaultPanel = panel;
        this.target = target;
        target.setVisible(false);
        this.defaultPanel.add(this.popup);
    }
    
    public void remove(JComponent j){
        this.defaultPanel.remove(j);
    }
    public void add(JComponent j){
        this.defaultPanel.add(j);
    }
    
    public int getPos(){
        return this.pos;
    }
    public void setViewPanel(int pos, int width, int height){
        for(int i = 0; i < defaultPanel.getComponentCount(); i++){
            try {
                if(!defaultPanel.getComponent(i).getName().isEmpty()){
                    changeProperties(target, ((JPanel)defaultPanel.getComponent(i)));
                    ((JPanel)defaultPanel.getComponent(i)).setSize(width,height);
                    ((JPanel)defaultPanel.getComponent(i)).setPreferredSize(new Dimension(width,height));
                } else {
                    defaultPanel.getComponent(i).setVisible(false);
                }
            } catch (Exception e) {
            }
        }
        defaultPanel.repaint();
        defaultPanel.revalidate();
    }

    public void setPanelPosition(int pos, int x, int y){
        for(int i = 0; i < defaultPanel.getComponentCount(); i++){
            try {
                if(defaultPanel.getComponent(i).getName().equals(pos+"")){
                    changeProperties(target, ((JPanel)defaultPanel.getComponent(i)));
                    ((JPanel)defaultPanel.getComponent(i)).setLocation(x,y);
                } else {
                    defaultPanel.getComponent(i).setVisible(false);
                }
            } catch (Exception e) {
            }
        }
        defaultPanel.repaint();
        defaultPanel.revalidate();
    }

    private void changeProperties(JPanel from, JPanel to){
        to.setPreferredSize(from.getPreferredSize());
        to.setSize(from.getSize());
        to.setLocation(from.getX(), from.getY());
        to.setBorder(from.getBorder());
        to.setVisible(true);
    }

    public void setView(int pos){
        setHistory(pos);

        for(int i = 0; i < ((Home)defaultFrame).target.getComponentCount(); i++){
            try {
                if(((Home)defaultFrame).target.getComponent(i).getName().equals(pos+"")){
                    changeProperties(target, ((JPanel)((Home)defaultFrame).target.getComponent(i)));
                    updateButtons(panelList[pos].getBList());
                } else {
                    ((Home)defaultFrame).target.getComponent(i).setVisible(false);
                }
            } catch (Exception e) {
            }
        }
        
        
        
        if(((pos == 0) || pos == 1 || (pos == 3) || (pos == 11) || (pos == 13)) && lock){
            V.home.buttonTrigger(pos);
        } else {
            lock = true;
        }
        
        defaultFrame.repaint();
        defaultFrame.revalidate();
    }
    
    public void setPanelView(int pos){
        
        setHistory(pos);

        for(int i = 0; i < defaultFrame.getContentPane().getComponentCount(); i++){
            try {
                if(defaultFrame.getContentPane().getComponent(i).getName().equals(pos+"")){
                    changeProperties(target, ((JPanel)defaultFrame.getContentPane().getComponent(i)));
                    updateButtons(panelList[pos].getBList());
                } else {
                    defaultFrame.getContentPane().getComponent(i).setVisible(false);
                }
            } catch (Exception e) {
            }
        }
        defaultFrame.repaint();
        defaultFrame.revalidate();
    }
    
    public void back(){
        back = true;
        if(!history.isEmpty()){
            int i = Integer.parseInt(history.get(history.size()-1));
            history.remove(history.size()-1);
            setView(i);
            this.pos = i;
        } else {
        }
        back = false;
    }
    
    public void setHistory(int pos){
        if(!back){
            if(this.pos >= 0){
                history.add(this.pos + "");
            }
            this.pos = pos;
        }
    }
    
    public void updateButtons(FunctionButton[] list){
        for(int i = 0; i < bList.length;i++){
            bList[i].setVisible(false);
        }
        
        for(int i = 0; i < bList.length;i++){
            try {
                if(list[i].getTitle().equals("Voltar")){
                    setBackButton(list[i], i);
                } else {
                    //bList[i].setText(list[i].getTitle());
                    bList[i].setActionCommand("b" + (i + 1));
                    bList[i].setName(list[i].getParam());
                    bList[i].setEnabled(list[i].isEnable());
                    bList[i].setIcon(list[i].getIcon());
                    bList[i].setVisible(true);
                    try {
                        bList[i].setRolloverIcon(list[i].getHoverIcon());
                    } catch (Exception e) {
                        bList[i].setRolloverIcon(null);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    
    public void setBackButton(FunctionButton button, int i){
        int j = bList.length -1;
        bList[j].setActionCommand("b" + (i + 1));
        bList[j].setName(button.getParam());
        bList[j].setEnabled(button.isEnable());
        bList[j].setVisible(true);
    }
    
    public void buttonEnableUpdate(boolean[] list){
        for(int i = 0; i < bList.length; i++){
            try {
                bList[i].setEnabled(list[i]);
            } catch (Exception e) {
            }
        }
    }
    
    public Object getPanel(int pos){
        return panelList[pos];
    }
}