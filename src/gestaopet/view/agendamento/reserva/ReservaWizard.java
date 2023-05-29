package gestaopet.view.agendamento.reserva;

import gestaopet.DB.ReservaDB;
import gestaopet.DB.CanilDB;
import gestaopet.V;
import gestaopet.classes.Reserva;
import gestaopet.classes.DateTools;
import gestaopet.classes.Canil;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.InputTools;
import gestaopet.classes.Navegation;
import gestaopet.classes.Pet;
import gestaopet.classes.WizardMode;
import gestaopet.components.DeleteEntity;
import gestaopet.reservas.PreReserva;
import gestaopet.reservas.TokenGenerator;
import gestaopet.tema.ComboBox.ComboBox;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;

public class ReservaWizard extends GlobalPanel {
    private WizardMode wm;
    private Pet pet = null;
    private Reserva bk = new Reserva();
    private java.util.List<Reserva> reservaLista = new ArrayList<>();
    private java.util.List<PreReserva> preReserva = new ArrayList<>();
    public Navegation nv;
    public Canil dk;
    public JPanel dkb = new ReservaAlojamento();
    private java.util.List<Canil> dklist = new ArrayList<>();
    private boolean reservaFracionada = false;
    private NovoCanil nc;
    
    public ReservaWizard(){
        initComponents();
        int[][] list = {{0,1},{1,1},{2,1},{3,1},{4,1},{5,1}};
        wm = new WizardMode(this.getComponents(),
                list, 
                6, 
                0, 
                backButton, 
                nextButton);     
        nv = new Navegation(jPanel6, area, dkb);
    }
    
    public void carregarCanil(boolean porData){
        if(porData){
            
            Date i = bk.getCheckin();
            Date f = bk.getCheckout();
            
            dk.setSectionListByDate(i, f);
        }

        ((ReservaAlojamento)dkb).updateAll(dk);
        nv.setViewPanel(10, 300, 300);
        dkb.setLocation(0, 80);
    }
    
    public void setPet(Pet pet){
        this.pet = pet;
        carregarCanis();
    }
    
    public void back(){
        wm.back();
    }
    
    public void setDate(Date date){
        String stringDate = DateTools.dateToString(date);
        Date today = Calendar.getInstance().getTime();
        switch(wm.getPos()){
            case 0:
                date.setHours(12);
                today.setHours(12);
                if(DateTools.dateCompare(today, date, true)){
                    bk.setCheckin(date);
                    checkIn.setText(stringDate);
                } else {
                    V.showMessage("Data de Check-in não pode ser inferior ao dia de hoje.", "OK");
                }
                break;
            case 1:
                date.setHours(11);
                bk.getCheckin().setHours(10);
                if(DateTools.dateCompare(bk.getCheckin(), date, false)){
                    bk.setCheckout(date);
                    checkOut.setText(stringDate);
                } else {
                    String data = DateTools.dateToString(bk.getCheckin());
                    V.showMessage("Data de Check-Out não pode ser inferior a " + data + ".", "OK");
                }
                break;
        }
    }

    public void criarReservas(java.util.List<PreReserva> prereserva){
        //int alojamento, int pet, String status, Date checkin, Date checkout, Double valor
        int totalDias = DateTools.daysBetween(bk.getCheckin(), bk.getCheckout(), true);
        String token = TokenGenerator.generate();
        
        for(int i = 0; i < prereserva.size(); i++){
            PreReserva pr = prereserva.get(i);
            String t = value.getText().replaceAll(",", ".");
            int tipo = (i == 0)? 1:(i == prereserva.size()-1)? 3: 2;
            reservaLista.add(new Reserva(pr.getAlojamentoId(), pr.getAlojamentoTitle(),pr.getPetId(), "RESERVADO", pr.getCheckin(), pr.getCheckout(), Double.valueOf(t), tipo, token, totalDias,""));

        }

    }
    
    public void setReserva(java.util.List<PreReserva> preReserva){
        this.preReserva = preReserva;
        wm.next(rules());
    }
    
    public void carregarCanis(){
        DogKennelList.removeAllItems();
        dklist.clear();
        dklist = CanilDB.getByPorte(pet.getPorte());
        DogKennelList.addItem("");
        for(int i = 0; i < dklist.size(); i++){
            DogKennelList.addItem(dklist.get(i) .getTitulo());
        }
        DogKennelList.setSelectedIndex(0);

    }

