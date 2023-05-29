package gestaopet.view.agendamento.servico;

import gestaopet.DB.ServicoDB;
import gestaopet.V;
import gestaopet.classes.DateTools;
import gestaopet.classes.InputTools;
import gestaopet.classes.Pet;
import gestaopet.classes.Servico;
import gestaopet.classes.WizardMode;
import gestaopet.enums.DateMethods;
import gestaopet.enums.Servicos;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JCheckBox;

public class ServicoWizard extends javax.swing.JPanel {
    private WizardMode wm;
    private Date startDateTime = Calendar.getInstance().getTime();
    private Date endDateTime = Calendar.getInstance().getTime();
    private ArrayList<Servico> agendamento = new ArrayList<>();
    private ArrayList<String> dias = new ArrayList<>();
    private Pet pet = null;
    
    public ServicoWizard() {
        initComponents();
        int[][] list = {{0,1},{1,1},{2,1},{3,1},{4,0},{5,1},{6,1}};
        wm = new WizardMode(this.getComponents(),
                list, 
                7, 
                0, 
                backButton, 
                nextButton);
    }
    
    public void setDate(Date date){
        String stringDate = DateTools.dateToString(date);
        Date hoje = DateTools.getDate(DateMethods.TODAY, 0, 0, 0);
        if(!DateTools.dateCompare(hoje, date, true)){
            V.showMessage("Data escolhida é anterior ao dia de hoje", "OK");
        } 
        switch(wm.getPos()){
            case 0:
                firstDate.setText(stringDate);
                break;
            case 4:
                Date d = DateTools.dateIncrease(date, -7);
                if(!DateTools.dateCompare(hoje, d, true)){
                    V.showMessage("Data escolhida deve ser no mínimo uma semana depois da data inicial.", "OK");
                } else {
                    setDataFinal(stringDate);
                }
                break;
        }
    }
    
    public void setDataFinal(String data){
        if(data.isEmpty()){
            msgRecorrencia.setVisible(true);
            endDate.setVisible(false);
            jLabel24.setVisible(false);
            jLabel25.setVisible(false);
        } else{
            msgRecorrencia.setVisible(false);
            endDate.setVisible(true);
            endDate.setText(data);
            jLabel24.setVisible(true);
            jLabel25.setVisible(true);
        }
    }
    
    
    private boolean setLoop(){
        if(no.isSelected()){
            wm.lock(4, no.isSelected());
        } else {
            wm.lock(4, no.isSelected());
        }
        return !no.isSelected();
    }
    
    private String getService(){
        return services.getSelection().getActionCommand();
    }

    private void setStartDateTime(){
        int hh = Integer.parseInt(hour.getValue().toString());
        int mm = Integer.parseInt(minutes.getValue().toString());
        
        startDateTime = DateTools.stringToDate(firstDate.getText(), hh + ":" + mm);
    }
    
    private void setEndDateTime(){
        int hh = Integer.parseInt(hour.getValue().toString());
        int mm = Integer.parseInt(minutes.getValue().toString());

        endDateTime = DateTools.stringToDate(endDate.getText(), hh + ":" + mm);
    }

    private void makeScheduleList(boolean quinzena){
        int x = DateTools.daysBetween(startDateTime, endDateTime, true);
        int t = getDays();
        Date d = this.startDateTime;
        Calendar c = Calendar.getInstance();
        for(int i = 0; i < x; i++){
            for(int j = 0; j < t; j++){
                int test = Integer.parseInt(dias.get(j));
                
                if(d.getDay() == test){
                    if(quinzena){
                        double isInt = i;
                        if(((isInt / 14) % 1) == 0){
                            agendamento.add(newService(d, false));
                        }
                    } else {
                        agendamento.add(newService(d, false));
                    }
                }
            }
            c.add(Calendar.DAY_OF_MONTH, 1);
            d = c.getTime();
        }
        agendamento.get(agendamento.size()-1).setLembrete(true);
    }

    private Servico newService(Date data, boolean lembrete){
        String s = services.getSelection().getActionCommand();
        double v = Double.parseDouble(value.getText().replace(",", "."));      
        Servico sc = new Servico(s, data, pet.getId(),v,lembrete, false);
        
        return sc;
    }

