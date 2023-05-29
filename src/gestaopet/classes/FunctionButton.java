package gestaopet.classes;

import javax.swing.ImageIcon;

public class FunctionButton {
    private String title;
    private boolean enable;
    private String icon;
    private String hoverIcon;
    private ImageIcon realIcon;
    private ImageIcon realHoverIcon;
    private String param;
    
    public FunctionButton(String title, boolean enable, String icon, String param){
        this.title = title;
        this.enable = enable;
        this.icon = icon;
        this.param = param;
        setRealIcon();
    }
    
    public FunctionButton(String title, boolean enable, String icon,String hoverIcon, String param){
        this.title = title;
        this.enable = enable;
        this.icon = icon;
        this.hoverIcon = hoverIcon;
        this.param = param;
        setRealIcon();
    }

    public String getTitle() {
        return title;
    }
    
    public ImageIcon getHoverIcon(){
        return new ImageIcon(getClass().getResource("/gestaopet/tema/icons/" + hoverIcon));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    private void setRealIcon(){
        try {
            this.realIcon = new ImageIcon(getClass().getResource("/gestaopet/icons/" + icon));
        } catch (Exception e) {
            this.realIcon = new ImageIcon(getClass().getResource("/gestaopet/tema/icons/" + icon));
        }
        
    }
    
    public ImageIcon getIcon(){
        return this.realIcon;
    }
    
    public String getParam(){
        return this.param;
    }

}
