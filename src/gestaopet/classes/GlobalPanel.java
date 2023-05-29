package gestaopet.classes;

import gestaopet.V;
import gestaopet.components.DeleteEntity;
import gestaopet.tema.checkbox.CheckBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class GlobalPanel extends JPanel{
    private FunctionButton[] bList;
    private String param;
    List<Pet> petList = new ArrayList<Pet>();
    List<CheckBox> checkBoxList = new ArrayList<>();
    List<String[]> textFilds = new ArrayList<>();

    public void buttonsInit(FunctionButton[] titles){
        this.bList = titles;
    }
    
    public FunctionButton[] getBList(){
        return this.bList;
    }    
    
    public Object getCheckBoxList(){
        return this.checkBoxList;
    }
    
    public void addComponentTo( JComponent obj, List<Object> list, Container cont){
        list.add(obj);
        cont.add(obj);
    }
    
    
    public void updateCheckbox(){
        for(int i =0; i < checkBoxList.size(); i++){
            checkBoxList.get(i).updateIcon();
        }
    }
    
    public void action(ActionEvent evt){
        System.out.println("Método não configurado para esta interface.");
    }

    public void openDialog(){
        System.out.println("Método não configurado para esta interface.");
    }
    
    public void cleanAll(Container container){
        Component[] c = container.getComponents();
        for(Component i: c){
            if(i instanceof JTextComponent){
                ((JTextComponent)i).setText("");
            }
            if(i instanceof Container){
                cleanAll((Container)i);
            }
        }
    }

    public List<String[]> getContent(Container container){
        Component[] c = container.getComponents();
        
        for(Component i: c){
            if(i instanceof JTextComponent){
                String[] t = {i.getName(),((JTextComponent)i).getText()};
                textFilds.add(t);
            }
            if(i instanceof Container){
                getContent((Container)i);
            }
        }
        return textFilds;
    }
    
    public boolean rules(){
        System.out.println("Nenhuma regra configurada");
        return true;
    }
    
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
    
    public void delete(){
        System.out.println("Comando não configurado");
    }
    
    public void deleteConfirmation(){
        DeleteEntity de = new DeleteEntity("Deseja mesmo excluir? ", this);
    }
    
    public void back(){
        cleanAll(this);
        V.nav.back();
    }
    
    public void clearAll(){
        
    }
    
    public void actionTrigger(String method){
        Class classe;
        try {
            classe = Class.forName(this.getClass().getName());
            Method metodo;
            metodo = classe.getDeclaredMethod(method,null);
            metodo.invoke(this,null);
        } catch (Exception e){
            System.out.println(method + " " + e);
        }
                        
    }
    
    public void updateButtons(FunctionButton[] list){
        V.nav.updateButtons(list);
    }
}