    private void closeOperation(){
        if(reservaFracionada){
            for(int i = 0; i < reservaLista.size(); i++){
                ReservaDB.insert(reservaLista.get(i));
            }
        } else {
            ReservaDB.insert(bk);
        }
        insertIncludes();
        reservaLista.clear();
        V.booking.closeInclude();
        gestaopet.GestaoPet.init.nav.setView(1);
    }
    
    private void insertIncludes(){
        ReservaDB.insertIncludes(V.booking.getInclusions(), bk.getToken());
    }
    
    private void makeExtract(){
        checkInDate.setText(DateTools.dateToString(bk.getCheckin()));
        checkOutDate.setText(DateTools.dateToString(bk.getCheckout()));
        int days = DateTools.daysBetween(bk.getCheckin(), bk.getCheckout(), true);
        String msg = (days == 1)? " dia" : " dias";
        totalDays.setText(days + msg);
        dailyValue.setText(bk.getValor() + "");
        totalPets.setText((V.booking.getInclusions().size() + 1)+"");
        totalValue.setText((DateTools.daysBetween(bk.getCheckin(), bk.getCheckout(), true) * bk.getValor()) + "");
    }
    
    @Override
    public boolean rules(){
        boolean matchRules = false;
        
        switch(wm.getPos()){
            case 0:
                if(!checkIn.getText().isEmpty()){
                    //data inicial está em branco?
                    matchRules = true;
                    setAlert("");
                } else{
                    matchRules = false;
                    setAlert("Escolha uma data");
                };
                break;
            case 1:
                    if(!checkOut.getText().isEmpty()){
                        matchRules = true;
                        setAlert("");

                    } else {
                        matchRules = false;
                        setAlert("Escolha uma data");
                    }
                break;
            case 2:
                    if(DogKennelList.getSelectedIndex() != 0){
                        carregarCanil(true);
                        checkKennel();
                        matchRules = true;
                        setAlert("");
                    } else {
                        matchRules = false;
                        setAlert("Selecione um canil");
                    }
                break;
            case 3:
                    reservaLista.clear();
                    if(((ReservaAlojamento)dkb).getAlojamento() != 999 || reservaFracionada){
                        matchRules = true;
                        setAlert("");
                    } else {
                        matchRules = false;
                        setAlert("Escolha um alojamento");
                    }
                break;
            case 4:
                    if(!value.getText().isEmpty()){
                        
                        if(reservaFracionada){
                            criarReservas(preReserva);
                        }
                        int includes = V.booking.getInclusions().size() + 1;
                        boolean dailyIncludes = V.booking.isDailyIncluded();
                        if(dailyIncludes){
                            bk.setValor(Double.valueOf(value.getText().replaceAll(",", ".")) * includes);
                        } else {
                            bk.setValor(Double.valueOf(value.getText().replaceAll(",", ".")));
                        }
                        

                        matchRules = true;
                        setAlert("");
                        setBooking();
                        makeExtract();
                    } else {
                        matchRules = false;
                        setAlert("Preencha o valor");
                    }
                break;
            case 5:
                if(confirm.isSelected()){
                    //executar comando final
                    matchRules = true;
                    setAlert("");
                    closeOperation();
                } else{
                    matchRules = false;
                    setAlert("Marque a confirmação para finalizar");
                };
                break;
        }
        return matchRules;
    }
    
    private void setAlert(String msg){
        alert.setText(msg);
    }
    
    public void checkKennel(){
        if(dk.isIndisponivel()){
            Object[] list = {
                pet,
                DogKennelList.getSelectedIndex()-1,
                DateTools.dateToString(bk.getCheckin()),
                DateTools.dateToString(bk.getCheckout())
            };
            DisponibilidadeCanil kd = new DisponibilidadeCanil(list);
        }
    }
    
    public void reservaFracionada(boolean reserva){
        this.reservaFracionada = reserva;
    }
    
    
    private void setValue(){
        //formatar valor considerando decimais
        String t = value.getText().replaceAll(",", ".");
        bk.setValor(Double.valueOf(t));
    }
    
    private void setBooking(){
        bk.setAlojamentoTitle(((ReservaAlojamento)dkb).getAlojamentoTitulo());
        bk.setAlojamento(((ReservaAlojamento)dkb).getAlojamento());
        bk.setPet(pet.getId());
        bk.setStatus("RESERVADO");
        bk.setTipo(0);
        bk.setToken(TokenGenerator.generate());

        bk.setDiasTotais(DateTools.daysBetween(bk.getCheckin(), bk.getCheckout(), true));
    }
    