    public void setPet(Pet pet){
        this.pet = pet;
    }
    
    public void setService(Servicos service, Pet pet){
        switch(service){
            case CRECHE :
                services.setSelected(creche.getModel(), true);
                break;
            case BANHO :
                services.setSelected(banho.getModel(), true);
                break;
            case BANHOETOSA :
                services.setSelected(banhoTosa.getModel(), true);
                break;
        }
        this.pet = pet;
    }
    
    private void closeOperation(){
        if(no.isSelected()){
            schedule(true);
        } else {
            schedule(false);
        }
        V.navigate(1);
    }
    
    private void schedule(boolean single){
        //setStartDateTime();
        if(single){
            //newService(startDateTime, true);
            ServicoDB.insert(newService(startDateTime, false));
            
        } else {
            //setEndDateTime();
            //makeScheduleList();
            for(int i = 0; i < agendamento.size(); i++){
                ServicoDB.insert(agendamento.get(i));
            }
            agendamento.clear();
        }
    }
    
    private void preschedule(boolean single){
        setStartDateTime();
        panelRecorr.setVisible(!single);
        Servico s = newService(startDateTime, false);
        rServico.setText(s.getServico());
        
        if(single){
            //newService(startDateTime, true);
            //ServicoDB.insert(newService(startDateTime, false));
            rData.setText(DateTools.dateToString(startDateTime));
            rValor.setText(s.getStringValor());
            rRecorr2.setText("");
            rRecorr.setText("");
        } else {
            setEndDateTime();
            makeScheduleList(quin.isSelected());
            rData.setText(DateTools.dateToString(startDateTime));
            rRecorr.setText("Total de: " + agendamento.size() + " recorrências.");
            rDataF.setText(DateTools.dateToString(agendamento.get(agendamento.size()-1).getData()));
            rValor.setText(s.getStringValor());
            rRecorr2.setText("Para cada recorrência.");
        }
    }
    
    public void clearAll(){
        wm.reset();
        value.setText("0,00");
        resetLoop();
        setDataFinal("");
        no.setSelected(true);
        hour.setValue(8);
        minutes.setValue(0);
        firstDate.setText("");
        confirm.setSelected(false);
    }
    
    public void navegation(boolean avancar){
        if(avancar){
            wm.next(rules());
        } else {
            if(wm.back()){
                V.nav.back();
            }
        }
    }
    
    private boolean rules(){
        boolean matchRules = false;
        
        switch(wm.getPos()){
            case 0:
                if(!firstDate.getText().isEmpty()){
                    //data inicial está em branco?
                    matchRules = true;
                    setAlert("");
                } else{
                    matchRules = false;
                    setAlert("Preencha a data");
                };
                break;
            case 1:
                    matchRules = true;
                    setAlert("");
                break;
            case 2:
                    matchRules = true;
                    setAlert("");
                break;
            case 3:
                    matchRules = true;
                    setAlert("");
                    setDataFinal("");
                break;
            case 4:
                
                if(no.isSelected()){
                    //tem recorrência?
                    matchRules = true;
                    setAlert("");
                } else {

                    if(getDays() == 0){
                        //tem data marcada?
                        matchRules = false;
                        setAlert("Selecione um dia da semana");
                    } else {
                        if(endDate.getText().isEmpty()){
                            //campo vazio?
                            matchRules = false;
                            setAlert("Preencha a data");
                        } else {
                            matchRules = true;
                            setAlert("");
                            
                        }
                    }
                };
                break;
            case 5:
                if(!(value.getText().isEmpty() || value.getText().equals("0,00")) ){
                    //data inicial está em prenchida?
                    //executar comando final
                    matchRules = true;
                    setAlert("");
                    //closeOperation();
                } else{
                    matchRules = false;
                    setAlert("Preencha o valor");
                };
                preschedule(no.isSelected());
                break;
            case 6:
                if(confirm.isSelected()){
                    //data inicial está em prenchida?
                    //executar comando final
                    matchRules = true;
                    setAlert("");
                    closeOperation();
                } else{
                    matchRules = false;
                    setAlert("Marque confirmar");
                };
                break;
        }
        return matchRules;
    }
    
