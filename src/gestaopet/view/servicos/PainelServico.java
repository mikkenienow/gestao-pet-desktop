package gestaopet.view.servicos;

import gestaopet.DB.PetDB;
import gestaopet.DB.ServicoDB;
import gestaopet.classes.DateTools;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.Pet;
import gestaopet.classes.Servico;
import gestaopet.enums.DateMethods;
import gestaopet.tema.ComboBox.ComboBox;
import gestaopet.tema.scroll.SpecialScroll;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

public class PainelServico extends GlobalPanel {

    private Pet pet;
    private boolean byPet = false;
    private String servicoF = "";
    private String dataF = "";
    private String situacaoF = "";
    private int idServico = 0;
    private boolean homeMode;
    ComboBox cb1;
    ComboBox cb2;

    private SpecialScroll ss;
    
    public PainelServico(boolean homeMode) {
        initComponents();
        ss = new SpecialScroll(jScrollPane3);
        add(ss);
        
        
        //getServicos("","", "", this.byPet);
        cb1 = ComboBox.setTheme(cb1, servicoFiltro, new int[] {60,0}, 40,0, target);
        cb2 = ComboBox.setTheme(cb2, dataFiltro, new int[] {210,0}, 40, 0,target);
        
        this.homeMode = homeMode;
        if(!this.homeMode){
            cb2.setOption(1);
        }
        filter();
    }
    
    public PainelServico(Pet pet) {
        initComponents();
        this.homeMode = true;
        setPet(pet);
        ss = new SpecialScroll(jScrollPane3);
        add(ss);
        getServicos("","", this.byPet);
        ComboBox.setTheme(cb1, servicoFiltro, new int[] {0,0}, 40,0, target);
        ComboBox.setTheme(cb2, dataFiltro, new int[] {150,0}, 40, 0,target);
        
        filter();
    }
    
    
    public void setPet(Pet pet){
        this.pet = pet;
        this.byPet = true;
        this.homeMode = true;
        getServicos("","", this.byPet);
    }
    
    public void unsetPet(){
        this.pet = null;
        this.byPet = false;
        clearAll();
    }
    
    @Override
    public void delete(){
        ServicoDB.delete(this.idServico);
        clearAll();
        this.idServico = 0;
    }
    
    public void back(){
        getServicos(servicoF, dataF, byPet);
    }
    
    public void setIdServico(int id){
        this.idServico = id;
    }
    
    
    public List<Servico> teste(int id, String servico, String periodo, String data){
        return null;
    }
    
    public void getServicos(String servico, String periodo, boolean byPet){
        clearAll();

        int id = (byPet)? this.pet.getId() : -1;
        String data = DateTools.dateToSQL(DateTools.getDate(DateMethods.TODAY, 0, 0, 0));
        List<Servico> list = ServicoDB.getByPet(id, servico, periodo, data);
        List<Pet> petList = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){
            petList.add(PetDB.getById(list.get(i).getPet()));
        }
        
        int height = (homeMode)? 60:40;
        Dimension d1 = new Dimension(600, height);
        Dimension d2 = new Dimension(600, list.size() * height);

        area.setSize(d2);
        area.setPreferredSize(d2);
        
        int y = 0;
        for(int i = 0; i < list.size(); i++){
            JPanel sf = new ItemListHome(list.get(i),petList.get(i), this);
            area.add(sf);
            if(byPet){
                ((ItemListHome)sf).excluir.setVisible(true);
                ((ItemListHome)sf).view2.setVisible(false);
            } else {
                ((ItemListHome)sf).view.setVisible(false);
                ((ItemListHome)sf).view2.setVisible(true);
            }
            sf.setVisible(true);
            sf.setSize(d1);
            sf.setPreferredSize(d1);
            sf.setLocation(0, y);
            y = y + height;
        }
        ss.updateSize();
    }
    
    
    public void clearAll(){
        area.removeAll();
        area.repaint();
        area.revalidate();
    }
    
    public void filter(){
        String a = ""; 
        switch(servicoFiltro.getSelectedIndex()){
            case -1:
                a = "todos";
                break;
            case 0:
                a = "todos";
                break;
            case 1:
                a = "creche";
                break;
            case 2:
                a = "banho";
                break;
            case 3:
                a = "banhoetosa";
                break;
        }

        String b = (dataFiltro.getSelectedIndex()== -1)? "": dataFiltro.getSelectedItem().toString().toLowerCase().strip();
        try {
            System.out.println("PET: " + this.pet.getNome() + " b: " + b);
        } catch (Exception e) {
        }

        getServicos(a, b, this.byPet);
        
    }
    
    /*
    @Override
    public void deleteConfirmation(){
        
    }*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        target = new javax.swing.JPanel();
        servicoFiltro = new javax.swing.JComboBox<>();
        dataFiltro = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("10"); // NOI18N

        jScrollPane3.setBorder(null);
        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(708, 1000));
        jScrollPane3.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane3MouseWheelMoved(evt);
            }
        });

        area.setBackground(new java.awt.Color(255, 255, 255));
        area.setPreferredSize(new java.awt.Dimension(708, 0));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 708, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(area);

        target.setBackground(new java.awt.Color(255, 255, 255));

        servicoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Creche", "Banho", "Banho & Tosa" }));
        servicoFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                servicoFiltroItemStateChanged(evt);
            }
        });

        dataFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoje", "Pendente", "Tudo" }));
        dataFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dataFiltroItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout targetLayout = new javax.swing.GroupLayout(target);
        target.setLayout(targetLayout);
        targetLayout.setHorizontalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, targetLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(servicoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(dataFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        targetLayout.setVerticalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(targetLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(servicoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 149, Short.MAX_VALUE))
                    .addComponent(target, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(target, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void servicoFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_servicoFiltroItemStateChanged
        filter();
    }//GEN-LAST:event_servicoFiltroItemStateChanged

    private void dataFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dataFiltroItemStateChanged
        filter();
    }//GEN-LAST:event_dataFiltroItemStateChanged

    private void jScrollPane3MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane3MouseWheelMoved
        ss.scroolAction(jScrollPane3.getVerticalScrollBar().getValue());
    }//GEN-LAST:event_jScrollPane3MouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area;
    private javax.swing.JComboBox<String> dataFiltro;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JComboBox<String> servicoFiltro;
    private javax.swing.JPanel target;
    // End of variables declaration//GEN-END:variables
}