    public void clearAll(){
        wm.reset();
        ((ReservaAlojamento)dkb).resetAlojamento();
        checkIn.setText("");
        bk = new Reserva();
        try {
            DogKennelList.setSelectedIndex(0);
        } catch (Exception e) {
        }
        reservaLista.clear();
        preReserva.clear();
        checkOut.setText("");
        value.setText("");
        confirm.setSelected(false);
    }
    
    public void recalculate(){
        if(wm.getPos()==5){
            wm.back();
            wm.next(rules());
        }
    }
    
    
    public void navegation(boolean inverse){
        if(inverse){
            if(wm.getPos() == 0){
                clearAll();
                V.nav.back();
            } else {
                wm.back();
            }
        } else {
            wm.next(rules());
        }
    }
    
    public void delete(){
        CanilDB.delete(dk.getId());
        carregarCanis();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        checkIn = new javax.swing.JFormattedTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        DogKennelList = new javax.swing.JComboBox<>();
        excluir = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        area = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        checkOut = new javax.swing.JFormattedTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        value = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        actionBar = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        alert = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        confirm = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        totalDays = new javax.swing.JLabel();
        checkOutDate = new javax.swing.JLabel();
        checkInDate = new javax.swing.JLabel();
        totalValue = new javax.swing.JLabel();
        dailyValue = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        totalPets = new javax.swing.JLabel();

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
        jLabel2.setText("Check-in");

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

        checkIn.setEditable(false);
        checkIn.setBackground(new java.awt.Color(255, 255, 255));
        checkIn.setBorder(null);
        checkIn.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        checkIn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        checkIn.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        checkIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInActionPerformed(evt);
            }
        });
        jPanel2.add(checkIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 116, 230, 30));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 40));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, 40));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setName("2"); // NOI18N
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(80, 176, 230));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Escolha um canil");

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

        DogKennelList.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        DogKennelList.setForeground(new java.awt.Color(15, 59, 146));
        DogKennelList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146)));
        DogKennelList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DogKennelListItemStateChanged(evt);
            }
        });
        jPanel4.add(DogKennelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 240, 40));

        excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/excluirTB1.png"))); // NOI18N
        excluir.setBorderPainted(false);
        excluir.setContentAreaFilled(false);
        excluir.setEnabled(false);
        excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirActionPerformed(evt);
            }
        });
        jPanel4.add(excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 95, 35));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/novoCanilP1.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(178, 160, 95, 35));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setName("3"); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 200));

        jPanel7.setBackground(new java.awt.Color(80, 176, 230));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Escolha um alojamento");

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

        area.setBackground(new java.awt.Color(255, 255, 255));
        area.setName("2"); // NOI18N
        area.setOpaque(false);

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setName("1"); // NOI18N
        jPanel10.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(80, 176, 230));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Check-out");

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

        checkOut.setBorder(null);
        checkOut.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        checkOut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        checkOut.setToolTipText("");
        checkOut.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        checkOut.setVerifyInputWhenFocusTarget(false);
        jPanel10.add(checkOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 112, 230, 35));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 40));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, 40));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setName("4"); // NOI18N
        jPanel12.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(80, 176, 230));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Adicione o valor da diária");

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
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 30, 40));

        value.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        value.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        value.setText("0,00");
        value.setBorder(null);
        value.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valueKeyTyped(evt);
            }
        });
        jPanel12.add(value, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 112, 180, 35));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel12.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 130, 40));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel12.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 150, 40));

        actionBar.setBackground(new java.awt.Color(255, 255, 255));
        actionBar.setMaximumSize(new java.awt.Dimension(32767, 130));
        actionBar.setMinimumSize(new java.awt.Dimension(0, 130));

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
        nextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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

        javax.swing.GroupLayout actionBarLayout = new javax.swing.GroupLayout(actionBar);
        actionBar.setLayout(actionBarLayout);
        actionBarLayout.setHorizontalGroup(
            actionBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionBarLayout.createSequentialGroup()
                .addGroup(actionBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(actionBarLayout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(actionBarLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1820, Short.MAX_VALUE))
        );
        actionBarLayout.setVerticalGroup(
            actionBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionBarLayout.createSequentialGroup()
                .addComponent(alert, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(actionBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setName("5"); // NOI18N
        jPanel14.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(80, 176, 230));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Resumo");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        confirm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        confirm.setText("Confirmar");
        jPanel14.add(confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, 40));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(15, 59, 146));
        jLabel1.setText("Check-in");
        jPanel14.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(15, 59, 146));
        jLabel5.setText("Check-out");
        jPanel14.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 30));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 59, 146));
        jLabel9.setText("Subtotal");
        jPanel14.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 30));

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(15, 59, 146));
        jLabel10.setText("Estadia");
        jPanel14.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, 30));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(15, 59, 146));
        jLabel11.setText("Valor diária");
        jPanel14.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(15, 59, 146));
        jLabel12.setText(":");
        jPanel14.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(15, 59, 146));
        jLabel13.setText(":");
        jPanel14.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, -1, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(15, 59, 146));
        jLabel14.setText(":");
        jPanel14.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, -1, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(15, 59, 146));
        jLabel15.setText(":");
        jPanel14.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, -1, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 59, 146));
        jLabel16.setText(":");
        jPanel14.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, -1, 30));

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(15, 59, 146));
        jLabel17.setText("R$");
        jPanel14.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, 30));

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(15, 59, 146));
        jLabel18.setText("R$");
        jPanel14.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, 30));

        totalDays.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        totalDays.setForeground(new java.awt.Color(15, 59, 146));
        totalDays.setText("total_days");
        jPanel14.add(totalDays, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, 30));

        checkOutDate.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        checkOutDate.setForeground(new java.awt.Color(15, 59, 146));
        checkOutDate.setText("checkout_date");
        jPanel14.add(checkOutDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, 30));

        checkInDate.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        checkInDate.setForeground(new java.awt.Color(15, 59, 146));
        checkInDate.setText("checkin_date");
        jPanel14.add(checkInDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, -1, -1));

        totalValue.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        totalValue.setForeground(new java.awt.Color(15, 59, 146));
        totalValue.setText("total_value");
        jPanel14.add(totalValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, 30));

        dailyValue.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dailyValue.setForeground(new java.awt.Color(15, 59, 146));
        dailyValue.setText("daily_value");
        jPanel14.add(dailyValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, -1, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/alojamentosWB0.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel14.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 140, 40));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 59, 146));
        jLabel20.setText("Pets");
        jPanel14.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 30));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(15, 59, 146));
        jLabel21.setText(":");
        jPanel14.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, 30));

        totalPets.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        totalPets.setForeground(new java.awt.Color(15, 59, 146));
        totalPets.setText("1");
        jPanel14.add(totalPets, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 60, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(actionBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(687, 687, 687))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actionBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        navegation(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
       navegation(false);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void checkInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkInActionPerformed

    private void valueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valueKeyTyped
        value.setText(InputTools.makeDecimal(value.getText(), evt, evt.getKeyChar()+""));
        evt.consume();
    }//GEN-LAST:event_valueKeyTyped

    private void DogKennelListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DogKennelListItemStateChanged
        try {
            if(DogKennelList.getSelectedIndex() != 0){
                dk = dklist.get((DogKennelList.getSelectedIndex() -1));
                carregarCanil(true);
                }
            
        } catch (Exception e) {
        }
        try{
            if(DogKennelList.getSelectedIndex() == 0 || DogKennelList.getSelectedItem().equals("")){
                excluir.setEnabled(false);
            } else {
                excluir.setEnabled(true);
            }
        } catch (Exception e){
            
        }

        
    }//GEN-LAST:event_DogKennelListItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AlojamentoEscolhido ae;
        if(reservaLista.size() == 0){
            bk.setCanil(dklist.get((DogKennelList.getSelectedIndex() -1)).getTitulo());
            reservaLista.add(bk);
        }
        ae = new AlojamentoEscolhido(reservaLista);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            nc.show(true);
        } catch (Exception e) {
            nc = new NovoCanil();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirActionPerformed
        DeleteEntity de = new DeleteEntity("Está ação não poderá ser desfeita, deseja continuar?", this, false);
    }//GEN-LAST:event_excluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DogKennelList;
    private javax.swing.JPanel actionBar;
    private javax.swing.JLabel alert;
    private javax.swing.JPanel area;
    private javax.swing.JButton backButton;
    private javax.swing.JFormattedTextField checkIn;
    private javax.swing.JLabel checkInDate;
    private javax.swing.JFormattedTextField checkOut;
    private javax.swing.JLabel checkOutDate;
    private javax.swing.JCheckBox confirm;
    private javax.swing.JLabel dailyValue;
    private javax.swing.JButton excluir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel totalDays;
    private javax.swing.JLabel totalPets;
    private javax.swing.JLabel totalValue;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables
}