    private void setAlert(String msg){
        alert.setText(msg);
    }
    
    private int getDays(){
        int j = 0;
        dias.clear();
        for(int i = 0; i < loopPanel.getComponentCount(); i++){
            if(loopPanel.getComponent(i) instanceof JCheckBox){
                if(((JCheckBox)loopPanel.getComponent(i)).isSelected()){
                    j++;
                    dias.add(loopPanel.getComponent(i).getName());
                }
            }
        }
        return j;
    }
    
   
    private void resetLoop(){
        for(int i = 0; i < loopPanel.getComponentCount(); i++){
            if(loopPanel.getComponent(i) instanceof JCheckBox){
                ((JCheckBox)loopPanel.getComponent(i)).setSelected(false);
            }
        }
    }
    /*
    private void setValue(){
        String t = "R$ " + value.getText();
        value.setText(t);
    }*/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loop = new javax.swing.ButtonGroup();
        services = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        firstDate = new javax.swing.JFormattedTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        banhoTosa = new javax.swing.JRadioButton();
        creche = new javax.swing.JRadioButton();
        banho = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        hour = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        minutes = new javax.swing.JSpinner();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        no = new javax.swing.JRadioButton();
        yes = new javax.swing.JRadioButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        loopPanel = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox7 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        msgRecorrencia = new javax.swing.JLabel();
        endDate = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        quin = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        value = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rData = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        confirm = new javax.swing.JCheckBox();
        rRecorr = new javax.swing.JLabel();
        rServico = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        rValor = new javax.swing.JLabel();
        rServico1 = new javax.swing.JLabel();
        rRecorr1 = new javax.swing.JLabel();
        rValor3 = new javax.swing.JLabel();
        panelRecorr = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        rValor2 = new javax.swing.JLabel();
        rDataF = new javax.swing.JLabel();
        rRecorr2 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        alert = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(300, 400));
        setName("10"); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setName("0"); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(80, 176, 230));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Escolha uma data");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        firstDate.setEditable(false);
        firstDate.setBackground(new java.awt.Color(255, 255, 255));
        firstDate.setBorder(null);
        firstDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        firstDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        firstDate.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        firstDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstDateActionPerformed(evt);
            }
        });
        jPanel2.add(firstDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 112, 230, 35));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, 40));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setName("1"); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(80, 176, 230));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Escolha um serviço");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        services.add(banhoTosa);
        banhoTosa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        banhoTosa.setText("Banho & Tosa");
        banhoTosa.setActionCommand("banhoetosa");
        banhoTosa.setContentAreaFilled(false);
        banhoTosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banhoTosaActionPerformed(evt);
            }
        });
        jPanel4.add(banhoTosa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        services.add(creche);
        creche.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        creche.setSelected(true);
        creche.setText("Creche");
        creche.setActionCommand("creche");
        creche.setContentAreaFilled(false);
        jPanel4.add(creche, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        services.add(banho);
        banho.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        banho.setText("Banho");
        banho.setActionCommand("banho");
        banho.setContentAreaFilled(false);
        jPanel4.add(banho, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setName("2"); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(80, 176, 230));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Escolha um horário");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        hour.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        hour.setModel(new javax.swing.SpinnerNumberModel(8, 0, 23, 1));
        hour.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        hour.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.add(hour, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 60));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText(":");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 20, -1));

        minutes.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        minutes.setModel(new javax.swing.SpinnerNumberModel(0, 0, 55, 5));
        minutes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        minutes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.add(minutes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 100, 60));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setName("3"); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(80, 176, 230));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Deseja criar recorrência?");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        loop.add(no);
        no.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        no.setSelected(true);
        no.setText("Não");
        no.setBorder(null);
        no.setContentAreaFilled(false);
        no.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        no.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                noItemStateChanged(evt);
            }
        });
        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noActionPerformed(evt);
            }
        });
        jPanel8.add(no, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 150, -1));

        loop.add(yes);
        yes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        yes.setText("Sim");
        yes.setBorder(null);
        yes.setContentAreaFilled(false);
        yes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        yes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                yesItemStateChanged(evt);
            }
        });
        jPanel8.add(yes, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, -1));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setName("4"); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(80, 176, 230));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Ajuste de recorrência");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        loopPanel.setBackground(new java.awt.Color(0, 85, 132));
        loopPanel.setForeground(new java.awt.Color(255, 255, 255));
        loopPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBox1.setAlignmentY(0.0F);
        jCheckBox1.setContentAreaFilled(false);
        jCheckBox1.setIconTextGap(1);
        jCheckBox1.setName("6"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, 30, -1));

        jCheckBox2.setAlignmentY(0.0F);
        jCheckBox2.setContentAreaFilled(false);
        jCheckBox2.setIconTextGap(1);
        jCheckBox2.setName("1"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, -1));

        jCheckBox3.setAlignmentY(0.0F);
        jCheckBox3.setContentAreaFilled(false);
        jCheckBox3.setIconTextGap(1);
        jCheckBox3.setName("2"); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 30, -1));

        jCheckBox4.setAlignmentY(0.0F);
        jCheckBox4.setContentAreaFilled(false);
        jCheckBox4.setIconTextGap(1);
        jCheckBox4.setName("3"); // NOI18N
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 30, -1));

        jCheckBox5.setAlignmentY(0.0F);
        jCheckBox5.setContentAreaFilled(false);
        jCheckBox5.setIconTextGap(1);
        jCheckBox5.setName("4"); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 30, -1));

        jCheckBox6.setAlignmentY(0.0F);
        jCheckBox6.setContentAreaFilled(false);
        jCheckBox6.setIconTextGap(1);
        jCheckBox6.setName("5"); // NOI18N
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 30, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sab");
        loopPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 30, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Seg");
        loopPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 30, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Ter");
        loopPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 30, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Qua");
        loopPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 30, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Qui");
        loopPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 30, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Sex");
        loopPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 30, -1));

        jCheckBox7.setAlignmentY(0.0F);
        jCheckBox7.setContentAreaFilled(false);
        jCheckBox7.setIconTextGap(1);
        jCheckBox7.setName("0"); // NOI18N
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });
        loopPanel.add(jCheckBox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 30, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Dom");
        loopPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 30, -1));

        jPanel10.add(loopPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, 40));

        msgRecorrencia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        msgRecorrencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        msgRecorrencia.setText("Escolha ao lado uma data final.");
        msgRecorrencia.setToolTipText("");
        jPanel10.add(msgRecorrencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 145, 290, 40));

        endDate.setEditable(false);
        endDate.setBackground(new java.awt.Color(255, 255, 255));
        endDate.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        endDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        endDate.setBorder(null);
        jPanel10.add(endDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 143, 230, 35));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 150, 40));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 130, 40));

        quin.setBackground(new java.awt.Color(255, 255, 255));
        quin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        quin.setForeground(new java.awt.Color(15, 59, 146));
        quin.setText("Quinzena");
        jPanel10.add(quin, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setName("5"); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(80, 176, 230));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Valor do serviço");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("R$");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 110, 30, 40));

        value.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        value.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        value.setText("0,00");
        value.setBorder(null);
        value.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valueKeyTyped(evt);
            }
        });
        jPanel12.add(value, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 112, 190, 35));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 40));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, 40));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setName("6"); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(80, 176, 230));

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Resumo");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 59, 146));
        jLabel16.setText("Serviço");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, 15));

        rData.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rData.setForeground(new java.awt.Color(15, 59, 146));
        rData.setText("não / total - até");
        jPanel14.add(rData, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, 15));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 59, 146));
        jLabel20.setText("Data");
        jPanel14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 15));

        confirm.setText("Confirmar");
        jPanel14.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, 30));

        rRecorr.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rRecorr.setForeground(new java.awt.Color(255, 153, 0));
        rRecorr.setText("Sem / total Com");
        jPanel14.add(rRecorr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, 15));

        rServico.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rServico.setForeground(new java.awt.Color(15, 59, 146));
        rServico.setText("servico");
        jPanel14.add(rServico, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, 15));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(15, 59, 146));
        jLabel18.setText("Valor");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, 20));

        rValor.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rValor.setForeground(new java.awt.Color(15, 59, 146));
        rValor.setText("Sem / total Com");
        jPanel14.add(rValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, 20));

        rServico1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rServico1.setForeground(new java.awt.Color(15, 59, 146));
        rServico1.setText(":");
        jPanel14.add(rServico1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 10, 15));

        rRecorr1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rRecorr1.setForeground(new java.awt.Color(15, 59, 146));
        rRecorr1.setText(":");
        jPanel14.add(rRecorr1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 10, 15));

        rValor3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rValor3.setForeground(new java.awt.Color(15, 59, 146));
        rValor3.setText(":");
        jPanel14.add(rValor3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 10, 15));

        panelRecorr.setOpaque(false);

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(15, 59, 146));
        jLabel17.setText("Data final");

        rValor2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rValor2.setForeground(new java.awt.Color(15, 59, 146));
        rValor2.setText(":");

        rDataF.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        rDataF.setForeground(new java.awt.Color(15, 59, 146));
        rDataF.setText("Sem / total Com");

        javax.swing.GroupLayout panelRecorrLayout = new javax.swing.GroupLayout(panelRecorr);
        panelRecorr.setLayout(panelRecorrLayout);
        panelRecorrLayout.setHorizontalGroup(
            panelRecorrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRecorrLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel17)
                .addGap(19, 19, 19)
                .addComponent(rValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(rDataF)
                .addContainerGap())
        );
        panelRecorrLayout.setVerticalGroup(
            panelRecorrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRecorrLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(panelRecorrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rValor2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rDataF, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14.add(panelRecorr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 280, 40));

        rRecorr2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        rRecorr2.setForeground(new java.awt.Color(255, 153, 0));
        rRecorr2.setText("Sem / total Com");
        jPanel14.add(rRecorr2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, 20));

        backButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/cancelarWB1.png"))); // NOI18N
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setIconTextGap(0);
        backButton.setMargin(null);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        nextButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/avancarWB0.png"))); // NOI18N
        nextButton.setBorderPainted(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setIconTextGap(0);
        nextButton.setMargin(null);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        alert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(204, 51, 0));
        alert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(677, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        navegation(false);
    }//GEN-LAST:event_backButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        navegation(true);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void noItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_noItemStateChanged
        setLoop();
    }//GEN-LAST:event_noItemStateChanged

    private void noActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noActionPerformed

    private void yesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_yesItemStateChanged
        setLoop();
    }//GEN-LAST:event_yesItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void banhoTosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banhoTosaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banhoTosaActionPerformed

    private void firstDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstDateActionPerformed

    private void valueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valueKeyTyped
        value.setText(InputTools.makeDecimal(value.getText(), evt, evt.getKeyChar()+""));
        evt.consume();
    }//GEN-LAST:event_valueKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton banho;
    private javax.swing.JRadioButton banhoTosa;
    private javax.swing.JCheckBox confirm;
    private javax.swing.JRadioButton creche;
    private javax.swing.JTextField endDate;
    private javax.swing.JFormattedTextField firstDate;
    private javax.swing.JSpinner hour;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.ButtonGroup loop;
    private javax.swing.JPanel loopPanel;
    private javax.swing.JSpinner minutes;
    private javax.swing.JLabel msgRecorrencia;
    private javax.swing.JButton nextButton;
    private javax.swing.JRadioButton no;
    private javax.swing.JPanel panelRecorr;
    private javax.swing.JCheckBox quin;
    private javax.swing.JLabel rData;
    private javax.swing.JLabel rDataF;
    private javax.swing.JLabel rRecorr;
    private javax.swing.JLabel rRecorr1;
    private javax.swing.JLabel rRecorr2;
    private javax.swing.JLabel rServico;
    private javax.swing.JLabel rServico1;
    private javax.swing.JLabel rValor;
    private javax.swing.JLabel rValor2;
    private javax.swing.JLabel rValor3;
    private javax.swing.ButtonGroup services;
    private javax.swing.JTextField value;
    private javax.swing.JRadioButton yes;
    // End of variables declaration//GEN-END:variables
}
