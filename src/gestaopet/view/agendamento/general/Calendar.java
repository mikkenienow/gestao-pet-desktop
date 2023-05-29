package gestaopet.view.agendamento.general;

import gestaopet.DB.PetDB;
import gestaopet.V;
import gestaopet.view.agendamento.servico.ServicoWizard;
import gestaopet.classes.DateTools;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.Navegation;
import gestaopet.classes.Pet;
import gestaopet.enums.CalendarMode;
import gestaopet.enums.DateMethods;
import gestaopet.tema.ComboBox.ComboBox;
import gestaopet.view.agendamento.reserva.ListarPets;
import gestaopet.view.agendamento.reserva.ReservaWizard;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Calendar extends GlobalPanel {
    private int dia = 0;
    private int mes = 0;
    private int ano = 0;
    private int anoAtual = 0;
    private boolean dataAtual = true;
    public final JPanel function;
    private Navegation nv;
    private CalendarMode cm;
    private Pet pet;
    private ListarPets lp;
    ComboBox cb1;
    
    
    public Calendar(CalendarMode cm) {
        initComponents();
        ComboBox.setTheme(cb1, mesMenu, new int[] {0,0}, 40,0, mesChoose);
        anoMenu.setForeground(new Color(15,59,146));
        setButtonsActions();
        dia = DateTools.getDate(DateMethods.TODAY,0,0,0).getDate();
        mes = DateTools.getDate(DateMethods.TODAY,0,0,0).getMonth()+1;
        ano = DateTools.getDate(DateMethods.TODAY,0,0,0).getYear() + 1900;
        anoAtual = DateTools.getDate(DateMethods.TODAY,0,0,0).getYear() + 1900;
        anoMenu.setValue(ano);
        updateCalendar(ano, mes);
        this.cm = cm;
        
        if(this.cm == CalendarMode.SERVICES){
            function = new ServicoWizard();
            nv = new Navegation(area, target, function);
            target.setVisible(false);
            nv.setViewPanel(10, 300, 800);
            jPanel1.setVisible(false);
        } else {
            //mudar para modo HOTEL
            function = new ReservaWizard();
            nv = new Navegation(area, target, function);
            nv.setViewPanel(10, 300, 800);
            jPanel1.setVisible(true);
        }
    }


    public class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(cm == CalendarMode.SERVICES){
                servicesAction(e);
            } else {
                bookingAction(e);
            }
        }
        
        private void bookingAction(ActionEvent e){
            dia = Integer.parseInt(((JButton)e.getSource()).getText());
            Date date = DateTools.convertDateTime(dia, mes, ano, 0, 0);
            
            ((ReservaWizard)function).setDate(date);
        }
        
        private void servicesAction(ActionEvent e){
            dia = Integer.parseInt(((JButton)e.getSource()).getText());
            Date date = DateTools.convertDateTime(dia, mes, ano, 8, 0);
            
            ((ServicoWizard)function).setDate(date);
        }
    }

    public void onChange(){
        if(mesMenu.getSelectedIndex() == 0){
            mes = DateTools.getDate(DateMethods.TODAY,0,0,0).getMonth()+1;
        } else {
            mes = mesMenu.getSelectedIndex();
        }
        
        ano = Integer.parseInt(anoMenu.getValue().toString());
        //setDate(DateMethods.NEWDAY, ano, mes, dia);
        updateCalendar(ano, mes);

        dataAtual = mesMenu.getSelectedItem().equals("atual");
    }
    
    public final void updateCalendar(int y, int m){
        updateDaysBlocks(y,m);
        updateDayOfWeekIndicator();
    }
    
    public void updateDaysBlocks(int y, int m){
        //Color padrao = new Color(255, 255, 255);
        //Color destaque = new Color(216,235,255);
        Color bpadrao = new Color(0,85,132);
        Border padrao = BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153), 0);
        Border destaque = BorderFactory.createLineBorder(Color.white, 4);

        int firstDay = DateTools.getDate(DateMethods.FIRSTDAYOFMONTH,y,m,1).getDay();
        int lastDay = DateTools.getDate(DateMethods.LASTDAYOFMONTH ,y,m,1).getDate();
        Component[] daysList = getButtondays();
        for(int i = 0; i < daysList.length; i++){
            try {
                if(daysList[i].getName().equals("d" + firstDay)){
                    for(int j = 0; j < daysList.length; j++){
                        ((JButton)daysList[i+j]).setBorder(padrao);
                        ((JButton)daysList[i+j]).setBackground(bpadrao);
                        ((JButton)daysList[i+j]).setForeground(Color.white);
                        if((j + 1) <= lastDay){
                            ((JButton)daysList[i+j]).setText((j+1) + "");
                            ((JButton)daysList[i+j]).setVisible(true);
                            if((j + 1) == dia && dataAtual && (ano == anoAtual)) {
                                //((JButton)daysList[i+j]).setBorder(destaque);
                                ((JButton)daysList[i+j]).setBackground(Color.white);
                                ((JButton)daysList[i+j]).setForeground(bpadrao);
                            }
                        } else {
                            ((JButton)daysList[i+j]).setText("");
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    
    private Component[] getButtondays(){
        Component[] daysList = new Component[42];
                
        for(int i = 0; i < 6; i++){
            int j = i * 7;
            daysList[j] = getComponentFromCol(colDom, "d" + (0 + j));
            daysList[j+1] = getComponentFromCol(colSeg, "d" + (1 + j));
            daysList[j+2] = getComponentFromCol(colTer, "d" + ( 2 + j));
            daysList[j+3] = getComponentFromCol(colQua, "d" + ( 3 + j));
            daysList[j+4] = getComponentFromCol(colQui, "d" + ( 4 + j));
            daysList[j+5] = getComponentFromCol(colSex, "d" + ( 5 + j));
            daysList[j+6] = getComponentFromCol(colSab, "d" + ( 6 + j));
        }
        return daysList;
    }
    
    private void setButtonsActions(){
        ButtonHandler handler = new ButtonHandler();
        for (Component buttonday : getButtondays()) {
            ((JButton) buttonday).addActionListener(handler);
        }
    }
    

    public void setService(gestaopet.enums.Servicos service, Pet pet){
        
        this.pet = pet;
        if(service.equals(gestaopet.enums.Servicos.HOTEL)){

            V.nav.setView(9);
            ((ReservaWizard)function).clearAll();
            ((ReservaWizard)function).setPet(this.pet);
        } else {
            ((ServicoWizard)function).clearAll();
            ((ServicoWizard)function).setService(service, this.pet);
            V.nav.setView(8);
        }
        
    }
    
    
    private Component getComponentFromCol(JPanel panel, String nome){
        Component res = null;
        for(int i = 0; i < panel.getComponentCount(); i++){
            Component c = panel.getComponent(i);
            try {
                if(c.getName().equals(nome)){
                    ((JButton)c).setText("");
                    ((JButton)c).setVisible(false);
                    return c;
                }
            } catch (Exception e) {
            }
        }
        
        return res;
    }
    
    public void updateDayOfWeekIndicator(){
        int diaDaSemana = DateTools.getDate(DateMethods.TODAY, ano, mes, dia).getDay();
        Color padrao = new Color(221,243,255);
        Color destaque = new Color(80,176,230);
        
        colDom.setBackground(padrao);
        colSeg.setBackground(padrao);
        colTer.setBackground(padrao);
        colQua.setBackground(padrao);
        colQui.setBackground(padrao);
        colSex.setBackground(padrao);
        colSab.setBackground(padrao);
        
        if(dataAtual){
            switch(diaDaSemana){
                case 0 :
                    colDom.setBackground(destaque);
                    break;
                case 1 :
                    colSeg.setBackground(destaque);
                    break;
                case 2 :
                    colTer.setBackground(destaque);
                    break;
                case 3 :
                    colQua.setBackground(destaque);
                    break;
                case 4 :
                    colQui.setBackground(destaque);
                    break;
                case 5 :
                    colSex.setBackground(destaque);
                    break;
                case 6 :
                    colSab.setBackground(destaque);
                    break;
            }
        }
    }
    
    public JPanel getTarget(int i){
        switch(i){
            case 0:
                return area;
        }
        return null;
    }
    
    public void closeInclude(){
        lp = null;
    }
    
    public void reset(){
        dia = DateTools.getDate(DateMethods.TODAY,0,0,0).getDate();
        mes = DateTools.getDate(DateMethods.TODAY,0,0,0).getMonth()+1;
        ano = DateTools.getDate(DateMethods.TODAY,0,0,0).getYear() + 1900;
        anoMenu.setValue(ano);
        updateCalendar(ano, mes);
        
        
    }
    
    public boolean isDailyIncluded(){
        try {
            return lp.isDailyIncluded();
        } catch (Exception e) {
        }
        return false;
    }
    
    public java.util.List<Pet> getInclusions(){
        try {
            return lp.getSelected();
        } catch (Exception e) {
        }
        return new java.util.ArrayList<Pet>();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        colSab = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        dia6 = new javax.swing.JButton();
        dia13 = new javax.swing.JButton();
        dia20 = new javax.swing.JButton();
        dia27 = new javax.swing.JButton();
        dia34 = new javax.swing.JButton();
        dia41 = new javax.swing.JButton();
        colDom = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        dia0 = new javax.swing.JButton();
        dia7 = new javax.swing.JButton();
        dia14 = new javax.swing.JButton();
        dia21 = new javax.swing.JButton();
        dia28 = new javax.swing.JButton();
        dia35 = new javax.swing.JButton();
        colSeg = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        dia1 = new javax.swing.JButton();
        dia8 = new javax.swing.JButton();
        dia15 = new javax.swing.JButton();
        dia22 = new javax.swing.JButton();
        dia29 = new javax.swing.JButton();
        dia36 = new javax.swing.JButton();
        colTer = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        dia2 = new javax.swing.JButton();
        dia9 = new javax.swing.JButton();
        dia16 = new javax.swing.JButton();
        dia23 = new javax.swing.JButton();
        dia30 = new javax.swing.JButton();
        dia37 = new javax.swing.JButton();
        colQua = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        dia3 = new javax.swing.JButton();
        dia10 = new javax.swing.JButton();
        dia17 = new javax.swing.JButton();
        dia24 = new javax.swing.JButton();
        dia31 = new javax.swing.JButton();
        dia38 = new javax.swing.JButton();
        colQui = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        dia4 = new javax.swing.JButton();
        dia11 = new javax.swing.JButton();
        dia18 = new javax.swing.JButton();
        dia25 = new javax.swing.JButton();
        dia32 = new javax.swing.JButton();
        dia39 = new javax.swing.JButton();
        colSex = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        dia5 = new javax.swing.JButton();
        dia12 = new javax.swing.JButton();
        dia19 = new javax.swing.JButton();
        dia26 = new javax.swing.JButton();
        dia33 = new javax.swing.JButton();
        dia40 = new javax.swing.JButton();
        anoMenu = new javax.swing.JSpinner();
        area = new javax.swing.JPanel();
        target = new javax.swing.JPanel();
        mesChoose = new javax.swing.JPanel();
        mesMenu = new javax.swing.JComboBox<>();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));
        jPanel1 = new javax.swing.JPanel();
        includePet = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("9"); // NOI18N

        colSab.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 85, 132));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Sábado");
        colSab.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        dia6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia6.setText("dia");
        dia6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia6.setName("d6"); // NOI18N
        colSab.add(dia6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia13.setText("dia");
        dia13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia13.setName("d13"); // NOI18N
        colSab.add(dia13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia20.setText("dia");
        dia20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia20.setName("d20"); // NOI18N
        colSab.add(dia20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia27.setText("dia");
        dia27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia27.setName("d27"); // NOI18N
        colSab.add(dia27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia34.setText("dia");
        dia34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia34.setName("d34"); // NOI18N
        colSab.add(dia34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia41.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia41.setText("dia");
        dia41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia41.setName("d41"); // NOI18N
        colSab.add(dia41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        colDom.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 85, 132));
        jLabel16.setText("Domingo");
        colDom.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        dia0.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia0.setText("dia");
        dia0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia0.setName("d0"); // NOI18N
        colDom.add(dia0, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia7.setText("dia");
        dia7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia7.setName("d7"); // NOI18N
        colDom.add(dia7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia14.setText("dia");
        dia14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia14.setName("d14"); // NOI18N
        colDom.add(dia14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia21.setText("dia");
        dia21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia21.setName("d21"); // NOI18N
        colDom.add(dia21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia28.setText("dia");
        dia28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia28.setName("d28"); // NOI18N
        colDom.add(dia28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia35.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia35.setText("dia");
        dia35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia35.setName("d35"); // NOI18N
        colDom.add(dia35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        colSeg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 85, 132));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Segunda");
        colSeg.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        dia1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia1.setText("dia");
        dia1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia1.setName("d1"); // NOI18N
        colSeg.add(dia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia8.setText("dia");
        dia8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia8.setName("d8"); // NOI18N
        colSeg.add(dia8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia15.setText("dia");
        dia15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia15.setName("d15"); // NOI18N
        colSeg.add(dia15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia22.setText("dia");
        dia22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia22.setName("d22"); // NOI18N
        colSeg.add(dia22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia29.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia29.setText("dia");
        dia29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia29.setName("d29"); // NOI18N
        colSeg.add(dia29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia36.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia36.setText("dia");
        dia36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia36.setName("d36"); // NOI18N
        colSeg.add(dia36, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        colTer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 85, 132));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Terça");
        colTer.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 0, 100, -1));

        dia2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia2.setText("dia");
        dia2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia2.setName("d2"); // NOI18N
        colTer.add(dia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia9.setText("dia");
        dia9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia9.setName("d9"); // NOI18N
        colTer.add(dia9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia16.setText("dia");
        dia16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia16.setName("d16"); // NOI18N
        colTer.add(dia16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia23.setText("dia");
        dia23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia23.setName("d23"); // NOI18N
        colTer.add(dia23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia30.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia30.setText("dia");
        dia30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia30.setName("d30"); // NOI18N
        colTer.add(dia30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia37.setText("dia");
        dia37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia37.setName("d37"); // NOI18N
        colTer.add(dia37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        colQua.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 85, 132));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Quarta");
        colQua.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        dia3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia3.setText("dia");
        dia3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia3.setName("d3"); // NOI18N
        colQua.add(dia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia10.setText("dia");
        dia10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia10.setName("d10"); // NOI18N
        colQua.add(dia10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia17.setText("dia");
        dia17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia17.setName("d17"); // NOI18N
        colQua.add(dia17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia24.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia24.setText("dia");
        dia24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia24.setName("d24"); // NOI18N
        colQua.add(dia24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia31.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia31.setText("dia");
        dia31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia31.setName("d31"); // NOI18N
        colQua.add(dia31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia38.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia38.setText("dia");
        dia38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia38.setName("d38"); // NOI18N
        colQua.add(dia38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        colQui.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 85, 132));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Quinta");
        colQui.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        dia4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia4.setText("dia");
        dia4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia4.setName("d4"); // NOI18N
        colQui.add(dia4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia11.setText("dia");
        dia11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia11.setName("d11"); // NOI18N
        colQui.add(dia11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia18.setText("dia");
        dia18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia18.setName("d18"); // NOI18N
        colQui.add(dia18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia25.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia25.setText("dia");
        dia25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia25.setName("d25"); // NOI18N
        colQui.add(dia25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia32.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia32.setText("dia");
        dia32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia32.setName("d32"); // NOI18N
        colQui.add(dia32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia39.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia39.setText("dia");
        dia39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia39.setName("d39"); // NOI18N
        colQui.add(dia39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        colSex.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 85, 132));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Sexta");
        colSex.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, -1));

        dia5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia5.setText("dia");
        dia5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia5.setName("d5"); // NOI18N
        colSex.add(dia5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 80, 60));

        dia12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia12.setText("dia");
        dia12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia12.setName("d12"); // NOI18N
        colSex.add(dia12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 60));

        dia19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia19.setText("dia");
        dia19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia19.setName("d19"); // NOI18N
        colSex.add(dia19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 80, 60));

        dia26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia26.setText("dia");
        dia26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia26.setName("d26"); // NOI18N
        colSex.add(dia26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 80, 60));

        dia33.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia33.setText("dia");
        dia33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia33.setName("d33"); // NOI18N
        colSex.add(dia33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 80, 60));

        dia40.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        dia40.setText("dia");
        dia40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        dia40.setName("d40"); // NOI18N
        colSex.add(dia40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 80, 60));

        anoMenu.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        anoMenu.setModel(new javax.swing.SpinnerNumberModel(2022, 2022, 3000, 1));
        anoMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146)));
        anoMenu.setEditor(new javax.swing.JSpinner.NumberEditor(anoMenu, "####"));
        anoMenu.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                anoMenuStateChanged(evt);
            }
        });
        anoMenu.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                anoMenuInputMethodTextChanged(evt);
            }
        });
        anoMenu.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                anoMenuPropertyChange(evt);
            }
        });

        area.setBackground(new java.awt.Color(255, 255, 255));
        area.setMinimumSize(new java.awt.Dimension(375, 450));
        area.setName(""); // NOI18N
        area.setPreferredSize(new java.awt.Dimension(375, 450));

        target.setBackground(new java.awt.Color(204, 255, 204));
        target.setMinimumSize(new java.awt.Dimension(360, 406));
        target.setName("target"); // NOI18N
        target.setOpaque(false);
        target.setPreferredSize(new java.awt.Dimension(300, 350));

        javax.swing.GroupLayout targetLayout = new javax.swing.GroupLayout(target);
        target.setLayout(targetLayout);
        targetLayout.setHorizontalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        targetLayout.setVerticalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 485, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(target, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(target, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        target.getAccessibleContext().setAccessibleName("");

        mesMenu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        mesMenu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "atual", "janeiro", "fevereiro", "março", "abril", "maio", "junho", "julho", "agosto", "setembro", "outubro", "novembro", "dezembro" }));
        mesMenu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                mesMenuItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout mesChooseLayout = new javax.swing.GroupLayout(mesChoose);
        mesChoose.setLayout(mesChooseLayout);
        mesChooseLayout.setHorizontalGroup(
            mesChooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mesChooseLayout.createSequentialGroup()
                .addComponent(mesMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        mesChooseLayout.setVerticalGroup(
            mesChooseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mesMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        includePet.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        includePet.setForeground(new java.awt.Color(15, 59, 146));
        includePet.setText("Incluir outro pet");
        includePet.setBorderPainted(false);
        includePet.setContentAreaFilled(false);
        includePet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includePetActionPerformed(evt);
            }
        });
        jPanel1.add(includePet, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 80, -1));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 90, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(mesChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(anoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(colDom, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(colSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(colTer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(colQua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(colQui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(colSex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(colSab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, Short.MAX_VALUE)
                .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anoMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mesChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(colQui, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colSex, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colSab, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colDom, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colTer, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colQua, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(214, Short.MAX_VALUE))
                    .addComponent(area, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void mesMenuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_mesMenuItemStateChanged
        onChange();
    }//GEN-LAST:event_mesMenuItemStateChanged

    private void anoMenuInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_anoMenuInputMethodTextChanged

    }//GEN-LAST:event_anoMenuInputMethodTextChanged

    private void anoMenuPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_anoMenuPropertyChange

    }//GEN-LAST:event_anoMenuPropertyChange

    private void anoMenuStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_anoMenuStateChanged
        onChange();
    }//GEN-LAST:event_anoMenuStateChanged

    private void includePetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includePetActionPerformed
        try {
            lp.show();
        } catch (Exception e) {
            lp = new ListarPets(pet);
        }

    }//GEN-LAST:event_includePetActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner anoMenu;
    private javax.swing.JPanel area;
    private javax.swing.JPanel colDom;
    private javax.swing.JPanel colQua;
    private javax.swing.JPanel colQui;
    private javax.swing.JPanel colSab;
    private javax.swing.JPanel colSeg;
    private javax.swing.JPanel colSex;
    private javax.swing.JPanel colTer;
    private javax.swing.JButton dia0;
    private javax.swing.JButton dia1;
    private javax.swing.JButton dia10;
    private javax.swing.JButton dia11;
    private javax.swing.JButton dia12;
    private javax.swing.JButton dia13;
    private javax.swing.JButton dia14;
    private javax.swing.JButton dia15;
    private javax.swing.JButton dia16;
    private javax.swing.JButton dia17;
    private javax.swing.JButton dia18;
    private javax.swing.JButton dia19;
    private javax.swing.JButton dia2;
    private javax.swing.JButton dia20;
    private javax.swing.JButton dia21;
    private javax.swing.JButton dia22;
    private javax.swing.JButton dia23;
    private javax.swing.JButton dia24;
    private javax.swing.JButton dia25;
    private javax.swing.JButton dia26;
    private javax.swing.JButton dia27;
    private javax.swing.JButton dia28;
    private javax.swing.JButton dia29;
    private javax.swing.JButton dia3;
    private javax.swing.JButton dia30;
    private javax.swing.JButton dia31;
    private javax.swing.JButton dia32;
    private javax.swing.JButton dia33;
    private javax.swing.JButton dia34;
    private javax.swing.JButton dia35;
    private javax.swing.JButton dia36;
    private javax.swing.JButton dia37;
    private javax.swing.JButton dia38;
    private javax.swing.JButton dia39;
    private javax.swing.JButton dia4;
    private javax.swing.JButton dia40;
    private javax.swing.JButton dia41;
    private javax.swing.JButton dia5;
    private javax.swing.JButton dia6;
    private javax.swing.JButton dia7;
    private javax.swing.JButton dia8;
    private javax.swing.JButton dia9;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton includePet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mesChoose;
    private javax.swing.JComboBox<String> mesMenu;
    private javax.swing.JPanel target;
    // End of variables declaration//GEN-END:variables

}
