package gestaopet.classes;

import gestaopet.V;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class WizardMode {
    private final Component[] cp;
    private final int[][] list;
    private int pos;
    private final int lim;
    private final JButton back;
    private final JButton next;
    
    public WizardMode(Component[] cplist, int[][] listIndex, int lim, int startPos, JButton backButton, JButton nextButton){
        this.cp = cplist;
        this.list = listIndex;
        this.pos = startPos;
        this.lim = lim;
        this.back = backButton;
        this.next = nextButton;
        setView(startPos);
    }
    
    public int getPos(){
        return pos;
    }
    
    public final void setView(int pos){
        for (Component cp1 : cp) {
            try {
                if (cp1.getName().equals(pos + "")) {
                    cp1.setVisible(true);
                } else {
                    cp1.setVisible(false);
                }
            }catch (Exception e) {
            }
        }
    }
    
    public boolean back(){
        //se posição for 0, ação deve ser cancelar
        boolean cancelar = false;
        if(pos == 0){
            cancelar = true;
        } else {
            posUpdate(-1);
        }
        return cancelar;
    }
    
    public void reset(){
        for(int i = 0; i < (lim + 5); i++){
            back();
        }
    }
    
    public void next(boolean matchRule){
        //se posição for final ação deve ser confirmar
        if(matchRule){
            if(pos == lim -1){
            } else {
                posUpdate(1);
            }
        }
        
    }
    
    public void posUpdate(int i){
	if(i > 0){
            if (pos + 1 == lim) { return; }
            pos++;
            while(list[pos][1] == 0){
                if (pos + 1 == lim) { break; }
                pos++;
            }
	} else {
            if (pos -1 == -1) { return; }
            pos--;
            while(list[pos][1] == 0){
                if (pos -1 == -1) { break; }
                pos--;
            }
	}
        setView(pos);
        buttonsUpdate();
    }
    
    public void buttonsUpdate(){
        if(pos == lim-1){
            //mudar botão para confirmar
            //next.setText("Confirmar");
            setIcon(next, 3);
        } else {
            //mudar botão para avançar
            //next.setText("Avançar");
            setIcon(next, 2);
        }
        
        if(pos == 0){
            //mudar botão para cancelar
            //back.setText("Cancelar");
            setIcon(back, 0);
        } else {
            //mudar botão para voltar
            //back.setText("Voltar");
            setIcon(back, 1);
        }
    }
    
    public void lock(int index, boolean lock){
        if(lock){
            list[index][1] = 0;
        } else {
            list[index][1] = 1;
        }
    }
    
    
    public void setIcon(JButton b, int op){
        ImageIcon realIcon = new ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarWB1.png"));
        switch(op){
            case 0:
                realIcon = new ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarWB1.png")); break;
            case 1: 
                realIcon = new ImageIcon(getClass().getResource("/gestaopet/tema/icons/voltarWB0.png")); break;
            case 2: 
                realIcon = new ImageIcon(getClass().getResource("/gestaopet/tema/icons/avancarWB0.png")); break;
            case 3: 
                realIcon = new ImageIcon(getClass().getResource("/gestaopet/tema/icons/confirmarWB1.png")); break;
        }
        b.setIcon(realIcon);
    }   

}
