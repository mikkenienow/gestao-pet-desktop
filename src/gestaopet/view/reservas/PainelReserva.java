package gestaopet.view.reservas;

import gestaopet.DB.PetDB;
import gestaopet.DB.ReservaDB;
import gestaopet.classes.DateTools;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.Pet;
import gestaopet.classes.Reserva;
import gestaopet.components.DataPicker;
import gestaopet.enums.DateMethods;
import gestaopet.tema.ComboBox.ComboBox;
import gestaopet.tema.checkbox.CheckBox;
import gestaopet.tema.field.Field;
import gestaopet.tema.scroll.SpecialScroll;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PainelReserva extends GlobalPanel {
    private List<Reserva> listareserva = new ArrayList<>();
    private List<Reserva> listareservaAgregados = new ArrayList<>();
    private List<Object[]> listaFinal = new ArrayList<>();
    private List<Pet> listapet = new ArrayList<>();
    private int idpet = -1;
    private DataPicker dataInicial; 
    private DataPicker dataFinal;
    private String hoje = DateTools.dateToString(DateTools.getDate(DateMethods.TODAY, 0, 0, 0));
    private String semana = DateTools.dateToString(DateTools.dateIncrease(hoje, 7));
    private CheckBox cb1;
    private CheckBox cb2;
    private SpecialScroll ss;
    private int he;
    private Field fI;
    private Field fF;
    private boolean individualMode = false;
    ComboBox cbx1;
    
    
    public PainelReserva(boolean individualMode) {
        initComponents();
        ss = new SpecialScroll(jScrollPane1);
        add(ss);
        this.individualMode = individualMode;
        ComboBox.setTheme(cbx1, dateModes, new int[] {310,0}, 40,0, checkboxpanel);
        di.setText(hoje);
        df.setText(hoje);
        
        

        
        cb1 = new CheckBox(checkinB,checks, this);
        cb2 = new CheckBox(checkoutB, checks, this);
        checkboxpanel.add(cb1);
        checkboxpanel.add(cb2);
        checkboxpanel.setSize(400, 70);
        checkboxpanel.setPreferredSize(new Dimension(450,70));
        checkboxpanel.setLocation(0, 0);

        cb1.setVisible(true);
        cb1.setSelection();
        cb1.setSize(110, 25);
        cb1.setPreferredSize(new Dimension(100, 25));
        cb1.setLocation(0, 0);
        cb2.setVisible(true);
        cb2.setSize(110, 25);
        cb2.setPreferredSize(new Dimension(100, 25));
        cb2.setLocation(0, 25);
        cb1.getComponent().setVisible(false);
        cb2.getComponent().setVisible(false);
        fI = new Field(95, di.getText(), di, "dataPickerInicial", new int[] {110,0});
        fF = new Field(95, df.getText(), df, "dataPickerFinal", new int[] {210,0});
        
        
    }

    public void setPetId(int id){
        idpet = id;
    }
    
    public void setFilter(){
        int f = dateModes.getSelectedIndex();
        di.setText(hoje);
        df.setText(hoje);
        String hoje = DateTools.dateToString(DateTools.getDate(DateMethods.TODAY, 0, 0, 0));
        String semana = DateTools.dateToString(DateTools.dateIncrease(hoje, 7));
        String mes = DateTools.dateToString(DateTools.dateIncrease(hoje, 31));
        String ini = DateTools.dateToString(DateTools.getDate(DateMethods.NEWDAY, 1990, 1, 1));
        String fin = DateTools.dateToString(DateTools.getDate(DateMethods.NEWDAY, 3000, 12, 31));
        
        switch(f){
            case 0: 
                fI.setText(hoje);
                fF.setText(hoje);
                break;
            case 1: 
                fI.setText(hoje);
                fF.setText(hoje);
                break;
            case 2:
                fI.setText(hoje);
                fF.setText(semana);
                break;
            case 3:
                fI.setText(hoje);
                fF.setText(mes);
                break;
            case 4: 
                fI.setText(ini);
                fF.setText(fin);
                break;
        }
        filter();
    }
    
    public void dataPickerInicial(){
        try {
            dataInicial.show(true);
        } catch (Exception e) {
            dataInicial  = new DataPicker(fI, "filter");
        }
    }
    
    public void dataPickerFinal(){
        try {
            dataFinal.show(true);
        } catch (Exception e) {
            dataFinal  = new DataPicker(fF, "filter");
        }
    }
    

    
    public void filter(){
        area.removeAll();
        listareserva.clear();
        listareservaAgregados.clear();
        listaFinal.clear();
        Date i = DateTools.stringToDate(fI.getText(), "00:00");
        Date f = DateTools.stringToDate(fF.getText(), "00:00");
        listareserva = ReservaDB.getAll(i, f, idpet, checkinB.isSelected());
        
        if(individualMode){
            listareservaAgregados = ReservaDB.getAllById(i, f, idpet, checkinB.isSelected());
            
        }
        listaFinal = orderByDate(listareserva, listareservaAgregados, i, f, checkinB.isSelected());
        
        
        
        getPet();
        Dimension d1 = new Dimension(380, 60);
        Dimension d2 = new Dimension(380, listareserva.size()*60);
        area.setSize(d2);
        area.setPreferredSize(d2);
        int y = 0;
        
        for(int j = 0; j < listaFinal.size(); j++){
            ItemListHome il = new ItemListHome(listaFinal.get(j), listapet.get(j), checkinB.isSelected(), this);
            area.add(il);
            il.setVisible(true);
            il.setSize(d1);
            il.setPreferredSize(d1);
            il.setLocation(0, y);
            y = y + 60;
        }
        
        area.repaint();
        area.revalidate();
        ss.updateSize();
    }
    
    public void getPet(){
        listapet.clear();
        for(int i = 0; i < listaFinal.size(); i++){
            listapet.add(PetDB.getById(((Reserva)listaFinal.get(i)[0]).getPet()));
        }
    }
    
    
    public List<Object[]> orderByDate(List<Reserva> list01, List<Reserva> list02, Date  dI, Date dF, boolean checkin){
        List<Object[]> list = mixLists(list01, list02);
        List<Object[]> output =  new ArrayList<>();
        int totalD = DateTools.daysBetween(dI, dF, true);
        Date compareDate = dI;
        
        for(int i = 0; i < totalD; i++){

            for(int j = 0; j < list.size(); j++){
                Date compareDateList = (checkin)? ((Reserva)list.get(j)[0]).getCheckin(): ((Reserva)list.get(j)[0]).getCheckout();
                if(DateTools.compareDates(compareDate, compareDateList)){
                    output.add(list.get(j));
                }
                
            }
            compareDate = DateTools.dateIncrease(compareDate, 1);
        }
        return output;
    }
    
    public List<Object[]> mixLists(List<Reserva> list01, List<Reserva> list02){
        List<Object[]> output = new ArrayList<>();
        for(int i = 0; i < list01.size(); i++){
            output.add(new Object[] {list01.get(i), false});
        }
        for(int i = 0; i < list02.size(); i++){
            output.add(new Object[] {list02.get(i), true});
        }
        return output;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checks = new javax.swing.ButtonGroup();
        checkboxpanel = new javax.swing.JPanel();
        checkinB = new javax.swing.JRadioButton();
        checkoutB = new javax.swing.JRadioButton();
        di = new javax.swing.JTextField();
        df = new javax.swing.JTextField();
        dateModes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("10"); // NOI18N

        checkboxpanel.setBackground(new java.awt.Color(204, 204, 255));
        checkboxpanel.setOpaque(false);

        checkinB.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        checkinB.setSelected(true);
        checkinB.setText("Check In");
        checkinB.setContentAreaFilled(false);
        checkinB.setMargin(new java.awt.Insets(0, 0, 0, 0));
        checkinB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkinBItemStateChanged(evt);
            }
        });

        checkoutB.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        checkoutB.setText("Check Out");
        checkoutB.setContentAreaFilled(false);
        checkoutB.setMargin(new java.awt.Insets(0, 0, 0, 0));

        di.setEditable(false);
        di.setBackground(new java.awt.Color(255, 255, 255));
        di.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        di.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        di.setText("01/01/1990");
        di.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diMouseClicked(evt);
            }
        });
        di.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                diPropertyChange(evt);
            }
        });

        df.setEditable(false);
        df.setBackground(new java.awt.Color(255, 255, 255));
        df.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        df.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        df.setText("01/05/2023");
        df.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dfMouseClicked(evt);
            }
        });
        df.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dfPropertyChange(evt);
            }
        });

        dateModes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Filtro", "Hoje", "Semana", "Mês", "Todos" }));
        dateModes.setToolTipText("");
        dateModes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                dateModesItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout checkboxpanelLayout = new javax.swing.GroupLayout(checkboxpanel);
        checkboxpanel.setLayout(checkboxpanelLayout);
        checkboxpanelLayout.setHorizontalGroup(
            checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkboxpanelLayout.createSequentialGroup()
                .addComponent(di, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(df, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkinB)
                    .addComponent(checkoutB))
                .addGap(110, 110, 110)
                .addComponent(dateModes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );
        checkboxpanelLayout.setVerticalGroup(
            checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkboxpanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(checkboxpanelLayout.createSequentialGroup()
                        .addComponent(dateModes)
                        .addContainerGap())
                    .addGroup(checkboxpanelLayout.createSequentialGroup()
                        .addGroup(checkboxpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(di, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(df)
                            .addGroup(checkboxpanelLayout.createSequentialGroup()
                                .addComponent(checkinB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(checkoutB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12))))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setOpaque(false);
        jScrollPane1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jScrollPane1MouseWheelMoved(evt);
            }
        });

        area.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 944, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(area);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(checkboxpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(checkboxpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void diMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diMouseClicked

        dataPickerInicial();
    }//GEN-LAST:event_diMouseClicked

    private void dfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dfMouseClicked
        dataPickerFinal();
    }//GEN-LAST:event_dfMouseClicked

    private void checkinBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkinBItemStateChanged
        filter();
        cb1.updateIcon();
        cb2.updateIcon();
        
    }//GEN-LAST:event_checkinBItemStateChanged

    private void diPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_diPropertyChange
        filter();
    }//GEN-LAST:event_diPropertyChange

    private void dfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dfPropertyChange
        filter();
    }//GEN-LAST:event_dfPropertyChange

    private void jScrollPane1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jScrollPane1MouseWheelMoved
        ss.scroolAction(jScrollPane1.getVerticalScrollBar().getValue());
    }//GEN-LAST:event_jScrollPane1MouseWheelMoved

    private void dateModesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_dateModesItemStateChanged
        setFilter();
    }//GEN-LAST:event_dateModesItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area;
    private javax.swing.JPanel checkboxpanel;
    private javax.swing.JRadioButton checkinB;
    private javax.swing.JRadioButton checkoutB;
    private javax.swing.ButtonGroup checks;
    private javax.swing.JComboBox<String> dateModes;
    private javax.swing.JTextField df;
    private javax.swing.JTextField di;
    public javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
